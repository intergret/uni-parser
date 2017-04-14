package com.local.labs.parser.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.local.labs.parser.common.model.parser.ParseData;
import com.local.labs.parser.common.redis.RedisCache;
import com.local.labs.parser.common.serializer.JsonSerializer;

/**
 * Author: Xing Wang <wangxing.bjtu@gmail.com> Date: 2017-03-24 Time: 12:29
 */
@Service
public class ParseDataServiceImpl implements ParseDataService {

  public static final String KEY_FORMAT = "ParseData_%s";

  @Autowired
  private JsonSerializer serializer;

  @Autowired
  private RedisCache redisCache;

  public String getKey(String urlMd5) {
    return String.format(KEY_FORMAT, urlMd5);
  }

  @Override
  public ParseData get(String url) {
    String urlMd5 = DigestUtils.md5Hex(url);
    String parseDataStr = redisCache.get(getKey(urlMd5));
    return serializer.deserialize(parseDataStr, ParseData.class);
  }

  @Override
  public void store(String url, ParseData parseData) {
    if (parseData == null) {
      return;
    }
    String urlMd5 = DigestUtils.md5Hex(url);
    String parseDataStr = serializer.serialize(parseData);
    redisCache.set(getKey(urlMd5), parseDataStr);
    System.out.println("Store parseData to redis " + urlMd5);
  }
}
