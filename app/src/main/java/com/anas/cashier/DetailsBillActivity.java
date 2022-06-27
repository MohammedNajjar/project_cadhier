package com.anas.cashier;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.anas.cashier.Adapters.ListBillAdapter;
import com.anas.cashier.RoomDB.MyViewModel;
import com.anas.cashier.RoomDB.Tables.Bill;
import com.anas.cashier.RoomDB.Tables.Products;
import com.anas.cashier.databinding.ActivityDetailsBillBinding;
import com.anas.cashier.ui.billing.BillingFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DetailsBillActivity extends AppCompatActivity {

    private ActivityDetailsBillBinding binding;
    private MyViewModel viewModel;
    private ListBillAdapter adapter;
    private ArrayList<Bill> list = new ArrayList<>();;
    private Bill BillDay;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBillBinding.inflate(getLayoutInflater());
        list.clear();
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        sp= getSharedPreferences("EmailSharedPreferences", Context.MODE_PRIVATE);
        String email = sp.getString("Email", null);
        binding.toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_icons__arrows_bill));
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                onBackPressed();
            }
        });

        adapter = new ListBillAdapter(new ArrayList<>());

        Intent intent = getIntent();
        int KeyBillDetails = intent.getIntExtra(BillingFragment.KEY_BillDetails, 0);
        if (KeyBillDetails == 1) {
            //All Invoices
            viewModel.getBill(email).observe(this, new Observer<List<Bill>>() {
                @Override
                public void onChanged(List<Bill> bills) {
                    adapter.setList(bills);
                }
            });

            binding.TextToolBar.setText(R.string.all_invoices);
        } else if (KeyBillDetails == 2) {
            //Sales Invoices

            viewModel.getSalesAndBuy(2, email).observe(this, new Observer<List<Bill>>() {
                @Override
                public void onChanged(List<Bill> bills) {
                    adapter.setList(bills);
                }
            });

            binding.TextToolBar.setText(R.string.sales_invoices);

        } else if (KeyBillDetails == 3) {
            //Purchase Invoices

            viewModel.getSalesAndBuy(1, email).observe(this, new Observer<List<Bill>>() {
                @Override
                public void onChanged(List<Bill> bills) {
                    adapter.setList(bills);
                }
            });
            binding.TextToolBar.setText(R.string.purchase_invoices);
        } else if (KeyBillDetails == 4) {
            //Today Bill

            viewModel.getBill(email).observe(this, new Observer<List<Bill>>() {
                @Override
                public void onChanged(List<Bill> bills) {
                    for (int i = 0; i < bills.size(); i++) {
                        Date date = bills.get(i).getDatePurchase();
                        String dateFormat = getBillFormatDay(date);

                        Date dateDay = new Date();
                        String formatDay = getBillFormatDay(dateDay);

                        BillDay = new Bill(bills.get(i).getName(),bills.get(i).getDatePurchase(),bills.get(i).getValidity(),
                                bills.get(i).getPriceBuy(),bills.get(i).getPriceSelling(),bills.get(i).getQuantity(),bills.get(i).getEmail());
                        if (dateFormat.equals(formatDay)) {
                            list.add(BillDay);
                        }
                    }
                    adapter.setList(list);
                }
            });
            binding.TextToolBar.setText(R.string.today_bills);
        } else if (KeyBillDetails == 5) {
            //Month Bill

            viewModel.getBill(email).observe(this, new Observer<List<Bill>>() {
                @Override
                public void onChanged(List<Bill> bills) {

                    for (int i = 0; i < bills.size(); i++) {
                        Date date = bills.get(i).getDatePurchase();
                        String dateFormat = getBillFormatMonth(date);

                        Date dateDay = new Date();
                        String formatDay = getBillFormatMonth(dateDay);

                        BillDay = new Bill(bills.get(i).getName(),bills.get(i).getDatePurchase(),bills.get(i).getValidity(),
                                bills.get(i).getPriceBuy(),bills.get(i).getPriceSelling(),bills.get(i).getQuantity(),bills.get(i).getEmail());

                        if (dateFormat.equals(formatDay)) {
                            list.add(BillDay);
                        }
                    }
                    adapter.setList(list);
                }
            });
            binding.TextToolBar.setText(R.string.bills_month);
        } else if (KeyBillDetails == 6) {
            //Year Bill
            viewModel.getBill(email).observe(this, new Observer<List<Bill>>() {
                @Override
                public void onChanged(List<Bill> bills) {

                    for (int i = 0; i < bills.size(); i++) {
                        Date date = bills.get(i).getDatePurchase();
                        String dateFormat = getBillFormatYear(date);

                        Date dateDay = new Date();
                        String formatDay = getBillFormatYear(dateDay);

                        BillDay = new Bill(bills.get(i).getName(),bills.get(i).getDatePurchase(),bills.get(i).getValidity(),
                                bills.get(i).getPriceBuy(),bills.get(i).getPriceSelling(),bills.get(i).getQuantity(),bills.get(i).getEmail());

                        if (dateFormat.equals(formatDay)) {
                            list.add(BillDay);
                        }
                    }
                    adapter.setList(list);
                }
            });
            binding.TextToolBar.setText(R.string.bills_year);
        }
        binding.rv.setAdapter(adapter);
        binding.rv.setHasFixedSize(true);
        binding.rv.setLayoutManager(new LinearLayoutManager(getBaseContext()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edite, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:

                SheetEditFragment sheet = new SheetEditFragment();
                sheet.show(getSupportFragmentManager(), null);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        list.clear();
    }

    public String getBillFormatDay(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd", Locale.ENGLISH);
        return simpleDateFormat.format(date);
    }

    public String getBillFormatMonth(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM", Locale.ENGLISH);
        return simpleDateFormat.format(date);
    }

    public String getBillFormatYear(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy", Locale.ENGLISH);
        return simpleDateFormat.format(date);
    }
}