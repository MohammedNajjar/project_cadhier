package com.anas.cashier.Classes;

public class Item_Onboarding {

    private int Image;
    private String Title;
    private String description;

    public Item_Onboarding(int image, String title, String description) {
        Image = image;
        Title = title;
        this.description = description;
    }

    public Item_Onboarding() {
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
