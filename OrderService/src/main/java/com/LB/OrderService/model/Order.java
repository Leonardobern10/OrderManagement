package com.LB.OrderService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order{
    private String orderId;
    private List<Item> items;

    public Double getTotalPrice () {
        Double total = 0.0;
        for (Item item : items) {
            total += item.getFinalPrice();
        }
        return total;
    }
}
