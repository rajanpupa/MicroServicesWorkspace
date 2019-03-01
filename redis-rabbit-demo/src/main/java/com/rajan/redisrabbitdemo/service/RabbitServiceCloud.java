package com.rajan.redisrabbitdemo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rajan.redisrabbitdemo.dto.RabbitMessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("!LOCAL")
public class RabbitServiceCloud {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitServiceCloud.class);

    RabbitTemplate rabbitTemplate;
    ObjectMapper objectMapper;

    @Value("${spring.rabbitmq.template.exchange}")
    String rabbitExchange;

    @Autowired
    public RabbitServiceCloud(
            RabbitTemplate rabbitTemplate,
            ObjectMapper objectMapper
    ){
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    public void publishToMessageQueue(String message) throws JsonProcessingException {
        LOGGER.info("Start: Posting to rabbitMQ. {}", message);

        RabbitMessageDto rmsg = RabbitMessageDto.builder()
                .id(1L)
                .name("Rajan")
                .message(message)
                .build();

        Message msg = MessageBuilder
                .withBody(this.objectMapper.writeValueAsBytes(rmsg))
                .build();

        msg.getMessageProperties().setHeader("messageType", "test");
        msg.getMessageProperties().setHeader("audience", "internet");
        msg.getMessageProperties().setHeader("channel", "web");
        msg.getMessageProperties().setHeader("updateType", "engg");

        rabbitTemplate.setExchange(rabbitExchange);

        rabbitTemplate.convertAndSend(msg);
        LOGGER.info("End: Posting to rabbitMQ. {}", message);
    }
}
