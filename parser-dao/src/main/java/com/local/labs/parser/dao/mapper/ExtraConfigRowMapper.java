package com.local.labs.parser.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.local.labs.parser.common.model.parser.rule.ExtraConfig;
import com.local.labs.parser.dao.Tables;

public class ExtraConfigRowMapper implements RowMapper<ExtraConfig> {

  @Override
  public ExtraConfig mapRow(ResultSet rs, int rowNum) throws SQLException {
    ExtraConfig config = new ExtraConfig();
    config.setId(rs.getLong(Tables.EXTRA_CONFIG_COLUMNS.ID));
    config.setInputType(ExtraConfig.ExtractorInput.valueOf(rs.getString(Tables.EXTRA_CONFIG_COLUMNS.INPUT_TYPE)));
    config.setInputOption(rs.getString(Tables.EXTRA_CONFIG_COLUMNS.INPUT_OPTION));
    config.setCond(rs.getString(Tables.EXTRA_CONFIG_COLUMNS.COND));
    config.setValue(rs.getString(Tables.EXTRA_CONFIG_COLUMNS.VALUE));
    config.setRefExtraConfigId(rs.getLong(Tables.EXTRA_CONFIG_COLUMNS.REF_EXTRA_CONFIG_ID));
    config.setExtractorType(ExtraConfig.ExtractorType.valueOf(rs.getString(Tables.EXTRA_CONFIG_COLUMNS.EXTRACTOR_TYPE)));
    config.setTransformType(ExtraConfig.TransformType.valueOf(rs.getString(Tables.EXTRA_CONFIG_COLUMNS.TRANSFORM_TYPE)));
    config.setPropId(rs.getLong(Tables.EXTRA_CONFIG_COLUMNS.PROP_ID));
    config.setNodeId(rs.getLong(Tables.EXTRA_CONFIG_COLUMNS.NODE_ID));
    config.setRuleId(rs.getLong(Tables.EXTRA_CONFIG_COLUMNS.RULE_ID));
    return config;
  }
}