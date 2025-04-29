package com.dtb.payment_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publish(Transaction txn) {
        rabbitTemplate.convertAndSend("transactions.exchange", "transactions.routingKey", txn);
    }
}
