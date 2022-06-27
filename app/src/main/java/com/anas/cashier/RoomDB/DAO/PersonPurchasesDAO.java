package com.anas.cashier.RoomDB.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import com.anas.cashier.RoomDB.DateConverter;
import com.anas.cashier.RoomDB.Tables.DebitPeople;
import com.anas.cashier.RoomDB.Tables.PersonPurchases;
import com.anas.cashier.RoomDB.Tables.Products;

import java.util.Date;
import java.util.List;

@Dao
@TypeConverters(DateConverter.class)
public interface PersonPurchasesDAO {

    @Insert
    void InsertPurchases(PersonPurchases... person);

    @Update
    void UpdatePerson(PersonPurchases... purchases);

    @Query("Select * from PersonPurchases")
    LiveData<List<PersonPurchases>> getPerson();

    @Query("Select * from PersonPurchases Where Barcode=:bar")
    PersonPurchases getPersonBar(long bar);

    @Query("SELECT * FROM PersonPurchases Where Name like '%'||:filter||'%'")
    LiveData<List<PersonPurchases>> getFilterPerson(String filter);

    @Query("DELETE From PersonPurchases")
    void DeletePerson();

    @Query("DELETE From PersonPurchases where Barcode=:id")
    void DeleteOnePerson(long id);

    @Query("SELECT SUM(PriceSelling)-0.04%PriceSelling FROM PersonPurchases")
    LiveData<Double> SumFinalTotalPerson();

    @Query("SELECT SUM(PriceSelling) FROM PersonPurchases")
    LiveData<Double> SumTotal();

    @Query("SELECT * FROM PersonPurchases where BarCode=:bar")
    boolean getListPerson(long bar);


}
