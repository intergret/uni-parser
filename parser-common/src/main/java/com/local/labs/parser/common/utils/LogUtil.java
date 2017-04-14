package com.local.labs.parser.common.utils;

import org.slf4j.Logger;

import com.google.common.base.Throwables;

public class LogUtil {

  public static void error(Logger logger, Exception e) {
    logger.error("{}", Throwables.getStackTraceAsString(e));
  }

  public static void error(Logger logger, String info, Exception e) {
    logger.error("{} {}", info, Throwables.getStackTraceAsString(e));
  }

  public static void warn(Logger logger, Exception e) {
    logger.warn("{}", Throwables.getStackTraceAsString(e));
  }

  public static void warn(Logger logger, String info, Exception e) {
    logger.warn("{} {}", info, Throwables.getStackTraceAsString(e));
  }
}
