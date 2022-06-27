package com.anas.cashier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.anas.cashier.databinding.ActivityMainBinding;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private String email;
    private SharedPreferences sp;
    private String lang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sp= getSharedPreferences("EmailSharedPreferences", Context.MODE_PRIVATE);
        email=sp.getString("Email",null);
        lang =sp.getString("My_lang",null);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (email!=null){
                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                }else {
                    startActivity(new Intent(getApplicationContext(),OnboardScreensActivity.class));
                }
            }
        },2000);

        if (lang == null){
            //لغة انجليزية
            Locale locale = new Locale("en");
            locale.setDefault(locale);
            Configuration configuration = new Configuration();
            configuration.locale = locale;
            getBaseContext().getResources().updateConfiguration(configuration,getBaseContext()
                    .getResources().getDisplayMetrics());
        }else if (lang.equals("ar")){
            //لغة عربية
            Locale locale = new Locale("ar");
            locale.setDefault(locale);
            Configuration configuration = new Configuration();
            configuration.locale = locale;
            getBaseContext()
                    .getResources()
                    .updateConfiguration(configuration,getBaseContext()
                            .getResources().getDisplayMetrics());
        } else if (lang.equals("zh")){
            //لغة صينية
            Locale locale = new Locale("zh");
            locale.setDefault(locale);
            Configuration configuration = new Configuration();
            configuration.locale = locale;
            getBaseContext()
                    .getResources()
                    .updateConfiguration(configuration,getBaseContext()
                            .getResources().getDisplayMetrics());
        }else if (lang.equals("es")){
            //لغة إسبانية
            Locale locale = new Locale("es");
            locale.setDefault(locale);
            Configuration configuration = new Configuration();
            configuration.locale = locale;
            getBaseContext()
                    .getResources()
                    .updateConfiguration(configuration,getBaseContext()
                            .getResources().getDisplayMetrics());
        }else if (lang.equals("hi")){
            //لغة هندية
            Locale locale = new Locale("hi");
            locale.setDefault(locale);
            Configuration configuration = new Configuration();
            configuration.locale = locale;
            getBaseContext()
                    .getResources()
                    .updateConfiguration(configuration,getBaseContext()
                            .getResources().getDisplayMetrics());
        }else if (lang.equals("pt")){
            //لغة برتغالية
            Locale locale = new Locale("pt");
            locale.setDefault(locale);
            Configuration configuration = new Configuration();
            configuration.locale = locale;
            getBaseContext()
                    .getResources()
                    .updateConfiguration(configuration,getBaseContext()
                            .getResources().getDisplayMetrics());
        }else if (lang.equals("ru")){
            //لغة روسية
            Locale locale = new Locale("ru");
            locale.setDefault(locale);
            Configuration configuration = new Configuration();
            configuration.locale = locale;
            getBaseContext()
                    .getResources()
                    .updateConfiguration(configuration,getBaseContext()
                            .getResources().getDisplayMetrics());
        } else if (lang.equals("ja")){
            //لغة اليابانية
            Locale locale = new Locale("ja");
            locale.setDefault(locale);
            Configuration configuration = new Configuration();
            configuration.locale = locale;
            getBaseContext()
                    .getResources()
                    .updateConfiguration(configuration,getBaseContext()
                            .getResources().getDisplayMetrics());
        }else if (lang.equals("gmh")){
            //لغة الالمانية
            Locale locale = new Locale("gmh");
            locale.setDefault(locale);
            Configuration configuration = new Configuration();
            configuration.locale = locale;
            getBaseContext()
                    .getResources()
                    .updateConfiguration(configuration,getBaseContext()
                            .getResources().getDisplayMetrics());
        }else if (lang.equals("tr")){
            //لغة التركية
            Locale locale = new Locale("tr");
            locale.setDefault(locale);
            Configuration configuration = new Configuration();
            configuration.locale = locale;
            getBaseContext()
                    .getResources()
                    .updateConfiguration(configuration,getBaseContext()
                            .getResources().getDisplayMetrics());
        }else if (lang.equals("fr")){
            //لغة الفرنسية
            Locale locale = new Locale("fr");
            locale.setDefault(locale);
            Configuration configuration = new Configuration();
            configuration.locale = locale;
            getBaseContext()
                    .getResources()
                    .updateConfiguration(configuration,getBaseContext()
                            .getResources().getDisplayMetrics());
        }

    }


    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}