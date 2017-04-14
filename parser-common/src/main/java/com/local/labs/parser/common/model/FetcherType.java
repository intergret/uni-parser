package com.local.labs.parser.common.model;

import java.util.ArrayList;
import java.util.List;

import com.local.labs.parser.common.model.crawler.HttpMethod;

public enum FetcherType {
  GET, POST, HEAD, PUT;
  public static List<String> list() {
    List<String> result = new ArrayList<>();
    HttpMethod[] array = HttpMethod.values();
    for (HttpMethod h : array) {
      result.add(h.name());
    }
    return result;
  }
}
