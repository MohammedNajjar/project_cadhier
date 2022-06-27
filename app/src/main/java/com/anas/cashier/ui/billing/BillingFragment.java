package com.anas.cashier.ui.billing;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.anas.cashier.DetailsBillActivity;
import com.anas.cashier.SheetDialogBillFragment;
import com.anas.cashier.databinding.FragmentBillingBinding;


public class BillingFragment extends Fragment {

    private FragmentBillingBinding binding;
    public static final String KEY_BillDetails="Bill details";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentBillingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SheetDialogBillFragment billFragment=new SheetDialogBillFragment();
                billFragment.show(getActivity().getSupportFragmentManager(), "TAG");
            }
        });


        binding.allInvoices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),DetailsBillActivity.class);
                intent.putExtra(KEY_BillDetails,1);
                startActivity(intent);
            }
        });

        binding.salesInvoices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),DetailsBillActivity.class);
                intent.putExtra(KEY_BillDetails,2);
                startActivity(intent);
            }
        });
        binding.purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),DetailsBillActivity.class);
                intent.putExtra(KEY_BillDetails,3);
                startActivity(intent);
            }
        });
        binding.todayBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),DetailsBillActivity.class);
                intent.putExtra(KEY_BillDetails,4);
                startActivity(intent);
            }
        });

        binding.monthBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),DetailsBillActivity.class);
                intent.putExtra(KEY_BillDetails,5);
                startActivity(intent);
            }
        });
        binding.yearBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),DetailsBillActivity.class);
                intent.putExtra(KEY_BillDetails,6);
                startActivity(intent);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}