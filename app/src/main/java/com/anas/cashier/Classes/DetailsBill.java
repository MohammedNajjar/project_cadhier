package com.anas.cashier.Classes;

public class DetailsBill {

    private String Name_pro;
    private String Date;
    private String Buy;
    private double Buy_price;
    private double Buy_sell;
    private double Quantity;
    private String Profit;


    public DetailsBill(String name_pro, String date, String buy, double buy_price, double buy_sell, double quantity, String profit) {
        Name_pro = name_pro;
        Date = date;
        Buy = buy;
        Buy_price = buy_price;
        Buy_sell = buy_sell;
        Quantity = quantity;
        Profit = profit;
    }

    public String getName_pro() {
        return Name_pro;
    }

    public void setName_pro(String name_pro) {
        Name_pro = name_pro;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getBuy() {
        return Buy;
    }

    public void setBuy(String buy) {
        Buy = buy;
    }

    public double getBuy_price() {
        return Buy_price;
    }

    public void setBuy_price(double buy_price) {
        Buy_price = buy_price;
    }

    public double getBuy_sell() {
        return Buy_sell;
    }

    public void setBuy_sell(double buy_sell) {
        Buy_sell = buy_sell;
    }

    public double getQuantity() {
        return Quantity;
    }

    public void setQuantity(double quantity) {
        Quantity = quantity;
    }

    public String getProfit() {
        return Profit;
    }

    public void setProfit(String profit) {
        Profit = profit;
    }
}
