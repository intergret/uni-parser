package com.local.labs.parser.common.model.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Xing Wang <wangxing.bjtu@gmail.com>
 * Date: 2017-03-24 Time: 09:52
 */
public class NodeResult {

  private Map<String,Object> data;

  private Map<String,String> headers;

  private Map<String,String> forms;

  private Map<String, String> failures;

  public NodeResult() {
    this.data = new HashMap<>();
    this.headers = new HashMap<>();
    this.forms = new HashMap<>();
    this.failures = new HashMap<>();
  }

  public Map<String, Object> getData() {
    return data;
  }

  public void setData(Map<String, Object> data) {
    this.data = data;
  }

  public Map<String, String> getHeaders() {
    return headers;
  }

  public void setHeaders(Map<String, String> headers) {
    this.headers = headers;
  }

  public Map<String, String> getForms() {
    return forms;
  }

  public void setForms(Map<String, String> forms) {
    this.forms = forms;
  }

  public Map<String, String> getFailures() {
    return failures;
  }

  public void setFailures(Map<String, String> failures) {
    this.failures = failures;
  }

  public void addData(String field, Object value) {
    // combine multiply value as a list
    Object valueExist = this.data.get(field);
    if (valueExist == null) {
      this.data.put(field, value);
    } else if (valueExist instanceof List) {
      if (value instanceof List) {
        ((List) this.data.get(field)).addAll((List) value);
      } else {
        ((List) this.data.get(field)).add(value);
      }
    } else {
      this.data.put(field, new ArrayList<>());
      ((List) this.data.get(field)).add(valueExist);
      if (value instanceof List) {
        ((List) this.data.get(field)).addAll((List) value);
      } else {
        ((List) this.data.get(field)).add(value);
      }
    }
  }

  public boolean isEmpty() {
    return data.isEmpty() && failures.isEmpty();
  }
}
