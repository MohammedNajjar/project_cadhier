package com.anas.cashier.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anas.cashier.Classes.Item_Onboarding;
import com.anas.cashier.Interface.OnClickButtonsBoarding;
import com.anas.cashier.Interface.OnClickNextOnboard;
import com.anas.cashier.R;
import com.anas.cashier.databinding.DesignOnboardingBinding;

import java.util.List;

public class OnboardAdapter extends RecyclerView.Adapter<OnboardAdapter.OnboardViewHolder> {


    private final List<Item_Onboarding> list;
    private OnClickButtonsBoarding listener;
    private OnClickNextOnboard onClickNextOnboard;
    public OnboardAdapter(List<Item_Onboarding> list, OnClickButtonsBoarding listener,OnClickNextOnboard onClickNextOnboard) {
        this.list = list;
        this.listener=listener;
        this.onClickNextOnboard=onClickNextOnboard;
    }

    @NonNull
    @Override
    public OnboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.design_onboarding,parent,false);
        return new OnboardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardViewHolder holder, int position) {
        Item_Onboarding item_onboarding=list.get(position);
        holder.bind(item_onboarding);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class OnboardViewHolder extends RecyclerView.ViewHolder {
        DesignOnboardingBinding binding;
        public OnboardViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=DesignOnboardingBinding.bind(itemView);

            binding.btnCreateAccount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.OnClickCreateAccount();
                }
            });

            binding.btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.OnClickLogin();
                }
            });

            binding.next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickNextOnboard.OnClickNext();
                }
            });

        }
        void bind(Item_Onboarding item){
            binding.image.setImageResource(item.getImage());
            binding.tvTitle.setText(item.getTitle());
            binding.tvDescription.setText(item.getDescription());

            if (item.getDescription()!=null){
                binding.btnLogin.setVisibility(View.GONE);
                binding.btnCreateAccount.setVisibility(View.GONE);
                binding.next.setVisibility(View.VISIBLE);
            }else {
                binding.btnLogin.setVisibility(View.VISIBLE);
                binding.btnCreateAccount.setVisibility(View.VISIBLE);
                binding.next.setVisibility(View.GONE);
            }
        }
    }
}
