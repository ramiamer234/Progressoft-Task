package com.progressoft.induction;

public class Snack {
    SnackType snackType;
    private int quantity;
    private double price;

    public Snack(SnackType snackType, int quantity, double price) {
        this.snackType = snackType;
        this.quantity = quantity;
        this.price = price;
    }

    public int quantity() {
        return quantity;
    }

    public double price() {
        return price;
    }

    public void decreaseQuantity() {
        this.quantity -= 1;
    }
}
