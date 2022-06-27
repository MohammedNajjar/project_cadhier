package com.anas.cashier.RoomDB;

import android.app.Application;

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
import com.anas.cashier.RoomDB.DAO.BillDAO;
import com.anas.cashier.RoomDB.DAO.DebitDAO;
import com.anas.cashier.RoomDB.DAO.DirectorDAO;
import com.anas.cashier.RoomDB.DAO.EmployeeDAO;
import com.anas.cashier.RoomDB.DAO.NotificationDAO;
import com.anas.cashier.RoomDB.DAO.PayDAO;
import com.anas.cashier.RoomDB.DAO.PersonPurchasesDAO;
import com.anas.cashier.RoomDB.DAO.ProductsDAO;
import com.anas.cashier.RoomDB.DAO.SalesDAO;
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

public class Repository {

    private DebitDAO debitDAO;
    private ProductsDAO productsDAO;
    private SalesDAO salesDAO;
    private DirectorDAO directorDAO;
    private EmployeeDAO employeeDAO;
    private PayDAO payDAO;
    private NotificationDAO notificationDAO;
    private PersonPurchasesDAO personPurchasesDAO;
    private BillDAO billDAO;

    public Repository(Application application) {
        MyDatabase db = MyDatabase.getDatabase(application);
        debitDAO = db.debitDAO();
        productsDAO = db.productsDAO();
        salesDAO = db.salesDAO();
        directorDAO = db.directorDAO();
        employeeDAO = db.employeeDAO();
        payDAO = db.payDAO();
        notificationDAO = db.notificationDAO();
        personPurchasesDAO = db.personPurchasesDAO();
        billDAO = db.billDAO();
    }


    public void InsertDebit(DebitPeople... debit) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                debitDAO.InsertDebit(debit);
            }
        });
    }

    public void UpdateDebit(DebitPeople... debit) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                debitDAO.UpdateDebit(debit);
            }
        });
    }

    public void DeleteOneDebit(int ID, String Email) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                debitDAO.DeleteOneDebit(ID, Email);
            }
        });
    }

    public LiveData<DebitPeople> getAllDebit(int id, String Email) {
        return debitDAO.getAllDebit(id, Email);
    }

    public LiveData<List<DebitPeople>> getItemDebit(String Email) {
        return debitDAO.getItemDebit(Email);
    }

    LiveData<List<DebitPeople>> getAll(String Email) {
        return debitDAO.getAll(Email);
    }

    public LiveData<List<DebitPeople>> getAllMonth(String Email) {
        return debitDAO.getAllMonth(Email);
    }

    public void getItem(int ID, GetItemDebit debit, String Email) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                DebitPeople people = debitDAO.getItem(ID, Email);
                debit.ItemDebit(people);
            }
        });
    }

    public LiveData<List<DebitPeople>> getHighPrice(String Email) {
        return debitDAO.getHighPrice(Email);
    }

    public LiveData<List<DebitPeople>> getLowPrice(String Email) {
        return debitDAO.getLowPrice(Email);
    }

    public LiveData<List<DebitPeople>> getExpiredDebts(String Email) {
        return debitDAO.getExpiredDebts(Email);
    }

    public LiveData<List<DebitPeople>> getFilterDebit(String filter, String Email) {
        return debitDAO.getFilterDebit(filter, Email);
    }

    public void DeleteDebit(String Email) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                debitDAO.DeleteDebit(Email);
            }
        });
    }

    public void DeleteItemDebit(int id, String Email) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                debitDAO.DeleteItemDebit(id, Email);
            }
        });
    }

    public void InsertProducts(Products... products) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                productsDAO.InsertProducts(products);
            }
        });
    }

    public void UpdateProducts(Products... products) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                productsDAO.UpdateProducts(products);
            }
        });
    }

    public LiveData<List<Products>> getAllProducts(String email) {
        return productsDAO.getAllProducts(email);
    }

    public LiveData<List<Products>> getProducts(String email) {
        return productsDAO.getProducts(email);
    }

    public void getListProduct(ListProduct p, String email) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                List<Products> products = productsDAO.getListProducts(email);
                p.getListProduct(products);
            }
        });
    }

    public void getList(String email, long bar, ListProductAdd add) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                boolean pr = productsDAO.getList(email, bar);
                add.ListProduct(pr);
            }
        });
        //add.ListProduct(products);
    }

    //    public LiveData<List<Products>> getSalesAndBuy(int id,String email){
