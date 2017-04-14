package com.local.labs.parser.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.local.labs.parser.common.model.parser.rule.Prop;
import com.local.labs.parser.dao.Tables;
import com.local.labs.parser.dao.impl.ExtraConfigDao;
import com.local.labs.parser.dao.impl.PropDao;

/**
 * Author: Xing Wang <wangxing.bjtu@gmail.com>
 * Date: 2017-01-23 Time: 16:16
 */
@Service
public class PropServiceImpl implements PropService {

  @Autowired
  private PropDao propDao;

  @Autowired
  private ExtraConfigDao extraConfigDao;

  @Override
  public long insert(Prop prop) {
    return propDao.insert(prop);
  }

  @Override
  public Prop get(Long id) {
    return propDao.getProp(id);
  }

  @Override
  public void update(Prop prop) {
    propDao.update(prop);
  }

  @Override
  public void delete(Prop prop) {
    Map<String,Object> where = new HashMap<>();
    where.put(Tables.PROP_COLUMNS.ID, prop.getId());
    propDao.delete(where);

    where = new HashMap<>();
    where.put(Tables.EXTRA_CONFIG_COLUMNS.PROP_ID, prop.getId());
    extraConfigDao.delete(where);
  }

  @Override
  public List<Prop> listByNode(Long nodeId) {
    return propDao.listByNode(nodeId);
  }
}
