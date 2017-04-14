package com.local.labs.parser.engine.extractor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.local.labs.parser.common.model.parser.rule.ExtraConfig;

/**
 * Author: Xing Wang <wangxing.bjtu@gmail.com>
 * Date: 2017-03-24 Time: 09:44
 */
@Component
public class ExtractorLoader {

  @Autowired
  private HtmlExtractor htmlExtractor;

  @Autowired
  private JsonExtractor jsonExtractor;

  @Autowired
  private XmlExtractor xmlExtractor;

  public Extractor load(ExtraConfig.ExtractorType extractorType) {
    if (extractorType == ExtraConfig.ExtractorType.HTML) {
      return htmlExtractor;
    } else if (extractorType == ExtraConfig.ExtractorType.JSON) {
      return jsonExtractor;
    } else if (extractorType == ExtraConfig.ExtractorType.XML) {
      return xmlExtractor;
    }
    return null;
  }
}
