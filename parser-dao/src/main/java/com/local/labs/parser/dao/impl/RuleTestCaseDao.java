package com.local.labs.parser.dao.impl;

import java.util.Map;

import com.local.labs.parser.common.model.parser.rule.RuleTestCase;

public interface RuleTestCaseDao {

  int delete(Map<String, Object> where);

  RuleTestCase get(Long ruleId);

  void upsert(RuleTestCase testCase);

}
