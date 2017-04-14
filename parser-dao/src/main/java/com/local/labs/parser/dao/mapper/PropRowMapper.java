package com.local.labs.parser.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.local.labs.parser.common.model.parser.ParserType;
import com.local.labs.parser.common.model.parser.rule.Prop;
import com.local.labs.parser.dao.Tables;

public class PropRowMapper implements RowMapper<Prop> {

  @Override
  public Prop mapRow(ResultSet rs, int rowNum) throws SQLException {
    Prop prop = new Prop();
    prop.setId(rs.getLong(Tables.PROP_COLUMNS.ID));
    prop.setLabel(rs.getString(Tables.PROP_COLUMNS.LABEL));
    prop.setIsRequired(rs.getBoolean(Tables.PROP_COLUMNS.IS_REQUIRED));
    prop.setIsMultiply(rs.getBoolean(Tables.PROP_COLUMNS.IS_MULTIPLY));
    prop.setResultType(Prop.ResultType.valueOf(rs.getString(Tables.PROP_COLUMNS.RESULT_TYPE).toUpperCase()));
    prop.setHttpMethod(rs.getString(Tables.PROP_COLUMNS.HTTP_METHOD));
    prop.setParserType(ParserType.valueOf(rs.getString(Tables.PROP_COLUMNS.PARSER_TYPE).toUpperCase()));
    prop.setScopeType(Prop.ScopeType.valueOf(rs.getString(Tables.PROP_COLUMNS.SCOPE_TYPE).toUpperCase()));
    prop.setNodeId(rs.getLong(Tables.PROP_COLUMNS.NODE_ID));
    return prop;
  }
}
