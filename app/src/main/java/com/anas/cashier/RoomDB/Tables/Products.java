package com.anas.cashier.RoomDB.Tables;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.anas.cashier.RoomDB.DateConverter;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

@Entity
@TypeConverters(DateConverter.class)
public class Products {

    @PrimaryKey(autoGenerate = true)
    private int Id;
    @NotNull
    private String Name;
    @NotNull
    private String Description;
    @NotNull
    private long BarCode;
    @NotNull
    private String Type;
    @NotNull
    private Date DatePurchase;
    @NotNull
    private int Quantity;
    @NotNull
    private double PriceBuy;
    @NotNull
    private double PriceSelling;
    @NotNull
    private String Email;
    @NotNull
    private long IndexSpinner;


    public Products(@NotNull String name, @NotNull String description, long barCode, @NotNull String type, @NotNull Date datePurchase, int quantity,
                    double priceBuy, double priceSelling, @NotNull String email, long indexSpinner) {
        Name = name;
        Description = description;
        BarCode = barCode;
        Type = type;
        DatePurchase = datePurchase;
        Quantity = quantity;
        PriceBuy = priceBuy;
        PriceSelling = priceSelling;
        Email = email;
        IndexSpinner = indexSpinner;
    }

    public Products() {
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
    public String getDescription() {
        return Description;
    }

    public void setDescription(@NotNull String description) {
        Description = description;
    }

    public long getBarCode() {
        return BarCode;
    }

    public void setBarCode(long barCode) {
        BarCode = barCode;
    }

    @NotNull
    public String getType() {
        return Type;
    }

    public void setType(@NotNull String type) {
        Type = type;
    }

    @NotNull
    public Date getDatePurchase() {
        return DatePurchase;
    }

    public void setDatePurchase(@NotNull Date datePurchase) {
        DatePurchase = datePurchase;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
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

    @NotNull
    public String getEmail() {
        return Email;
    }

    public void setEmail(@NotNull String email) {
        Email = email;
    }

    public long getIndexSpinner() {
        return IndexSpinner;
    }

    public void setIndexSpinner(long indexSpinner) {
        IndexSpinner = indexSpinner;
    }
}
