package com.local.labs.parser.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.local.labs.parser.common.sql.Sql;
import com.local.labs.parser.common.sql.SqlBuilder;

public abstract class AbstractDao {

  private JdbcTemplate jdbcTemplate;

  protected abstract String getTableName();

  public JdbcTemplate getJdbcTemplate() {
    return jdbcTemplate;
  }

  public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public int update(Map<String,Object> data, Map<String,Object> where) {
    Sql sql = SqlBuilder.buildUpdate(getTableName(), data, where);
    return getJdbcTemplate().update(sql.getSql(), sql.getParams());
  }

  public long getMaxId() {
    String sql = "SELECT MAX(id) FROM " + getTableName();
    return getJdbcTemplate().queryForObject(sql, Long.class);
  }

  public int execute(String sql) {
    return getJdbcTemplate().update(sql);
  }

  public int insert(Map<String, Object> data) {
    return insert(data, false);
  }

  public int insert(Map<String,Object> data, boolean ignoreDuplicate) {
    Sql sql = SqlBuilder.buildInsert(getTableName(), data, ignoreDuplicate);
    return getJdbcTemplate().update(sql.getSql(), sql.getParams());
  }

  public long insertAndReturnId(Map<String,Object> data, boolean ignoreDuplicate) {
    final Sql sql = SqlBuilder.buildInsert(getTableName(), data, ignoreDuplicate);
    KeyHolder keyHolder = new GeneratedKeyHolder();
    getJdbcTemplate().update(new PreparedStatementCreator() {
      @Override
      public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
        PreparedStatement ps = con.prepareStatement(sql.getSql(), Statement.RETURN_GENERATED_KEYS);
        PreparedStatementSetter setter = new ArgumentPreparedStatementSetter(sql.getParams());
        setter.setValues(ps);
        return ps;
      }
    }, keyHolder);

    Number result = keyHolder.getKey();
    if (result != null) {
      return result.longValue();
    } else {
      return 0;
    }
  }
  public int upsert(Map<String, Object> data, Map<String, Object> where) {
    if (where != null && !where.isEmpty()) {
      if (this.exists(where)) {
        return update(data, where);
      } else {
        Map<String, Object> totalData = new HashMap<>();
        totalData.putAll(data);
        totalData.putAll(where);
        return insert(totalData);
      }
    }
    return 0;
  }

  public <T> List<T> get(final String[] fields, final Map<String,Object> where, RowMapper<T> mapper) {
    Sql sql = SqlBuilder.buildSelect(getTableName(), fields, where);
    return getJdbcTemplate().query(sql.getSql(), sql.getParams(), mapper);
  }

  public boolean exists(Map<String,Object> where) {
    Sql sql = SqlBuilder.buildSelect(getTableName(), new String[] {}, where);
    return getJdbcTemplate().query(sql.getSql(), sql.getParams(), new ResultSetExtractor<Boolean>() {
      @Override
      public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException {
        return rs.first();
      }
    });
  }

  public int delete(Map<String,Object> where) {
    Sql sql = SqlBuilder.buildDelete(getTableName(), where);
    return getJdbcTemplate().update(sql.getSql(), sql.getParams());
  }
}
