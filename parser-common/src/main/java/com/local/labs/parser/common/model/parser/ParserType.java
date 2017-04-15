package com.local.labs.parser.common.model.parser;

import java.util.ArrayList;
import java.util.List;

public enum ParserType {
  HTML, XML, JSON, BINARY;

  public static List<String> list() {
    List<String> result = new ArrayList<>();
    ParserType[] array = ParserType.values();
    for (ParserType p : array) {
      result.add(p.name());
    }
    return result;
  }
}
