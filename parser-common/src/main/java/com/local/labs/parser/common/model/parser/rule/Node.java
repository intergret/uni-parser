package com.local.labs.parser.common.model.parser.rule;

public class Node {

  private Long id;

  private String label;

  private String nodeType;

  private Long extraConfigId;

  private Long ruleId;

  private Long parentNode;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getNodeType() {
    return nodeType;
  }

  public void setNodeType(String nodeType) {
    this.nodeType = nodeType;
  }

  public Long getExtraConfigId() {
    return extraConfigId;
  }

  public void setExtraConfigId(Long extraConfigId) {
    this.extraConfigId = extraConfigId;
  }

  public Long getRuleId() {
    return ruleId;
  }

  public void setRuleId(Long ruleId) {
    this.ruleId = ruleId;
  }

  public Long getParentNode() {
    return parentNode;
  }

  public void setParentNode(Long parentNode) {
    this.parentNode = parentNode;
  }

  public boolean isTopNode() {
    return parentNode == 0;
  }

  @Override
  public Node clone() throws CloneNotSupportedException {
    Node node = (Node) super.clone();
    return node;
  }
}
