package com.anas.cashier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.anas.cashier.Interface.SumPayPerson;
import com.anas.cashier.Interface.TotalAccount;
import com.anas.cashier.Interface.TotalSincereDebtPerson;
import com.anas.cashier.RoomDB.MyViewModel;
import com.anas.cashier.RoomDB.Tables.DebitPeople;
import com.anas.cashier.RoomDB.Tables.Paying;
import com.anas.cashier.databinding.ActivityCreditorDebtBinding;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

public class CreditorDebtActivity extends AppCompatActivity {

    private ActivityCreditorDebtBinding binding;
    private Calendar selectedDate;
    private MyViewModel viewModel;
    private SharedPreferences sh;
    private String email;
    private SharedPreferences sp;
    int a;
    double s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreditorDebtBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        selectedDate = Calendar.getInstance();
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        sp = getSharedPreferences("EmailSharedPreferences", Context.MODE_PRIVATE);
        email = sp.getString("Email", null);


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

        sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        a = sh.getInt(DetailsDebtActivity.KeyCreditorData, 0);
        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(binding.etPayingAmount.getText().toString())) {
                    Toast.makeText(CreditorDebtActivity.this, R.string.please_enter_paying_amount, Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(binding.etNot.getText().toString())) {
                    Toast.makeText(CreditorDebtActivity.this, R.string.please_enter_note, Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(binding.etDate.getText().toString())) {
                    Toast.makeText(CreditorDebtActivity.this, R.string.please_enter_date, Toast.LENGTH_SHORT).show();
                    return;
                }

                String not = binding.etNot.getText().toString();
                double paying = Double.parseDouble(binding.etPayingAmount.getText().toString());

                Paying pay = new Paying(selectedDate.getTime(), not, paying, a);
                viewModel.InsertPay(pay);

                finish();

            }
        });

        viewModel.SumDebitPerson(a, new TotalSincereDebtPerson() {
            @Override
            public void TotalDebtPerson(double number) {
                binding.tvTotalAccount.setText(number + "");
            }
        }, email);

        s = Double.parseDouble(binding.tvTotalAccount.getText().toString());
        viewModel.SumPayPerson(a, new SumPayPerson() {
            @Override
            public void SumPay(double sumPay) {
                binding.tvSumPay.setText(sumPay + "");
            }
        });

        viewModel.TotalAccount(a, new TotalAccount() {
            @Override
            public void TotalAccountPerson(double number) {
                binding.tvTotalAccountPerson.setText(String.valueOf(number));
            }
        });

    }

}