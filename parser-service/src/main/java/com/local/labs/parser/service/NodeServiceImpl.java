package com.local.labs.parser.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.local.labs.parser.common.model.parser.rule.Node;
import com.local.labs.parser.dao.Tables;
import com.local.labs.parser.dao.impl.ExtraConfigDao;
import com.local.labs.parser.dao.impl.NodeDao;
import com.local.labs.parser.dao.impl.PropDao;

/**
 * Author: Xing Wang <wangxing.bjtu@gmail.com>
 * Date: 2017-01-23 Time: 16:14
 */
@Service
public class NodeServiceImpl implements NodeService {

  @Autowired
  private NodeDao nodeDao;

  @Autowired
  private PropDao propDao;

  @Autowired
  private ExtraConfigDao extraConfigDao;

  @Override
  public long insert(Node node) {
    return nodeDao.insert(node);
  }

  @Override
  public Node get(Long id) {
    return nodeDao.getNode(id);
  }

  @Override
  public void update(Node node) {
    nodeDao.update(node);
  }

  @Override
  public void delete(Node node) {
    Map<String,Object> where = new HashMap<>();
    where.put(Tables.NODE_COLUMNS.ID, node.getId());
    nodeDao.delete(where);

    where = new HashMap<>();
    where.put(Tables.PROP_COLUMNS.NODE_ID, node.getId());
    propDao.delete(where);

    where = new HashMap<>();
    where.put(Tables.EXTRA_CONFIG_COLUMNS.NODE_ID, node.getId());
    extraConfigDao.delete(where);
  }

  @Override
  public List<Node> listByRule(Long ruleId) {
    return nodeDao.listByRule(ruleId);
  }
}
