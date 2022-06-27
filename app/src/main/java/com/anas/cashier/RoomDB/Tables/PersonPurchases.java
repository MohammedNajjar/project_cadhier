package com.anas.cashier.RoomDB.Tables;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity
public class PersonPurchases {

    @PrimaryKey
    private long Barcode;
    @NotNull
    private String Name;
    @NotNull
    private String Category;
    @NotNull
    private double PriceSelling;
    @NotNull
    private int Quantity;


    public PersonPurchases(long barcode, @NotNull String name, @NotNull String category, double priceSelling, int quantity) {
        Barcode = barcode;
        Name = name;
        Category = category;
        PriceSelling = priceSelling;
        Quantity = quantity;
    }

    public PersonPurchases() {
    }

    public long getBarcode() {
        return Barcode;
    }

    public void setBarcode(long barcode) {
        Barcode = barcode;
    }

    @NotNull
    public String getName() {
        return Name;
    }

    public void setName(@NotNull String name) {
        Name = name;
    }

    @NotNull
    public String getCategory() {
        return Category;
    }

    public void setCategory(@NotNull String category) {
        Category = category;
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
}
