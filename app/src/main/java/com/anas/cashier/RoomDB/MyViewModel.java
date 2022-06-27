package com.anas.cashier.RoomDB;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.anas.cashier.Interface.CountDebt;
import com.anas.cashier.Interface.CountNotification;
import com.anas.cashier.Interface.CountProduct;
import com.anas.cashier.Interface.FinalTotalProduct;
import com.anas.cashier.Interface.GetItemDebit;
import com.anas.cashier.Interface.ListBill;
import com.anas.cashier.Interface.ListProduct;
import com.anas.cashier.Interface.ListProductAdd;
import com.anas.cashier.Interface.PersonList;
import com.anas.cashier.Interface.SellProduct;
import com.anas.cashier.Interface.IdProduct;
import com.anas.cashier.Interface.SumDebitToday;
import com.anas.cashier.Interface.SumDiscountProduct;
import com.anas.cashier.Interface.SumFinalTotalPerson;
import com.anas.cashier.Interface.SumPayPerson;
import com.anas.cashier.Interface.SumQuantityProduct;
import com.anas.cashier.Interface.SumTotal;
import com.anas.cashier.Interface.TotalAccount;
import com.anas.cashier.Interface.TotalDebtAll;
import com.anas.cashier.Interface.TotalExpiredDebts;
import com.anas.cashier.Interface.TotalHighPriceDebts;
import com.anas.cashier.Interface.TotalLowPriceDebts;
import com.anas.cashier.Interface.SumTotalProductAll;
import com.anas.cashier.Interface.TotalSincereDebtPerson;
import com.anas.cashier.RoomDB.Tables.Bill;
import com.anas.cashier.RoomDB.Tables.DebitPeople;
import com.anas.cashier.RoomDB.Tables.Director;
import com.anas.cashier.RoomDB.Tables.Employee;
import com.anas.cashier.RoomDB.Tables.Notification;
import com.anas.cashier.RoomDB.Tables.Paying;
import com.anas.cashier.RoomDB.Tables.PersonPurchases;
import com.anas.cashier.RoomDB.Tables.Products;
import com.anas.cashier.RoomDB.Tables.Sales;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyViewModel extends AndroidViewModel {


    private Repository repository;
    public MyViewModel(@NonNull Application application) {
        super(application);
        repository=new Repository(application);
    }


    public void InsertDebit(DebitPeople... debit){

        repository.InsertDebit(debit);
    }
    public void UpdateDebit(DebitPeople... debit){

        repository.UpdateDebit(debit);
    }
    public void DeleteOneDebit(int ID,String Email){
        repository.DeleteOneDebit(ID,Email);
    }
    public LiveData<DebitPeople> getAllDebit(int id,String Email){
        return repository.getAllDebit(id,Email);
    }
    public LiveData<List<DebitPeople>> getItemDebit(String Email){
        return repository.getItemDebit(Email);
    }
    public LiveData<List<DebitPeople>> getAll(String Email){
        return repository.getAll(Email);
    }
    public LiveData<List<DebitPeople>> getAllMonth(String Email){
        return repository.getAllMonth(Email);
    }
    public void getItem(int ID, GetItemDebit debit,String Email){
        repository.getItem(ID,debit,Email);
    }
    public  LiveData<List<DebitPeople>> getHighPrice(String Email){
        return repository.getHighPrice(Email);
    }
    public LiveData<List<DebitPeople>> getLowPrice(String Email){
        return repository.getLowPrice(Email);
    }
    public LiveData<List<DebitPeople>> getExpiredDebts(String Email){
        return repository.getExpiredDebts(Email);
    }
    public LiveData<List<DebitPeople>> getFilterDebit(String filter,String Email){
        return repository.getFilterDebit(filter,Email);
    }
    public void DeleteDebit(String Email){

        repository.DeleteDebit(Email);
    }
    public void DeleteItemDebit(int id,String Email){

        repository.DeleteItemDebit(id,Email);
    }

    public void InsertProducts(Products... products){

        repository.InsertProducts(products);
    }
    public void UpdateProducts(Products... products){

        repository.UpdateProducts(products);
    }
    public LiveData<List<Products>> getAllProducts(String email){
        return repository.getAllProducts(email);
    }
    public LiveData<List<Products>> getProducts(String email){
        return repository.getProducts(email);
    }
    public void getListProduct(ListProduct p,String email){
        repository.getListProduct(p,email);
    }
    public LiveData<List<Bill>> getSalesAndBuy(int id,String email){
        return repository.getSalesAndBuy(id,email);
    }

    public void getList(String email,long bar,ListProductAdd add){
        repository.getList(email,bar,add);
    }

    public LiveData<List<Bill>> getBill(String  email){
        return repository.getBill(email);
    }

    public LiveData<List<Products>> getListDepartment(long index,String email){
        return repository.getListDepartment(index,email);
    }
    public LiveData<Products> getIdDepartment(int Id,String email){
        return repository.getIdDepartment(Id,email);
    }
    public void getItemBarCode(long BarCode, SellProduct sellProduct,String email){

        repository.getItemBarCode(BarCode, sellProduct,email);

    }
    public LiveData<List<Products>> getFilterProducts(String filter,String email){
        return repository.getFilterProducts(filter,email);
    }
    public LiveData<List<Products>> getFilterInventory(String filter,long index,String email){
        return repository.getFilterInventory(filter,index,email);
    }
    public void DeleteProducts(String email){

        repository.DeleteProducts(email);
    }
    public void DeleteItemProducts(long id,String email){

        repository.DeleteItemProducts(id,email);
    }

    public void SumTotalProductAll(SumTotalProductAll all,String email){
        repository.SumTotalProductAll(all,email);
    }
    public void SumTotalDiscount(SumDiscountProduct product,String email){
        repository.SumTotalDiscount(product,email);
    }
    public void SumQuantityProduct(SumQuantityProduct product,String email){
        repository.SumPriceBuyProduct(product,email);
    }
    public void SumFinalTotal(FinalTotalProduct product,String email){
        repository.SumFinalTotal(product,email);
    }
    public void IdProduct(Long bar, IdProduct product,String email){
        repository.IdProduct(bar,product,email);
    }
    public void CountProduct(String email, CountProduct count){
        repository.CountProduct(email,count);
    }

    public void InsertSales(Sales... sales){

        repository.InsertSales(sales);
    }
    public void UpdateSales(Sales... sales){
        repository.UpdateSales(sales);
    }
    public void InsertDirector(Director... directors){
        repository.InsertDirector(directors);
    }
    public void UpdateDirector(Director... directors){
        repository.UpdateDirector(directors);
    }
    public LiveData<List<Director>> getAllDirector(){
        return repository.getAllDirector();
    }
    public LiveData<List<Director>> getFilterDirector(String filter){
        return repository.getFilterDirector(filter);
    }
    public void DeleteDirector(){
        repository.DeleteDirector();
    }
    public void DeleteItemDirector(int id){
        repository.DeleteItemDirector(id);
    }


    public void InsertEmployee(Employee... employees){
        repository.InsertEmployee(employees);
    }
    public void UpdateEmployee(Employee... employees){
        repository.UpdateEmployee(employees);
    }
    public LiveData<List<Employee>> getAllEmployee(){
        return repository.getAllEmployee();
    }
    public LiveData<List<Employee>> getFilterEmployee(String filter){
        return repository.getFilterEmployee(filter);
    }
    public void DeleteEmployee(){
        repository.DeleteEmployee();
    }
    public void DeleteItemEmployee(int id){
        repository.DeleteItemEmployee(id);
    }



    public void InsertPay(Paying... payings){
        repository.InsertPay(payings);
    }
    public void SumTotalAll(TotalDebtAll debtAll,String Email){
        repository.SumTotalAll(debtAll,Email);
    }
    public void SumDebitPerson(int id, TotalSincereDebtPerson person,String Email){
        repository.SumDebitPerson(id,person,Email);
    }
    public void SumExpiredDebts(TotalExpiredDebts expiredDebts,String Email){

        repository.SumExpiredDebts(expiredDebts,Email);
    }
    public void SumHighPriceDebts(TotalHighPriceDebts highDebts,String Email){
        repository.SumHighPriceDebts(highDebts,Email);
    }
    public void SumDebtToday(Date date, SumDebitToday debitToday,String Email){
        repository.SumDebtToday(date,debitToday,Email);
    }
    public void SumLowPriceDebts(TotalLowPriceDebts lowDebts,String Email){

        repository.SumLowPriceDebts(lowDebts,Email);
    }

    public void CountDebts(String email, CountDebt count){
        repository.CountDebts(email,count);
    }
    public void SumPayPerson(int id, SumPayPerson person){
        repository.SumPayPerson(id, person);
    }
    public void TotalAccount(int id, TotalAccount account){
        repository.TotalAccount(id,account);
    }

    public void DeleteItemNotification(int id,String Email){
        repository.DeleteItemNotification(id,Email);
    }
//    public void CountNotification(String email){
//        repository.CountNotification(email);
//    }
    public LiveData<List<Notification>> getAllNotification(String Email){
        return repository.getAllNotification(Email);
    }
    public LiveData<Integer> CountNotification(String email){
        return repository.CountNotification(email);
    }

    public void InsertPurchases(PersonPurchases... person){
        repository.InsertPurchases(person);
    }

    public void UpdatePerson(PersonPurchases... purchases){
        repository.UpdatePerson(purchases);
    }

    public LiveData<List<PersonPurchases>> getPerson(){
        return repository.getPerson();
    }
    public LiveData<List<PersonPurchases>> getFilterPerson(String filter){
        return repository.getFilterPerson(filter);
    }

    public void getPersonBar(long bar, PersonList list){
         repository.getPersonBar(bar,list);
    }

    public void DeletePerson(){
        repository.DeletePerson();
    }

    public void DeleteOnePerson(long id){
        repository.DeleteOnePerson(id);
    }
    public LiveData<Double> SumFinalTotalPerson(){
       return repository.SumFinalTotalPerson();
    }

    public LiveData<Double> SumTotal(){
        return repository.SumTotal();
    }

    public void InsertBill(Bill... bills){
        repository.InsertBill(bills);
    }

    public void getListBill(long bar, ListBill bill){

        repository.getListBill(bar,bill);
    }
}
