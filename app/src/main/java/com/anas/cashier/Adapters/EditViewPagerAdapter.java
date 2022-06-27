package com.anas.cashier.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.anas.cashier.EditAccountFragment;
import com.anas.cashier.EditDataFragment;

public class EditViewPagerAdapter extends FragmentStateAdapter {


    public EditViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0:
                return new EditDataFragment();


            default:return new EditAccountFragment();
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
