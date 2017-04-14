package com.local.labs.parser.engine.extractor;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.local.labs.parser.common.utils.LogUtil;
import com.ximpleware.AutoPilot;
import com.ximpleware.NavException;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;

/**
 * Author: Xing Wang <wangxing.bjtu@gmail.com>
 * Date: 2017-03-14 Time: 16:48
 */
@Component
public class XmlExtractor implements Extractor {

  private static final Logger LOG = LoggerFactory.getLogger(XmlExtractor.class);

  private String doExtract(VTDNav vn, int index, String cond, String value) {
    try {
      if ("@attribute".equals(value)) {
        int val = vn.getAttrVal(vn.toString(index));
        if (val != -1) {
          return vn.toNormalizedString(val);
        }
      } else if ("$text".equals(value)) {
        int val = vn.getText();
        if (val != -1) {
          return vn.toNormalizedString(val);
        }
      } else if ("$xml".equals(value)) {
        long l = vn.getElementFragment();
        if (l != -1) {
          return new String(vn.getXML().getBytes((int) l, (int) (l >> 32)), CharEncoding.UTF_8);
        }
      }
    } catch (NavException e) {
      LogUtil.error(LOG, e);
    } catch (UnsupportedEncodingException e) {
      LogUtil.error(LOG, e);
    }
    return null;
  }

  @Override
  public String extract(String input, String cond, String value) {
    if (StringUtils.isBlank(input) || StringUtils.isBlank(cond) || StringUtils.isBlank(value)) {
      return null;
    }

    try {
      VTDGen vg = new VTDGen();
      vg.setDoc(input.getBytes(CharEncoding.UTF_8));
      vg.parse(false);
      VTDNav vn = vg.getNav();
      AutoPilot ap = new AutoPilot(vn);
      ap.selectXPath(cond);
      int index;
      if ((index = ap.evalXPath()) != -1) {
        String tmp = doExtract(vn, index, cond, value);
        if (tmp != null && StringUtils.isNotBlank(tmp.trim())) {
          return tmp.trim();
        }
      }
    } catch (Exception e) {
      LogUtil.error(LOG, e);
    }
    return null;
  }

  @Override
  public List<String> extractMulti(String input, String cond, String value) {
    if (StringUtils.isBlank(input) || StringUtils.isBlank(cond) || StringUtils.isBlank(value)) {
      return Collections.EMPTY_LIST;
    }

    List<String> result = new ArrayList<>();
    try {
      VTDGen vg = new VTDGen();
      vg.setDoc(input.getBytes(CharEncoding.UTF_8));
      vg.parse(false);
      VTDNav vn = vg.getNav();
      AutoPilot ap = new AutoPilot(vn);
      ap.selectXPath(cond);
      int index;
      while ((index = ap.evalXPath()) != -1) {
        String tmp = doExtract(vn, index, cond, value);
        if (tmp != null && StringUtils.isNotBlank(tmp.trim())) {
          result.add(tmp.trim());
        }
      }
    } catch (Exception e) {
      LogUtil.error(LOG, e);
    }
    return result;
  }
}
