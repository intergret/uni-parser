package com.local.labs.parser.common.model.crawler;

import java.util.Map;

public class HttpRequest {

  private String url;

  private String referer;

  private Map<String,String> forms;

  private HttpMethod httpMethod;

  public HttpRequest() {
    super();
  }

  public HttpRequest(String url) {
    this.url = url;
  }

  public HttpRequest(String url, String referer) {
    this.url = url;
    this.referer = referer;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getReferer() {
    return referer;
  }

  public void setReferer(String referer) {
    this.referer = referer;
  }

  public Map<String,String> getForms() {
    return forms;
  }

  public void setForms(Map<String,String> forms) {
    this.forms = forms;
  }

  public HttpMethod getHttpMethod() {
    return httpMethod;
  }

  public void setHttpMethod(HttpMethod httpMethod) {
    this.httpMethod = httpMethod;
  }
}
