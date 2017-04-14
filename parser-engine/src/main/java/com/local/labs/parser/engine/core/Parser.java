package com.local.labs.parser.engine.core;

import java.io.IOException;

import com.local.labs.parser.common.model.parser.ParseData;
import com.local.labs.parser.common.model.parser.ParserTask;

public interface Parser {

  ParseData parse(ParserTask task) throws IOException;

}
