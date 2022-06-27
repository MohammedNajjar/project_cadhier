package com.anas.cashier.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anas.cashier.Interface.OnClickItemAllProducts;
import com.anas.cashier.R;
import com.anas.cashier.RoomDB.Tables.Products;
import com.anas.cashier.databinding.CustomItemAllprodcutsBinding;

import java.util.List;

public class AllProductsAdapter extends RecyclerView.Adapter<AllProductsAdapter.ItemViewHolder> {

    private List<Products> list;
    private OnClickItemAllProducts listener;

    public AllProductsAdapter(List<Products> list,OnClickItemAllProducts listener) {
        this.list = list;
        this.listener=listener;
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
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_allprodcuts,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Products products=list.get(position);
        holder.bind(products);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private CustomItemAllprodcutsBinding binding;
        private Products product;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=CustomItemAllprodcutsBinding.bind(itemView);
        }
        void bind(Products p){
            product=p;
            binding.tvName.setText(": "+String.valueOf(p.getName()));
            binding.tvBarcode.setText(": "+String.valueOf(p.getBarCode()));
            binding.tvQuantity.setText(": "+String.valueOf(p.getQuantity()));
            binding.tvSellPrice.setText(": "+String.valueOf(p.getPriceSelling()));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.OnClickItem(p);
                }
            });
            binding.close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.OnClickItemClose(p);
                }
            });
        }
    }
}
