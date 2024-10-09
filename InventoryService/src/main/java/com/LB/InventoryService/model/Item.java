package com.LB.InventoryService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private String itemId;
    private String itemName;
    private Integer quantity;
    private Double unitPrice;

    public String getItemName () {
        return itemName;
    }

    public void setItemName ( String itemName ) {
        this.itemName = itemName;
    }

    public int getQuantity () {
        return quantity;
    }

    public void setQuantity ( int quantity ) {
        this.quantity = quantity;
    }

    public Double getUnitPrice () {
        return unitPrice;
    }

    public void setUnitPrice ( Double unitPrice ) {
        this.unitPrice = unitPrice;
    }

    public Double getFinalPrice () {
        return unitPrice * quantity;
    }
    public String getItemId () {
        return itemId;
    }

    public void setItemId ( String itemId ) {
        this.itemId = itemId;
    }
}
