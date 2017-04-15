package com.local.labs.parser.web.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.local.labs.parser.common.model.State;
import com.local.labs.parser.common.model.parser.PageType;
import com.local.labs.parser.common.model.parser.ParserType;
import com.local.labs.parser.common.model.parser.rule.ExtraConfig;
import com.local.labs.parser.common.model.parser.rule.Node;
import com.local.labs.parser.common.model.parser.rule.Prop;
import com.local.labs.parser.common.model.parser.rule.Rule;
import com.local.labs.parser.service.ExtraConfigService;
import com.local.labs.parser.service.NodeService;
import com.local.labs.parser.service.PropService;
import com.local.labs.parser.service.RuleService;
import com.local.labs.parser.web.consts.PageConst;
import com.local.labs.parser.web.consts.Pagination;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Author: Xing Wang <wangxing.bjtu@gmail.com>
 * Date: 2017-01-23 Time: 16:14
 */
@Controller
@RequestMapping("/admin")
public class RuleController {

  @Autowired
  private RuleService ruleService;

  @Autowired
  private NodeService nodeService;

  @Autowired
  private PropService propService;

  @Autowired
  private ExtraConfigService extraConfigService;

  @RequestMapping(value = "/rule/index", method = RequestMethod.GET)
  public ModelAndView showRuleListAction(final ModelMap model,
      @RequestParam(value = "searchWord", required = false, defaultValue = "") final String searchWord,
      @RequestParam(value = "parserType", required = false, defaultValue = "") final String parserType,
      @RequestParam(value = "pageType", required = false, defaultValue = "") final String pageType,
      @RequestParam(value = "state", required = false, defaultValue = "") final String state,
      @RequestParam(value = "page", required = false, defaultValue = "1") final Integer page,
      HttpServletRequest request) {
    String pageUrl = UrlUtils.buildFullRequestUrl(request);
    Pagination pagination = new Pagination(page, PageConst.PAGE_SIZE, pageUrl);

    List<Rule> allRules = ruleService.listAll();
    Iterator<Rule> iter = allRules.iterator();
    while (iter.hasNext()) {
      Rule nextRule = iter.next();
      String patternStr = nextRule.getPattern();
      String parserTypeStr = nextRule.getParserType().name();
      String pageTypeStr = nextRule.getPageType().name();
      String stateStr = nextRule.getState().name();
      String description = nextRule.getDescription();
      if (StringUtils.isNotEmpty(searchWord)) {
        if ("DEFAULT".equalsIgnoreCase(searchWord)) {
          if (StringUtils.isNotEmpty(patternStr)) {
            iter.remove();
            continue;
          }
        } else {
          if (StringUtils.isEmpty(patternStr)
              || (!patternStr.contains(searchWord) && !description.contains(searchWord))) {
            iter.remove();
            continue;
          }
        }
      }
      if (StringUtils.isNotEmpty(parserType) && !parserTypeStr.equals(parserType)) {
        iter.remove();
        continue;
      }
      if (StringUtils.isNotEmpty(pageType) && !pageTypeStr.equals(pageType)) {
        iter.remove();
        continue;
      }
      if (StringUtils.isNotEmpty(state) && !stateStr.equals(state)) {
        iter.remove();
        continue;
      }
    }
    pagination.setTotalRowsAndPageTotal(allRules.size());

    int fromIndex = pagination.getStartIndex();
    int toIndex = Math.min(pagination.getStartIndex() + pagination.getPageSize(), allRules.size());
    List<Rule> rules = allRules.subList(fromIndex, toIndex);

    model.put("pageNation", pagination);
    model.put("page", page);
    model.put("rules", rules);
    model.put("pageTypeStrList", PageConst.PAGE_TYPE_LIST);
    model.put("parserTypeStrList", PageConst.PARSER_TYPE_LIST);
    model.put("stateStrList", PageConst.STATE_LIST);
    return new ModelAndView("/admin/rule/rule-list", model);
  }

  @RequestMapping(value = "/rule/skeleton", method = RequestMethod.GET)
  public ModelAndView showRuleSkeletonAction(final ModelMap model) {
    List<Rule> allRules = ruleService.listAll();
    model.put("allRules", allRules);
    model.put("pageTypeStrList", PageConst.PAGE_TYPE_LIST);
    model.put("parserTypeStrList", PageConst.PARSER_TYPE_LIST);
    model.put("stateStrList", PageConst.STATE_LIST);
    return new ModelAndView("/admin/rule/rule-skeleton", model);
  }

