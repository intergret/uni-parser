package com.local.labs.parser.engine.core;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.local.labs.parser.common.model.State;
import com.local.labs.parser.common.model.parser.ParserTask;
import com.local.labs.parser.common.model.parser.rule.Rule;
import com.local.labs.parser.common.model.parser.rule.RuleTree;
import com.local.labs.parser.service.RuleService;
import com.local.labs.parser.service.RuleTreeService;

/**
 * Author: Xing Wang <wangxing.bjtu@gmail.com>
 * Date: 2017-03-24 Time: 09:53
 */
@Service
public class RuleLoader {

  @Autowired
  private RuleTreeService ruleTreeService;

  @Autowired
  private RuleService ruleService;

  public RuleTree loadRuleByTask(ParserTask task) {
    if (task == null) {
      return null;
    }

    Rule rule = getRuleForParse(task);
    if (rule != null) {
      return ruleTreeService.getRuleTree(rule);
    }
    return null;
  }

  public Rule getRuleForParse(ParserTask task) {
    Rule parserRule = null;
    List<Rule> rules = getRules(State.ENABLE);
    for (Rule rule : rules) {
      if (isUrlMatchRule(task.getUrl(), rule)) {
        parserRule = rule;
        break;
      }
    }
    return parserRule;
  }

  public List<Rule> getRules(State state) {
    List<Rule> rules = new ArrayList<>();
    List<Rule> allRules = ruleService.listAll();
    for (Rule rule : allRules) {
      if (rule.getState() == state) {
        rules.add(rule);
      }
    }
    return rules;
  }

  private boolean isUrlMatchRule(String url, Rule rule) {
    String pattern = rule.getPattern();
    if (StringUtils.isBlank(pattern)) {
      return false;
    } else {
      Pattern p = Pattern.compile(pattern);
      Matcher matcher = p.matcher(url);
      return matcher.find();
    }
  }
}
