package com.local.labs.parser.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.local.labs.parser.common.model.parser.rule.ExtraConfig;
import com.local.labs.parser.dao.Tables;
import com.local.labs.parser.dao.impl.ExtraConfigDao;

/**
 * Author: Xing Wang <wangxing.bjtu@gmail.com>
 * Date: 2017-01-23 Time: 16:18
 */
@Service
public class ExtraConfigServiceImpl implements ExtraConfigService {

  @Autowired
  private ExtraConfigDao extraConfigDao;

  @Override
  public ExtraConfig get(Long id) {
    return extraConfigDao.get(id);
  }

  @Override
  public long insert(ExtraConfig extraConfig) {
    return extraConfigDao.insert(extraConfig);
  }

  @Override
  public void update(ExtraConfig extraConfig) {
    extraConfigDao.update(extraConfig);
  }

  @Override
  public void delete(ExtraConfig extraConfig) {
    Map<String,Object> where = new HashMap<>();
    where.put(Tables.EXTRA_CONFIG_COLUMNS.ID, extraConfig.getId());
    extraConfigDao.delete(where);
  }

  @Override
  public List<ExtraConfig> listByProp(Long propId) {
    return extraConfigDao.listByProp(propId);
  }
}
