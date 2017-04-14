package com.local.labs.parser.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.local.labs.parser.common.kafka.AbstractKafkaConsumer;
import com.local.labs.parser.common.model.parser.ParserTask;
import com.local.labs.parser.common.serializer.JsonSerializer;
import com.local.labs.parser.engine.core.TextParser;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

/**
 * Author: Xing Wang <wangxing.bjtu@gmail.com>
 * Date: 2017-03-24 Time: 15:44
 */
public class ParserKafkaConsumer extends AbstractKafkaConsumer {

  private static final Logger LOG = LoggerFactory.getLogger(ParserKafkaConsumer.class);

  private TextParser textParser;
  private JsonSerializer serializer;

  public ParserKafkaConsumer(KafkaStream kafkaStream, Integer threadSeq, TextParser textParser,
      JsonSerializer serializer) {
    super(kafkaStream, threadSeq);
    this.textParser = textParser;
    this.serializer = serializer;
  }

  @Override
  public void run() {
    ConsumerIterator<byte[],byte[]> it = kafkaStream.iterator();
    while (true) {
      try {
        if (it.hasNext()) {
          byte[] data = it.next().message();
          System.out.println("Consumer parser task " + new String(data));
          ParserTask parseTask = serializer.deserialize(data, ParserTask.class);
          if (parseTask != null) {
            textParser.parse(parseTask);
          }
        } else {
          Thread.sleep(3000);
          LOG.info("No More Message, sleep 3000ms");
        }
      } catch (Exception e) {
        LOG.error("Unknown Exception ", e);
      }
    }
  }
}
