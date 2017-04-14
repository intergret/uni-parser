package com.local.labs.parser.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.local.labs.parser.common.model.crawler.HttpMethod;
import com.local.labs.parser.common.model.parser.rule.RuleTestCase;
import com.local.labs.parser.dao.Tables;

public class RuleTestCaseRowMapper implements RowMapper<RuleTestCase> {

  @Override
  public RuleTestCase mapRow(ResultSet rs, int rowNum) throws SQLException {
    RuleTestCase ruleTestCase = new RuleTestCase();
    ruleTestCase.setId(rs.getLong(Tables.RULE_TEST_CASE_COLUMNS.ID));
    ruleTestCase.setRuleId(rs.getLong(Tables.RULE_TEST_CASE_COLUMNS.RULE_ID));
    ruleTestCase.setUrl(rs.getString(Tables.RULE_TEST_CASE_COLUMNS.URL));
    ruleTestCase.setTestContent(rs.getString(Tables.RULE_TEST_CASE_COLUMNS.TEST_CONTENT));
    ruleTestCase.setRefer(rs.getString(Tables.RULE_TEST_CASE_COLUMNS.REFER));
    ruleTestCase.setReferInfo(rs.getString(Tables.RULE_TEST_CASE_COLUMNS.REFER_INFO));
    ruleTestCase.setHttpMethod(HttpMethod.valueOf(rs.getString(Tables.RULE_TEST_CASE_COLUMNS.HTTP_METHOD)));
    ruleTestCase.setHeaders(rs.getString(Tables.RULE_TEST_CASE_COLUMNS.HEADERS));
    ruleTestCase.setForms(rs.getString(Tables.RULE_TEST_CASE_COLUMNS.FORMS));
    return ruleTestCase;
  }
}