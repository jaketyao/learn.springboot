package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CustomMessageSender {
    @Value("${spring.rabbitmq.queuename}")
    String queueName;
    @Value("${spring.rabbitmq.exchange}")
    String queueExchange;
    @Value("${spring.rabbitmq.routingkey}")
    String routingkey;

    private static final Logger log = LoggerFactory.getLogger(CustomMessageSender.class);

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public CustomMessageSender(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    private final AtomicInteger counter = new AtomicInteger();

   // @Scheduled(fixedDelay = 3000L)
    public void sendMessage(int i) {
        final CustomMessage message = new CustomMessage("Hello there!",counter.incrementAndGet(), false);
        log.info("Sending message..." + message.toString()  + " i " + i);
        rabbitTemplate.convertAndSend(queueExchange, routingkey, message);
    }
}
