package com.anas.cashier.RoomDB.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.anas.cashier.RoomDB.Tables.Notification;

import java.util.List;

@Dao
public interface NotificationDAO {

    @Insert
    void InsertNotification(Notification... notifications);

    @Update
    void UpdateNotification(Notification... update);

    @Query("Select * From Notification Where Email=:email")
    LiveData<List<Notification>> getAllNotification(String email);

    @Query("DELETE FROM Notification WHERE ID=:id and Email=:email")
    void DeleteItemNotification(int id,String email);

    @Query("SELECT COUNT(id) FROM Notification WHERE Email=:email")
    LiveData<Integer> CountNotification(String email);

    @Query("SELECT COUNT(id) FROM Notification WHERE Email=:email")
    int CountNotificationService(String email);
}

