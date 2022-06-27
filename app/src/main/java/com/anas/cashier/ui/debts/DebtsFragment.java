package com.anas.cashier.ui.debts;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.anas.cashier.AddCreditorDataActivity;
import com.anas.cashier.DetailsDebtActivity;
import com.anas.cashier.R;
import com.anas.cashier.SheetDialogDebtFragment;
import com.anas.cashier.databinding.FragmentDebtsBinding;


public class DebtsFragment extends Fragment {


    private FragmentDebtsBinding binding;
    public static final String KEY_DEBTSList ="KeyDebtsList";
    public static final String KEY_DEBTS ="KeyDebts";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentDebtsBinding.inflate(inflater,container,false);
        setHasOptionsMenu(true);
        binding.filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SheetDialogDebtFragment sheet=new SheetDialogDebtFragment();
                sheet.show(getActivity().getSupportFragmentManager(),"TAG");
            }
        });

        binding.todayDebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),DetailsDebtActivity.class);
                intent.putExtra(KEY_DEBTSList,1);
                startActivity(intent);
            }
        });

        binding.expiredDebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),DetailsDebtActivity.class);
                intent.putExtra(KEY_DEBTSList,2);
                startActivity(intent);
            }
        });

        binding.debtYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),DetailsDebtActivity.class);
                intent.putExtra(KEY_DEBTSList,3);
                startActivity(intent);
            }
        });

        binding.highPriceDebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),DetailsDebtActivity.class);
                intent.putExtra(KEY_DEBTSList,4);
                startActivity(intent);
            }
        });

        binding.lowPriceDebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),DetailsDebtActivity.class);
                intent.putExtra(KEY_DEBTSList,5);
                startActivity(intent);
            }
        });

        binding.monthDebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),DetailsDebtActivity.class);
                intent.putExtra(KEY_DEBTSList,6);
                startActivity(intent);
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.add, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_add:

                Intent intent=new Intent(getActivity(),AddCreditorDataActivity.class);
                intent.putExtra(KEY_DEBTS,1);
                startActivity(intent);
                return true;

        }

        return false;
    }

}