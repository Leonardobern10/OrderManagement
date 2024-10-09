package com.LB.InventoryService.service;

import com.LB.InventoryService.config.RabbitMqConfig;
import com.LB.InventoryService.model.Order;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class InventoryConsumer {

    private final RabbitTemplate rabbitTemplate;

    public InventoryConsumer (RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = "orderQueue")
    public void processOrder ( String order) {
        String status = order + " processed!";
        System.out.println(status);

        rabbitTemplate.convertAndSend( RabbitMqConfig.INVENTORY_EXCHANGE, "inventory.processed", status );
    }
}
