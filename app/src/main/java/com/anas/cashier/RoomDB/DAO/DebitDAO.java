package com.anas.cashier.RoomDB.DAO;

import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import com.anas.cashier.RoomDB.DateConverter;
import com.anas.cashier.RoomDB.Tables.Bill;
import com.anas.cashier.RoomDB.Tables.DebitPeople;

import java.util.Date;
import java.util.List;

@Dao
@TypeConverters(DateConverter.class)
public interface DebitDAO {

    @Insert
    void InsertDebit(DebitPeople... debit);

    @Update
    void UpdateDebit(DebitPeople... debit);

    @Query("DELETE FROM DebitPeople WHERE Id=:ID and Email=:email")
    void DeleteOneDebit(int ID,String email);

    @Query("SELECT * FROM DebitPeople  Where Id=:id and Email=:email ORDER BY TotalDebt ASC")
    LiveData<DebitPeople> getAllDebit(int id,String email);

    @Query("SELECT *  from DebitPeople Where Email=:email")
    LiveData<List<DebitPeople>> getAllMonth(String email);

    @Query("SELECT * FROM DebitPeople Where Email=:email")
    LiveData<List<DebitPeople>> getAll(String email);

    @Query("SELECT * FROM DebitPeople Where Id=:ID and Email=:email ")
    DebitPeople getItem(int ID,String email);

    @Query("SELECT * FROM DEBITPEOPLE WHERE TotalDebt >= 100 and Email=:email")
    LiveData<List<DebitPeople>> getHighPrice(String email);

    @Query("SELECT * FROM DebitPeople WHERE TotalDebt < 100 and TotalDebt>0 and Email=:email")
    LiveData<List<DebitPeople>> getLowPrice(String email);

    @Query("SELECT * FROM DEBITPEOPLE WHERE TotalDebt == 0 and Email=:email")
    LiveData<List<DebitPeople>> getExpiredDebts(String email);

    @Query("SELECT * FROM DebitPeople Where Email=:email ORDER BY TotalDebt ASC")
    LiveData<List<DebitPeople>> getItemDebit(String email);

    @Query("SELECT * FROM DebitPeople Where Name like '%'||:filter||'%' and Email=:email")
    LiveData<List<DebitPeople>> getFilterDebit(String filter,String email);

    @Query("DELETE From DebitPeople where  Email=:email")
    void DeleteDebit(String email);

    @Query("DELETE FROM DebitPeople WHERE Id=:id and Email=:email")
    void DeleteItemDebit(int id,String email);

    @Query("SELECT SUM(TotalDebt) FROM DebitPeople Where Email=:email")
    double SumDebitAll(String email);

    @Query("SELECT SUM(TotalDebt) FROM DebitPeople Where Id=:id and Email=:email")
    double SumDebitPerson(int id,String email);

    @Query("SELECT SUM(TotalDebt) FROM DebitPeople WHERE Date =:date and Email=:email" )
    double SumDebtToday(Date date,String email);

    @Query("SELECT SUM(TotalDebt) FROM DEBITPEOPLE WHERE TotalDebt == 0 and Email=:email")
    double SumExpiredDebts(String email);

    @Query("SELECT SUM(TotalDebt) FROM DEBITPEOPLE WHERE TotalDebt >= 100 and Email=:email")
    double SumHighPriceDebts(String email);

    @Query("SELECT SUM(TotalDebt) FROM DEBITPEOPLE WHERE TotalDebt < 100 and Email=:email")
    double SumLowPriceDebts(String email);

    @Query("SELECT COUNT(id) FROM DebitPeople WHERE Email=:email")
    int CountDebts(String email);

}
