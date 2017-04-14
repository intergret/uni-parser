package com.local.labs.parser.engine.extractor;

import java.util.List;

public interface Extractor {

  String extract(String input, String cond, String value);

  List<String> extractMulti(String input, String cond, String value);

}