//        return productsDAO.getSalesAndBuy(id,email);
//    }
//    public LiveData<List<Products>> getBill(String email){
//        return productsDAO.getBill(email);
//    }
    public LiveData<List<Products>> getListDepartment(long index, String email) {
        return productsDAO.getListDepartment(index, email);
    }

    public LiveData<Products> getIdDepartment(int Id, String email) {
        return productsDAO.getIdDepartment(Id, email);
    }

    public void getItemBarCode(long BarCode, SellProduct sellProduct, String email) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {

                Products p = productsDAO.getItemBarCode(BarCode, email);
                sellProduct.getProduct(p);
            }
        });

    }

    public LiveData<List<Products>> getFilterProducts(String filter, String email) {
        return productsDAO.getFilterProducts(filter, email);
    }

    public LiveData<List<Products>> getFilterInventory(String filter, long index, String email) {
        return productsDAO.getFilterInventory(filter, index, email);
    }

    public void DeleteProducts(String email) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                productsDAO.DeleteProducts(email);
            }
        });
    }

    public void DeleteItemProducts(long id, String email) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                productsDAO.DeleteItemProducts(id, email);
            }
        });
    }

    public void SumTotalProductAll(SumTotalProductAll all, String email) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                double Number = productsDAO.SumTotalProductAll(email);
                all.TotalProduct(Number);
            }
        });
    }

    public void SumTotalDiscount(SumDiscountProduct product, String email) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                double Number = productsDAO.SumTotalDiscount(email);
                product.DiscountProduct(Number);
            }
        });
    }

    public void SumPriceBuyProduct(SumQuantityProduct product, String email) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                int number = productsDAO.SumPriceBuyProduct(email);
                product.SumQuantity(number);

            }
        });
    }

    public void SumPriceSellingProduct(SumQuantityProduct product, String email) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                int number = productsDAO.SumPriceSellingProduct(email);
                product.SumQuantity(number);
            }
        });
    }

    public void SumFinalTotal(FinalTotalProduct product, String email) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                double Number = productsDAO.SumFinalTotal(email);
                product.TotalProduct(Number);
            }
        });
    }

    public void IdProduct(Long bar, IdProduct product, String email) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                int i = productsDAO.IdProduct(bar, email);
                product.IdProducts(i);
            }
        });
    }

    public void CountProduct(String email, CountProduct count) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                int c = productsDAO.CountProduct(email);
                count.count(c);
            }
        });
    }

    public void InsertSales(Sales... sales) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                salesDAO.InsertSales(sales);
            }
        });
    }

    public void UpdateSales(Sales... sales) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                salesDAO.UpdateSales(sales);
            }
        });
    }

    public void InsertDirector(Director... directors) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                directorDAO.InsertDirector(directors);
            }
        });
    }

    public void UpdateDirector(Director... directors) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                directorDAO.UpdateDirector(directors);
            }
        });
    }

    public LiveData<List<Director>> getAllDirector() {
        return directorDAO.getAllDirector();
    }

    public LiveData<List<Director>> getFilterDirector(String filter) {
        return directorDAO.getFilterDirector(filter);
    }

    public void DeleteDirector() {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                directorDAO.DeleteDirector();
            }
        });
    }

    public void DeleteItemDirector(int id) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                directorDAO.DeleteItemDirector(id);
            }
        });
    }

    public void InsertEmployee(Employee... employees) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                employeeDAO.InsertEmployee(employees);
            }
        });
    }

    public void UpdateEmployee(Employee... employees) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                employeeDAO.UpdateEmployee(employees);
            }
        });
    }

    public LiveData<List<Employee>> getAllEmployee() {
        return employeeDAO.getAllEmployee();
    }

    public LiveData<List<Employee>> getFilterEmployee(String filter) {
        return employeeDAO.getFilterEmployee(filter);
    }

    public void DeleteEmployee() {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                employeeDAO.DeleteEmployee();
            }
        });
    }

    public void DeleteItemEmployee(int id) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                employeeDAO.DeleteItemEmployee(id);
            }
        });
    }

    public void InsertPay(Paying... payings) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                payDAO.InsertPay(payings);
            }
        });
    }

    public void SumTotalAll(TotalDebtAll debtAll, String Email) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                double s = debitDAO.SumDebitAll(Email);
                debtAll.TotalDebt(s);
            }
        });
    }

    public void SumDebitPerson(int id, TotalSincereDebtPerson person, String Email) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                double d = debitDAO.SumDebitPerson(id, Email);
                person.TotalDebtPerson(d);
            }
        });
    }

    public void SumExpiredDebts(TotalExpiredDebts expiredDebts, String Email) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {

                double Ed = debitDAO.SumExpiredDebts(Email);
                expiredDebts.ExpiredDebts(Ed);
            }
        });
    }

    public void SumDebtToday(Date date, SumDebitToday debitToday, String Email) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                Double today = debitDAO.SumDebtToday(date, Email);
                debitToday.DebitToday(today);
            }
        });
    }

    public void SumHighPriceDebts(TotalHighPriceDebts highDebts, String Email) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                double Hp = debitDAO.SumHighPriceDebts(Email);
                highDebts.HighPriceDebts(Hp);
            }
        });
    }

    public void SumLowPriceDebts(TotalLowPriceDebts lowDebts, String Email) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                double Lp = debitDAO.SumLowPriceDebts(Email);
                lowDebts.LowPriceDebts(Lp);
            }
        });
    }

    public void CountDebts(String email, CountDebt count) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                int c = debitDAO.CountDebts(email);
                count.count(c);
            }
        });
    }

    public void SumPayPerson(int id, SumPayPerson person) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                double a = payDAO.SumPayPerson(id);
                person.SumPay(a);
            }
        });
    }

    public void TotalAccount(int id, TotalAccount account) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                double total = payDAO.TotalAccount(id);
                account.TotalAccountPerson(total);

            }
        });
    }

    public void InsertNotification(Notification... notifications) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                notificationDAO.InsertNotification(notifications);
            }
        });
    }

    public void DeleteItemNotification(int id, String Email) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                notificationDAO.DeleteItemNotification(id, Email);
            }
        });
    }

    public void CountNotificationService(String email, CountNotification c) {

        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                int count = notificationDAO.CountNotificationService(email);
                c.count(count);
            }
        });
    }

    public LiveData<Integer> CountNotification(String email) {
        return notificationDAO.CountNotification(email);
    }


    public LiveData<List<Notification>> getAllNotification(String Email) {
        return notificationDAO.getAllNotification(Email);
    }

    public void InsertPurchases(PersonPurchases... person) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                personPurchasesDAO.InsertPurchases(person);
            }
        });
    }

    public void UpdatePerson(PersonPurchases... purchases) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                personPurchasesDAO.UpdatePerson(purchases);
            }
        });
    }

    public LiveData<List<PersonPurchases>> getPerson() {
        return personPurchasesDAO.getPerson();
    }

    public LiveData<List<PersonPurchases>> getFilterPerson(String filter) {
        return personPurchasesDAO.getFilterPerson(filter);
    }

    public void getPersonBar(long bar, PersonList list) {

        PersonPurchases p = personPurchasesDAO.getPersonBar(bar);
        list.listPerson(p);
    }

    public void DeletePerson() {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                personPurchasesDAO.DeletePerson();
            }
        });
    }

    public void DeleteOnePerson(long id) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                personPurchasesDAO.DeleteOnePerson(id);
            }
        });
    }

    public LiveData<Double> SumFinalTotalPerson() {
        return personPurchasesDAO.SumFinalTotalPerson();
    }

    public LiveData<Double> SumTotal() {
        return personPurchasesDAO.SumTotal();
    }

    public void InsertBill(Bill... bills) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                billDAO.InsertBill(bills);
            }
        });
    }

    public LiveData<List<Bill>> getBill(String email) {
        return billDAO.getBill(email);
    }

    public LiveData<List<Bill>> getSalesAndBuy(int Id, String email) {
        return billDAO.getSalesAndBuy(Id, email);
    }

    public void getListBill(long bar, ListBill bill) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                boolean item = personPurchasesDAO.getListPerson(bar);
                bill.ItemBill(item);
            }
        });
    }
}
