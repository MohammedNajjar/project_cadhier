package com.anas.cashier.Adapters;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anas.cashier.Classes.DetailsBill;
import com.anas.cashier.R;
import com.anas.cashier.RoomDB.Tables.Bill;
import com.anas.cashier.RoomDB.Tables.Products;
import com.anas.cashier.databinding.CustomItemBillBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ListBillAdapter extends RecyclerView.Adapter<ListBillAdapter.DetailsBillViewHolder> {

    List<Bill> list;

    public ListBillAdapter(List<Bill> list) {
        this.list = list;
    }

    public List<Bill> getList() {
        return list;
    }

    public void setList(List<Bill> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DetailsBillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_bill,parent,false);
        DetailsBillViewHolder viewHolder=new DetailsBillViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsBillViewHolder holder, int position) {
        Bill products=list.get(position);
        holder.bind(products);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class DetailsBillViewHolder extends RecyclerView.ViewHolder {
        CustomItemBillBinding binding;
        public DetailsBillViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=CustomItemBillBinding.bind(itemView);
        }

        @SuppressLint("SetTextI18n")
        void bind(Bill bill){

            String date=getDateFormatDay(bill.getDatePurchase());
            binding.namePro.setText(bill.getName());
            binding.date.setText(date);
            binding.tvBuyPrice.setText(String.valueOf(bill.getPriceBuy()));
            binding.tvQuantity.setText(String.valueOf(bill.getQuantity()));
            binding.tvSellingPrice.setText(String.valueOf(bill.getPriceSelling()));
            binding.tvProfit.setText(String.valueOf(bill.getPriceSelling()-bill.getPriceBuy()));

            if (bill.getValidity()==1){
                binding.buy.setText("Buy");
                binding.buy.setTextColor(Color.GREEN);
            }else if (bill.getValidity()==2){
                binding.buy.setText("Sell");
                binding.buy.setTextColor(Color.RED);
            }

        }
    }

    public String getDateFormatDay(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yy HH:mm:ss", Locale.ENGLISH);
        return simpleDateFormat.format(date);
    }
}
