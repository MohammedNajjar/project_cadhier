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
import android.widget.Toast;

import com.anas.cashier.Adapters.ListDebtAdapter;
import com.anas.cashier.Interface.CountDebt;
import com.anas.cashier.Interface.OnClickItemDebt;
import com.anas.cashier.Interface.SumDebitToday;
import com.anas.cashier.Interface.TotalDebtAll;
import com.anas.cashier.Interface.TotalExpiredDebts;
import com.anas.cashier.Interface.TotalHighPriceDebts;
import com.anas.cashier.Interface.TotalLowPriceDebts;
import com.anas.cashier.RoomDB.MyViewModel;
import com.anas.cashier.RoomDB.Tables.DebitPeople;
import com.anas.cashier.databinding.ActivityDetailsDebtBinding;
import com.anas.cashier.ui.debts.DebtsFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DetailsDebtActivity extends AppCompatActivity implements OnClickItemDebt {

    private ActivityDetailsDebtBinding binding;
    private MyViewModel viewModel;
    private DebitPeople debit;
    private List<DebitPeople> listDay = new ArrayList<>();
    private List<DebitPeople> listMonth = new ArrayList<>();
    private List<DebitPeople> listYear = new ArrayList<>();
    private ListDebtAdapter adapter;
    private String email;
    private SharedPreferences sp;
    public static final String KeyCreditorData = "key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsDebtBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        sp= getSharedPreferences("EmailSharedPreferences", Context.MODE_PRIVATE);
        email=sp.getString("Email",null);


        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        binding.toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_icons__arrows_bill));

        binding.searchView.setQueryHint(getString(R.string.search_here));

        binding.text.setVisibility(View.GONE);


        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                onBackPressed();
            }
        });
        new ArrayList<>();


        viewModel.CountDebts(email, new CountDebt() {
            @Override
            public void count(int count) {
                if (count==0){
                    binding.text.setVisibility(View.VISIBLE);
                }else {
                    binding.text.setVisibility(View.GONE);
                }
            }
        });
        adapter = new ListDebtAdapter(new ArrayList<>(), this);
        binding.rv.setAdapter(adapter);
        binding.rv.setHasFixedSize(true);
        binding.rv.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                viewModel.getFilterDebit(query,email).observe(DetailsDebtActivity.this, new Observer<List<DebitPeople>>() {
                    @Override
                    public void onChanged(List<DebitPeople> debitPeople) {
                        adapter.setList(debitPeople);
                    }
                });
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                viewModel.getFilterDebit(newText,email).observe(DetailsDebtActivity.this, new Observer<List<DebitPeople>>() {
                    @Override
                    public void onChanged(List<DebitPeople> debitPeople) {
                        adapter.setList(debitPeople);
                    }
                });
                return true;
            }
        });

        Intent intent = getIntent();
        int w = intent.getIntExtra(DebtsFragment.KEY_DEBTSList, 0);
        if (w == 1) {
            //Today

            viewModel.getAll(email).observe(this, new Observer<List<DebitPeople>>() {
                @Override
                public void onChanged(List<DebitPeople> debitPeople) {

                    for (int i = 0; i < debitPeople.size(); i++) {

                        Date date = debitPeople.get(i).getDate();
                        String dateFormat = getDateFormatDay(date);

                        Date dateDay = new Date();
                        String formatDay = getDateFormatDay(dateDay);

                        debit = new DebitPeople(debitPeople.get(i).getId(), debitPeople.get(i).getName(),
                                debitPeople.get(i).getNumberPhone(), debitPeople.get(i).getAddress(), debitPeople.get(i).getDebtPaid()
                                , debitPeople.get(i).getTotalDebt(), debitPeople.get(i).getDate(), debitPeople.get(i).getNotes(),
                                debitPeople.get(i).getPayingAmount(),debitPeople.get(i).getEmail());

                        if (dateFormat.equals(formatDay)) {
                            listDay.add(debit);

                        }
                    }
                    adapter.setList(listDay);

                }
            });

            binding.TextToolBar.setText(R.string.today_debt);
            binding.debtToday.setText(R.string.total_debt_today);
            binding.tvDebtToday.setText("0.0");

            viewModel.SumDebtToday(new Date(), new SumDebitToday() {
                @Override
                public void DebitToday(double Number) {
                    binding.tvDebtToday.setText(Number+"");
                }
            },email);


        } else if (w == 2) {
            //Expired Debts
            viewModel.getExpiredDebts(email).observe(this, new Observer<List<DebitPeople>>() {
                @Override
                public void onChanged(List<DebitPeople> debitPeople) {
                    adapter.setList(debitPeople);
                }
            });

            binding.TextToolBar.setText(R.string.expired_debts);
            binding.debtToday.setText(R.string.total_expired_debts);
            viewModel.SumExpiredDebts(new TotalExpiredDebts() {
                @Override
                public void ExpiredDebts(double Number) {

                    binding.tvDebtToday.setText(Number + "");
                }
            },email);

        } else if (w == 3) {
            //Year Debts
            viewModel.getAll(email).observe(this, new Observer<List<DebitPeople>>() {
                @Override
                public void onChanged(List<DebitPeople> debitPeople) {

                    for (int i = 0; i < debitPeople.size(); i++) {

                        Date date = debitPeople.get(i).getDate();
                        String dateFormat = getDateFormatYear(date);

                        Date dateYear = new Date();
                        String formatYear = getDateFormatYear(dateYear);

                        debit = new DebitPeople(debitPeople.get(i).getId(), debitPeople.get(i).getName(),
                                debitPeople.get(i).getNumberPhone(), debitPeople.get(i).getAddress(), debitPeople.get(i).getDebtPaid()
                                , debitPeople.get(i).getTotalDebt(), debitPeople.get(i).getDate(), debitPeople.get(i).getNotes(),
                                debitPeople.get(i).getPayingAmount(),debitPeople.get(i).getEmail());

                        if (dateFormat.equals(formatYear)) {
                            listYear.add(debit);

                        }
                    }
                    adapter.setList(listYear);

                }
            });
            binding.TextToolBar.setText(R.string.debt_year);
            binding.debtToday.setText(R.string.total_debt_year);
            binding.tvDebtToday.setText("0.0");

        } else if (w == 4) {
            //High Price
            viewModel.getHighPrice(email).observe(this, new Observer<List<DebitPeople>>() {
                @Override
                public void onChanged(List<DebitPeople> debitPeople) {
                    adapter.setList(debitPeople);
                }
            });

            binding.TextToolBar.setText(R.string.high_price_debts);
            binding.debtToday.setText(R.string.total_high_price_debts);

            viewModel.SumHighPriceDebts(new TotalHighPriceDebts() {
                @Override
                public void HighPriceDebts(double Number) {

                    binding.tvDebtToday.setText(Number + "");
                }
            },email);

        }
        else if (w == 5) {
            //Low Price
            viewModel.getLowPrice(email).observe(this, new Observer<List<DebitPeople>>() {
                @Override
                public void onChanged(List<DebitPeople> debitPeople) {
                    adapter.setList(debitPeople);
                }
            });

            binding.TextToolBar.setText(R.string.low_price_debts);
            binding.debtToday.setText(R.string.Total_Low_Price_Debts);

            viewModel.SumLowPriceDebts(new TotalLowPriceDebts() {
                @Override
                public void LowPriceDebts(double Number) {

                    binding.tvDebtToday.setText(Number + "");
                }
            },email);
        } else if (w == 6) {
            //Month

            viewModel.getAll(email).observe(this, new Observer<List<DebitPeople>>() {
                @Override
                public void onChanged(List<DebitPeople> debitPeople) {


                    for (int i = 0; i < debitPeople.size(); i++) {

                        Date date = debitPeople.get(i).getDate();
                        String dateFormat = getDateFormatMonth(date);

                        Date dateMonth = new Date();
                        String formatMonth = getDateFormatMonth(dateMonth);

                        debit = new DebitPeople(debitPeople.get(i).getId(), debitPeople.get(i).getName(),
                                debitPeople.get(i).getNumberPhone(), debitPeople.get(i).getAddress(), debitPeople.get(i).getDebtPaid()
                                , debitPeople.get(i).getTotalDebt(), debitPeople.get(i).getDate(), debitPeople.get(i).getNotes(),
                                debitPeople.get(i).getPayingAmount(),debitPeople.get(i).getEmail());

                        if (dateFormat.equals(formatMonth)) {
                            listMonth.add(debit);

                        }
                        adapter.notifyDataSetChanged();
                    }
                    adapter.setList(listMonth);
                }
            });
            binding.TextToolBar.setText(R.string.Debts_Month);
            binding.debtToday.setText(R.string.Total_Debt_Month);
            binding.tvDebtToday.setText("0.0");
        }

        viewModel.SumTotalAll(new TotalDebtAll() {
            @Override
            public void TotalDebt(double Number) {
                binding.tvSincereDebt.setText(Number + "");
            }
        },email);

    }

    @Override
    public void onClickItem(DebitPeople debitPeople) {
        DebtDialogFragment dialog = new DebtDialogFragment();
        dialog.show(getSupportFragmentManager(), null);

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putInt(KeyCreditorData, debitPeople.getId());
        myEdit.commit();

    }

    @Override
    public void OnClickItemDelete(DebitPeople people) {
        viewModel.DeleteOneDebit(people.getId(),email);
        listMonth.clear();
        listYear.clear();
        listDay.clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void OnClickItemEdit(DebitPeople people) {

        Intent intent=new Intent(getApplicationContext(),AddCreditorDataActivity.class);
        intent.putExtra(DebtsFragment.KEY_DEBTS,2);
        intent.putExtra("KetDebit",people.getId());
        startActivity(intent);
    }

    public String getDateFormatDay(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd", Locale.ENGLISH);
        return simpleDateFormat.format(date);
    }

    public String getDateFormatMonth(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM", Locale.ENGLISH);
        return simpleDateFormat.format(date);
    }

    public String getDateFormatYear(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy", Locale.ENGLISH);
        return simpleDateFormat.format(date);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        listMonth.clear();
        listDay.clear();
        listYear.clear();
    }
}