package com.anas.cashier.ui.logout;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anas.cashier.R;
import com.anas.cashier.databinding.FragmentLogoutBinding;


public class LogoutFragment extends DialogFragment {

    private OnClickButtonYes buttonYes;
    private OnClickButtonNo buttonNo;
    private FragmentLogoutBinding binding;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnClickButtonYes) {
            buttonYes= (OnClickButtonYes) context;
        }else{
            throw new RuntimeException("Please implements listener :OnClickButtonYes");
        } if (context instanceof OnClickButtonNo) {
            buttonNo= (OnClickButtonNo) context;
        }else{
            throw new RuntimeException("Please implements listener :OnClickButtonNo");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        buttonYes=null;
        buttonNo=null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentLogoutBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonNo.OnClickNo();
            }
        });

        binding.btYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonYes.OnClickYes();
            }
        });

    }

    public interface OnClickButtonYes{
        void OnClickYes();
    }

    public interface OnClickButtonNo{
        void OnClickNo();
    }
}