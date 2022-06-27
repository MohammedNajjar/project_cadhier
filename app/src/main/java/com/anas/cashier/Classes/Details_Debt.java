package com.anas.cashier.Classes;

public class Details_Debt {

    private String Name;
    private String Rated_Debt;
    private int Total_Debt;


    public Details_Debt(String name, String rated_Debt, int total_Debt) {
        Name = name;
        Rated_Debt = rated_Debt;
        Total_Debt = total_Debt;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRated_Debt() {
        return Rated_Debt;
    }

    public void setRated_Debt(String rated_Debt) {
        Rated_Debt = rated_Debt;
    }

    public int getTotal_Debt() {
        return Total_Debt;
    }

    public void setTotal_Debt(int total_Debt) {
        Total_Debt = total_Debt;
    }
}
