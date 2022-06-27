package com.anas.cashier.RoomDB.Tables;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(foreignKeys = {
        @ForeignKey(entity = Director.class, parentColumns = {"ID"},
                childColumns = {"DirectorID"}, onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)
})
public class Employee {

    @PrimaryKey(autoGenerate = true)
    private int ID;
    @NotNull
    private String Name;
    @NotNull
    private double Salary;
    @NotNull
    private String Address;
    @NotNull
    private long PhoneNumber;
    @NotNull
    private String JobTitle;
    @NotNull
    private int DirectorID;

    public Employee(@NotNull String name, double salary,
                    @NotNull String address, long phoneNumber, @NotNull String jobTitle, int directorID) {
        Name = name;
        Salary = salary;
        Address = address;
        PhoneNumber = phoneNumber;
        JobTitle = jobTitle;
        DirectorID = directorID;
    }

    public Employee() {
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

    public double getSalary() {
        return Salary;
    }

    public void setSalary(double salary) {
        Salary = salary;
    }

    @NotNull
    public String getAddress() {
        return Address;
    }

    public void setAddress(@NotNull String address) {
        Address = address;
    }

    public long getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    @NotNull
    public String getJobTitle() {
        return JobTitle;
    }

    public void setJobTitle(@NotNull String jobTitle) {
        JobTitle = jobTitle;
    }

    public int getDirectorID() {
        return DirectorID;
    }

    public void setDirectorID(int directorID) {
        DirectorID = directorID;
    }
}
