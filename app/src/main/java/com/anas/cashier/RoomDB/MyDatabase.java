package com.anas.cashier.RoomDB;

import android.app.Person;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {DebitPeople.class, Products.class, Sales.class, Paying.class,
        Director.class, Employee.class, Notification.class, PersonPurchases.class, Bill.class}, version = 1, exportSchema = false)

public abstract class MyDatabase extends RoomDatabase {


    public abstract DebitDAO debitDAO();

    public abstract ProductsDAO productsDAO();

    public abstract SalesDAO salesDAO();

    public abstract DirectorDAO directorDAO();

    public abstract EmployeeDAO employeeDAO();

    public abstract PayDAO payDAO();

    public abstract NotificationDAO notificationDAO();

    public abstract PersonPurchasesDAO personPurchasesDAO();

    public abstract BillDAO billDAO();


    private static volatile MyDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 10;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static MyDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MyDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MyDatabase.class, "Cashier")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    // عند إنشاء وإستدعاء الDataBase لأول مرة
    private static Callback sRoomDatabaseCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);


            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.


            });
        }
    };
}
