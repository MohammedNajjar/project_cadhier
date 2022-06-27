package com.anas.cashier.RoomDB.DAO;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.anas.cashier.RoomDB.Tables.Bill;
import com.anas.cashier.RoomDB.Tables.DebitPeople;
import com.anas.cashier.RoomDB.Tables.Products;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Dao
public interface ProductsDAO {

    @Insert
    void InsertProducts(Products... products);

    @Update
    void UpdateProducts(Products... products);

    @Query("SELECT * FROM Products Where Email=:email ORDER BY Name ASC ")
    LiveData<List<Products>> getAllProducts(String email);

    @Query("SELECT * FROM Products Where Email=:email ")
    LiveData<List<Products>> getProducts(String email);

    @Query("SELECT * FROM Products where Email=:email ")
    List<Products> getListProducts(String email);

    @Query("SELECT * FROM Products where Email=:email and BarCode=:bar")
    boolean getList(String email,long bar);

//    @Query("SELECT * FROM Products where Email=:email")
//    LiveData<List<Products>> getSalesAndBuy(int Id,String email);

    @Query("SELECT * FROM PRODUCTS WHERE IndexSpinner=:index  and Email=:email")
    LiveData<List<Products>> getListDepartment(long index,String email);

    @Query("SELECT * FROM Products  Where  Email=:email ORDER BY  Name ASC")
    LiveData<List<Products>> getBill(String email);

    @Query("SELECT * FROM PRODUCTS WHERE Id=:Id and  Email=:email")
    LiveData<Products> getIdDepartment(int Id,String email);

    @Query("SELECT * FROM products WHERE BarCode=:barCode and Email=:email")
    Products getItemBarCode(long barCode,String email);

    @Query("SELECT * FROM Products Where Name like '%'||:filter||'%' and  Email=:email")
    LiveData<List<Products>> getFilterProducts(String filter,String email);

    @Query("SELECT * FROM Products Where Name like '%'||:filter||'%' and IndexSpinner=:index and  Email=:email")
    LiveData<List<Products>> getFilterInventory(String filter,long index,String email);

    @Query("DELETE From Products Where Email=:email")
    void DeleteProducts(String email);

    @Query("DELETE FROM Products WHERE BarCode=:id and Email=:email")
    void DeleteItemProducts(long id,String email);

    @Query("SELECT SUM(PriceBuy) FROM Products where  Email=:email")
    double SumTotalProductAll(String email);

    @Query("SELECT SUM(PriceBuy) FROM Products Where  Email=:email")
    int SumPriceBuyProduct(String email);

    @Query("SELECT SUM(PriceSelling) FROM Products Where  Email=:email ")
    int SumPriceSellingProduct(String email);

    @Query("SELECT SUM(PriceSelling) FROM Products Where Email=:email")
    double SumTotalDiscount(String email);

    @Query("SELECT SUM(PriceBuy)-0.04 FROM Products where Email=:email")
    double SumFinalTotal(String email);

    @Query("Select Id from Products Where BarCode=:bar and Email=:email")
    int IdProduct(Long bar,String email);

    @Query("SELECT COUNT(id) FROM Products WHERE Email=:email")
    int CountProduct(String email);
}
