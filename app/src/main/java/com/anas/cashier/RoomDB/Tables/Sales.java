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

public class Sales {

    @PrimaryKey(autoGenerate = true)
    private long BarCode;
    @NotNull
    private Date PurchaseTime;
    @NotNull
    private double Quantity;


    public Sales(@NotNull Date purchaseTime, double quantity, long barCode) {
        PurchaseTime = purchaseTime;
        Quantity = quantity;
        BarCode = barCode;
    }

    public Sales() {
    }


    @NotNull
    public Date getPurchaseTime() {
        return PurchaseTime;
    }

    public void setPurchaseTime(@NotNull Date purchaseTime) {
        PurchaseTime = purchaseTime;
    }

    public double getQuantity() {
        return Quantity;
    }

    public void setQuantity(double quantity) {
        Quantity = quantity;
    }

    public long getBarCode() {
        return BarCode;
    }

    public void setBarCode(long barCode) {
        BarCode = barCode;
    }

}
