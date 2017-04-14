package com.local.labs.parser.service;

import com.local.labs.parser.common.model.parser.rule.Node;
import com.local.labs.parser.common.model.parser.rule.NodeTree;
import com.local.labs.parser.common.model.parser.rule.Prop;
import com.local.labs.parser.common.model.parser.rule.PropTree;
import com.local.labs.parser.common.model.parser.rule.Rule;
import com.local.labs.parser.common.model.parser.rule.RuleTree;

public interface RuleTreeService {

  RuleTree getRuleTree(Rule rule);

  NodeTree getNodeTree(Node node);

  PropTree getPropTree(Prop prop);

}
