package com.local.labs.parser.common.serializer;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.local.labs.parser.common.utils.LogUtil;

/**
 * Author: Xing Wang <wangxing.bjtu@gmail.com>
 * Date: 2017-03-06 Time: 19:38
 */
public class JsonSerializer {

  private static final Logger LOG = LoggerFactory.getLogger(JsonSerializer.class);

  private static final ObjectMapper MAPPER = new ObjectMapper();

  public <T> T deserialize(byte[] input, Class<T> t) {
    if (input != null) {
      try {
        return MAPPER.readValue(input, t);
      } catch (IOException e) {
        LogUtil.error(LOG, e);
      }
    }
    return null;
  }

  public <T> T deserialize(byte[] input, TypeReference<T> t) {
    if (input != null) {
      try {
        return MAPPER.readValue(input, t);
      } catch (IOException e) {
        LogUtil.error(LOG, e);
      }
    }
    return null;
  }

  public <T> T deserialize(String json, Class<T> t) {
    if (json != null) {
      try {
        return MAPPER.readValue(json, t);
      } catch (IOException e) {
        LogUtil.error(LOG, e);
      }
    }
    return null;
  }

  public <T> T deserialize(String json, TypeReference<T> t) {
    if (json != null) {
      try {
        return MAPPER.readValue(json, t);
      } catch (IOException e) {
        LogUtil.error(LOG, e);
      }
    }
    return null;
  }

  public String serialize(Object t) {
    if (t != null) {
      try {
        return MAPPER.writeValueAsString(t);
      } catch (IOException e) {
        LogUtil.error(LOG, e);
      }
    }
    return null;
  }

  public String serializeWithPretty(Object t) {
    if (t != null) {
      try {
        return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(t);
      } catch (IOException e) {
        LogUtil.error(LOG, e);
      }
    }
    return null;
  }
}
