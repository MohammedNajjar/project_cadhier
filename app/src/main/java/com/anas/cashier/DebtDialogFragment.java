package com.anas.cashier;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anas.cashier.databinding.FragmentDebtDialogBinding;
import com.google.android.material.datepicker.CompositeDateValidator;

public class DebtDialogFragment extends DialogFragment {


    FragmentDebtDialogBinding binding;

//    @NonNull
//    @Override
//    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//
//        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
//        builder.setTitle("Change password");
//        builder.setMessage("Do you Sure you want change password ?");
//
//
//        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//
//              //  onPositiveClickListener.OnPositiveButtonClick();
//            }
//        });
//        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//
//              //  onNegativeClickListener.OnNegativeButtonClick();
//            }
//        });
//        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//
//            //    onNeutralClickListener.OnNeutralButtonClick();
//            }
//        });
//        return builder.create();
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding=FragmentDebtDialogBinding.inflate(inflater,container,false);

        binding.debt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),CreditorDebtActivity.class));
            }
        });

        binding.data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CreditorDataActivity.class));
            }
        });
        return binding.getRoot();
    }
}