package com.local.labs.parser.dao.impl;

import java.util.List;
import java.util.Map;

import com.local.labs.parser.common.model.parser.rule.Rule;

public interface RuleDao {

  void insert(Rule rule);

  void update(Rule rule);

  boolean exists(Map<String, Object> where);

  int delete(Map<String, Object> where);

  int delete(long id);

  List<Rule> getAll();

  List<Rule> getRules(Map<String, Object> where);

  List<Rule> getRules(Map<String, Object> where, List<String> tails);

  List<Rule> getAll(Integer pageIndexNum, Integer pageSize);

  Rule getRule(Long id);

}
