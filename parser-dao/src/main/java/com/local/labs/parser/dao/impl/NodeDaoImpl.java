package com.local.labs.parser.dao.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.local.labs.parser.common.model.parser.rule.Node;
import com.local.labs.parser.common.sql.Sql;
import com.local.labs.parser.common.sql.SqlBuilder;
import com.local.labs.parser.dao.Tables;
import com.local.labs.parser.dao.mapper.NodeRowMapper;

/**
 * Author: Xing Wang <wangxing.bjtu@gmail.com>
 * Date: 2017-03-23 Time: 18:21
 */
@Repository
public class NodeDaoImpl extends AbstractDao implements NodeDao {

  private final static String[] NODE_FIELDS = {Tables.NODE_COLUMNS.ID, Tables.NODE_COLUMNS.LABEL,
      Tables.NODE_COLUMNS.NODE_TYPE, Tables.NODE_COLUMNS.EXTRA_CONFIG_ID, Tables.NODE_COLUMNS.RULE_ID,
      Tables.NODE_COLUMNS.PARENT_NODE};

  private final static String DEFAULT_QUERY_PREFIX;

  static {
    StringBuilder sb = new StringBuilder();
    sb.append("SELECT " + StringUtils.join(NODE_FIELDS, ", ") + " FROM ");
    DEFAULT_QUERY_PREFIX = sb.toString();
  }

  @Override
  protected String getTableName() {
    return Tables.NODE_TABLE;
  }

  @Override
  public List<Node> listByRule(Long ruleId) {
    Map<String,Object> where = new HashMap<>();
    where.put(Tables.NODE_COLUMNS.RULE_ID, ruleId);
    Sql sql = SqlBuilder.buildSelect(getTableName(), NODE_FIELDS, where);
    return getJdbcTemplate().query(sql.getSql(), sql.getParams(), new NodeRowMapper());
  }

  @Override
  public Node getNode(Long id) {
    String sql = DEFAULT_QUERY_PREFIX + getTableName() + " WHERE " + Tables.NODE_COLUMNS.ID + " = ?";
    List<Node> nodes = getJdbcTemplate().query(sql, new Object[] {id}, new NodeRowMapper());
    return CollectionUtils.isNotEmpty(nodes) ? nodes.get(0) : null;
  }

  @Override
  public int delete(long nodeId) {
    return super.delete(Collections.singletonMap(Tables.NODE_COLUMNS.ID, (Object) nodeId));
  }

  @Override
  public long insert(Node node) {
    Map<String,Object> data = new HashMap<>();
    data.put(Tables.NODE_COLUMNS.LABEL, node.getLabel());
    data.put(Tables.NODE_COLUMNS.NODE_TYPE, node.getNodeType());
    data.put(Tables.NODE_COLUMNS.PARENT_NODE, node.getParentNode());
    data.put(Tables.NODE_COLUMNS.RULE_ID, node.getRuleId());
    data.put(Tables.NODE_COLUMNS.EXTRA_CONFIG_ID, node.getExtraConfigId());
    insert(data, false);
    return getMaxId();
  }

  @Override
  public void update(Node node) {
    Map<String,Object> data = new HashMap<>();
    data.put(Tables.NODE_COLUMNS.LABEL, node.getLabel());
    data.put(Tables.NODE_COLUMNS.NODE_TYPE, node.getNodeType());
    data.put(Tables.NODE_COLUMNS.PARENT_NODE, node.getParentNode());
    data.put(Tables.NODE_COLUMNS.RULE_ID, node.getRuleId());
    data.put(Tables.NODE_COLUMNS.EXTRA_CONFIG_ID, node.getExtraConfigId());
    if (node.getId() != null) {
      Map<String,Object> where = new HashMap<>();
      where.put(Tables.NODE_COLUMNS.ID, node.getId());
      update(data, where);
    }
  }
}
