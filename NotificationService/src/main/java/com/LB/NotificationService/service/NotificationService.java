package com.LB.NotificationService.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @RabbitListener(queues = "inventoryQueue")
    public void sendNotification (String status) {
        System.out.println("Notificação enviada ao cliente: " + status);
    }
}
