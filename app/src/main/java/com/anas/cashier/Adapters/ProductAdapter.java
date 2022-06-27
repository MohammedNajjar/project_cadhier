package com.anas.cashier.Adapters;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anas.cashier.Classes.Product;
import com.anas.cashier.R;
import com.anas.cashier.databinding.CustomItemBillBinding;
import com.anas.cashier.databinding.CustomItemHeaderProBinding;
import com.anas.cashier.databinding.CustomItemProBinding;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.Holder> {


    ArrayList<Product> list;
    private static final int TYPE_HEAD=0;
    private static final int TYPE_LIST=1;
    private int index = 0;

    public ProductAdapter(ArrayList<Product> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (TextUtils.equals(list.get(index).getName(),"Name")){
            return new Holder2(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_header_pro,parent,false));
        }else {
            return new Holder1(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_pro,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        index++;
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    abstract class Holder extends RecyclerView.ViewHolder {
        public Holder(@NonNull View itemView) {
            super(itemView);
        }
        abstract void bind(Product product);
    }

    class Holder1 extends Holder {
        private CustomItemProBinding binding;
        private Product product;

        public Holder1(@NonNull View itemView) {
            super(itemView);
            binding = CustomItemProBinding.bind(itemView);
        }
        @Override
        void bind(Product product) {
            this.product = product;
            binding.etCategoryPro.setText(product.getCategory());
            binding.etNamePro.setText(product.getName());
            binding.etPricePro.setText(product.getPrice()+"");
            binding.etQuantityPro.setText(product.getQuantity());
        }
    }

    class Holder2 extends Holder {
        private CustomItemHeaderProBinding binding;
        private Product product;

        public Holder2(@NonNull View itemView) {
            super(itemView);
            binding = CustomItemHeaderProBinding.bind(itemView);
        }

        @Override
        void bind(Product product) {
            this.product = product;
            binding.etName.setText(product.getName());
            binding.etPrice.setText("Price");
            binding.etQuantity.setText(product.getQuantity());
        }

    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView name,price,category,quantity;
        int view_type;
        public ProductViewHolder(@NonNull View itemView,int view_type) {
            super(itemView);

            if (view_type==TYPE_LIST){
                view_type=1;

            }else if (view_type==TYPE_HEAD){
                name=itemView.findViewById(R.id.et_name);
                price=itemView.findViewById(R.id.et_price);
                category=itemView.findViewById(R.id.sp_department
                );
                quantity=itemView.findViewById(R.id.et_quantity);
                view_type=0;
            }



        }

    }

}
