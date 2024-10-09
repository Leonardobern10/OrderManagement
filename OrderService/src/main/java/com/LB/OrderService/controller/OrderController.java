package com.LB.OrderService.controller;

import com.LB.OrderService.model.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final RabbitTemplate rabbitTemplate;
    private final String exchange = "orderExchange";

    public OrderController (RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping
    public ResponseEntity<String> createOrder ( @RequestBody Order order ) {
        rabbitTemplate.convertAndSend( exchange, "order.created", order.toString());
        System.out.println(order.toString());
        return ResponseEntity.ok("Order placed successfully!");
    }
}
