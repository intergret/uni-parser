package com.local.labs.parser.engine.extractor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jayway.jsonpath.JsonPath;
import com.local.labs.parser.common.serializer.JsonSerializer;

/**
 * Author: Xing Wang <wangxing.bjtu@gmail.com>
 * Date: 2017-03-14 Time: 16:40
 */
@Component
public class JsonExtractor implements Extractor {

  @Autowired
  private JsonSerializer serializer;

  public String doExtract(String input, String cond, String value) {
    String result = StringUtils.EMPTY;
    if ("$json".equals(value)) {
      Map<String,Object> item = JsonPath.read(input, cond);
      if (item != null) {
        result = serializer.serialize(item);
      }
    } else if ("$text".equals(value)) {
      Object object = JsonPath.read(input, cond);
      if (object != null) {
        if (object instanceof List) {
          List<Object> items = ((List<Object>) object);
          if (CollectionUtils.isNotEmpty(items)) {
            for (Object item : items) {
              result = item.toString();
              break;
            }
          }
        } else if (object instanceof Map) {
          Map<String,Object> items = (Map<String,Object>) object;
          if (MapUtils.isNotEmpty(items)) {
            for (Object o : items.values()) {
              if (StringUtils.isNotEmpty(o.toString())) {
                result = o.toString();
                break;
              }
            }
          }
        } else {
          result = object.toString();
        }
      }
    }
    return result;
  }

  public List<String> doExtractMulti(String input, String cond, String value) {
    List<String> results = new ArrayList<>();
    if ("$json".equals(value)) {
      List<Map<String,Object>> items = JsonPath.read(input, cond);
      if (CollectionUtils.isNotEmpty(items)) {
        for (Map<String,Object> item : items) {
          results.add(serializer.serialize(item));
        }
      }
    } else if ("$text".equals(value)) {
      Object object = JsonPath.read(input, cond);
      if (object != null) {
        if (object instanceof List) {
          List<Object> items = ((List<Object>) object);
          if (CollectionUtils.isNotEmpty(items)) {
            for (Object item : items) {
              results.add(item.toString());
            }
          }
        } else if (object instanceof Map) {
          Map<String,Object> items = (Map<String,Object>) object;
          if (MapUtils.isNotEmpty(items)) {
            for (Object o : items.values()) {
              if (StringUtils.isNotEmpty(o.toString())) {
                results.add(o.toString());
              }
            }
          }
        }
      }
    }
    return results;
  }

  @Override
  public String extract(String input, String cond, String value) {
    if (StringUtils.isBlank(input) || StringUtils.isBlank(cond) || StringUtils.isBlank(value)) {
      return null;
    }
    String tmp = doExtract(input, cond, value);
    if (tmp != null && StringUtils.isNotEmpty(tmp.trim())) {
      return tmp.trim();
    }
    return null;
  }

  @Override
  public List<String> extractMulti(String input, String cond, String value) {
    if (StringUtils.isBlank(input) || StringUtils.isBlank(cond) || StringUtils.isBlank(value)) {
      return Collections.EMPTY_LIST;
    }
    List<String> result = new ArrayList<>();
    List<String> items = doExtractMulti(input, cond, value);
    for (String item : items) {
      if (item != null && StringUtils.isNotEmpty(item.trim())) {
        result.add(item.trim());
      }
    }
    return result;
  }
}
