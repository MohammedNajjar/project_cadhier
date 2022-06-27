package com.anas.cashier.Classes;

public class User {


    private String Id;
    private String Email;
    private String NameShop;
    private String NameOwn;
    private String password;
    private String Image;
    private String MobileNumber;


    public User(String email, String nameShop, String password, String mobileNumber, String nameOwn) {
        Email = email;
        NameShop = nameShop;
        this.password = password;
        MobileNumber = mobileNumber;
        this.NameOwn = nameOwn;
    }

    public User() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getNameShop() {
        return NameShop;
    }

    public void setNameShop(String nameShop) {
        NameShop = nameShop;
    }

    public String getNameOwn() {
        return NameOwn;
    }

    public void setNameOwn(String nameOwn) {
        NameOwn = nameOwn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }
}
