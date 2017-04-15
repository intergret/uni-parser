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

import com.local.labs.parser.engine.extractor.JsonExtractor;

@ContextConfiguration(locations = {"classpath:applicationContext-engine.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class JsonExtractorTest {

  @Autowired
  private JsonExtractor jsonExtractor;

  @Value(value = "classpath:/MyApp.json")
  private Resource jsonFile;

  @Test
  public void testMyApp() throws Exception {
    String jsonExample = FileUtils.readFileToString(jsonFile.getFile(), CharEncoding.UTF_8);

    String cond = "$.info.value[1]";
    System.out.println(jsonExtractor.extract(jsonExample, cond, "$json"));

    cond = "$.info.value[1].softname";
    System.out.println(jsonExtractor.extract(jsonExample, cond, "$text"));

    cond = "$.info.['pageNo','pageCount']";
    System.out.println(jsonExtractor.extract(jsonExample, cond, "$text"));

    cond = "$.info.value[*]";
    System.out.println(jsonExtractor.extractMulti(jsonExample, cond, "$json"));

    cond = "$.info.value[*].softname";
    System.out.println(jsonExtractor.extractMulti(jsonExample, cond, "$text"));

    cond = "$.info.['pageNo','pageCount']";
    System.out.println(jsonExtractor.extractMulti(jsonExample, cond, "$text"));
  }
}