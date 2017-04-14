package com.local.labs.parser.common.serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.local.labs.parser.common.utils.LogUtil;

/**
 * Author: Xing Wang <wangxing.bjtu@gmail.com>
 * Date: 2017-03-06 Time: 21:31
 */
public class JavaSerializer {

  private static final Logger LOG = LoggerFactory.getLogger(JsonSerializer.class);

  public static byte[] serialize(Object object) {
    if (object != null) {
      try {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(object);
        oos.flush();
        return baos.toByteArray();
      } catch (IOException e) {
        LogUtil.error(LOG, e);
      }
    }
    return null;
  }

  public static Object deserialize(byte[] bytes) {
    if (bytes != null) {
      try {
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
        return ois.readObject();
      } catch (Exception e) {
        LogUtil.error(LOG, e);
      }
    }
    return null;
  }
}
