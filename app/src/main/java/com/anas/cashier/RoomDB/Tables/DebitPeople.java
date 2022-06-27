package com.anas.cashier.RoomDB.Tables;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.anas.cashier.RoomDB.DateConverter;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

@Entity
@TypeConverters(DateConverter.class)
public class DebitPeople {

    @PrimaryKey(autoGenerate = true)
    private int Id;
    @NotNull
    private String Name;
    @NotNull
    private long NumberPhone;
    @NotNull
    private String Address;
    private String Notes;
    private double DebtPaid;
    private double TotalDebt;
    @NotNull
    private double PayingAmount;
    private Date Date;
    private String Email;


    public DebitPeople(@NotNull String name, long numberPhone, @NotNull String address,
                       double debtPaid, double totalDebt, @NotNull java.util.Date date,String Notes,double PayingAmount,String email) {
        Name = name;
        NumberPhone = numberPhone;
        Address = address;
        DebtPaid = debtPaid;
        TotalDebt = totalDebt;
        Date = date;
        this.Notes=Notes;
        this.PayingAmount=PayingAmount;
        Email=email;
    }
    public DebitPeople(int id,@NotNull String name, long numberPhone, @NotNull String address,
                       double debtPaid, double totalDebt, @NotNull java.util.Date date,String Notes,double PayingAmount,String email) {
        Name = name;
        NumberPhone = numberPhone;
        Address = address;
        DebtPaid = debtPaid;
        TotalDebt = totalDebt;
        Date = date;
        this.Notes=Notes;
        this.PayingAmount=PayingAmount;
        this.Id=id;
        Email=email;
    }


    public DebitPeople() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public double getPayingAmount() {
        return PayingAmount;
    }

    public void setPayingAmount(double payingAmount) {
        PayingAmount = payingAmount;
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

    public double getDebtPaid() {
        return DebtPaid;
    }

    public void setDebtPaid(double debtPaid) {
        DebtPaid = debtPaid;
    }

    public double getTotalDebt() {
        return TotalDebt;
    }

    public void setTotalDebt(double totalDebt) {
        TotalDebt = totalDebt;
    }

    @NotNull
    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(@NotNull java.util.Date date) {
        Date = date;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
