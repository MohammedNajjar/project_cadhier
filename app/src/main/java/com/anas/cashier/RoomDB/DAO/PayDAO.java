package com.anas.cashier.RoomDB.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.anas.cashier.RoomDB.Tables.DebitPeople;
import com.anas.cashier.RoomDB.Tables.Paying;

import java.util.List;

@Dao
public interface PayDAO {

    @Insert
    void InsertPay(Paying... payings);

    @Update
    void UpdatePay(Paying... payings);

    @Query("SELECT * FROM Paying  Where Id=:id ORDER BY Id ASC")
    LiveData<Paying> getAllPaying(int id);

    @Query("SELECT * FROM Paying ORDER BY Id ASC")
    LiveData<List<Paying>> getItemPaying();

    @Query("SELECT * FROM Paying Where Id like '%'||:filter||'%'")
    LiveData<List<Paying>> getFilterPaying(String filter);

    @Query("DELETE From Paying ")
    void DeletePaying();

    @Query("DELETE FROM Paying WHERE Id=:id")
    void DeleteItemPaying(int id);

    @Query("SELECT SUM(Paying.paying) FROM Paying Inner Join" +
            " DebitPeople On paying.DebitId = DebitPeople.Id  Where debitpeople.Id=:id")
    double SumPayPerson(int id);

    @Query("SELECT (SUM (DebitPeople.TotalDebt)-SUM (Paying.paying))  FROM DebitPeople  Join" +
            " Paying On  DebitPeople.Id = Paying.DebitId Where debitpeople.Id=:id")
    double TotalAccount(int id);
}