package com.LB.NotificationService.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String NOTIFICATION_QUEUE = "notificationQueue";
    public static final String NOTIFICATION_EXCHANGE = "notificationExchange";

    @Bean
    Queue notificationQueue () {
        return new Queue (NOTIFICATION_QUEUE, false);
    }

    @Bean
    TopicExchange notificationExchange () {
        return new TopicExchange( NOTIFICATION_EXCHANGE );
    }

    @Bean
    Binding notificationBinding (Queue notificationQueue, TopicExchange notificationExchange) {
        return BindingBuilder.bind(notificationQueue).to(notificationExchange).with("notification.#");
    }
}
