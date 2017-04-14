package com.local.labs.parser.dao;

public class Tables {

  public static final String RULE_TABLE = "Rule";
  public static final String NODE_TABLE = "Node";
  public static final String PROP_TABLE = "Prop";
  public static final String EXTRA_CONFIG_TABLE = "ExtraConfig";
  public static final String RULE_TEST_CASE_TABLE = "RuleTestCase";

  public static final class RULE_COLUMNS {
    public static final String ID = "id";
    public static final String PATTERN = "pattern";
    public static final String INSTANCE = "instance";
    public static final String PARSER_TYPE = "parserType";
    public static final String PAGE_TYPE = "pageType";
    public static final String STATE = "state";
    public static final String DESCRIPTION = "description";
    public static final String AUTHOR = "author";
  }

  public static final class NODE_COLUMNS {
    public static final String ID = "id";
    public static final String LABEL = "label";
    public static final String NODE_TYPE = "nodeType";
    public static final String EXTRA_CONFIG_ID = "extraConfigId";
    public static final String RULE_ID = "ruleId";
    public static final String PARENT_NODE = "parentNode";
  }

  public static final class PROP_COLUMNS {
    public static final String ID = "id";
    public static final String LABEL = "label";
    public static final String IS_REQUIRED = "isRequired";
    public static final String IS_MULTIPLY = "isMultiply";
    public static final String RESULT_TYPE = "resultType";
    public static final String HTTP_METHOD = "httpMethod";
    public static final String PARSER_TYPE = "parserType";
    public static final String SCOPE_TYPE = "scopeType";
    public static final String NODE_ID = "nodeId";
    public static final String RULE_ID = "ruleId";
  }

  public static final class EXTRA_CONFIG_COLUMNS {
    public static final String ID = "id";
    public static final String INPUT_TYPE = "inputType";
    public static final String INPUT_OPTION = "inputOption";
    public static final String COND = "cond";
    public static final String VALUE = "value";
    public static final String REF_EXTRA_CONFIG_ID = "refExtraConfigId";
    public static final String EXTRACTOR_TYPE = "extractorType";
    public static final String TRANSFORM_TYPE = "transformType";
    public static final String PROP_ID = "propId";
    public static final String NODE_ID = "nodeId";
    public static final String RULE_ID = "ruleId";
  }

  public static final class RULE_TEST_CASE_COLUMNS {
    public static final String ID = "id";
    public static final String RULE_ID = "ruleId";
    public static final String URL = "url";
    public static final String TEST_CONTENT = "testContent";
    public static final String REFER = "refer";
    public static final String REFER_INFO = "referInfo";
    public static final String HTTP_METHOD = "httpMethod";
    public static final String HEADERS = "headers";
    public static final String FORMS = "forms";
  }
}
