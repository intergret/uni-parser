package com.local.labs.parser.common.model.parser.rule;

import java.util.ArrayList;
import java.util.List;

public class ExtraConfig {

  private Long id;

  private ExtractorInput inputType;

  private String cond;

  private String value;

  private Long refExtraConfigId;

  private ExtractorType extractorType;

  private TransformType transformType;

  private Long propId;

  private Long nodeId;

  private Long ruleId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ExtractorInput getInputType() {
    return inputType;
  }

  public void setInputType(ExtractorInput inputType) {
    this.inputType = inputType;
  }

  public String getCond() {
    return cond;
  }

  public void setCond(String cond) {
    this.cond = cond;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public Long getRefExtraConfigId() {
    return refExtraConfigId;
  }

  public void setRefExtraConfigId(Long refExtraConfigId) {
    this.refExtraConfigId = refExtraConfigId;
  }

  public boolean hasRefExtraConfig() {
    return refExtraConfigId != 0;
  }

  public ExtractorType getExtractorType() {
    return extractorType;
  }

  public void setExtractorType(ExtractorType extractorType) {
    this.extractorType = extractorType;
  }

  public TransformType getTransformType() {
    return transformType;
  }

  public void setTransformType(TransformType transformType) {
    this.transformType = transformType;
  }

  public Long getPropId() {
    return propId;
  }

  public void setPropId(Long propId) {
    this.propId = propId;
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

  @Override
  public ExtraConfig clone() throws CloneNotSupportedException {
    ExtraConfig extraConfig = (ExtraConfig) super.clone();
    return extraConfig;
  }

  public enum ExtractorInput {
    DEFAULT, URL, REFER;

    public static List<String> list() {
      List<String> result = new ArrayList<>();
      ExtractorInput[] array = ExtractorInput.values();
      for (ExtractorInput e : array) {
        result.add(e.name());
      }
      return result;
    }
  }

  public enum ExtractorType {
    HTML, JSON, XML, REGEX, CONST, TEMPLATE, TIME, MAP, UNIT, NEXTPAGE;

    public static List<String> list() {
      List<String> result = new ArrayList<>();
      ExtractorType[] array = ExtractorType.values();
      for (ExtractorType e : array) {
        result.add(e.name());
      }
      return result;
    }
  }

  public enum TransformType {
    ONE_TO_ONE, ONE_TO_MANY;

    public static List<String> list() {
      List<String> result = new ArrayList<>();
      TransformType[] array = TransformType.values();
      for (TransformType t : array) {
        result.add(t.name());
      }
      return result;
    }
  }
}
