package com.local.labs.parser.common.model.parser;

import java.util.ArrayList;
import java.util.List;

public enum PageType {
  LIST, DETAIL, BINARY, SEED;

  public static List<String> list() {
    List<String> result = new ArrayList<>();
    PageType[] array = PageType.values();
    for (PageType p : array) {
      result.add(p.name());
    }
    return result;
  }
}
