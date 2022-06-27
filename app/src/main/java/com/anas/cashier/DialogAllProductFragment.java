package com.anas.cashier;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anas.cashier.RoomDB.MyViewModel;
import com.anas.cashier.RoomDB.Tables.Products;
import com.anas.cashier.databinding.FragmentDialogAllProductBinding;


public class DialogAllProductFragment extends DialogFragment {

    private FragmentDialogAllProductBinding binding;
    private OnClickCancel onClickCancel;
    private OnClickEdit onClickEdit;
    private Products p;
    private MyViewModel viewModel;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnClickEdit){
            onClickEdit= (OnClickEdit) context;
        }else {
            throw new RuntimeException("Please implements listener : OnClickEdit");
        } if (context instanceof OnClickCancel){
            onClickCancel= (OnClickCancel) context;
        }else {
            throw new RuntimeException("Please implements listener : OnClickCancel");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onClickCancel=null;
        onClickEdit=null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding=FragmentDialogAllProductBinding.inflate(inflater,container,false);
        viewModel=new ViewModelProvider(this).get(MyViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        p=new Products();

        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickCancel.onClickCancelBtn();
            }
        });

        binding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickEdit.onClickEditBtn(binding.tvName.getText().toString());
            }
        });

    }

    public interface OnClickEdit{
        void onClickEditBtn(String a);
    }

    public interface OnClickCancel{
        void onClickCancelBtn();
    }
}