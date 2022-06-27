package com.anas.cashier.ui.setting;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anas.cashier.AboutActivity;
import com.anas.cashier.ChangeLanguageActivity;
import com.anas.cashier.ChangePasswordActivity;
import com.anas.cashier.NotificationsActivity;
import com.anas.cashier.PrivacyPolicyActivity;
import com.anas.cashier.R;
import com.anas.cashier.databinding.FragmentSettingBinding;

public class SettingFragment extends Fragment {


    private FragmentSettingBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentSettingBinding.inflate(inflater,container,false);

        binding.changeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ChangeLanguageActivity.class));
            }
        });

        binding.notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), NotificationsActivity.class));
            }
        });

        binding.About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AboutActivity.class));
            }
        });

        binding.PrivacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PrivacyPolicyActivity.class));
            }
        });

        binding.changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ChangePasswordActivity.class));
            }
        });
        return binding.getRoot();
    }
}