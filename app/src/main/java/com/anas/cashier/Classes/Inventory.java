package com.anas.cashier.Classes;

import com.anas.cashier.RoomDB.Tables.Products;

public class Inventory {

    private String Title;
    private int image;
    private long Index;
    private Products products;

    public Inventory(String title, int image,long index) {
        Title = title;
        this.image = image;
        this.Index=index;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public long getIndex() {
        return Index;
    }

    public void setIndex(long index) {
        Index = index;
    }
}
