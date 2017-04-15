package com.local.labs.parser.dao.impl;

import java.util.List;
import java.util.Map;

import com.local.labs.parser.common.model.parser.rule.ExtraConfig;

public interface ExtraConfigDao {

  boolean exists(Map<String,Object> where);

  int delete(Map<String,Object> where);

  ExtraConfig get(Long id);

  List<ExtraConfig> listByProp(long propId);

  long insert(ExtraConfig extraConfig);

  void update(ExtraConfig extraConfig);

}
