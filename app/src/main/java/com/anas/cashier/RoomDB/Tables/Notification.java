package com.anas.cashier.RoomDB.Tables;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.anas.cashier.RoomDB.DateConverter;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

@Entity
@TypeConverters(DateConverter.class)
public class Notification {

    @PrimaryKey(autoGenerate = true)
    private int ID;
    @NotNull
    private String details;
    @NotNull
    private int Image;
    @NotNull
    private Date date;
    @NotNull
    private String Email;


    public Notification() {
    }

    public Notification(@NotNull String details, int image, @NotNull Date date,String email) {
        this.details = details;
        Image = image;
        this.date = date;
        Email=email;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @NotNull
    public String getDetails() {
        return details;
    }

    public void setDetails(@NotNull String details) {
        this.details = details;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    @NotNull
    public Date getDate() {
        return date;
    }

    public void setDate(@NotNull Date date) {
        this.date = date;
    }

    @NotNull
    public String getEmail() {
        return Email;
    }

    public void setEmail(@NotNull String email) {
        Email = email;
    }
}
