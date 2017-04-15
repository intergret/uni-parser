package com.local.labs.parser.common.model.crawler;

import java.util.ArrayList;
import java.util.List;

public enum HttpMethod {
  GET, POST;

  public static List<String> list() {
    List<String> result = new ArrayList<>();
    HttpMethod[] array = HttpMethod.values();
    for (HttpMethod h : array) {
      result.add(h.name());
    }
    return result;
  }
}
