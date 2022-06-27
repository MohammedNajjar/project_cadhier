package com.anas.cashier;

import static android.os.ParcelFileDescriptor.MODE_APPEND;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anas.cashier.Adapters.ViewPagerAdapter;
import com.anas.cashier.databinding.FragmentSheetLoginBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class SheetLoginFragment extends BottomSheetDialogFragment {


    FragmentSheetLoginBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentSheetLoginBinding.inflate(inflater,container,false);

        ViewPagerAdapter viewPager=new ViewPagerAdapter(getActivity());
        binding.ViewPager2.setAdapter(viewPager);


        new TabLayoutMediator(binding.tabLayout, binding.ViewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {


                switch (position){
                    case 0:
                        tab.setText(R.string.Create_Account);
                        break;
                    case 1:
                        tab.setText(R.string.Login);
                        break;
                }
            }
        }).attach();

        SharedPreferences sh = getActivity().getSharedPreferences("MySharedPref", MODE_APPEND);

        int a = sh.getInt("Key", 0);
        if (a==1){

            binding.tabLayout.getTabAt(0).select();
        }else if (a==2){
            binding.tabLayout.getTabAt(1).select();
        }


        return binding.getRoot();
    }
}