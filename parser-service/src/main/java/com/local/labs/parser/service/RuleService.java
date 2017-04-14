package com.local.labs.parser.service;

import java.util.List;

import com.local.labs.parser.common.model.parser.rule.Rule;
import com.local.labs.parser.common.model.parser.rule.RuleTestCase;

public interface RuleService {

  List<Rule> listAll();

  Rule get(Long id);

  void update(Rule rule);

  void insert(Rule rule);

  void delete(Rule rule);

  RuleTestCase getTestCase(Long ruleId);

  void upsertTestCase(RuleTestCase testCase);

}
