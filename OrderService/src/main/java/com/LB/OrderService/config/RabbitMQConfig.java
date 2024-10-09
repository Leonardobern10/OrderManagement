package com.LB.OrderService.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String ORDER_QUEUE = "orderQueue";
    public static final String ORDER_EXCHANGE = "orderExchange";

    @Bean
    Queue queue () {
        return new Queue (ORDER_QUEUE, false);
    }

    @Bean
    TopicExchange exchange () {
        return new TopicExchange( ORDER_EXCHANGE );
    }

    @Bean
    Binding binding (Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with( "order.#" );
    }

}
