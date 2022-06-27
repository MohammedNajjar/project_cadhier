package com.anas.cashier.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anas.cashier.Classes.Product;
import com.anas.cashier.Interface.OnClickImageDeleteProduct;
import com.anas.cashier.R;
import com.anas.cashier.RoomDB.Tables.PersonPurchases;
import com.anas.cashier.RoomDB.Tables.Products;
import com.anas.cashier.databinding.CustomItemProBinding;

import java.util.List;

public class ListProductAdapter extends RecyclerView.Adapter<ListProductAdapter.ProductViewHolder> {

    private List<PersonPurchases> list;
    private OnClickImageDeleteProduct onClickImageDeleteProduct;

    public ListProductAdapter(List<PersonPurchases> list,OnClickImageDeleteProduct onClickImageDeleteProduct) {
        this.list = list;
        this.onClickImageDeleteProduct=onClickImageDeleteProduct;
    }

    public List<PersonPurchases> getList() {
        return list;
    }

    public void setList(List<PersonPurchases> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_pro,parent,false);
        ProductViewHolder holder=new ProductViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        PersonPurchases product=list.get(position);
        holder.bind(product);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        CustomItemProBinding binding;
        PersonPurchases product;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=CustomItemProBinding.bind(itemView);

        }

        void bind(PersonPurchases product){
            this.product=product;
            binding.etNamePro.setText(product.getName());
            binding.etCategoryPro.setText(product.getCategory());
            binding.etPricePro.setText(String.valueOf(product.getPriceSelling()));
            binding.etQuantityPro.setText(String.valueOf(product.getQuantity()));

            binding.imageDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickImageDeleteProduct.OnClickImage(product);
                }
            });

        }
    }
}