  @RequestMapping(value = "/rule/", method = RequestMethod.POST)
  public ModelAndView addRuleAction(final ModelMap model,
      @RequestParam(value = "pattern", required = true) final String pattern,
      @RequestParam(value = "instance", required = true) final String instance,
      @RequestParam(value = "parserType", required = false) final ParserType parserType,
      @RequestParam(value = "pageType", required = false) PageType pageType,
      @RequestParam(value = "description", required = false) String description,
      @RequestParam(value = "author", required = false) String author,
      @RequestParam(value = "page", required = false, defaultValue = "1") final Integer page,
      RedirectAttributes redirectAttributes) {
    Rule rule = new Rule();
    rule.setPattern(pattern);
    rule.setInstance(instance);
    rule.setParserType(parserType);
    rule.setPageType(pageType);
    rule.setState(State.DISABLE);
    rule.setDescription(description);
    rule.setAuthor(author);
    ruleService.insert(rule);

    redirectAttributes.addAttribute("page", page);
    return new ModelAndView("redirect:/admin/rule/index", model);
  }

  @RequestMapping(value = "/rule/{ruleId:.+}", method = RequestMethod.DELETE)
  public ModelAndView deleteRuleAction(final ModelMap model, @PathVariable final Long ruleId,
      @RequestParam(value = "page", required = false, defaultValue = "1") final Integer page,
      RedirectAttributes redirectAttributes) {

    Rule rule = ruleService.get(ruleId);
    if (rule != null) {
      ruleService.delete(rule);
    }
    redirectAttributes.addAttribute("page", page);
    return new ModelAndView("redirect:/admin/rule/index", model);
  }

  @RequestMapping(value = "/rule/{ruleId:.+}", method = RequestMethod.PUT)
  public ModelAndView updateRuleAction(@PathVariable final Long ruleId,
      @RequestParam(value = "pattern", required = false, defaultValue = "") final String pattern,
      @RequestParam(value = "instance", required = false, defaultValue = "") final String instance,
      @RequestParam(value = "parserType", required = false, defaultValue = "") final ParserType parserType,
      @RequestParam(value = "pageType", required = false, defaultValue = "") final PageType pageType,
      @RequestParam(value = "state", required = false, defaultValue = "") final State state,
      @RequestParam(value = "description", required = false, defaultValue = "") final String description,
      RedirectAttributes redirectAttributes) {
    Rule rule = ruleService.get(ruleId);
    if (rule == null) {
      redirectAttributes.addAttribute("updateState", "failed");
      return new ModelAndView("redirect:/admin/rule/" + ruleId + "#Rule");
    }

    rule.setPattern(pattern);
    rule.setInstance(instance);
    rule.setParserType(parserType);
    rule.setPageType(pageType);
    rule.setState(state);
    rule.setDescription(description);
    ruleService.update(rule);

    redirectAttributes.addAttribute("updateState", "success");
    return new ModelAndView("redirect:/admin/rule/" + ruleId + "#Rule");
  }

  @RequestMapping(value = "/rule/{ruleId:.+}", method = RequestMethod.GET)
  public ModelAndView showRuleInfoAction(final ModelMap model, @PathVariable final Long ruleId,
      @RequestParam(value = "updateState", required = false, defaultValue = "") final String updateState) {
    model.put("updateState", updateState);
    return getRuleInfo(model, ruleId);
  }

  @RequestMapping(value = "/rule/node/skeleton", method = RequestMethod.GET)
  public ModelAndView showNodeSkeletonAction(final ModelMap model,
      @RequestParam(value = "ruleId", required = true) final Long ruleId) {
    Rule rule = ruleService.get(ruleId);
    model.put("rule", rule);
    return getNodeSkeleton(model, ruleId);
  }

