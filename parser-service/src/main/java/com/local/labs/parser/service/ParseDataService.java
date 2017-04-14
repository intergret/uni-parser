package com.local.labs.parser.service;

import com.local.labs.parser.common.model.parser.ParseData;

public interface ParseDataService {

  ParseData get(String url);

  void store(String url, ParseData parseData);

}
