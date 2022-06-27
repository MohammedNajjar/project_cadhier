package com.anas.cashier.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anas.cashier.Classes.Inventory;
import com.anas.cashier.Interface.OnClickItemInventory;
import com.anas.cashier.R;
import com.anas.cashier.databinding.CustomItemInventoryBinding;

import java.util.ArrayList;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.InventoryViewHolder> {


    ArrayList<Inventory> list;
    OnClickItemInventory onClickItemInventory;

    public InventoryAdapter(ArrayList<Inventory> list,OnClickItemInventory onClickItemInventory) {
        this.list = list;
        this.onClickItemInventory=onClickItemInventory;
    }

    @NonNull
    @Override
    public InventoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_inventory,parent,false);
        InventoryViewHolder viewHolder=new InventoryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InventoryViewHolder holder, int position) {
        Inventory inventory=list.get(position);
        holder.bind(inventory);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class InventoryViewHolder extends RecyclerView.ViewHolder {

        CustomItemInventoryBinding binding;
        Inventory i;
        public InventoryViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=CustomItemInventoryBinding.bind(itemView);


        }

        void bind(Inventory inventory){
            i=inventory;
            binding.imageInventory.setImageResource(inventory.getImage());
            binding.tvInventory.setText(inventory.getTitle());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickItemInventory.OnClickItem_Inventory(inventory);
                }
            });
        }
    }
}
