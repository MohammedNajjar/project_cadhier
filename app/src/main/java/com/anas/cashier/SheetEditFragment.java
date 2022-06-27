package com.anas.cashier;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anas.cashier.Adapters.EditViewPagerAdapter;
import com.anas.cashier.Adapters.ViewPagerAdapter;
import com.anas.cashier.databinding.FragmentSheetEditeBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class SheetEditFragment extends BottomSheetDialogFragment {


    FragmentSheetEditeBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding=FragmentSheetEditeBinding.inflate(inflater,container,false);
        EditViewPagerAdapter viewPager=new EditViewPagerAdapter(getActivity());
        binding.ViewPager2.setAdapter(viewPager);

        new TabLayoutMediator(binding.tabLayout, binding.ViewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                switch (position){
                    case 0:
                        tab.setText(R.string.Edit_Data);
                        break;
                    case 1:
                        tab.setText(R.string.Edit_Accounts);
                        break;
                }
            }
        }).attach();

        return binding.getRoot();
    }
}