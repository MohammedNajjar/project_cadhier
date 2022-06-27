package com.anas.cashier;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.anas.cashier.Interface.CountNotification;
import com.anas.cashier.Interface.ListProduct;
import com.anas.cashier.Interface.SumQuantityProduct;
import com.anas.cashier.RoomDB.Repository;
import com.anas.cashier.RoomDB.Tables.Notification;
import com.anas.cashier.RoomDB.Tables.Products;

import java.util.Date;
import java.util.List;


public class JobService extends android.app.job.JobService {

    public static final String CHANNEL_ID = "channel_id";
    Repository repository;
    private String email;
    private SharedPreferences sp;
    private int count_notification;

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        repository = new Repository(getApplication());
        sp= getSharedPreferences("EmailSharedPreferences", Context.MODE_PRIVATE);
        email=sp.getString("Email",null);

        MethodNotification();

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {

        Toast.makeText(this, R.string.Finished, Toast.LENGTH_SHORT).show();
        return false;
    }


    private void MethodNotification() {

        Date date = new Date();
        repository.CountNotificationService(email, new CountNotification() {
            @Override
            public void count(int count) {
                count_notification=count;
            }
        });

        repository.SumPriceBuyProduct(new SumQuantityProduct() {
            @Override
            public void SumQuantity(int Quantity) {
                int icon = R.drawable.ic_purchased;
                String details = getString(R.string.Today_merchandise) + Quantity + getString(R.string.shekel);
                displayNotification(icon, details);

                Notification notification = new Notification(details, icon, date,email);
                repository.InsertNotification(notification);
            }
        },email);
        repository.SumPriceSellingProduct(new SumQuantityProduct() {
            @Override
            public void SumQuantity(int Quantity) {

                int icon = R.drawable.ic_sold;
                String details = getString(R.string.Today_was_sold) + Quantity + getString(R.string.shekel);
                displayNotification(icon, details);

                Notification notification = new Notification(details, icon, date,email);
                repository.InsertNotification(notification);

            }
        },email);

        repository.getListProduct(new ListProduct() {
            @Override
            public void getListProduct(List<Products> products) {
                for (int i = 0; i < products.size(); i++) {
                    if (products.get(i).getQuantity() == 5) {
                        int icon = R.drawable.ic_wallet;
                        String title = getString(R.string.Product) + products.get(i).getName() + getString(R.string.is_about_to_expire);
                        displayNotification(icon, title);
                        Notification notification = new Notification(title, icon, date,email);
                        repository.InsertNotification(notification);
                    }
                }
            }
        },email);
    }

    private void displayNotification(int Icon, String Details) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    getString(R.string.Notification_Cashier),
                    NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        Intent i = new Intent(getBaseContext(), NotificationsActivity.class);
        PendingIntent p = PendingIntent.getBroadcast(getBaseContext(), 0, i, 0);
        NotificationCompat.Builder compat = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
        compat.setSmallIcon(Icon)
                .setContentTitle("Cashier App")
                .setContentIntent(p)
                .setContentText(Details)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setStyle(new NotificationCompat.BigPictureStyle());


        NotificationManagerCompat nmb = NotificationManagerCompat.from(this);
        nmb.notify(10, compat.build());
    }


}