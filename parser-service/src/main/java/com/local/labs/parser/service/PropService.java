package com.local.labs.parser.service;

import java.util.List;

import com.local.labs.parser.common.model.parser.rule.Prop;

public interface PropService {

  Prop get(Long id);

  void update(Prop prop);

  long insert(Prop prop);

  void delete(Prop prop);

  List<Prop> listByNode(Long nodeId);

}
