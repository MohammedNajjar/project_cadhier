package com.anas.cashier.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anas.cashier.Interface.OnClickItemDebt;
import com.anas.cashier.R;
import com.anas.cashier.RoomDB.Tables.DebitPeople;
import com.anas.cashier.databinding.CustomItemDebtBinding;
import com.chauthai.swipereveallayout.ViewBinderHelper;

import java.util.List;

public class ListDebtAdapter extends RecyclerView.Adapter<ListDebtAdapter.ListViewHolder> {


    List<DebitPeople> list;
    OnClickItemDebt onClickItemDebt;
    private final ViewBinderHelper viewBinderHelper=new ViewBinderHelper();

    public ListDebtAdapter(List<DebitPeople> list,OnClickItemDebt onClickItemDebt) {
        this.list = list;
        this.onClickItemDebt=onClickItemDebt;
    }

    public List<DebitPeople> getList() {
        return list;
    }

    public void setList(List<DebitPeople> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_debt,parent,false);
        ListViewHolder listViewHolder=new ListViewHolder(view);
        return listViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {

        DebitPeople debit= list.get(position);
        viewBinderHelper.setOpenOnlyOne(true);
        viewBinderHelper.bind(holder.binding.swipe,String.valueOf(list.get(position).getId()));
        holder.bind(debit);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {

        CustomItemDebtBinding binding;
        DebitPeople debitPeople;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=CustomItemDebtBinding.bind(itemView);



        }

        public void bind(DebitPeople debt){
            debitPeople=debt;
            binding.etNameDebt.setText(debt.getName());
            binding.etTotalDebt.setText(debt.getTotalDebt()+"");
            binding.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickItemDebt.OnClickItemEdit(debt);
                }
            });
            binding.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickItemDebt.OnClickItemDelete(debt);
                }
            });

            binding.linearW.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onClickItemDebt.onClickItem(debitPeople);
                    return false;
                }
            });


            if (debt.getTotalDebt()>=100){
                binding.etRatedDebt.setText("High");
            }else if (debt.getTotalDebt()<100){
                binding.etRatedDebt.setText("Small");
            }

            if (debt.getTotalDebt()==0){
                binding.IconDebt.setImageResource(R.drawable.ic_true_green);
            }else if (debt.getTotalDebt()>0){
                binding.IconDebt.setImageResource(R.drawable.ic_false_red);
            }
        }
    }
}
