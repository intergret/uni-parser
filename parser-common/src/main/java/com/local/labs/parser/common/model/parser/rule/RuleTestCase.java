package com.local.labs.parser.common.model.parser.rule;

import com.local.labs.parser.common.model.crawler.HttpMethod;

public class RuleTestCase {

  private Long id;

  private Long ruleId;

  private String url;

  private String testContent;

  private String refer;

  private String referInfo;

  private HttpMethod httpMethod;

  private String headers;

  private String forms;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getRuleId() {
    return ruleId;
  }

  public void setRuleId(Long ruleId) {
    this.ruleId = ruleId;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getTestContent() {
    return testContent;
  }

  public void setTestContent(String testContent) {
    this.testContent = testContent;
  }

  public String getRefer() {
    return refer;
  }

  public void setRefer(String refer) {
    this.refer = refer;
  }

  public String getReferInfo() {
    return referInfo;
  }

  public void setReferInfo(String referInfo) {
    this.referInfo = referInfo;
  }

  public HttpMethod getHttpMethod() {
    return httpMethod;
  }

  public void setHttpMethod(HttpMethod httpMethod) {
    this.httpMethod = httpMethod;
  }

  public String getHeaders() {
    return headers;
  }

  public void setHeaders(String headers) {
    this.headers = headers;
  }

  public String getForms() {
    return forms;
  }

  public void setForms(String forms) {
    this.forms = forms;
  }
}
