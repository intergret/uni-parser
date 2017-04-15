package com.local.labs.parser.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.local.labs.parser.common.model.parser.rule.ExtraConfig;
import com.local.labs.parser.common.sql.Sql;
import com.local.labs.parser.common.sql.SqlBuilder;
import com.local.labs.parser.dao.Tables;
import com.local.labs.parser.dao.mapper.ExtraConfigRowMapper;

/**
 * Author: Xing Wang <wangxing.bjtu@gmail.com>
 * Date: 2017-03-23 Time: 16:01
 */
@Repository
public class ExtraConfigDaoImpl extends AbstractDao implements ExtraConfigDao {

  private final static String[] EXTRA_CONFIG_FIELDS = {Tables.EXTRA_CONFIG_COLUMNS.ID,
      Tables.EXTRA_CONFIG_COLUMNS.INPUT_TYPE, Tables.EXTRA_CONFIG_COLUMNS.COND, Tables.EXTRA_CONFIG_COLUMNS.VALUE,
      Tables.EXTRA_CONFIG_COLUMNS.REF_EXTRA_CONFIG_ID, Tables.EXTRA_CONFIG_COLUMNS.EXTRACTOR_TYPE,
      Tables.EXTRA_CONFIG_COLUMNS.TRANSFORM_TYPE, Tables.EXTRA_CONFIG_COLUMNS.PROP_ID,
      Tables.EXTRA_CONFIG_COLUMNS.NODE_ID, Tables.EXTRA_CONFIG_COLUMNS.RULE_ID};

  public static final ExtraConfigRowMapper EXTRA_CONFIG_ROW_MAPPER = new ExtraConfigRowMapper();

  @Override
  protected String getTableName() {
    return Tables.EXTRA_CONFIG_TABLE;
  }

  @Override
  public ExtraConfig get(Long id) {
    Map<String,Object> where = new HashMap<>();
    where.put(Tables.EXTRA_CONFIG_COLUMNS.ID, id);
    Sql sql = SqlBuilder.buildSelect(getTableName(), EXTRA_CONFIG_FIELDS, where);
    List<ExtraConfig> extraConfigs = getJdbcTemplate().query(sql.getSql(), sql.getParams(), EXTRA_CONFIG_ROW_MAPPER);
    return CollectionUtils.isNotEmpty(extraConfigs) ? extraConfigs.get(0) : null;
  }

  @Override
  public List<ExtraConfig> listByProp(long propId) {
    Map<String,Object> where = new HashMap<>();
    where.put(Tables.EXTRA_CONFIG_COLUMNS.PROP_ID, propId);
    return super.get(EXTRA_CONFIG_FIELDS, where, EXTRA_CONFIG_ROW_MAPPER);
  }

  @Override
  public long insert(ExtraConfig extraConfig) {
    Map<String,Object> data = new HashMap<>();
    data.put(Tables.EXTRA_CONFIG_COLUMNS.INPUT_TYPE, extraConfig.getInputType().name());
    data.put(Tables.EXTRA_CONFIG_COLUMNS.COND, extraConfig.getCond());
    data.put(Tables.EXTRA_CONFIG_COLUMNS.VALUE, extraConfig.getValue());
    data.put(Tables.EXTRA_CONFIG_COLUMNS.REF_EXTRA_CONFIG_ID, extraConfig.getRefExtraConfigId());
    data.put(Tables.EXTRA_CONFIG_COLUMNS.EXTRACTOR_TYPE, extraConfig.getExtractorType().name());
    data.put(Tables.EXTRA_CONFIG_COLUMNS.TRANSFORM_TYPE, extraConfig.getTransformType().name());
    data.put(Tables.EXTRA_CONFIG_COLUMNS.PROP_ID, extraConfig.getPropId());
    data.put(Tables.EXTRA_CONFIG_COLUMNS.NODE_ID, extraConfig.getNodeId());
    data.put(Tables.EXTRA_CONFIG_COLUMNS.RULE_ID, extraConfig.getRuleId());
    insert(data, false);
    return getMaxId();
  }

  @Override
  public void update(ExtraConfig extraConfig) {
    Map<String,Object> data = new HashMap<>();
    data.put(Tables.EXTRA_CONFIG_COLUMNS.INPUT_TYPE, extraConfig.getInputType().name());
    data.put(Tables.EXTRA_CONFIG_COLUMNS.COND, extraConfig.getCond());
    data.put(Tables.EXTRA_CONFIG_COLUMNS.VALUE, extraConfig.getValue());
    data.put(Tables.EXTRA_CONFIG_COLUMNS.REF_EXTRA_CONFIG_ID, extraConfig.getRefExtraConfigId());
    data.put(Tables.EXTRA_CONFIG_COLUMNS.EXTRACTOR_TYPE, extraConfig.getExtractorType().name());
    data.put(Tables.EXTRA_CONFIG_COLUMNS.TRANSFORM_TYPE, extraConfig.getTransformType().name());
    data.put(Tables.EXTRA_CONFIG_COLUMNS.PROP_ID, extraConfig.getPropId());
    data.put(Tables.EXTRA_CONFIG_COLUMNS.NODE_ID, extraConfig.getNodeId());
    data.put(Tables.EXTRA_CONFIG_COLUMNS.RULE_ID, extraConfig.getRuleId());
    if (extraConfig.getId() != null) {
      Map<String,Object> where = new HashMap<>();
      where.put(Tables.EXTRA_CONFIG_COLUMNS.ID, extraConfig.getId());
      update(data, where);
    }
  }
}
