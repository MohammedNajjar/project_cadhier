package com.anas.cashier.RoomDB.Tables;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity
public class Director {

    @PrimaryKey(autoGenerate = true)
    private int ID;
    @NotNull
    private String Name;
    @NotNull
    private long NumberPhone;
    @NotNull
    private String Address;


    public Director(@NotNull String name, long numberPhone, @NotNull String address) {
        Name = name;
        NumberPhone = numberPhone;
        Address = address;
    }

    public Director() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @NotNull
    public String getName() {
        return Name;
    }

    public void setName(@NotNull String name) {
        Name = name;
    }

    public long getNumberPhone() {
        return NumberPhone;
    }

    public void setNumberPhone(long numberPhone) {
        NumberPhone = numberPhone;
    }

    @NotNull
    public String getAddress() {
        return Address;
    }

    public void setAddress(@NotNull String address) {
        Address = address;
    }
}