  @RequestMapping(value = "/rule/node/", method = RequestMethod.POST)
  public ModelAndView addNodeAction(final ModelMap model,
      @RequestParam(value = "inputType", required = false, defaultValue = "DEFAULT") final ExtraConfig.ExtractorInput inputType,
      @RequestParam(value = "cond", required = false, defaultValue = "") final String cond,
      @RequestParam(value = "value", required = false, defaultValue = "") final String value,
      @RequestParam(value = "extractorType", required = false, defaultValue = "") final String extractorType,
      @RequestParam(value = "refExtraConfigId", required = false, defaultValue = "0") final long refExtraConfigId,
      @RequestParam(value = "label", required = false, defaultValue = "") final String label,
      @RequestParam(value = "nodeType", required = false, defaultValue = "") final String nodeType,
      @RequestParam(value = "parentNodeId", required = false, defaultValue = "0") final Long parentNodeId,
      @RequestParam(value = "ruleId", required = true) final Long ruleId) {
    ExtraConfig extraConfig = new ExtraConfig();
    extraConfig.setInputType(inputType);
    extraConfig.setValue(value);
    extraConfig.setCond(cond);
    extraConfig.setExtractorType(ExtraConfig.ExtractorType.valueOf(extractorType));
    extraConfig.setRefExtraConfigId(refExtraConfigId);
    extraConfig.setRuleId(ruleId);
    if ("single".equals(nodeType)) {
      extraConfig.setTransformType(ExtraConfig.TransformType.ONE_TO_ONE);
    } else if ("multiply".equals(nodeType)) {
      extraConfig.setTransformType(ExtraConfig.TransformType.ONE_TO_MANY);
    }
    long extraConfigId = extraConfigService.insert(extraConfig);
    extraConfig.setId(extraConfigId);

    Node node = new Node();
    node.setLabel(label.trim());
    node.setNodeType(nodeType);
    node.setParentNode(parentNodeId);
    node.setRuleId(ruleId);
    node.setExtraConfigId(extraConfigId);
    long nodeId = nodeService.insert(node);

    extraConfig.setNodeId(nodeId);
    extraConfigService.update(extraConfig);

    model.put("nodeId", nodeId);
    return new ModelAndView("redirect:/admin/rule/prop/skeleton#AddProp", model);
  }

  @RequestMapping(value = "/rule/node/{nodeId:.+}", method = RequestMethod.DELETE)
  public ModelAndView deleteNodeAction(final ModelMap model, @PathVariable final Long nodeId,
      @RequestParam(value = "ruleId", required = true) final Long ruleId) {
    Node deleteNode = nodeService.get(nodeId);
    if (deleteNode != null) {
      Set<Long> nodeToDelete = new HashSet<>();
      nodeToDelete.add(deleteNode.getId());
      nodeService.delete(deleteNode);

      List<Node> allNodes = nodeService.listByRule(ruleId);
      boolean haveNodeToDelete = true;
      while (haveNodeToDelete) {
        haveNodeToDelete = false;
        Iterator<Node> iter = allNodes.iterator();
        while (iter.hasNext()) {
          Node node = iter.next();
          if (nodeToDelete.contains(node.getParentNode())) {
            nodeToDelete.add(node.getId());
            haveNodeToDelete = true;
            iter.remove();
            nodeService.delete(node);
          }
        }
      }
    }
    return new ModelAndView("redirect:/admin/rule/" + ruleId + "#Rule", model);
  }

  @RequestMapping(value = "/rule/node/{nodeId:.+}", method = RequestMethod.PUT)
  public ModelAndView updateNodeAction(@PathVariable final Long nodeId,
      @RequestParam(value = "ruleId", required = true) final Long ruleId,
      @RequestParam(value = "label", required = false, defaultValue = "") final String label,
      @RequestParam(value = "nodeType", required = false, defaultValue = "") final String nodeType,
      @RequestParam(value = "parentNodeId", required = false, defaultValue = "0") final Long parentNodeId,
      @RequestParam(value = "inputType", required = false, defaultValue = "DEFAULT") final ExtraConfig.ExtractorInput inputType,
      @RequestParam(value = "condition", required = false, defaultValue = "") final String cond,
      @RequestParam(value = "valueStr", required = false, defaultValue = "") final String value,
      @RequestParam(value = "extractorType", required = false, defaultValue = "") final String extractorType,
      RedirectAttributes redirectAttributes) {
    Node node = nodeService.get(nodeId);
    if (node == null) {
      redirectAttributes.addAttribute("updateState", "failed");
      return new ModelAndView("redirect:/admin/rule/node/" + nodeId + "#Node");
    }

    node.setNodeType(nodeType);
    node.setLabel(label.trim());
    node.setRuleId(ruleId);
    node.setParentNode(parentNodeId);
    nodeService.update(node);

    ExtraConfig extraConfig = extraConfigService.get(node.getExtraConfigId());
    extraConfig.setInputType(inputType);
    extraConfig.setCond(cond);
    extraConfig.setValue(value);
    extraConfig.setExtractorType(ExtraConfig.ExtractorType.valueOf(extractorType));
    if ("single".equals(nodeType)) {
      extraConfig.setTransformType(ExtraConfig.TransformType.ONE_TO_ONE);
    } else if ("multiply".equals(nodeType)) {
      extraConfig.setTransformType(ExtraConfig.TransformType.ONE_TO_MANY);
    }
    extraConfigService.update(extraConfig);

    redirectAttributes.addAttribute("updateState", "success");
    return new ModelAndView("redirect:/admin/rule/node/" + nodeId + "#Node");
  }

