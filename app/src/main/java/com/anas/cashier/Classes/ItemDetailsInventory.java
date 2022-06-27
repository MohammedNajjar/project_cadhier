package com.anas.cashier.Classes;

public class ItemDetailsInventory {

    private String Name;
    private String Date;
    private String Quertiy;
    private int Buy;
    private int Selling;
    private int image;


    public ItemDetailsInventory(String name, String date, String quertiy, int buy, int selling,int image) {
        Name = name;
        Date = date;
        Quertiy = quertiy;
        Buy = buy;
        Selling = selling;
        this.image=image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getQuertiy() {
        return Quertiy;
    }

    public void setQuertiy(String quertiy) {
        Quertiy = quertiy;
    }

    public int getBuy() {
        return Buy;
    }

    public void setBuy(int buy) {
        Buy = buy;
    }

    public int getSelling() {
        return Selling;
    }

    public void setSelling(int selling) {
        Selling = selling;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
