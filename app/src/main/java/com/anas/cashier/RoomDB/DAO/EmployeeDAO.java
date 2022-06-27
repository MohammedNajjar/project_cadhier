package com.anas.cashier.RoomDB.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.anas.cashier.RoomDB.Tables.Bill;
import com.anas.cashier.RoomDB.Tables.Employee;

import java.util.List;

@Dao
public interface EmployeeDAO {

    @Insert
    void InsertEmployee(Employee... employees);

    @Update
    void UpdateEmployee(Employee... employees);

    @Query("SELECT * FROM Employee ORDER BY Name ASC")
    LiveData<List<Employee>> getAllEmployee();

    @Query("SELECT * FROM Employee Where Name like '%'||:filter||'%'")
    LiveData<List<Employee>> getFilterEmployee(String filter);

    @Query("DELETE From Employee ")
    void DeleteEmployee();

    @Query("DELETE FROM Employee WHERE Id=:id")
    void DeleteItemEmployee(int id);
}