  @RequestMapping(value = "/rule/node/{nodeId:.+}", method = RequestMethod.GET)
  public ModelAndView showNodeInfoAction(final ModelMap model, @PathVariable final Long nodeId,
      @RequestParam(value = "updateState", required = false, defaultValue = "") final String updateState) {
    model.put("updateState", updateState);
    return getNodeInfo(model, nodeId);
  }

  @RequestMapping(value = "/rule/prop/skeleton", method = RequestMethod.GET)
  public ModelAndView showPropSkeletonAction(final ModelMap model,
      @RequestParam(value = "nodeId", required = false, defaultValue = "") final Long nodeId) {
    Node node = nodeService.get(nodeId);
    return getPropSkeleton(model, nodeId);
  }

  @RequestMapping(value = "/rule/prop", method = RequestMethod.POST)
  public ModelAndView addPropAction(final ModelMap model,
      @RequestParam(value = "label", required = false, defaultValue = "") final String label,
      @RequestParam(value = "isRequired", required = false) final boolean isRequired,
      @RequestParam(value = "isMultiply", required = false) final boolean isMultiply,
      @RequestParam(value = "resultType", required = false) final Prop.ResultType resultType,
      @RequestParam(value = "httpMethod", required = false) final String httpMethod,
      @RequestParam(value = "parserType", required = false) final ParserType parserType,
      @RequestParam(value = "ruleId", required = true) final Long ruleId,
      @RequestParam(value = "nodeId", required = true) final Long nodeId) {
    Prop prop = new Prop();
    prop.setLabel(label.trim());
    prop.setIsMultiply(isMultiply);
    prop.setIsRequired(isRequired);
    prop.setResultType(resultType);
    prop.setHttpMethod(httpMethod);
    prop.setParserType(parserType);
    prop.setNodeId(nodeId);
    prop.setRuleId(ruleId);

    long propId = propService.insert(prop);
    model.put("propId", propId);
    return new ModelAndView("redirect:/admin/rule/extraConfig/skeleton#AddExtraConfig", model);
  }

  @RequestMapping(value = "/rule/prop/{propId:.+}", method = RequestMethod.DELETE)
  public ModelAndView deletePropAction(final ModelMap model, @PathVariable final Long propId,
      @RequestParam(value = "ruleId", required = true) final Long ruleId,
      @RequestParam(value = "nodeId", required = true) final Long nodeId) {
    Prop prop = propService.get(propId);
    if (prop != null) {
      propService.delete(prop);
    }
    return new ModelAndView("redirect:/admin/rule/node/" + nodeId + "#Node", model);
  }

