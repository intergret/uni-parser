package com.local.labs.parser.common.model.parser.rule;

import java.util.ArrayList;
import java.util.List;

import com.local.labs.parser.common.model.parser.ParserType;

public class Prop {

  private Long id;

  private String label;

  private Boolean isRequired;

  private Boolean isMultiply;

  private ScopeType scopeType;

  private ResultType resultType;

  private String httpMethod;

  private ParserType parserType;

  private Long nodeId;

  private Long ruleId;

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

  public Boolean getIsRequired() {
    return isRequired;
  }

  public void setIsRequired(Boolean isRequired) {
    this.isRequired = isRequired;
  }

  public Boolean getIsMultiply() {
    return isMultiply;
  }

  public void setIsMultiply(Boolean isMultiply) {
    this.isMultiply = isMultiply;
  }

  public ResultType getResultType() {
    return resultType;
  }

  public void setResultType(ResultType resultType) {
    this.resultType = resultType;
  }

  public String getHttpMethod() {
    return httpMethod;
  }

  public void setHttpMethod(String httpMethod) {
    this.httpMethod = httpMethod;
  }

  public ParserType getParserType() {
    return parserType;
  }

  public void setParserType(ParserType parserType) {
    this.parserType = parserType;
  }

  public Long getNodeId() {
    return nodeId;
  }

  public void setNodeId(Long nodeId) {
    this.nodeId = nodeId;
  }

  public Long getRuleId() {
    return ruleId;
  }

  public void setRuleId(Long ruleId) {
    this.ruleId = ruleId;
  }

  public ScopeType getScopeType() {
    return scopeType;
  }

  public void setScopeType(ScopeType scopeType) {
    this.scopeType = scopeType;
  }

  public enum ResultType {
    TEXT, LINK, FORMITEM, HEADITEM;

    public static List<String> list() {
      List<String> result = new ArrayList<>();
      Prop.ResultType[] array = Prop.ResultType.values();
      for (Prop.ResultType r : array) {
        result.add(r.name());
      }
      return result;
    }
  }

  public enum ScopeType {
    LOCAL, NODE, RULE;

    public static List<String> list() {
      List<String> result = new ArrayList<>();
      Prop.ScopeType[] array = Prop.ScopeType.values();
      for (Prop.ScopeType s : array) {
        result.add(s.name());
      }
      return result;
    }
  }

  @Override
  public Prop clone() throws CloneNotSupportedException {
    Prop prop = (Prop) super.clone();
    return prop;
  }
}
