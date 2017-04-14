package com.local.labs.parser.common.model.parser.rule;

import java.util.ArrayList;
import java.util.List;

public class RuleTree {

  Rule rule;

  List<NodeTree> topNodeTrees;

  public RuleTree() {}

  public RuleTree(Rule rule) {
    this.rule = rule;
    this.topNodeTrees = new ArrayList<>();
  }

  public Rule getRule() {
    return rule;
  }

  public void setRule(Rule rule) {
    this.rule = rule;
  }

  public List<NodeTree> getTopNodeTrees() {
    return topNodeTrees;
  }

  public void setTopNodeTrees(List<NodeTree> topNodeTrees) {
    this.topNodeTrees = topNodeTrees;
  }
}