  @RequestMapping(value = "/rule/prop/{propId:.+}", method = RequestMethod.PUT)
  public ModelAndView updatePropAction(
      @RequestParam(value = "label", required = false, defaultValue = "") final String label,
      @RequestParam(value = "isRequired", required = false) final boolean isRequired,
      @RequestParam(value = "isMultiply", required = false) final boolean isMultiply,
      @RequestParam(value = "resultType", required = false, defaultValue = "") final Prop.ResultType resultType,
      @RequestParam(value = "httpMethod", required = false, defaultValue = "") final String httpMethod,
      @RequestParam(value = "parserType", required = false, defaultValue = "") final ParserType parserType,
      @RequestParam(value = "nodeId", required = true) final Long nodeId,
      @RequestParam(value = "propId", required = true) final Long propId,
      @RequestParam(value = "ruleId", required = true) final Long ruleId,
      RedirectAttributes redirectAttributes) {
    Prop prop = propService.get(propId);
    if (prop == null) {
      redirectAttributes.addAttribute("updateState", "failed");
      return new ModelAndView("redirect:/admin/rule/prop/" + propId + "#Property");
    }

    prop.setLabel(label.trim());
    prop.setIsMultiply(isMultiply);
    prop.setIsRequired(isRequired);
    prop.setResultType(resultType);
    prop.setHttpMethod(httpMethod);
    prop.setParserType(parserType);
    prop.setNodeId(nodeId);
    prop.setRuleId(ruleId);
    propService.update(prop);

    redirectAttributes.addAttribute("updateState", "success");
    return new ModelAndView("redirect:/admin/rule/prop/" + propId + "#Property");
  }

  @RequestMapping(value = "/rule/prop/{propId:.+}", method = RequestMethod.GET)
  public ModelAndView showPropInfoAction(final ModelMap model, @PathVariable final Long propId,
      @RequestParam(value = "updateState", required = false, defaultValue = "") final String updateState) {
    model.put("updateState", updateState);
    return getPropInfo(model, propId);
  }

  @RequestMapping(value = "/rule/extraConfig/skeleton", method = RequestMethod.GET)
  public ModelAndView showExtraConfigSkeletonAction(final ModelMap model,
      @RequestParam(value = "propId", required = false, defaultValue = "") final Long propId) {
    return getExtraConfigSkeleton(model, propId);
  }

  @RequestMapping(value = "/rule/extraConfig", method = RequestMethod.POST)
  public ModelAndView addExtraConfigAction(final ModelMap model,
      @RequestParam(value = "inputType", required = false, defaultValue = "DEFAULT") final ExtraConfig.ExtractorInput inputType,
      @RequestParam(value = "condition", required = false, defaultValue = "") final String cond,
      @RequestParam(value = "valueStr", required = false, defaultValue = "") final String value,
      @RequestParam(value = "extractorType", required = false, defaultValue = "") final String extractorType,
      @RequestParam(value = "transformType", required = false, defaultValue = "") final String transformType,
      @RequestParam(value = "refExtraConfigId", required = false, defaultValue = "0") final long refExtraConfigId,
      @RequestParam(value = "ruleId", required = true) final Long ruleId,
      @RequestParam(value = "nodeId", required = true) final Long nodeId,
      @RequestParam(value = "propId", required = true) final Long propId,
      @RequestParam(value = "redirectPage", required = false, defaultValue = "prop") final String redirectPage) {
    ExtraConfig extraConfig = new ExtraConfig();
    extraConfig.setInputType(inputType);
    extraConfig.setCond(cond);
    extraConfig.setValue(value);
    extraConfig.setExtractorType(ExtraConfig.ExtractorType.valueOf(extractorType));
    extraConfig.setTransformType(ExtraConfig.TransformType.valueOf(transformType));
    extraConfig.setRefExtraConfigId(refExtraConfigId);
    extraConfig.setPropId(propId);
    extraConfig.setNodeId(nodeId);
    extraConfig.setRuleId(ruleId);

    extraConfigService.insert(extraConfig);
    model.put("ruleId", ruleId);
    model.put("nodeId", nodeId);
    model.put("propId", propId);
    if (redirectPage.equals("prop")) {
      return new ModelAndView("redirect:/admin/rule/prop/skeleton#AddProp", model);
    } else {
      return new ModelAndView("redirect:/admin/rule/extraConfig/skeleton#AddExtraConfig", model);
    }
  }

  @RequestMapping(value = "/rule/extraConfig/{extraConfigId:.+}", method = RequestMethod.DELETE)
  public ModelAndView deleteExtraConfigAction(final ModelMap model,
      @PathVariable final Long extraConfigId, @RequestParam(value = "ruleId", required = true) final Long ruleId,
      @RequestParam(value = "nodeId", required = true) final Long nodeId,
      @RequestParam(value = "propId", required = true) final Long propId) {
    ExtraConfig extraConfig = extraConfigService.get(extraConfigId);
    if (extraConfig != null) {
      extraConfigService.delete(extraConfig);
    }
    return new ModelAndView("redirect:/admin/rule/prop/" + propId + "#Property", model);
  }

