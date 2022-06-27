package com.anas.cashier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.anas.cashier.Adapters.AllProductsAdapter;
import com.anas.cashier.Interface.OnClickItemAllProducts;
import com.anas.cashier.RoomDB.MyViewModel;
import com.anas.cashier.RoomDB.Tables.Products;
import com.anas.cashier.databinding.ActivityAllProductsBinding;

import java.util.ArrayList;
import java.util.List;

public class AllProductsActivity extends AppCompatActivity implements
        OnClickItemAllProducts, DialogAllProductFragment.OnClickCancel,
        DialogAllProductFragment.OnClickEdit {

    private ActivityAllProductsBinding binding;
    private MyViewModel viewModel;
    private ArrayList<Products> list;
    private AllProductsAdapter adapter;
    private String email;
    private SharedPreferences sp;
    private DialogAllProductFragment dialogAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAllProductsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel=new ViewModelProvider(this).get(MyViewModel.class);
        sp= getSharedPreferences("EmailSharedPreferences", Context.MODE_PRIVATE);
        email=sp.getString("Email",null);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        binding.toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_icons__arrows_bill));

        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                onBackPressed();
            }
        });

        list=new ArrayList<>(new ArrayList<>());
        adapter=new AllProductsAdapter(list,this);

        viewModel.getAllProducts(email).observe(this, new Observer<List<Products>>() {
            @Override
            public void onChanged(List<Products> products) {
                adapter.setList(products);
            }
        });

        binding.rv.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        binding.rv.setHasFixedSize(true);
        binding.rv.setAdapter(adapter);

    }

    @Override
    public void OnClickItem(Products products) {


        Intent intent=new Intent(getApplicationContext(),EditProductActivity.class);
        intent.putExtra("EditProduct",products.getId());
        startActivity(intent);
    }

    @Override
    public void OnClickItemClose(Products products) {
        viewModel.DeleteItemProducts(products.getBarCode(),email);
        Toast.makeText(this, getString(R.string.done_delete)+products.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickEditBtn(String a) {
        Toast.makeText(this, ""+a, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickCancelBtn() {
        dialogAll.dismiss();
    }
}