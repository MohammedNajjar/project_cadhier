package com.anas.cashier.RoomDB.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.anas.cashier.RoomDB.Tables.Director;

import java.util.List;

@Dao
public interface DirectorDAO {

    @Insert
    void InsertDirector(Director... directors);

    @Update
    void UpdateDirector(Director... directors);

    @Query("SELECT * FROM Director ORDER BY Id ASC")
    LiveData<List<Director>> getAllDirector();

    @Query("SELECT * FROM Director Where Id like '%'||:filter||'%'")
    LiveData<List<Director>> getFilterDirector(String filter);

    @Query("DELETE From Director ")
    void DeleteDirector();

    @Query("DELETE FROM Director WHERE Id=:id")
    void DeleteItemDirector(int id);
}
