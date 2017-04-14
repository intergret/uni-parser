package com.local.labs.parser.common.redis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

/**
 * Author: Xing Wang <wangxing.bjtu@gmail.com>
 * Date: 2017-03-06 Time: 10:41
 */
public class RedisCache {

  private RedisTemplate redisTemplate;

  public RedisCache(RedisTemplate redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  public String get(final String key) {
    return redisTemplate.query(new RedisAction<String>() {
      public String query(Jedis jedis) {
        return jedis.get(key);
      }
    });
  }

  public Map<String,String> batchGet(final List<String> keys) {
    final Map<String,String> result = new HashMap<>();
    if (keys != null && keys.size() > 0) {
      redisTemplate.execute(new RedisAction<String>() {
        public void execute(Jedis jedis) {
          Pipeline p = jedis.pipelined();
          for (String key : keys) {
            p.get(key);
          }
          List<Object> resultObjects = p.syncAndReturnAll();
          for (int i = 0; i < keys.size(); i++) {
            result.put(keys.get(i), (String) resultObjects.get(i));
          }
        }
      });
    }
    return result;
  }

  public void set(final String key, final String value) {
    redisTemplate.execute(new RedisAction<String>() {
      public void execute(Jedis jedis) {
        jedis.set(key, value);
      }
    });
  }

  public void batchSet(final Map<String,String> keyValues) {
    if (keyValues != null && keyValues.size() > 0) {
      redisTemplate.execute(new RedisAction<String>() {
        public void execute(Jedis jedis) {
          Pipeline p = jedis.pipelined();
          for (String key : keyValues.keySet()) {
            p.set(key, keyValues.get(key));
          }
          p.syncAndReturnAll();
        }
      });
    }
  }

  public void set(final String key, final String value, final int exp) {
    redisTemplate.execute(new RedisAction<String>() {
      public void execute(Jedis jedis) {
        jedis.setex(key, exp, value);
      }
    });
  }

  public void batchSet(final Map<String,String> keyValues, final int exp) {
    if (keyValues != null && keyValues.size() > 0) {
      redisTemplate.execute(new RedisAction<String>() {
        public void execute(Jedis jedis) {
          Pipeline p = jedis.pipelined();
          for (String key : keyValues.keySet()) {
            p.setex(key, exp, keyValues.get(key));
          }
          p.syncAndReturnAll();
        }
      });
    }
  }

  public void delete(final String key) {
    redisTemplate.execute(new RedisAction<String>() {
      public void execute(Jedis jedis) {
        jedis.del(key);
      }
    });
  }

  public void batchDelete(final List<String> keys) {
    if (keys != null && keys.size() > 0) {
      redisTemplate.execute(new RedisAction<String>() {
        public void execute(Jedis jedis) {
          Pipeline p = jedis.pipelined();
          for (String key : keys) {
            p.del(key);
          }
          p.syncAndReturnAll();
        }
      });
    }
  }

  public boolean exist(final String key) {
    return redisTemplate.query(new RedisAction<Boolean>() {
      public Boolean query(Jedis jedis) {
        return jedis.exists(key.getBytes());
      }
    });
  }

  public Set<String> keys(final String pattern) {
    if (StringUtils.isEmpty(pattern)) {
      return null;
    }

    return redisTemplate.query(new RedisAction<Set<String>>() {
      public Set<String> query(Jedis jedis) {
        return jedis.keys(pattern);
      }
    });
  }
}
