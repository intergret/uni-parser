package com.local.labs.parser.common.model.crawler;

import java.io.Serializable;
import java.util.Map;

public class HttpResponse implements Serializable {

  private String url;

  private Map<String, String> params;

  private Map<String, String> forms;

  private Map<String, String> headers;

  private byte[] data;

  private int statusCode;

  private String statusDescription;

  private Proxy proxy;

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Map<String, String> getParams() {
    return params;
  }

  public void setParams(Map<String, String> params) {
    this.params = params;
  }

  public Map<String, String> getForms() {
    return forms;
  }

  public void setForms(Map<String, String> forms) {
    this.forms = forms;
  }

  public Map<String, String> getHeaders() {
    return headers;
  }

  public void setHeaders(Map<String, String> headers) {
    this.headers = headers;
  }

  public byte[] getData() {
    return data;
  }

  public void setData(byte[] data) {
    this.data = data;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }

  public String getStatusDescription() {
    return statusDescription;
  }

  public void setStatusDescription(String statusDescription) {
    this.statusDescription = statusDescription;
  }

  public Proxy getProxy() {
    return proxy;
  }

  public void setProxy(Proxy proxy) {
    this.proxy = proxy;
  }
}
