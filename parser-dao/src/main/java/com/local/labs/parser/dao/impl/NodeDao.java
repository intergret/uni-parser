package com.local.labs.parser.dao.impl;

import java.util.List;
import java.util.Map;

import com.local.labs.parser.common.model.parser.rule.Node;

public interface NodeDao {

  boolean exists(Map<String,Object> where);

  int delete(Map<String,Object> where);

  List<Node> listByRule(Long ruleId);

  Node getNode(Long id);

  int delete(long nodeId);

  long insert(Node node);

  void update(Node node);

}
