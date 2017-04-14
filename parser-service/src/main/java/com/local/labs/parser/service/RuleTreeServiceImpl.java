package com.local.labs.parser.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.local.labs.parser.common.model.parser.rule.ExtraConfig;
import com.local.labs.parser.common.model.parser.rule.Node;
import com.local.labs.parser.common.model.parser.rule.NodeTree;
import com.local.labs.parser.common.model.parser.rule.Prop;
import com.local.labs.parser.common.model.parser.rule.PropTree;
import com.local.labs.parser.common.model.parser.rule.Rule;
import com.local.labs.parser.common.model.parser.rule.RuleTree;

/**
 * Author: Xing Wang <wangxing.bjtu@gmail.com>
 * Date: 2017-01-24 Time: 11:34
 */
@Service
public class RuleTreeServiceImpl implements RuleTreeService {

  @Autowired
  private NodeService nodeService;

  @Autowired
  private PropService propService;

  @Autowired
  private ExtraConfigService extraConfigService;

  @Override
  public RuleTree getRuleTree(Rule rule) {
    if (rule == null) {
      return null;
    }

    RuleTree ruleTree = new RuleTree(rule);
    List<Node> nodes = nodeService.listByRule(rule.getId());
    for (Node node : nodes) {
      if (node.isTopNode()) {
        NodeTree nodeTree = this.getNodeTree(node);
        ruleTree.getTopNodeTrees().add(nodeTree);
      }
    }
    return ruleTree;
  }

  @Override
  public NodeTree getNodeTree(Node node) {
    NodeTree nodeTree = new NodeTree(node);
    ExtraConfig extraConfig = extraConfigService.get(node.getExtraConfigId());
    nodeTree.setExtraConfig(extraConfig);

    List<Prop> props = propService.listByNode(node.getId());
    for (Prop prop : props) {
      PropTree propTree = this.getPropTree(prop);
      nodeTree.getPropTrees().add(propTree);
    }

    List<Node> nodes = nodeService.listByRule(node.getRuleId());
    for (Node oneNode : nodes) {
      if (oneNode.getParentNode().equals(node.getId())) {
        NodeTree childNodeTree = this.getNodeTree(oneNode);
        nodeTree.getChildNodeTrees().add(childNodeTree);
      }
    }
    return nodeTree;
  }

  @Override
  public PropTree getPropTree(Prop prop) {
    PropTree propTree = new PropTree(prop);
    List<ExtraConfig> extraConfigs = extraConfigService.listByProp(prop.getId());
    propTree.getExtraConfigs().addAll(extraConfigs);
    return propTree;
  }
}
