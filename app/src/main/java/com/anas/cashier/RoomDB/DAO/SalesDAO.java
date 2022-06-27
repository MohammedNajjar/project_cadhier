package com.anas.cashier.RoomDB.DAO;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.anas.cashier.RoomDB.Tables.DebitPeople;
import com.anas.cashier.RoomDB.Tables.Sales;

import java.util.List;

@Dao
public interface SalesDAO {

    @Insert
    void InsertSales(Sales... sales);

    @Update
    void UpdateSales(Sales... sales);


    @Query("DELETE From Sales ")
    void DeleteSales();

}
