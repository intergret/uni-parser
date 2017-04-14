package com.local.labs.parser.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.local.labs.parser.common.model.State;
import com.local.labs.parser.common.model.parser.PageType;
import com.local.labs.parser.common.model.parser.ParserType;
import com.local.labs.parser.common.model.parser.rule.Rule;
import com.local.labs.parser.dao.Tables;

public class RuleRowMapper implements RowMapper<Rule> {

  @Override
  public Rule mapRow(ResultSet rs, int rowNum) throws SQLException {
    Rule rule = new Rule();
    rule.setId(rs.getLong(Tables.RULE_COLUMNS.ID));
    rule.setPattern(rs.getString(Tables.RULE_COLUMNS.PATTERN));
    rule.setInstance(rs.getString(Tables.RULE_COLUMNS.INSTANCE));
    rule.setParserType(ParserType.valueOf(rs.getString(Tables.RULE_COLUMNS.PARSER_TYPE).toUpperCase()));
    rule.setPageType(PageType.valueOf(rs.getString(Tables.RULE_COLUMNS.PAGE_TYPE).toUpperCase()));
    rule.setState(State.valueOf(rs.getString(Tables.RULE_COLUMNS.STATE).toUpperCase()));
    rule.setDescription(rs.getString(Tables.RULE_COLUMNS.DESCRIPTION));
    rule.setAuthor(rs.getString(Tables.RULE_COLUMNS.AUTHOR));
    return rule;
  }
}
