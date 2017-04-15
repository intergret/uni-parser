package com.local.labs.parser.engine;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.local.labs.parser.engine.extractor.HtmlExtractor;

@ContextConfiguration(locations = {"classpath:applicationContext-engine.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class HtmlExtractorTest {

  @Autowired
  private HtmlExtractor htmlExtractor;

  // http://zhushou.sogou.com/apps/detail/14695.html
  @Value(value = "classpath:/SogouApp.html")
  private Resource htmlFile;

  @Test
  public void testMyApp() throws Exception {
    String htmlExample = FileUtils.readFileToString(htmlFile.getFile(), CharEncoding.UTF_8);

    String cond = ".appinfo";
    String appInfo = htmlExtractor.extract(htmlExample, cond, "$html");
    System.out.println(htmlExtractor.extract(appInfo, ".overview > img:nth-child(1)", "@src"));
    System.out.println(htmlExtractor.extract(appInfo, ".overview > p:nth-child(2)", "$text"));
    System.out.println(htmlExtractor.extract(appInfo, ".info > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1)", "$text"));

    cond = "div.rec:nth-child(3) > ul:nth-child(3)";
    String hotapps = htmlExtractor.extract(htmlExample, cond, "$html");
    System.out.println(htmlExtractor.extractMulti(hotapps, "li > a:nth-child(1)", "$text"));
  }
}