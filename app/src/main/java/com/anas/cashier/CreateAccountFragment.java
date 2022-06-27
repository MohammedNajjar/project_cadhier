package com.anas.cashier;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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

import com.anas.cashier.Classes.User;
import com.anas.cashier.databinding.FragmentCreateAccountBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateAccountFragment extends Fragment {


    private FragmentCreateAccountBinding binding;
    private FirebaseAuth firebaseAuth;
    private Uri uri;
    private String currentTime;
    private String currentDate;
    private Calendar calendar;
    private String Image = null;
    private FirebaseStorage firebaseStorage;
    public static SharedPreferences sp;
    public static SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("HH.mm.ss");
        currentTime = format.format(calendar.getTime());

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM.dd");
        currentDate = format1.format(calendar.getTime());

        binding = FragmentCreateAccountBinding.inflate(inflater, container, false);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        binding.progressBar.setVisibility(View.GONE);
        sp = getActivity().getSharedPreferences("EmailSharedPreferences", Context.MODE_PRIVATE);
        editor = sp.edit();


        ActivityResultLauncher<String> arl = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        if (result != null) {
                            uri = result;
                            binding.imageOwn.setImageURI(uri);
                        }
                    }
                });
        binding.addImageOwn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arl.launch("image/*");
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(binding.etNameShop.getText().toString())) {
                    Toast.makeText(getActivity(), R.string.please_enter_name_shop_eEmail, Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(binding.etEmailAddress.getText().toString())) {
                    Toast.makeText(getActivity(), R.string.please_enter_email, Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(binding.etMubileNumber.getText().toString())) {
                    Toast.makeText(getActivity(), R.string.please_enter_mobile_number, Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(binding.etPassword.getText().toString())) {
                    Toast.makeText(getActivity(), R.string.please_enter_password, Toast.LENGTH_SHORT).show();
                    return;
                }

                binding.progressBar.setVisibility(View.VISIBLE);

                if (uri != null) {
                    firebaseStorage.getReference().child("Images/" + currentDate + " - " + currentTime).putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Image = uri.toString();
                                    binding.progressBar.setVisibility(View.GONE);
                                    Image = String.valueOf(uri);
                                    publishUser();
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            binding.progressBar.setVisibility(View.GONE);
                            Toast.makeText(getActivity(), getString(R.string.faild_to_upload_image) + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    binding.progressBar.setVisibility(View.GONE);
                    publishUser();
                }
                firebaseAuth.createUserWithEmailAndPassword(binding.etEmailAddress.getText().toString(), binding.etPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            binding.progressBar.setVisibility(View.GONE);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        binding.progressBar.setVisibility(View.GONE);
                        Log.d("ttt", e.getMessage());
                    }
                });

            }
        });
    }

    public void publishUser() {
        User user = new User(binding.etEmailAddress.getText().toString(), binding.etNameShop.getText().toString()
                , binding.etPassword.getText().toString(),
                binding.etMubileNumber.getText().toString(), binding.etNameOwn.getText().toString());

        DocumentReference documentReference = FirebaseFirestore.getInstance().collection("user").document();
        user.setId(documentReference.getId());
        if (Image != null) {
            user.setImage(Image);
        }
        documentReference.set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {
                    if (task.isSuccessful()) {
                        editor.putString("Email", binding.etEmailAddress.getText().toString());
                        editor.apply();
                        binding.progressBar.setVisibility(View.GONE);
                        startActivity(new Intent(getActivity(), HomeActivity.class));
                        Toast.makeText(getActivity(), R.string.login_succeeded, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), R.string.please_check_the_information, Toast.LENGTH_SHORT).show();
                        binding.progressBar.setVisibility(View.GONE);
                    }
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                binding.progressBar.setVisibility(View.GONE);
                Log.d("ttt", e.getMessage());
            }
        });
    }
}