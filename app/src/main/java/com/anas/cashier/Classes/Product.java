package com.anas.cashier.Classes;

public class Product {

    private String Name;
    private String Quantity;
    private String Category;
    private int price;



    public Product(String name, String quantity, String category, int price) {
        Name = name;
        Quantity = quantity;
        Category = category;
        this.price = price;
    }




    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
