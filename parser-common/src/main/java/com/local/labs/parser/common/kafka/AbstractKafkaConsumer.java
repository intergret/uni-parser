package com.local.labs.parser.common.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

/**
 * Author: Xing Wang <wangxing.bjtu@gmail.com>
 * Date: 2017-03-06 Time: 19:34
 */
public class AbstractKafkaConsumer implements Runnable {

  private static final Logger LOG = LoggerFactory.getLogger(AbstractKafkaConsumer.class);

  protected KafkaStream kafkaStream;

  protected Integer threadSeq;

  public AbstractKafkaConsumer(KafkaStream kafkaStream, Integer threadSeq) {
    this.kafkaStream = kafkaStream;
    this.threadSeq = threadSeq;
  }

  public void run() {
    ConsumerIterator<byte[],byte[]> it = kafkaStream.iterator();
    while (it.hasNext()) {
      LOG.info("Thread " + threadSeq + ": " + new String(it.next().message()));
    }
    LOG.info("Shutting down Thread: " + threadSeq);
  }
}