  @RequestMapping(value = "/rule/extraConfig/{extraConfigId:.+}", method = RequestMethod.PUT)
  public ModelAndView updateExtraConfigAction(
      @RequestParam(value = "inputType", required = false, defaultValue = "DEFAULT") final ExtraConfig.ExtractorInput inputType,
      @RequestParam(value = "condition", required = true, defaultValue = "") final String cond,
      @RequestParam(value = "valueStr", required = true, defaultValue = "") final String value,
      @RequestParam(value = "extractorType", required = true, defaultValue = "") final String extractorType,
      @RequestParam(value = "transformType", required = false, defaultValue = "") final String transformType,
      @RequestParam(value = "refExtraConfigId", required = true, defaultValue = "") final long refExtraConfigId,
      @RequestParam(value = "ruleId", required = true) final Long ruleId,
      @RequestParam(value = "nodeId", required = true) final Long nodeId,
      @RequestParam(value = "propId", required = true) final Long propId,
      @RequestParam(value = "extraConfigId", required = true) final Long extraConfigId,
      RedirectAttributes redirectAttributes) {
    ExtraConfig extraConfig = extraConfigService.get(extraConfigId);
    if (extraConfig == null) {
      redirectAttributes.addAttribute("updateState", "failed");
      redirectAttributes.addAttribute("propId", propId);
      return new ModelAndView("redirect:/admin/rule/extraConfig/" + extraConfigId + "#ExtraConfig");
    }

    extraConfig.setPropId(propId);
    extraConfig.setNodeId(nodeId);
    extraConfig.setRuleId(ruleId);
    extraConfig.setInputType(inputType);
    extraConfig.setCond(cond);
    extraConfig.setValue(value);
    extraConfig.setExtractorType(ExtraConfig.ExtractorType.valueOf(extractorType));
    extraConfig.setTransformType(ExtraConfig.TransformType.valueOf(transformType));
    extraConfig.setRefExtraConfigId(refExtraConfigId);
    extraConfigService.update(extraConfig);

    redirectAttributes.addAttribute("updateState", "success");
    redirectAttributes.addAttribute("propId", propId);
    return new ModelAndView("redirect:/admin/rule/extraConfig/" + extraConfigId + "#ExtraConfig");
  }

  @RequestMapping(value = "/rule/extraConfig/{extraConfigId:.+}", method = RequestMethod.GET)
  public ModelAndView showExtraConfigInfoAction(final ModelMap model, @PathVariable final Long extraConfigId,
      @RequestParam(value = "propId", required = true) final Long propId,
      @RequestParam(value = "updateState", required = false, defaultValue = "") final String updateState) {
    model.put("updateState", updateState);
    return getExtraConfigInfo(model, propId, extraConfigId);
  }

  private ModelAndView getRuleInfo(final ModelMap model, final Long ruleId) {
    Rule rule = ruleService.get(ruleId);
    if (rule == null) {
      return new ModelAndView("/admin/rule/rule-list", model);
    }
    List<Rule> allRules = ruleService.listAll();
    List<Node> nodes = nodeService.listByRule(rule.getId());

    model.put("allRules", allRules);
    model.put("rule", rule);
    model.put("pageTypeStrList", PageConst.PAGE_TYPE_LIST);
    model.put("parserTypeStrList", PageConst.PARSER_TYPE_LIST);
    model.put("stateStrList", PageConst.STATE_LIST);
    model.put("nodelist", nodes);
    return new ModelAndView("/admin/rule/rule-detail", model);
  }

