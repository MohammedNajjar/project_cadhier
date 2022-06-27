package com.anas.cashier.RoomDB.Tables;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.anas.cashier.RoomDB.DateConverter;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

@Entity(foreignKeys = {
        @ForeignKey(entity = DebitPeople.class, parentColumns = {"Id"},
                childColumns = {"DebitId"}, onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)
})
@TypeConverters(DateConverter.class)
public class Paying {

    @PrimaryKey(autoGenerate = true)
    public int Id;
    @NotNull
    public Date Date;
    @NotNull
    public String Note;
    @NotNull
    public double paying;
    public int DebitId;

    public Paying(@NotNull java.util.Date date, @NotNull String note, double paying, int debitId) {
        Date = date;
        Note = note;
        this.paying = paying;
        DebitId = debitId;
    }

    public Paying() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    @NotNull
    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(@NotNull java.util.Date date) {
        Date = date;
    }

    @NotNull
    public String getNote() {
        return Note;
    }

    public void setNote(@NotNull String note) {
        Note = note;
    }

    public double getPaying() {
        return paying;
    }

    public void setPaying(double paying) {
        this.paying = paying;
    }

    public int getDebitId() {
        return DebitId;
    }

    public void setDebitId(int debitId) {
        DebitId = debitId;
    }
}
