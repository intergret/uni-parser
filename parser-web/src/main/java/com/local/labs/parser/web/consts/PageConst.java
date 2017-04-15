package com.local.labs.parser.web.consts;

import java.util.List;

import com.local.labs.parser.common.model.State;
import com.local.labs.parser.common.model.crawler.HttpMethod;
import com.local.labs.parser.common.model.parser.PageType;
import com.local.labs.parser.common.model.parser.ParserType;
import com.local.labs.parser.common.model.parser.rule.ExtraConfig;
import com.local.labs.parser.common.model.parser.rule.Prop;

public class PageConst {

  public static final Integer PAGE_SIZE = 20;

  public static final List<String> STATE_LIST = State.list();

  public static final List<String> PARSER_TYPE_LIST = ParserType.list();

  public static final List<String> HTTP_METHOD_LIST = HttpMethod.list();

  public static final List<String> PAGE_TYPE_LIST = PageType.list();

  public static final List<String> EXTRACTOR_INPUT_TYPE_LIST = ExtraConfig.ExtractorInput.list();

  public static final List<String> EXTRACTOR_TYPE_LIST = ExtraConfig.ExtractorType.list();

  public static final List<String> TRANSFORM_TYPE_LIST = ExtraConfig.TransformType.list();

  public static final List<String> RESULT_TYPE_LIST = Prop.ResultType.list();

}