package com.local.labs.parser.dao.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.local.labs.parser.common.model.parser.rule.Rule;
import com.local.labs.parser.common.sql.Sql;
import com.local.labs.parser.common.sql.SqlBuilder;
import com.local.labs.parser.dao.Tables;
import com.local.labs.parser.dao.mapper.RuleRowMapper;

/**
 * Author: Xing Wang <wangxing.bjtu@gmail.com>
 * Date: 2017-03-23 Time: 22:31
 */
@Repository
public class RuleDaoImpl extends AbstractDao implements RuleDao {

  private final static String SQL_OFFSET = " LIMIT ?,? ";

  private final static String[] RULE_FIELDS = {Tables.RULE_COLUMNS.ID, Tables.RULE_COLUMNS.PATTERN,
      Tables.RULE_COLUMNS.INSTANCE, Tables.RULE_COLUMNS.PARSER_TYPE, Tables.RULE_COLUMNS.PAGE_TYPE,
      Tables.RULE_COLUMNS.STATE, Tables.RULE_COLUMNS.DESCRIPTION, Tables.RULE_COLUMNS.AUTHOR};

  private final static String DEFAULT_QUERY_PREFIX;

  static {
    StringBuilder sb = new StringBuilder();
    sb.append("SELECT " + StringUtils.join(RULE_FIELDS, ", ") + " FROM ");
    DEFAULT_QUERY_PREFIX = sb.toString();
  }

  @Override
  protected String getTableName() {
    return Tables.RULE_TABLE;
  }

  @Override
  public int delete(long id) {
    return delete(Collections.singletonMap(Tables.RULE_COLUMNS.ID, (Object) id));
  }

  @Override
  public List<Rule> getAll() {
    return getRules(null, null);
  }

  @Override
  public List<Rule> getRules(Map<String,Object> where) {
    Sql sql = SqlBuilder.buildSelect(getTableName(), RULE_FIELDS, where);
    return getJdbcTemplate().query(sql.getSql(), sql.getParams(), new RuleRowMapper());
  }

  @Override
  public List<Rule> getRules(Map<String,Object> where, List<String> tails) {
    Sql sql = SqlBuilder.buildSelect(getTableName(), RULE_FIELDS, where, tails);
    return getJdbcTemplate().query(sql.getSql(), sql.getParams(), new RuleRowMapper());
  }

  public List<Rule> getAll(Integer pageIndexNum, Integer pageSize) {
    String sql = DEFAULT_QUERY_PREFIX + getTableName() + SQL_OFFSET;
    getJdbcTemplate().execute("SET @c=1;");
    return getJdbcTemplate().query(sql, new Object[] {pageIndexNum, pageSize}, new RuleRowMapper());
  }

  @Override
  public Rule getRule(Long id) {
    String sql = DEFAULT_QUERY_PREFIX + getTableName() + " WHERE " + Tables.RULE_COLUMNS.ID + " = ?";
    List<Rule> rules = getJdbcTemplate().query(sql, new Object[] {id}, new RuleRowMapper());
    return CollectionUtils.isNotEmpty(rules) ? rules.get(0) : null;
  }

  @Override
  public void update(Rule rule) {
    Map<String,Object> data = new HashMap<>();
    data.put(Tables.RULE_COLUMNS.PATTERN, rule.getPattern());
    data.put(Tables.RULE_COLUMNS.INSTANCE, rule.getInstance());
    data.put(Tables.RULE_COLUMNS.PARSER_TYPE, rule.getParserType().name());
    data.put(Tables.RULE_COLUMNS.PAGE_TYPE, rule.getPageType().name());
    data.put(Tables.RULE_COLUMNS.STATE, rule.getState().name());
    data.put(Tables.RULE_COLUMNS.DESCRIPTION, rule.getDescription());
    data.put(Tables.RULE_COLUMNS.AUTHOR, rule.getAuthor());

    if (rule.getId() != null) {
      Map<String,Object> where = new HashMap<>();
      where.put(Tables.RULE_COLUMNS.ID, rule.getId());
      update(data, where);
    }
  }

  @Override
  public void insert(Rule rule) {
    Map<String,Object> data = new HashMap<>();
    data.put(Tables.RULE_COLUMNS.PATTERN, rule.getPattern());
    data.put(Tables.RULE_COLUMNS.INSTANCE, rule.getInstance());
    data.put(Tables.RULE_COLUMNS.PARSER_TYPE, rule.getParserType().name());
    data.put(Tables.RULE_COLUMNS.PAGE_TYPE, rule.getPageType().name());
    data.put(Tables.RULE_COLUMNS.STATE, rule.getState().name());
    data.put(Tables.RULE_COLUMNS.DESCRIPTION, rule.getDescription());
    data.put(Tables.RULE_COLUMNS.AUTHOR, rule.getAuthor());
    insert(data, false);
  }
}
