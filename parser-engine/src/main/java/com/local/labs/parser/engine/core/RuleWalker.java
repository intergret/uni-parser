package com.local.labs.parser.engine.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.local.labs.parser.common.model.crawler.CrawlerTask;
import com.local.labs.parser.common.model.crawler.HttpMethod;
import com.local.labs.parser.common.model.crawler.HttpRequest;
import com.local.labs.parser.common.model.parser.NodeResult;
import com.local.labs.parser.common.model.parser.ParseData;
import com.local.labs.parser.common.model.parser.ParserTask;
import com.local.labs.parser.common.model.parser.rule.ExtraConfig;
import com.local.labs.parser.common.model.parser.rule.Node;
import com.local.labs.parser.common.model.parser.rule.NodeTree;
import com.local.labs.parser.common.model.parser.rule.Prop;
import com.local.labs.parser.common.model.parser.rule.PropResult;
import com.local.labs.parser.common.model.parser.rule.PropTree;
import com.local.labs.parser.common.model.parser.rule.RuleTree;
import com.local.labs.parser.engine.extractor.Extractor;
import com.local.labs.parser.engine.extractor.ExtractorLoader;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Author: Xing Wang <wangxing.bjtu@gmail.com>
 * Date: 2017-03-24 Time: 09:58
 */
@Component
public class RuleWalker {

  private static final Logger LOG = LoggerFactory.getLogger(RuleWalker.class);

  @Autowired
  private ExtractorLoader extractorLoader;

  public ParseData walk(ParserTask task, RuleTree ruleTree) {
    ParseData parseData = new ParseData();
    parseData.setRule(ruleTree.getRule());

    List<NodeTree> nodeTrees = ruleTree.getTopNodeTrees();
    for (NodeTree nodeTree : nodeTrees) {
      List<NodeResult> nodeResults = this.walkNode(task, task.getContent(), nodeTree, parseData);
      if (CollectionUtils.isNotEmpty(nodeResults)) {
        for (NodeResult result : nodeResults) {
          if (!result.isEmpty()) {
            parseData.getNodeResults().add(result);
          }
        }
      }
    }
    return parseData;
  }

  public List<NodeResult> walkNode(ParserTask task, String content, NodeTree nodeTree, ParseData parseData) {
    NodeResult nodeResult = new NodeResult();
    Node node = nodeTree.getNode();
    ExtraConfig nodeExtraConfig = nodeTree.getExtraConfig();
    if (nodeExtraConfig == null) {
      return null;
    }

    List<NodeResult> result = new ArrayList<>();
    if (nodeExtraConfig.getInputType() != ExtraConfig.ExtractorInput.DEFAULT) {
      content = getInput(task, nodeExtraConfig);
    }

    try {
      Extractor extractor = extractorLoader.load(nodeExtraConfig.getExtractorType());
      if (node.getNodeType().equals("single")) {
        String input = extractor.extract(content, nodeExtraConfig.getCond(), nodeExtraConfig.getValue());
        if (StringUtils.isNotEmpty(input)) {
          this.getNodeResult(task, parseData, nodeResult, input, nodeTree);
          if (!nodeResult.isEmpty()) {
            result.add(nodeResult);
          }
        }
      } else {
        List<String> inputs = extractor.extractMulti(content, nodeExtraConfig.getCond(), nodeExtraConfig.getValue());
        if (CollectionUtils.isNotEmpty(inputs)) {
          for (String input : inputs) {
            this.getNodeResult(task, parseData, nodeResult, input, nodeTree);
            if (!nodeResult.isEmpty()) {
              result.add(nodeResult);
            }
            nodeResult = new NodeResult();
          }
        }
      }
    } catch (Exception e) {
      LOG.error("[Parse Exception] ", e);
    }
    return result;
  }

  protected String getInput(ParserTask task, ExtraConfig config) {
    String input = StringUtils.EMPTY;
    if (config.getInputType() != null) {
      ExtraConfig.ExtractorInput extractorInput = config.getInputType();
      if (extractorInput == ExtraConfig.ExtractorInput.URL) {
        input = task.getUrl();
      }
    }
    return input;
  }

  public void getNodeResult(ParserTask task, ParseData parseData, NodeResult nodeResult, String content, NodeTree nodeTree) {
    // visit node's properties
    List<PropResult> linkResults = this.walkProps(task, nodeResult, content, nodeTree);
    this.buildLinkResult(task, parseData, nodeResult, linkResults);

    // visit child node
    List<NodeTree> childNodeTrees = nodeTree.getChildNodeTrees();
    for (NodeTree childNodeTree : childNodeTrees) {
      List<NodeResult> nodeResults = this.walkNode(task, content, childNodeTree, parseData);
      if (CollectionUtils.isNotEmpty(nodeResults)) {
        nodeResult.addData(childNodeTree.getNode().getLabel(), nodeResults);
      }
    }
  }

