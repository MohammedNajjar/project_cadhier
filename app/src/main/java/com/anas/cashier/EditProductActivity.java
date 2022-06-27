package com.anas.cashier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.anas.cashier.RoomDB.MyViewModel;
import com.anas.cashier.RoomDB.Tables.Products;
import com.anas.cashier.databinding.ActivityEditProductBinding;

import java.util.List;

public class EditProductActivity extends AppCompatActivity {

    private ActivityEditProductBinding binding;
    private MyViewModel viewModel;
    private String email;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditProductBinding.inflate(getLayoutInflater());
        sp = getSharedPreferences("EmailSharedPreferences", Context.MODE_PRIVATE);
        email = sp.getString("Email", null);
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

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

        Intent intent = getIntent();
        int ID = intent.getIntExtra("EditProduct", 0);

        viewModel.getIdDepartment(ID, email).observe(this, new Observer<Products>() {
            @Override
            public void onChanged(Products products) {
                binding.etDescription.setText(products.getDescription());
                binding.etPriceBuy.setText(products.getPriceBuy() + "");
                binding.etPriceSelling.setText(products.getPriceSelling() + "");
                binding.etQuantity.setText(products.getQuantity() + "");
                binding.textView2.setText(products.getName());

                binding.Update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Products product = new Products();
                        product.setId(ID);
                        product.setName(products.getName());
                        product.setType(products.getType());
                        product.setBarCode(products.getBarCode());
                        product.setDatePurchase(products.getDatePurchase());
                        product.setDescription(binding.etDescription.getText().toString());
                        product.setPriceBuy(Double.parseDouble(binding.etPriceBuy.getText().toString()));
                        product.setPriceSelling(Double.parseDouble(binding.etPriceSelling.getText().toString()));
                        product.setQuantity(Integer.parseInt(binding.etQuantity.getText().toString()));
                        product.setEmail(products.getEmail());
                        product.setIndexSpinner(products.getIndexSpinner());

                        viewModel.UpdateProducts(product);
                        finish();
                    }
                });
            }
        });


    }
}