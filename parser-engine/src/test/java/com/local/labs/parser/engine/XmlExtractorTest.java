package com.local.labs.parser.engine;

import java.util.List;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.local.labs.parser.engine.extractor.XmlExtractor;

@ContextConfiguration(locations = {"classpath:applicationContext-engine.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class XmlExtractorTest {

  @Autowired
  private XmlExtractor xmlExtractor;

  @Value(value = "classpath:/QQVideo.xml")
  private Resource qqVideoXml;

  @Test
  public void testQQVideo() throws Exception {
    String xmlExample = FileUtils.readFileToString(qqVideoXml.getFile(), CharEncoding.UTF_8);
    String videoCond = "/urlset/url";
    List<String> videoXmls = xmlExtractor.extractMulti(xmlExample, videoCond, "$xml");
    for (String videoXml : videoXmls) {
      String title = xmlExtractor.extract(videoXml, "/url/video/title", "$text");
      List<String> actors = xmlExtractor.extractMulti(videoXml, "/url/video/actor", "$text");
      System.out.println("QQVideo\t" + title + "\t" + actors);
    }
  }
}