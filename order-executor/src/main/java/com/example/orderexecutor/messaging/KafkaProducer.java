package com.example.orderexecutor.messaging;

import java.io.Serializable;

public interface KafkaProducer<K extends Serializable, V> {

    void send(String topicName, V message);
}
