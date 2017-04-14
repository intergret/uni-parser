package com.local.labs.parser.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.local.labs.parser.common.model.parser.rule.RuleTestCase;
import com.local.labs.parser.common.sql.Sql;
import com.local.labs.parser.common.sql.SqlBuilder;
import com.local.labs.parser.dao.Tables;
import com.local.labs.parser.dao.mapper.RuleTestCaseRowMapper;

/**
 * Author: Xing Wang <wangxing.bjtu@gmail.com>
 * Date: 2017-03-22 Time: 12:31
 */
@Repository
public class RuleTestCaseDaoImpl extends AbstractDao implements RuleTestCaseDao {

  private final static String[] RULE_TEST_CASE_FIELDS = {Tables.RULE_TEST_CASE_COLUMNS.ID,
      Tables.RULE_TEST_CASE_COLUMNS.RULE_ID, Tables.RULE_TEST_CASE_COLUMNS.URL,
      Tables.RULE_TEST_CASE_COLUMNS.TEST_CONTENT, Tables.RULE_TEST_CASE_COLUMNS.REFER,
      Tables.RULE_TEST_CASE_COLUMNS.REFER_INFO, Tables.RULE_TEST_CASE_COLUMNS.HTTP_METHOD,
      Tables.RULE_TEST_CASE_COLUMNS.HEADERS, Tables.RULE_TEST_CASE_COLUMNS.FORMS};

  @Override
  protected String getTableName() {
    return Tables.RULE_TEST_CASE_TABLE;
  }

  @Override
  public RuleTestCase get(Long ruleId) {
    Map<String,Object> where = new HashMap<>();
    where.put(Tables.RULE_TEST_CASE_COLUMNS.RULE_ID, ruleId);
    Sql sql = SqlBuilder.buildSelect(getTableName(), RULE_TEST_CASE_FIELDS, where);
    List<RuleTestCase> ruleTestCases = getJdbcTemplate().query(sql.getSql(), sql.getParams(),
        new RuleTestCaseRowMapper());
    return CollectionUtils.isNotEmpty(ruleTestCases) ? ruleTestCases.get(0) : null;
  }

  @Override
  public void upsert(RuleTestCase testCase) {
    Map<String,Object> data = new HashMap<>();
    if (testCase.getRuleId() != null) {
      data.put(Tables.RULE_TEST_CASE_COLUMNS.RULE_ID, testCase.getRuleId());
    }
    if (StringUtils.isNotEmpty(testCase.getUrl())) {
      data.put(Tables.RULE_TEST_CASE_COLUMNS.URL, testCase.getUrl());
    }
    if (testCase.getTestContent() != null) {
      data.put(Tables.RULE_TEST_CASE_COLUMNS.TEST_CONTENT, testCase.getTestContent());
    }
    if (testCase.getRefer() != null) {
      data.put(Tables.RULE_TEST_CASE_COLUMNS.REFER, testCase.getRefer());
    }
    if (testCase.getReferInfo() != null) {
      data.put(Tables.RULE_TEST_CASE_COLUMNS.REFER_INFO, testCase.getReferInfo());
    }
    if (testCase.getHttpMethod() != null) {
      data.put(Tables.RULE_TEST_CASE_COLUMNS.HTTP_METHOD, testCase.getHttpMethod().name());
    }
    if (testCase.getHeaders() != null) {
      data.put(Tables.RULE_TEST_CASE_COLUMNS.HEADERS, testCase.getHeaders());
    }
    if (testCase.getForms() != null) {
      data.put(Tables.RULE_TEST_CASE_COLUMNS.FORMS, testCase.getForms());
    }
    if (testCase.getId() != null) {
      Map<String,Object> where = new HashMap<>();
      where.put(Tables.RULE_TEST_CASE_COLUMNS.ID, testCase.getId());
      update(data, where);
    } else {
      insert(data, false);
    }
  }
}
