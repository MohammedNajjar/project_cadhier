package com.anas.cashier.RoomDB.Tables;


import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.anas.cashier.RoomDB.DateConverter;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
@Entity
@TypeConverters(DateConverter.class)
public class Bill {

    @PrimaryKey(autoGenerate = true)
    private int Id;
    @NotNull
    private String Name;
    @NotNull
    private Date DatePurchase;
    @NotNull
    private int Validity;
    @NotNull
    private double PriceBuy;
    @NotNull
    private double PriceSelling;
    @NotNull
    private int Quantity;
    @NotNull
    private String Email;

    public Bill(@NotNull String name, @NotNull Date datePurchase,
                int validity, double priceBuy, double priceSelling, int quantity,String email) {
        Name = name;
        DatePurchase = datePurchase;
        Validity = validity;
        PriceBuy = priceBuy;
        PriceSelling = priceSelling;
        Quantity = quantity;
        Email=email;
    }

    public Bill() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    @NotNull
    public String getName() {
        return Name;
    }

    public void setName(@NotNull String name) {
        Name = name;
    }

    @NotNull
    public Date getDatePurchase() {
        return DatePurchase;
    }

    public void setDatePurchase(@NotNull Date datePurchase) {
        DatePurchase = datePurchase;
    }

    public int getValidity() {
        return Validity;
    }

    public void setValidity(int validity) {
        Validity = validity;
    }

    public double getPriceBuy() {
        return PriceBuy;
    }

    public void setPriceBuy(double priceBuy) {
        PriceBuy = priceBuy;
    }

    public double getPriceSelling() {
        return PriceSelling;
    }

    public void setPriceSelling(double priceSelling) {
        PriceSelling = priceSelling;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    @NotNull
    public String getEmail() {
        return Email;
    }

    public void setEmail(@NotNull String email) {
        Email = email;
    }
}
