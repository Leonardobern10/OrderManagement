package com.LB.InventoryService.service;

import com.LB.InventoryService.config.RabbitMqConfig;
import com.LB.InventoryService.model.Item;
import com.LB.InventoryService.model.Order;
import com.google.gson.Gson;
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
    public void processOrder ( String orderJson ) {
        Gson gson = new Gson();
        Order order = gson.fromJson( orderJson, Order.class );

        System.out.println("\n\nPEDIDO PROCESSADO COM SUCESSO!");
        System.out.println("\nOrder ID: " + order.getOrderId());
        for ( Item item : order.getItems()) {
            System.out.println("Item Name: " + item.getItemName());
            System.out.println("Quantity: " + item.getQuantity());
            System.out.println("Unit Price: " + item.getUnitPrice());
            String finalPrice = String.format( "%.2f", item.getFinalPrice());
            System.out.println("Total Price: " + finalPrice + "\n");
        }
        System.out.println("----------------------------------");
        String totalPrice = String.format("%.2f", order.getTotalPrice());
        System.out.println("Total order price: " + totalPrice);

        rabbitTemplate.convertAndSend( RabbitMqConfig.INVENTORY_EXCHANGE, "inventory.processed", orderJson );
    }
}
