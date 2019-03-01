package com.rajan.redisrabbitdemo.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfigurations {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Value("${spring.rabbitmq.template.exchange}")
    private String rabbitmqExchangeName;

    @Autowired
    private ConnectionFactory rabbitConnectionFactory;

    @Bean
    public RabbitTemplate rabbitTemplate(){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(rabbitConnectionFactory);
        rabbitTemplate.setExchange(rabbitmqExchangeName);
        rabbitTemplate.setConnectionFactory(rabbitConnectionFactory);
        return rabbitTemplate;
    }
}
