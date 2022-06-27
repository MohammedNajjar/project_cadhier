package com.anas.cashier.RoomDB.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;

import com.anas.cashier.RoomDB.DateConverter;
import com.anas.cashier.RoomDB.Tables.Bill;
import com.anas.cashier.RoomDB.Tables.Products;

import java.util.List;

@Dao
@TypeConverters(DateConverter.class)
public interface BillDAO {

    @Insert
    void InsertBill(Bill... bills);

    @Query("select * from Bill Where Email=:email")
    LiveData<List<Bill>> getBill(String  email);

    @Query("SELECT * FROM Bill where validity=:Id and Email=:email")
    LiveData<List<Bill>> getSalesAndBuy(int Id,String email);

}
