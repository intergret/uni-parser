package com.local.labs.parser.engine.core;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.local.labs.parser.common.model.parser.ParseData;
import com.local.labs.parser.common.model.parser.ParserTask;
import com.local.labs.parser.common.model.parser.rule.RuleTree;

/**
 * Author: Xing Wang <wangxing.bjtu@gmail.com>
 * Date: 2017-03-24 Time: 09:35
 */
public abstract class AbstractParser implements Parser {

  private static final Logger LOG = LoggerFactory.getLogger(AbstractParser.class);

  @Autowired
  private RuleLoader rulesLoader;

  @Override
  public ParseData parse(ParserTask task) throws IOException {
    LOG.info("Parser begin {}", task.getUrl());
    RuleTree ruleTree = rulesLoader.loadRuleByTask(task);
    if (ruleTree == null || ruleTree.getRule() == null) {
      LOG.info("failed to load parse rule for {}", task.getUrl());
      return null;
    }

    ParseData parseData = this.doParse(task, ruleTree);
    if (parseData != null) {
      this.doStore(task, parseData);
    }
    return parseData;
  }

  protected abstract ParseData doParse(ParserTask task, RuleTree ruleTree) throws IOException;

  protected abstract void doStore(ParserTask task, ParseData parseData) throws IOException;

}
