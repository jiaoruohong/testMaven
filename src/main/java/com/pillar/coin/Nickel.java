package com.pillar.coin;

/**
 * Created by ian on 9/15/16.
 */
public class Nickel implements Coin {

    private double weight;
    private double size;

    public Nickel(double weight, double size) {
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
