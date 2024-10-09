package com.LB.InventoryService.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String orderId;
    private String item;
    private int quantity;

    public String getOrderId () {
        return orderId;
    }
}
