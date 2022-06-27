package com.anas.cashier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Update;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.anas.cashier.Interface.GetItemDebit;
import com.anas.cashier.RoomDB.MyViewModel;
import com.anas.cashier.RoomDB.Tables.DebitPeople;
import com.anas.cashier.databinding.ActivityAddCreditorDataBinding;
import com.anas.cashier.ui.debts.DebtsFragment;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddCreditorDataActivity extends AppCompatActivity {

    private ActivityAddCreditorDataBinding binding;
    private Calendar selectedDate;
    private MyViewModel viewModel;
    private int debit;
    private String email;
    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddCreditorDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        selectedDate = Calendar.getInstance();
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
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

        binding.etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar now = Calendar.getInstance();

                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePickerDialog view,
                                                  int year,
                                                  int monthOfYear,
                                                  int dayOfMonth) {
                                binding.etDate
                                        .setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                                selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                selectedDate.set(Calendar.YEAR, year);
                                selectedDate.set(Calendar.MONTH, monthOfYear);
                                selectedDate.clear(Calendar.HOUR_OF_DAY);
                                selectedDate.clear(Calendar.MINUTE);
                                selectedDate.clear(Calendar.SECOND);
                            }
                        },
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)

                );
                dpd.setAccentColor(Color.parseColor("#2C3E50"));
                dpd.show(getSupportFragmentManager(), "Date picker dialog");
            }
        });

        Intent intent=getIntent();
        debit= intent.getIntExtra(DebtsFragment.KEY_DEBTS,0);

        if (debit==1){
            binding.btnSave.setText(R.string.save);
            binding.btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (TextUtils.isEmpty(binding.etCreditorName.getText().toString())) {
                        Toast.makeText(getBaseContext(), R.string.please_enter_creditor_name, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(binding.etAddress.getText().toString())) {
                        Toast.makeText(getBaseContext(), R.string.please_enter_address, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(binding.etDebtPaid.getText().toString())) {
                        Toast.makeText(getBaseContext(), R.string.please_enter_debt_paid, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(binding.etPhoneNumber.getText().toString())) {
                        Toast.makeText(getBaseContext(), R.string.please_enter_phone_number, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(binding.etTotalDebt.getText().toString())) {
                        Toast.makeText(getBaseContext(), R.string.please_enter_total_debt, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(binding.etDate.getText().toString())) {
                        Toast.makeText(getBaseContext(), R.string.please_enter_date, Toast.LENGTH_SHORT).show();
                        return;
                    }

                    String Name = binding.etCreditorName.getText().toString();
                    String NumberPhone = binding.etPhoneNumber.getText().toString();
                    String Address = binding.etAddress.getText().toString();
                    String DebtPaid = binding.etDebtPaid.getText().toString();
                    String TotalDebt = binding.etTotalDebt.getText().toString();

                    DebitPeople debit = new DebitPeople(Name, Long.valueOf(NumberPhone), Address,
                            Double.valueOf(DebtPaid), Double.valueOf(TotalDebt), selectedDate.getTime(),"",0.0,email);

                    viewModel.InsertDebit(debit);
                    finish();


                }
            });
            binding.TextToolBar.setText(R.string.add_creditor_data_2);




        }else if (debit==2){

            binding.btnSave.setText(R.string.add_creditor_data);
            int KeyDebit=intent.getIntExtra("KetDebit",0);

            viewModel.getItem(KeyDebit, new GetItemDebit() {
                @Override
                public void ItemDebit(DebitPeople people) {

                    String date=getDateFormat(people.getDate());
                    binding.etCreditorName.setText(people.getName());
                    binding.etPhoneNumber.setText(people.getNumberPhone()+"");
                    binding.etAddress.setText(people.getAddress());
                    binding.etDebtPaid.setText(people.getDebtPaid()+"");
                    binding.etTotalDebt.setText(people.getTotalDebt()+"");
                    binding.etDate.setText(date);

                    binding.btnSave.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (TextUtils.isEmpty(binding.etCreditorName.getText().toString())) {
                                Toast.makeText(getBaseContext(), R.string.please_enter_creditor_name, Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (TextUtils.isEmpty(binding.etAddress.getText().toString())) {
                                Toast.makeText(getBaseContext(), R.string.please_enter_address, Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (TextUtils.isEmpty(binding.etDebtPaid.getText().toString())) {
                                Toast.makeText(getBaseContext(), R.string.please_enter_debt_paid, Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (TextUtils.isEmpty(binding.etPhoneNumber.getText().toString())) {
                                Toast.makeText(getBaseContext(), R.string.please_enter_phone_number, Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (TextUtils.isEmpty(binding.etTotalDebt.getText().toString())) {
                                Toast.makeText(getBaseContext(), R.string.please_enter_total_debt, Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (TextUtils.isEmpty(binding.etDate.getText().toString())) {
                                Toast.makeText(getBaseContext(), R.string.please_enter_date, Toast.LENGTH_SHORT).show();
                                return;
                            }

                            String Name = binding.etCreditorName.getText().toString();
                            String NumberPhone = binding.etPhoneNumber.getText().toString();
                            String Address = binding.etAddress.getText().toString();
                            String DebtPaid = binding.etDebtPaid.getText().toString();
                            String TotalDebt = binding.etTotalDebt.getText().toString();

                            DebitPeople peoples=new DebitPeople();
                            peoples.setId(KeyDebit);
                            peoples.setName(Name);
                            peoples.setNumberPhone(Long.parseLong(NumberPhone));
                            peoples.setAddress(Address);
                            peoples.setDebtPaid(Double.parseDouble(DebtPaid));
                            peoples.setTotalDebt(Double.parseDouble(TotalDebt));
                            peoples.setDate(selectedDate.getTime());
                            peoples.setNotes(people.getNotes());
                            peoples.setEmail(people.getEmail());
                            peoples.setPayingAmount(people.getPayingAmount());
                            viewModel.UpdateDebit(peoples);
                            finish();

                        }
                    });

                }
            },email);

            binding.TextToolBar.setText(R.string.edit_creditor_data);

        }



    }
    public String getDateFormat(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy", Locale.ENGLISH);
        return simpleDateFormat.format(date);
    }
}