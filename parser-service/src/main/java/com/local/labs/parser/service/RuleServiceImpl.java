package com.local.labs.parser.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.local.labs.parser.common.model.parser.rule.Rule;
import com.local.labs.parser.common.model.parser.rule.RuleTestCase;
import com.local.labs.parser.dao.Tables;
import com.local.labs.parser.dao.impl.ExtraConfigDao;
import com.local.labs.parser.dao.impl.NodeDao;
import com.local.labs.parser.dao.impl.PropDao;
import com.local.labs.parser.dao.impl.RuleDao;
import com.local.labs.parser.dao.impl.RuleTestCaseDao;

/**
 * Author: Xing Wang <wangxing.bjtu@gmail.com>
 * Date: 2017-01-23 Time: 16:34
 */
@Service
public class RuleServiceImpl implements RuleService {

  @Autowired
  private RuleDao ruleDao;

  @Autowired
  private NodeDao nodeDao;

  @Autowired
  private PropDao propDao;

  @Autowired
  private ExtraConfigDao extraConfigDao;

  @Autowired
  private RuleTestCaseDao ruleTestCaseDao;

  @Override
  public void insert(Rule rule) {
    ruleDao.insert(rule);
  }

  @Override
  public List<Rule> listAll() {
    return ruleDao.getAll();
  }

  @Override
  public Rule get(Long id) {
    return ruleDao.getRule(id);
  }

  @Override
  public void update(Rule rule) {
    ruleDao.update(rule);
  }

  @Override
  public void delete(Rule rule) {
    Map<String,Object> where = new HashMap<>();
    where.put(Tables.RULE_COLUMNS.ID, rule.getId());
    ruleDao.delete(where);

    where = new HashMap<>();
    where.put(Tables.NODE_COLUMNS.RULE_ID, rule.getId());
    nodeDao.delete(where);

    where = new HashMap<>();
    where.put(Tables.PROP_COLUMNS.RULE_ID, rule.getId());
    propDao.delete(where);

    where = new HashMap<>();
    where.put(Tables.EXTRA_CONFIG_COLUMNS.RULE_ID, rule.getId());
    extraConfigDao.delete(where);

    where = new HashMap<>();
    where.put(Tables.RULE_TEST_CASE_COLUMNS.RULE_ID, rule.getId());
    ruleTestCaseDao.delete(where);
  }

  public RuleTestCase getTestCase(Long ruleId) {
    return ruleTestCaseDao.get(ruleId);
  }

  @Override
  public void upsertTestCase(RuleTestCase testCase) {
    ruleTestCaseDao.upsert(testCase);
  }
}