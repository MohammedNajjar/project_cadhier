package com.anas.cashier.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anas.cashier.Classes.ItemDetailsInventory;
import com.anas.cashier.Classes.Product;
import com.anas.cashier.Interface.OnClickEditListener;
import com.anas.cashier.R;
import com.anas.cashier.RoomDB.Tables.Products;
import com.anas.cashier.databinding.CustomDetailsInventoryBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DetailsInventoryAdapter extends RecyclerView.Adapter<DetailsInventoryAdapter.DetailsInventoryViewHolder> {


    List<Products> list;
    OnClickEditListener listener;

    public DetailsInventoryAdapter(ArrayList<Products> list, OnClickEditListener listener) {
        this.list = list;
        this.listener = listener;
    }

    public List<Products> getList() {
        return list;
    }

    public void setList(List<Products> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DetailsInventoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_details_inventory, parent, false);
        DetailsInventoryViewHolder viewHolder = new DetailsInventoryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsInventoryViewHolder holder, int position) {

        Products inventory = list.get(position);
        holder.bind(inventory);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class DetailsInventoryViewHolder extends RecyclerView.ViewHolder {
        CustomDetailsInventoryBinding binding;

        public DetailsInventoryViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = CustomDetailsInventoryBinding.bind(itemView);
        }

        @SuppressLint("SetTextI18n")
        void bind(Products inventory) {

            String dateFormatDay=getDateFormatDay(inventory.getDatePurchase());
            binding.date.setText(dateFormatDay);
            binding.namePro.setText(inventory.getName());
            binding.tvPriceBuy.setText(String.valueOf(inventory.getPriceBuy()));
            binding.tvPriceSelling.setText(String.valueOf(inventory.getPriceSelling()));
            binding.qurity.setText("Quantity : "+inventory.getQuantity());
            binding.imageView1.setImageResource(R.drawable.images_shps_3);


            binding.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.OnClickEdit(inventory.getId());
                }
            });


        }
    }
    public String getDateFormatDay(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy", Locale.ENGLISH);
        return simpleDateFormat.format(date);
    }
}
