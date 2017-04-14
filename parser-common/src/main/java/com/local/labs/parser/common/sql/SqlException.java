package com.local.labs.parser.common.sql;

public class SqlException extends RuntimeException {

  private static final long serialVersionUID = 3746788811312581129L;

  public SqlException() {
    super();
  }

  public SqlException(String message) {
    super(message);
  }
}
