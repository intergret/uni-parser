package com.local.labs.parser.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.local.labs.parser.common.model.parser.rule.Node;
import com.local.labs.parser.dao.Tables;

public class NodeRowMapper implements RowMapper<Node> {
  @Override
  public Node mapRow(ResultSet rs, int rowNum) throws SQLException {
    Node node = new Node();
    node.setId(rs.getLong(Tables.NODE_COLUMNS.ID));
    node.setLabel(rs.getString(Tables.NODE_COLUMNS.LABEL));
    node.setNodeType(rs.getString(Tables.NODE_COLUMNS.NODE_TYPE));
    node.setParentNode(rs.getLong(Tables.NODE_COLUMNS.PARENT_NODE));
    node.setExtraConfigId(rs.getLong(Tables.NODE_COLUMNS.EXTRA_CONFIG_ID));
    node.setRuleId(rs.getLong(Tables.NODE_COLUMNS.RULE_ID));
    return node;
  }
}
