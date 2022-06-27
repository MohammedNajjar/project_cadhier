package com.anas.cashier;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anas.cashier.Adapters.ListNotificationAdapter;
import com.anas.cashier.Interface.CountNotification;
import com.anas.cashier.Interface.OnClickNotification;
import com.anas.cashier.RoomDB.MyViewModel;
import com.anas.cashier.RoomDB.Tables.Notification;
import com.anas.cashier.databinding.ActivityNotificationsBinding;
import com.williammora.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NotificationsActivity extends AppCompatActivity implements OnClickNotification {

    private ActivityNotificationsBinding binding;
    private MyViewModel viewModel;
    private ListNotificationAdapter adapter;
    private String email;
    private SharedPreferences sp;
    private TextView notifCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotificationsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel=new ViewModelProvider(this).get(MyViewModel.class);
        sp= getSharedPreferences("EmailSharedPreferences", Context.MODE_PRIVATE);
        email=sp.getString("Email",null);

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

//        binding.swipe.setChecked(true);
//        binding.swipe.setTextOn(getString(R.string.On));
//        binding.swipe.setTextOff(getString(R.string.Off));
//
//        binding.swipe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//
//                if (b){
//                    Calendar calendar=Calendar.getInstance();
//                    calendar.set(Calendar.HOUR_OF_DAY,4);
//                    calendar.set(Calendar.MINUTE,40);
//                    calendar.set(Calendar.SECOND,0);
//
//                    ComponentName name = new ComponentName(getBaseContext(), JobService.class);
//                    JobInfo info ;
//
//                    if (Build.VERSION.SDK_INT<=Build.VERSION_CODES.N){
//                        info= new JobInfo.Builder(10, name)
//                                .setPeriodic(5*1000)
//                                .build();
//
//                        JobScheduler scheduler= (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
//                        scheduler.schedule(info);
//                    }else{
//                        info= new JobInfo.Builder(10, name)
//                                .setMinimumLatency(5)
//                                .build();
//
//                        JobScheduler scheduler= (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
//                        scheduler.schedule(info);
//                    }
//                }
//            }
//        });


        Notification();

        binding.toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_icons__arrows_previuos));
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                onBackPressed();
            }
        });

        adapter = new ListNotificationAdapter(new ArrayList<>(),this);
        viewModel.getAllNotification(email).observe(this, new Observer<List<Notification>>() {
            @Override
            public void onChanged(List<Notification> notifications) {
                adapter.setList(notifications);
            }
        });

        binding.rv.setAdapter(adapter);
        binding.rv.setHasFixedSize(true);
        binding.rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        viewModel.CountNotification(email).observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.count.setText(String.valueOf(integer));
            }
        });
        binding.count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.count.setText(String.valueOf(0));
            }
        });

    }

    @Override
    public void OnClickItem(Notification notification) {
        viewModel.DeleteItemNotification(notification.getID(),email);
    }

    public void Notification(){
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,4);
        calendar.set(Calendar.MINUTE,40);
        calendar.set(Calendar.SECOND,0);

        ComponentName name = new ComponentName(getBaseContext(), JobService.class);
        JobInfo info ;

        if (Build.VERSION.SDK_INT<=Build.VERSION_CODES.N){
            info= new JobInfo.Builder(10, name)
                    .setPeriodic(5*1000)
                    .build();

            JobScheduler scheduler= (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
            scheduler.schedule(info);
        }else{
            info= new JobInfo.Builder(10, name)
                    .setMinimumLatency(5)
                    .build();

            JobScheduler scheduler= (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
            scheduler.schedule(info);
        }
    }

}