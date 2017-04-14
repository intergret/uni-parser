package com.local.labs.parser.dao.impl;

import java.util.List;
import java.util.Map;

import com.local.labs.parser.common.model.parser.rule.Prop;

public interface PropDao {

  long insert(Prop prop);

  void update(Prop prop);

  boolean exists(Map<String, Object> where);

  int delete(Map<String, Object> where);

  List<Prop> listByNode(Long nodeId);

  Prop getProp(Long id);

  int delete(Long id);

}
