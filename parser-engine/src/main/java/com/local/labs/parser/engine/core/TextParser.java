package com.local.labs.parser.engine.core;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.local.labs.parser.common.model.parser.ParseData;
import com.local.labs.parser.common.model.parser.ParserTask;
import com.local.labs.parser.common.model.parser.rule.RuleTree;
import com.local.labs.parser.service.ParseDataService;

/**
 * Author: Xing Wang <wangxing.bjtu@gmail.com>
 * Date: 2017-03-24 Time: 09:48
 */
@Component
public class TextParser extends AbstractParser {

  private static final Logger LOG = LoggerFactory.getLogger(TextParser.class);

  @Autowired
  private ParseDataService parseDataService;

  @Autowired
  private RuleWalker rulesWalker;

  @Override
  protected ParseData doParse(ParserTask task, RuleTree ruleTree) throws IOException {
    return rulesWalker.walk(task, ruleTree);
  }

  @Override
  protected void doStore(ParserTask task, ParseData parseData) throws IOException {
    parseDataService.store(task.getUrl(), parseData);
  }
}