  public List<PropResult> walkProps(ParserTask task, NodeResult nodeResult, String content, NodeTree nodeTree) {
    List<PropTree> propTrees = nodeTree.getPropTrees();
    List<PropResult> linkResults = new ArrayList<>();
    for (PropTree propTree : propTrees) {
      try {
        Prop prop = propTree.getProp();
        List<PropResult> propResults = this.getPropResult(task, content, propTree);
        if (CollectionUtils.isNotEmpty(propResults)) {
          this.fillNodeResult(nodeResult, propResults);
          if (prop.getResultType() == Prop.ResultType.LINK) {
            linkResults.addAll(propResults);
          }
        }
      } catch (Exception e) {
        LOG.error("[Parse Exception] " + e);
      }
    }
    return linkResults;
  }

  public List<PropResult> getPropResult(ParserTask task, String content, PropTree propTree) throws Exception {
    if (StringUtils.isEmpty(content)) {
      return null;
    }

    Prop prop = propTree.getProp();
    List<PropResult> result = new ArrayList<>();
    Map<Long,List<String>> context = new HashMap<>();
    List<String> multiValues = new ArrayList<>();
    List<String> inputs = new ArrayList<>();
    inputs.add(content);

    List<ExtraConfig> extraConfigs = propTree.getExtraConfigs();
    for (ExtraConfig extraConfig : extraConfigs) {
      if (CollectionUtils.isEmpty(inputs)) {
        break;
      }

      if (extraConfig.hasRefExtraConfig()) {
        List<String> preValues = context.get(extraConfig.getRefExtraConfigId());
        if (CollectionUtils.isEmpty(preValues)) {
          break;
        } else {
          inputs.clear();
          inputs.addAll(preValues);
        }
      }

      if (extraConfig.getInputType() != ExtraConfig.ExtractorInput.DEFAULT) {
        String input = this.getInput(task, extraConfig);
        if (StringUtils.isEmpty(input)) {
          break;
        } else {
          inputs.clear();
          inputs.add(input);
        }
      }

      Extractor extractor = extractorLoader.load(extraConfig.getExtractorType());
      ExtraConfig.TransformType transformType = extraConfig.getTransformType();
      multiValues.clear();
      if (transformType == ExtraConfig.TransformType.ONE_TO_MANY) {
        for (String input : inputs) {
          List<String> values = extractor.extractMulti(input, extraConfig.getCond(), extraConfig.getValue());
          multiValues.addAll(values);
        }
      } else {
        for (String input : inputs) {
          String value = extractor.extract(input, extraConfig.getCond(), extraConfig.getValue());
          if (StringUtils.isNotEmpty(value)) {
            multiValues.add(value);
          }
        }
      }

      if (CollectionUtils.isNotEmpty(multiValues)) {
        context.put(extraConfig.getId(), multiValues);
      }
      // here let last result as next input
      inputs.clear();
      inputs.addAll(multiValues);
    }

    for (String value : multiValues) {
      result.add(new PropResult(prop, value));
    }
    return result;
  }

  public void fillNodeResult(NodeResult nodeResult, List<PropResult> propResults) {
    if (CollectionUtils.isEmpty(propResults)) {
      return;
    }

    for (PropResult result : propResults) {
      if (result.getValue() == null) {
        continue;
      }
      Prop.ResultType resultType = result.getProp().getResultType();
      if (resultType == Prop.ResultType.TEXT) {
        nodeResult.addData(result.getProp().getLabel(), result.getValue());
      } else if (resultType == Prop.ResultType.LINK) {
        String url = (String) result.getValue();
        if (StringUtils.isNotEmpty(url)) {
          nodeResult.addData(result.getProp().getLabel(), url);
        }
      }
    }
  }

  public void buildLinkResult(ParserTask parseTask, ParseData parseData, NodeResult nodeResult, List<PropResult> propResults) {
    for (PropResult propResult : propResults) {
      Prop.ResultType resultType = propResult.getProp().getResultType();
      if (resultType != Prop.ResultType.LINK) {
        continue;
      }

      String url = (String) propResult.getValue();
      if (StringUtils.isEmpty(url)) {
        continue;
      }

      HttpRequest httpRequest = new HttpRequest(url);
      httpRequest.setReferer(parseTask.getUrl());
      httpRequest.setForms(nodeResult.getForms());
      String httpMethodStr = propResult.getProp().getHttpMethod();
      httpRequest.setHttpMethod(HttpMethod.valueOf(httpMethodStr));

      CrawlerTask crawlerTask = new CrawlerTask();
      crawlerTask.addHttpRequest(httpRequest);
      crawlerTask.setSiteName(parseTask.getSiteName());
      crawlerTask.setPageType(parseData.getRule().getPageType());
      parseData.getCrawlerTasks().add(crawlerTask);
    }
  }
}
