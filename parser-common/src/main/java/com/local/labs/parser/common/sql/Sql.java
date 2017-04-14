
package com.local.labs.parser.common.sql;

import java.util.Arrays;
import java.util.List;

public class Sql {

  private final String sql;

  private Object[] params;

  private List<Object[]> paramsList;

  private final boolean isBatch;

  public Sql(final String sql, final Object[] params) {
    this.sql = sql;
    this.params = params;
    this.isBatch = false;
  }

  public Sql(final String sql, final List<Object[]> paramsList) {
    this.sql = sql;
    this.paramsList = paramsList;
    this.isBatch = true;
  }

  public String getSql() {
    return sql;
  }

  public Object[] getParams() {
    if (isBatch) {
      throw new SqlException("batch sql should call getParamsList");
    }
    return params;
  }

  public List<Object[]> getParamsList() {
    if (!isBatch) {
      throw new SqlException("non batch sql should call getParams");
    }
    return paramsList;
  }

  @Override
  public String toString() {
    if (isBatch) {
      return "Batch Sql [sql=" + sql + "]";
    } else {
      return "Sql [sql=" + sql + ", params=" + Arrays.toString(params) + "]";
    }
  }
}
