package com.project.cartimplementation;

public class CartItems {
    String dishName;
    int dishPrice;
    int itemCount;

    public CartItems() {
    }

    public CartItems(String dishName, int dishPrice, int itemCount) {
        this.dishName = dishName;
        this.dishPrice = dishPrice;
        this.itemCount = itemCount;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public int getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(int dishPrice) {
        this.dishPrice = dishPrice;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }
}