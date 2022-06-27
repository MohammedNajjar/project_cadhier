package com.anas.cashier;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.anas.cashier.databinding.FragmentLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginFragment extends Fragment {



    private FragmentLoginBinding binding;
    private FirebaseAuth firebaseAuth;
    public static SharedPreferences sp;
    public static SharedPreferences.Editor editor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentLoginBinding.inflate(inflater,container,false);
        firebaseAuth=FirebaseAuth.getInstance();
        binding.progressBar.setVisibility(View.GONE);
        sp= getActivity().getSharedPreferences("EmailSharedPreferences",Context.MODE_PRIVATE);
        editor=sp.edit();

        binding.tvForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),ForgetPasswordActivity.class));
            }
        });

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.progressBar.setVisibility(View.VISIBLE);

                if (TextUtils.isEmpty(binding.etEmail.getText().toString())){

                    Toast.makeText(getActivity(), R.string.please_Enter_an_Email, Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(binding.etPassword.getText().toString())){
                    Toast.makeText(getActivity(), R.string.please_Enter_an_Password, Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(binding.etEmail.getText().toString(),
                        binding.etPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            binding.progressBar.setVisibility(View.GONE);
                            editor.putString("Email",binding.etEmail.getText().toString());
                            editor.apply();

                            startActivity(new Intent(getActivity(),HomeActivity.class));
                        }else {
                            binding.progressBar.setVisibility(View.GONE);
                            Toast.makeText(getActivity(), R.string.Please_confirm_your_email_and_password, Toast.LENGTH_SHORT).show();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        binding.progressBar.setVisibility(View.GONE);
                        Log.d("ttt",e.getMessage());
                    }
                });
            }
        });
    }
}