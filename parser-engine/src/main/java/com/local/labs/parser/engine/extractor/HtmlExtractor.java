package com.local.labs.parser.engine.extractor;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

/**
 * Author: Xing Wang <wangxing.bjtu@gmail.com>
 * Date: 2017-03-14 Time: 16:44
 */
@Component
public class HtmlExtractor implements Extractor {

  public String doExtract(Element element, String value) {
    if (value.startsWith("@")) {
      String attr = value.substring(1);
      return element.attr(attr);
    } else if ("$html".equals(value)) {
      return element.html();
    } else if ("$text".equals(value)) {
      return element.text();
    } else if ("$ownText".equals(value)) {
      return element.ownText();
    } else if ("$string".equals(value)) {
      return element.toString();
    }
    return null;
  }

  @Override
  public String extract(String input, String cond, String value) {
    if (StringUtils.isBlank(input) || StringUtils.isBlank(cond) || StringUtils.isBlank(value)) {
      return null;
    }

    Document doc = Jsoup.parse(input);
    Elements elements = doc.select(cond);
    if (!elements.isEmpty()) {
      String tmp = doExtract(elements.first(), value);
      if (tmp != null && StringUtils.isNotEmpty(tmp.trim())) {
        return tmp.trim();
      }
    }
    return null;
  }

  @Override
  public List<String> extractMulti(String input, String cond, String value) {
    if (StringUtils.isBlank(input) || StringUtils.isBlank(cond) || StringUtils.isBlank(value)) {
      return null;
    }

    Document doc = Jsoup.parse(input);
    Elements elements = doc.select(cond);
    List<String> result = new ArrayList<>();
    if (!elements.isEmpty()) {
      for (Element e : elements) {
        String tmp = doExtract(e, value);
        if (tmp != null && StringUtils.isNotEmpty(tmp.trim())) {
          result.add(tmp.trim());
        }
      }
    }
    return result;
  }
}
