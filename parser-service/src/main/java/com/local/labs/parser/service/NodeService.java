package com.local.labs.parser.service;

import java.util.List;

import com.local.labs.parser.common.model.parser.rule.Node;

public interface NodeService {

  Node get(Long id);

  void update(Node node);

  long insert(Node node);

  void delete(Node node);

  List<Node> listByRule(Long ruleId);

}
