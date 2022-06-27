package com.anas.cashier;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;

import com.anas.cashier.databinding.ActivityChangeLanguageBinding;

import java.util.Locale;

public class ChangeLanguageActivity extends AppCompatActivity {

    private ActivityChangeLanguageBinding binding;
    public static SharedPreferences sp;
    public static SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChangeLanguageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sp= getSharedPreferences("EmailSharedPreferences", Context.MODE_PRIVATE);
        editor=sp.edit();

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        binding.toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_icons__arrows_previuos));
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                onBackPressed();
            }
        });

        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.English:
                        setLocal("en");
                        finish();
                        break;
                    case R.id.Chinese:
                        setLocal("zh");
                        finish();
                        break;
                    case R.id.Spanish:
                        setLocal("es");
                        finish();
                        break;
                    case R.id.Hindi:
                        setLocal("hi");
                        finish();
                        break;
                    case R.id.Arabic:
                        setLocal("ar");
                        finish();
                        break;
                    case R.id.Portuguese:
                        setLocal("pt");
                        finish();
                        break;
                    case R.id.Russian:
                        setLocal("ru");
                        finish();
                        break;
                    case R.id.Japanese:
                        setLocal("ja");
                        finish();
                        break;
                    case R.id.German:
                        setLocal("gmh");
                        finish();
                        break;
                    case R.id.turkish:
                        setLocal("tr");
                        finish();
                        break;
                    case R.id.french:
                        setLocal("fr");
                        finish();
                        break;
                }
            }
        });
    }

    private void setLocal(String lang) {
        Locale locale = new Locale(lang);
        locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getResources().updateConfiguration(configuration,getApplicationContext()
                .getResources().getDisplayMetrics());
        if (lang.equals("en")){
            editor.putString("My_lang",null);
        }
        else if (lang.equals("ar")){
            editor.putString("My_lang","ar");
        }else if (lang.equals("zh")){
            editor.putString("My_lang","zh");
        }else if (lang.equals("es")){
             editor.putString("My_lang","es");
        }else if (lang.equals("hi")){
            editor.putString("My_lang","hi");
        }else if (lang.equals("pt")){
            editor.putString("My_lang","pt");
        }else if (lang.equals("ru")){
            editor.putString("My_lang","ru");
        }else if (lang.equals("ja")){
            editor.putString("My_lang","ja");
        }else if (lang.equals("gmh")){
            editor.putString("My_lang","gmh");
        }else if (lang.equals("tr")){
            editor.putString("My_lang","tr");
        }
        else if (lang.equals("fr")){
            editor.putString("My_lang","fr");
        }
        editor.apply();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.update, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_update:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}