  private ModelAndView getNodeInfo(final ModelMap model, final Long nodeId) {
    Node node = nodeService.get(nodeId);
    ExtraConfig nodeExtraConfig = extraConfigService.get(node.getExtraConfigId());
    List<Node> nodeList = nodeService.listByRule(node.getRuleId());
    List<String> parentNodeStrList = new ArrayList<>();
    parentNodeStrList.add("0");
    for (Node nodeItem : nodeList) {
      if (!nodeItem.getId().equals(node.getId())) {
        parentNodeStrList.add(String.valueOf(nodeItem.getId()));
      }
    }

    Rule rule = ruleService.get(node.getRuleId());
    List<Prop> propList = propService.listByNode(nodeId);
    List<String> isMultiplyStrList = getPropMultiplyList(propList);
    List<String> isRequiredStrList = getPropRequiredList(propList);

    model.put("rule", rule);
    model.put("node", node);
    model.put("nodeExtraConfig", nodeExtraConfig);
    model.put("extractorInputTypeStrList", PageConst.EXTRACTOR_INPUT_TYPE_LIST);
    model.put("extractorTypeStrList", PageConst.EXTRACTOR_TYPE_LIST);
    model.put("parentNodeStrList", parentNodeStrList);
    model.put("propList", propList);
    model.put("isMultiplyStrList", isMultiplyStrList);
    model.put("isRequiredStrList", isRequiredStrList);
    return new ModelAndView("/admin/rule/rule-node-detail", model);
  }

  private ModelAndView getPropInfo(final ModelMap model, final Long propId) {
    Prop prop = propService.get(propId);
    String isRequiredStr = prop.getIsRequired() ? "是" : "否";
    String isMultiplyStr = prop.getIsMultiply() ? "是" : "否";

    Node node = nodeService.get(prop.getNodeId());
    ExtraConfig nodeExtraConfig = extraConfigService.get(node.getExtraConfigId());
    Rule rule = ruleService.get(node.getRuleId());
    List<ExtraConfig> extraConfigList = extraConfigService.listByProp(propId);

    model.put("rule", rule);
    model.put("node", node);
    model.put("nodeExtraConfig", nodeExtraConfig);
    model.put("prop", prop);
    model.put("isRequiredStr", isRequiredStr);
    model.put("isMultiplyStr", isMultiplyStr);
    model.put("extraConfigList", extraConfigList);
    model.put("resultTypeStrList", PageConst.RESULT_TYPE_LIST);
    model.put("httpMethodStrList", PageConst.HTTP_METHOD_LIST);
    model.put("parserTypeStrList", PageConst.PARSER_TYPE_LIST);
    return new ModelAndView("/admin/rule/rule-prop-detail", model);
  }

  private ModelAndView getExtraConfigInfo(final ModelMap model, final Long propId, final Long extraConfigId) {
    ExtraConfig extraConfig = extraConfigService.get(extraConfigId);
    Prop prop = propService.get(propId);
    String isRequiredStr = prop.getIsRequired() ? "是" : "否";
    String isMultiplyStr = prop.getIsMultiply() ? "是" : "否";
    Node node = nodeService.get(prop.getNodeId());
    ExtraConfig nodeExtraConfig = extraConfigService.get(node.getExtraConfigId());
    Rule rule = ruleService.get(node.getRuleId());

    List<ExtraConfig> extraConfigs = extraConfigService.listByProp(propId);
    List<String> refExtraConfigStrList = new ArrayList<>();
    refExtraConfigStrList.add("0");
    for (ExtraConfig extraConfigItem : extraConfigs) {
      if (!extraConfigItem.getId().equals(extraConfig.getId())) {
        refExtraConfigStrList.add(String.valueOf(extraConfigItem.getId()));
      }
    }

    model.put("rule", rule);
    model.put("node", node);
    model.put("nodeExtraConfig", nodeExtraConfig);
    model.put("prop", prop);
    model.put("isRequiredStr", isRequiredStr);
    model.put("isMultiplyStr", isMultiplyStr);
    model.put("extraConfig", extraConfig);
    model.put("extractorInputTypeStrList", PageConst.EXTRACTOR_INPUT_TYPE_LIST);
    model.put("extractorTypeStrList", PageConst.EXTRACTOR_TYPE_LIST);
    model.put("transformTypeStrList", PageConst.TRANSFORM_TYPE_LIST);
    model.put("refExtraConfigStrList", refExtraConfigStrList);
    return new ModelAndView("/admin/rule/rule-extraconfig-detail", model);
  }

