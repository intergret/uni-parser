package com.local.labs.parser.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.local.labs.parser.common.kafka.KafkaConsumerGroup;
import com.local.labs.parser.common.serializer.JsonSerializer;
import com.local.labs.parser.engine.core.TextParser;

public class ParserMain {

  private static final Logger LOG = LoggerFactory.getLogger(ParserMain.class);

  public static void main(String[] args) {
    LOG.info("Parser is starting...");
    String[] springConfiguration = {"classpath:applicationContext-parser.xml"};
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(springConfiguration);

    String zookeeper = (String) context.getBean("kafkaZookeeperAddress");
    String parserGroup = (String) context.getBean("parserGroup");
    String parserTopic = (String) context.getBean("parserTopic");

    TextParser textParser = context.getBean(TextParser.class);
    JsonSerializer serializer = context.getBean(JsonSerializer.class);

    KafkaConsumerGroup kafkaConsumerGroup = new KafkaConsumerGroup(zookeeper, parserGroup, parserTopic, 1);
    kafkaConsumerGroup.run(ParserKafkaConsumer.class, textParser, serializer);
    LOG.info("Parser is started!");
  }
}
