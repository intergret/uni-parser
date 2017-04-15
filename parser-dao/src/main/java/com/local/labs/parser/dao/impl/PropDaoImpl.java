package com.local.labs.parser.dao.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.local.labs.parser.common.model.parser.rule.Prop;
import com.local.labs.parser.common.sql.Sql;
import com.local.labs.parser.common.sql.SqlBuilder;
import com.local.labs.parser.dao.Tables;
import com.local.labs.parser.dao.mapper.PropRowMapper;

/**
 * Author: Xing Wang <wangxing.bjtu@gmail.com>
 * Date: 2017-03-23 Time: 19:31
 */
@Repository
public class PropDaoImpl extends AbstractDao implements PropDao {

  private final static String[] PROP_FIELDS = {Tables.PROP_COLUMNS.ID, Tables.PROP_COLUMNS.LABEL,
      Tables.PROP_COLUMNS.IS_REQUIRED, Tables.PROP_COLUMNS.IS_MULTIPLY, Tables.PROP_COLUMNS.RESULT_TYPE,
      Tables.PROP_COLUMNS.HTTP_METHOD, Tables.PROP_COLUMNS.PARSER_TYPE,
      Tables.PROP_COLUMNS.NODE_ID, Tables.PROP_COLUMNS.RULE_ID};

  private final static String DEFAULT_QUERY_PREFIX;

  static {
    StringBuilder sb = new StringBuilder();
    sb.append("SELECT " + StringUtils.join(PROP_FIELDS, ", ") + " FROM ");
    DEFAULT_QUERY_PREFIX = sb.toString();
  }

  @Override
  protected String getTableName() {
    return Tables.PROP_TABLE;
  }

  @Override
  public List<Prop> listByNode(Long nodeId) {
    Map<String,Object> where = new HashMap<>();
    where.put(Tables.PROP_COLUMNS.NODE_ID, nodeId);
    Sql sql = SqlBuilder.buildSelect(getTableName(), PROP_FIELDS, where);
    return getJdbcTemplate().query(sql.getSql(), sql.getParams(), new PropRowMapper());
  }

  @Override
  public Prop getProp(Long id) {
    String sql = DEFAULT_QUERY_PREFIX + getTableName() + " WHERE " + Tables.PROP_COLUMNS.ID + " = ?";
    List<Prop> props = getJdbcTemplate().query(sql, new Object[] {id}, new PropRowMapper());
    return CollectionUtils.isNotEmpty(props) ? props.get(0) : null;
  }

  @Override
  public int delete(Long id) {
    return delete(Collections.singletonMap(Tables.PROP_COLUMNS.ID, (Object) id));
  }

  @Override
  public void update(Prop prop) {
    Map<String,Object> data = new HashMap<>();
    data.put(Tables.PROP_COLUMNS.LABEL, prop.getLabel());
    data.put(Tables.PROP_COLUMNS.IS_REQUIRED, prop.getIsRequired());
    data.put(Tables.PROP_COLUMNS.IS_MULTIPLY, prop.getIsMultiply());
    data.put(Tables.PROP_COLUMNS.RESULT_TYPE, prop.getResultType().name());
    data.put(Tables.PROP_COLUMNS.HTTP_METHOD, prop.getHttpMethod());
    data.put(Tables.PROP_COLUMNS.PARSER_TYPE, prop.getParserType().name());
    data.put(Tables.PROP_COLUMNS.NODE_ID, prop.getNodeId());
    data.put(Tables.PROP_COLUMNS.RULE_ID, prop.getRuleId());
    if (prop.getId() != null) {
      Map<String,Object> where = new HashMap<>();
      where.put(Tables.PROP_COLUMNS.ID, prop.getId());
      update(data, where);
    }
  }

  @Override
  public long insert(Prop prop) {
    Map<String,Object> data = new HashMap<>();
    data.put(Tables.PROP_COLUMNS.LABEL, prop.getLabel());
    data.put(Tables.PROP_COLUMNS.IS_REQUIRED, prop.getIsRequired());
    data.put(Tables.PROP_COLUMNS.IS_MULTIPLY, prop.getIsMultiply());
    data.put(Tables.PROP_COLUMNS.RESULT_TYPE, prop.getResultType().name());
    data.put(Tables.PROP_COLUMNS.HTTP_METHOD, prop.getHttpMethod());
    data.put(Tables.PROP_COLUMNS.PARSER_TYPE, prop.getParserType().name());
    data.put(Tables.PROP_COLUMNS.NODE_ID, prop.getNodeId());
    data.put(Tables.PROP_COLUMNS.RULE_ID, prop.getRuleId());
    insert(data, false);
    return getMaxId();
  }
}