  private ModelAndView getNodeSkeleton(final ModelMap model, final Long ruleId) {
    Rule rule = ruleService.get(ruleId);
    List<Node> nodeList = nodeService.listByRule(rule.getId());
    List<String> parentNodeStrList = new ArrayList<>();
    parentNodeStrList.add("0");
    for (Node nodeItem : nodeList) {
      parentNodeStrList.add(String.valueOf(nodeItem.getId()));
    }

    model.put("rule", rule);
    model.put("nodeList", nodeList);
    model.put("extractorInputTypeStrList", PageConst.EXTRACTOR_INPUT_TYPE_LIST);
    model.put("extractorTypeStrList", PageConst.EXTRACTOR_TYPE_LIST);
    model.put("parentNodeStrList", parentNodeStrList);
    return new ModelAndView("/admin/rule/rule-node-skeleton", model);
  }

  private ModelAndView getPropSkeleton(final ModelMap model, final Long nodeId) {
    Node node = nodeService.get(nodeId);
    ExtraConfig nodeExtraConfig = extraConfigService.get(node.getExtraConfigId());

    Rule rule = ruleService.get(node.getRuleId());
    List<Prop> props = propService.listByNode(nodeId);
    List<String> isRequiredStrList = getPropRequiredList(props);
    List<String> isMultiplyStrList = getPropMultiplyList(props);

    model.put("node", node);
    model.put("rule", rule);
    model.put("propList", props);
    model.put("nodeExtraConfig", nodeExtraConfig);
    model.put("isRequiredStrList", isRequiredStrList);
    model.put("isMultiplyStrList", isMultiplyStrList);
    model.put("resultTypeStrList", PageConst.RESULT_TYPE_LIST);
    model.put("httpMethodStrList", PageConst.HTTP_METHOD_LIST);
    model.put("parserTypeStrList", PageConst.PARSER_TYPE_LIST);
    return new ModelAndView("/admin/rule/rule-prop-skeleton", model);
  }

  private ModelAndView getExtraConfigSkeleton(final ModelMap model, final Long propId) {
    Prop prop = propService.get(propId);
    String isRequiredStr = prop.getIsRequired() ? "是" : "否";
    String isMultiplyStr = prop.getIsMultiply() ? "是" : "否";

    Node node = nodeService.get(prop.getNodeId());
    ExtraConfig nodeExtraConfig = extraConfigService.get(node.getExtraConfigId());
    String defaultExtractorType = nodeExtraConfig.getExtractorType().name();

    Rule rule = ruleService.get(node.getRuleId());
    List<ExtraConfig> extraConfigs = extraConfigService.listByProp(propId);
    List<Long> refExtraConfigStrList = new ArrayList<>();
    refExtraConfigStrList.add(0l);
    for (ExtraConfig extraConfig : extraConfigs) {
      refExtraConfigStrList.add(extraConfig.getId());
    }

    Long suggestRefExtraConfig = Collections.max(refExtraConfigStrList);
    model.put("node", node);
    model.put("rule", rule);
    model.put("extraConfigList", extraConfigs);
    model.put("prop", prop);
    model.put("isRequiredStr", isRequiredStr);
    model.put("isMultiplyStr", isMultiplyStr);
    model.put("nodeExtraConfig", nodeExtraConfig);
    model.put("defaultExtractorType", defaultExtractorType);
    model.put("refExtraConfigStrList", refExtraConfigStrList);
    model.put("suggestRefExtraConfig", suggestRefExtraConfig);
    model.put("extractorTypeStrList", PageConst.EXTRACTOR_TYPE_LIST);
    model.put("transformTypeStrList", PageConst.TRANSFORM_TYPE_LIST);
    model.put("extractorInputTypeStrList", PageConst.EXTRACTOR_INPUT_TYPE_LIST);
    return new ModelAndView("/admin/rule/rule-extraconfig-skeleton", model);
  }

  private List<String> getPropRequiredList(List<Prop> props) {
    List<String> isRequiredStrList = new ArrayList<>();
    for (Prop prop : props) {
      if (prop.getIsRequired()) {
        isRequiredStrList.add("是");
      } else {
        isRequiredStrList.add("否");
      }
    }
    return isRequiredStrList;
  }

  private List<String> getPropMultiplyList(List<Prop> props) {
    List<String> isMultiplyStrList = new ArrayList<>();
    for (Prop prop : props) {
      if (prop.getIsMultiply()) {
        isMultiplyStrList.add("是");
      } else {
        isMultiplyStrList.add("否");
      }
    }
    return isMultiplyStrList;
  }
}
