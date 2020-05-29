package com.pillar.coin;

/**
 * Created by ian on 9/15/16.
 */
public class Penny implements Coin {

    private double weight;
    private double size;

    public Penny(double weight, double size) {
        this.weight = weight;
        this.size = size;
    }

    public double getWeight() {
        return weight;
    }

    public double getSize() {
        return size;
    }
}
