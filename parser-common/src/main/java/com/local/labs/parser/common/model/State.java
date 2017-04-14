package com.local.labs.parser.common.model;

import java.util.ArrayList;
import java.util.List;

public enum State {
  ENABLE, DISABLE;

  public static List<String> list() {
    List<String> result = new ArrayList<>();
    State[] array = State.values();
    for (State s : array) {
      result.add(s.name());
    }
    return result;
  }
}
