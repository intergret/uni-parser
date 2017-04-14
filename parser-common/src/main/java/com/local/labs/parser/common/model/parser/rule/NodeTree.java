package com.local.labs.parser.common.model.parser.rule;

import java.util.ArrayList;
import java.util.List;

public class NodeTree {

  Node node;

  ExtraConfig extraConfig;

  List<PropTree> props;

  List<NodeTree> childNodes;

  public NodeTree() {}

  public NodeTree(Node node) {
    this.node = node;
    this.props = new ArrayList<>();
    this.childNodes = new ArrayList<>();
  }

  public Node getNode() {
    return node;
  }

  public void setNode(Node node) {
    this.node = node;
  }

  public ExtraConfig getExtraConfig() {
    return extraConfig;
  }

  public void setExtraConfig(ExtraConfig extraConfig) {
    this.extraConfig = extraConfig;
  }

  public List<PropTree> getPropTrees() {
    return props;
  }

  public void setPropTrees(List<PropTree> propTrees) {
    this.props = propTrees;
  }

  public List<NodeTree> getChildNodeTrees() {
    return childNodes;
  }

  public void setChildNodeTrees(List<NodeTree> childNodes) {
    this.childNodes = childNodes;
  }
}