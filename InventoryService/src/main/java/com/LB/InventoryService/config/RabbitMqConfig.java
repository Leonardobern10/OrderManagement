package com.LB.InventoryService.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    public static final String INVENTORY_QUEUE = "inventoryQueue";
    public static final String INVENTORY_EXCHANGE = "inventoryExchange";
    public static final String ORDER_QUEUE = "orderQueue";

    @Bean
    Queue inventoryQueue () {
        return new Queue (INVENTORY_QUEUE, false);
    }

    @Bean
    Queue orderQueue () {
        return new Queue (ORDER_QUEUE, false);
    }

    @Bean
    TopicExchange inventoryExchange () {
        return new TopicExchange( INVENTORY_EXCHANGE );
    }

    @Bean
    Binding inventoryBinding (Queue inventoryQueue, TopicExchange inventoryExchange) {
        return BindingBuilder.bind(inventoryQueue).to(inventoryExchange).with("inventory.#");
    }
}

