package com.lly.spark.kafka;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;

/**
 * kafka生产者
 */
public class kafkaProducer extends Thread {

    private String topic;

    private Producer<Integer, String> producer;

    public kafkaProducer(String topic) {

        this.topic = topic;

        Properties properties = new Properties();

        properties.put("metadata.broker.list", kafkaProperties.BROKER_LIST);
        properties.put("serializer.class", "kafka.serializer.StringEncoder");
        properties.put("request.required.acks", "1");

        producer = new Producer<Integer, String>(new ProducerConfig(properties));

    }

    @Override
    public void run() {
        int messageNo = 1;
        while (true) {
            String message = "message_" + messageNo;
            producer.send(new KeyedMessage<Integer, String>(topic, message));
            System.out.println("Sent:" + message);
            messageNo++;
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

