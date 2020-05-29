package com.pillar.product;

/**
 * Created by ian on 9/15/16.
 */
public class Candy implements Product {

    private double price;
    private int quantity;

    public Candy(double price, int quantity) {
        this.price = price;
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
