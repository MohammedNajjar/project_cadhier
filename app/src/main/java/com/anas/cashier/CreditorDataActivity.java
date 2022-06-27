package com.anas.cashier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.anas.cashier.RoomDB.MyViewModel;
import com.anas.cashier.RoomDB.Tables.DebitPeople;
import com.anas.cashier.databinding.ActivityAddCreditorDataBinding;
import com.anas.cashier.databinding.ActivityCreditorDataBinding;

public class CreditorDataActivity extends AppCompatActivity {

    private ActivityCreditorDataBinding binding;
    private MyViewModel viewModel;
    private SharedPreferences sh ;
    private String email;
    private SharedPreferences sp;
    int a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCreditorDataBinding.inflate(getLayoutInflater());
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

        sh = getSharedPreferences("MySharedPref", MODE_APPEND);
        a = sh.getInt(DetailsDebtActivity.KeyCreditorData, 0);

        viewModel.getAllDebit(a,email).observe(this, new Observer<DebitPeople>() {
            @Override
            public void onChanged(DebitPeople debitPeople) {

                binding.etCreditorName.setText(debitPeople.getName());
                binding.etAddress.setText(debitPeople.getAddress());
                binding.etMobileNumber.setText(debitPeople.getNumberPhone()+"");
                binding.etNot.setText(debitPeople.getNotes());
            }
        });

        binding.Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name=binding.etCreditorName.getText().toString();
                String address=binding.etAddress.getText().toString();
                String Note=binding.etNot.getText().toString();
                Long number=Long.valueOf(binding.etMobileNumber.getText().toString());

                DebitPeople people=new DebitPeople();
                people.setId(a);
                people.setName(name);
                people.setAddress(address);
                people.setNumberPhone(number);
                people.setNotes(Note);
                people.setEmail(email);
                viewModel.UpdateDebit(people);
                finish();

            }
        });




    }
}