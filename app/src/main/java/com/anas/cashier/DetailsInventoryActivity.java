package com.anas.cashier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.anas.cashier.Adapters.DetailsInventoryAdapter;
import com.anas.cashier.Interface.OnClickEditListener;
import com.anas.cashier.RoomDB.MyViewModel;
import com.anas.cashier.RoomDB.Tables.Products;
import com.anas.cashier.databinding.ActivityDetailsInventoryBinding;

import java.util.ArrayList;
import java.util.List;

public class DetailsInventoryActivity extends AppCompatActivity implements OnClickEditListener {

    private ActivityDetailsInventoryBinding binding;
    private MyViewModel viewModel;
    private long Index;
    private String email;
    private String Catogory;
    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDetailsInventoryBinding.inflate(getLayoutInflater());
        sp= getSharedPreferences("EmailSharedPreferences", Context.MODE_PRIVATE);
        email=sp.getString("Email",null);
        setContentView(binding.getRoot());
        viewModel= new ViewModelProvider(this).get(MyViewModel.class);

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(null);
        binding.toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_icons__arrows_previuos));
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                onBackPressed();
            }
        });

        Intent intent=getIntent();
        Index =intent.getLongExtra("index",-1);
        Catogory =intent.getStringExtra("Catogory");
        binding.TextToolBar.setText(Catogory);
        DetailsInventoryAdapter adapter=new DetailsInventoryAdapter(new ArrayList<>(),this);
        viewModel.getListDepartment(Index,email).observe(this, new Observer<List<Products>>() {
            @Override
            public void onChanged(List<Products> products) {
                adapter.setList(products);
            }
        });
        binding.rv.setAdapter(adapter);
        binding.rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.rv.setHasFixedSize(true);


        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                viewModel.getFilterInventory(query, Index,email).observe(DetailsInventoryActivity.this, new Observer<List<Products>>() {
                    @Override
                    public void onChanged(List<Products> products) {
                        adapter.setList(products);
                    }
                });
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                viewModel.getFilterInventory(newText, Index,email).observe(DetailsInventoryActivity.this, new Observer<List<Products>>() {
                    @Override
                    public void onChanged(List<Products> products) {
                        adapter.setList(products);
                    }
                });
                return true;
            }
        });

//        binding.searchView.setOnCloseListener(new SearchView.OnCloseListener() {
//            @Override
//            public boolean onClose() {
//                viewModel.getListDepartment(Category,email).observe(DetailsInventoryActivity.this, new Observer<List<Products>>() {
//                    @Override
//                    public void onChanged(List<Products> products) {
//                        adapter.setList(products);
//                    }
//                });
//                return true;
//            }
//        });

    }


    @Override
    public void OnClickEdit(int Id) {

        Intent intent=new Intent(getApplicationContext(),EditProductActivity.class);
        intent.putExtra("EditProduct",Id);
        startActivity(intent);
    }
}