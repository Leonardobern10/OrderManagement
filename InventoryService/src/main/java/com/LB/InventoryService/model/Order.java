package com.LB.InventoryService.model;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String orderId;
    private List<Item> items;

    public String getOrderId () {
        return orderId;
    }
    public List<Item> getItems() { return items; }
    public Double getTotalPrice () {
        Double total = 0.0;
        for (Item item : items) {
            total += item.getFinalPrice();
        }
        return total;
    }
}
