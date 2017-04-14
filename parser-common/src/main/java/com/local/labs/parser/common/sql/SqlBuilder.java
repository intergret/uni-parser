package com.local.labs.parser.common.sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

public class SqlBuilder {

  private static final String IS_NULL_FRAGMENT = " is NULL ";

  private static final String EQ_NULL_FRAGMENT = " = NULL ";

  private static final String EQ_FRAGMENT = " = ? ";

  private static final String PROPERTY_FORMAT = "`%s`";

  public static Sql buildUpdate(final String table, final Map<String,Object> data, final Map<String,Object> where) {
    StringBuilder builder = new StringBuilder();
    List<Object> params = new ArrayList<>(data.size());
    sqlClause(builder, "UPDATE ", table, PROPERTY_FORMAT);
    sqlClause(builder, params, "SET ", "", PROPERTY_FORMAT + EQ_FRAGMENT, data, ", ", "");
    buildWhere(builder, params, where);
    return new Sql(builder.toString(), params.toArray(new Object[0]));
  }

  public static Sql buildInsert(final String table, final Map<String,Object> data) {
    return buildInsert(table, data, false);
  }

  public static Sql buildInsert(final String table, final Map<String,Object> data, boolean ignore) {
    StringBuilder builder = new StringBuilder();
    List<Object> params = new ArrayList<>(data.size());
    sqlClause(builder, "INSERT " + (ignore ? "IGNORE INTO " : "INTO "), table, PROPERTY_FORMAT);
    sqlClause(builder, params, "", "(", PROPERTY_FORMAT, data, ", ", ")");
    sqlClause(builder, params, "VALUES ", "(", "?", data, ", ", ")");
    return new Sql(builder.toString(), params.toArray(new Object[0]));
  }

  public static Sql buildSelect(final String table, final String[] fields, final Map<String,Object> where) {
    StringBuilder builder = new StringBuilder();
    if (ArrayUtils.isEmpty(fields)) {
      sqlClause(builder, "SELECT * FROM ", table, PROPERTY_FORMAT);
    } else {
      sqlClause(builder, "SELECT " + StringUtils.join(fields, ", ") + " FROM ", table, PROPERTY_FORMAT);
    }
    List<Object> params = new ArrayList<>();
    buildWhere(builder, params, where);
    return new Sql(builder.toString(), params.toArray(new Object[0]));
  }

  public static Sql buildSelect(final String table, final String[] fields, final Map<String,Object> where,
      final List<String> tails) {
    Sql sql = buildSelect(table, fields, where);
    StringBuilder builder = new StringBuilder(sql.getSql());

    if (CollectionUtils.isNotEmpty(tails)) {
      builder.append(" " + StringUtils.join(tails.toArray(), " "));
    }
    return new Sql(builder.toString(), sql.getParams());
  }

  public static Sql buildDelete(final String table, final Map<String,Object> where) {
    StringBuilder builder = new StringBuilder();
    sqlClause(builder, "DELETE FROM ", table, PROPERTY_FORMAT);
    List<Object> params = new ArrayList<>();
    buildWhere(builder, params, where);
    return new Sql(builder.toString(), params.toArray(new Object[0]));
  }

  public static void buildWhere(StringBuilder builder, List<Object> params, final Map<String,Object> where) {
    if (!MapUtils.isEmpty(where)) {
      sqlClause(builder, params, "WHERE ", "(", PROPERTY_FORMAT + EQ_FRAGMENT, where, " AND ", ")");
    }
  }

  public static void buildMultiTableWhere(StringBuilder builder, List<Object> params, final Map<String,Object> where) {
    if (!MapUtils.isEmpty(where)) {
      sqlClause(builder, params, "WHERE", "(", "%s" + EQ_FRAGMENT, where, " AND ", ")");
    }
  }

  public static void buildMultiTableAnd(StringBuilder builder, List<Object> params, final Map<String,Object> and) {
    if (!MapUtils.isEmpty(and)) {
      sqlClause(builder, params, "AND ", "", "%s" + EQ_FRAGMENT, and, " AND ", "");
    }
  }

  private static void sqlClause(StringBuilder builder, final String keyword, final String part, final String format) {
    builder.append(keyword).append(String.format(format, part));
  }

  private static void sqlClause(StringBuilder builder, List<Object> data, final String keyword, final String open,
      final String format, final Map<String,Object> parts, final String conjunction, final String close) {
    if (!(MapUtils.isEmpty(parts))) {
      if (builder.length() > 0) {
        builder.append(" ");
      }
      if (keyword != null && keyword.length() > 0) {
        builder.append(keyword);
      }
      builder.append(open);
      String last = "________";
      int i = 0;
      for (Map.Entry<String,Object> entry : parts.entrySet()) {
        String part = entry.getKey();
        if ((i > 0) && (!(part.equals(") AND ("))) && (!(part.equals(") OR ("))) && (!(last.equals(") AND (")))
            && (!(last.equals(") OR (")))) {
          builder.append(conjunction);
        }
        i++;
        last = part;
        String fragment = String.format(format, part);
        if (format.indexOf("?") >= 0) {
          if (entry.getValue() == null) {
            if (keyword.trim().equals("WHERE")) {
              fragment = fragment.replace(EQ_FRAGMENT, IS_NULL_FRAGMENT);
            } else if (keyword.trim().equals("SET")) {
              fragment = fragment.replace(EQ_FRAGMENT, EQ_NULL_FRAGMENT);
            } else if (keyword.trim().equals("VALUES")) {
              data.add(null);
            }
          } else {
            data.add(entry.getValue());
          }
        }
        builder.append(fragment);
      }
      builder.append(close);
    }
  }
}