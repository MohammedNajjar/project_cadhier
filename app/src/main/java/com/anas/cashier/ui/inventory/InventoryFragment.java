package com.anas.cashier.ui.inventory;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.anas.cashier.Adapters.InventoryAdapter;
import com.anas.cashier.BottomSheet;
import com.anas.cashier.Classes.Inventory;
import com.anas.cashier.DetailsInventoryActivity;
import com.anas.cashier.Interface.OnClickItemInventory;
import com.anas.cashier.R;
import com.anas.cashier.databinding.FragmentInventoryBinding;

import java.util.ArrayList;

public class InventoryFragment extends Fragment implements OnClickItemInventory {

    private FragmentInventoryBinding binding;
    ArrayList<Inventory> list;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentInventoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        list=new ArrayList<>();
        list.add(new Inventory(getString(R.string.Legumes), R.drawable.ic_beans,0));
        list.add(new Inventory(getString(R.string.Snacks_and_Crackers), R.drawable.ic_snack,1));
        list.add(new Inventory(getString(R.string.Meat), R.drawable.ic_meat,2));
        list.add(new Inventory(getString(R.string.Fish), R.drawable.ic_fish,3));
        list.add(new Inventory(getString(R.string.Sauce), R.drawable.ic_sauce,4));
        list.add(new Inventory(getString(R.string.Dairy), R.drawable.ic_dairy,5));
        list.add(new Inventory(getString(R.string.Sweets), R.drawable.ic_sweets,6));
        list.add(new Inventory(getString(R.string.Vegetables_and_Fruits), R.drawable.ic_vegetables_fruits,7));
        list.add(new Inventory(getString(R.string.Coffee), R.drawable.ic_coffee,8));
        list.add(new Inventory(getString(R.string.Canned_food), R.drawable.ic_canned_food,9));
        list.add(new Inventory(getString(R.string.Cleaning_materials), R.drawable.ic_cleaning_materials,10));
        list.add(new Inventory(getString(R.string.Home_needs), R.drawable.ic_need_home,11));
        list.add(new Inventory(getString(R.string.Drinks), R.drawable.ic_drinks,12));

        InventoryAdapter adapter=new InventoryAdapter(list,this);
        binding.rv.setAdapter(adapter);
        binding.rv.setHasFixedSize(true);
        binding.rv.setLayoutManager(new GridLayoutManager(getActivity(),2));


        binding.filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheet bottomSheet=new BottomSheet();
                bottomSheet.show(getActivity().getSupportFragmentManager(), "TAG");
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @Override
    public void OnClickItem_Inventory(Inventory inventory) {
        Intent intent=new Intent(getContext(), DetailsInventoryActivity.class);
        intent.putExtra("index",inventory.getIndex());
        intent.putExtra("Catogory",inventory.getTitle());
        startActivity(intent);
    }
}