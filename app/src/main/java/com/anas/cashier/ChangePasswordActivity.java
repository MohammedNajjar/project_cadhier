package com.anas.cashier;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.anas.cashier.Classes.User;
import com.anas.cashier.databinding.ActivityChangePasswordBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ChangePasswordActivity extends AppCompatActivity {

    private ActivityChangePasswordBinding binding;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private String password;
    private SharedPreferences sp;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChangePasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        sp = getSharedPreferences("EmailSharedPreferences", Context.MODE_PRIVATE);
        email = sp.getString("Email", null);

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        binding.toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_icons__arrows_previuos));

        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                onBackPressed();
            }
        });

        FirebaseFirestore.getInstance().collection("user").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                            User user = queryDocumentSnapshot.toObject(User.class);
                            if (email != null) {
                                if (user.getEmail().equals(email)) {

                                    FirebaseFirestore.getInstance().collection("user").document(user.getId())
                                            .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                            DocumentSnapshot documentSnapshot = task.getResult();
                                            User user = documentSnapshot.toObject(User.class);
                                            password = user.getPassword();

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(getBaseContext(), getString(R.string.error) + e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });


                                }
                            }
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("ttt", e.getMessage());
            }
        });

        binding.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseFirestore.getInstance().collection("user").get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                                    User user = queryDocumentSnapshot.toObject(User.class);
                                    if (email != null) {
                                        if (user.getEmail().equals(email)) {
                                            FirebaseFirestore.getInstance().collection("user").document(user.getId())
                                                    .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                    DocumentSnapshot documentSnapshot = task.getResult();
                                                    User user = documentSnapshot.toObject(User.class);
                                                    if (binding.CurrentPassword.getText().toString().equals(user.getPassword())) {
                                                        firebaseUser.updatePassword(binding.NewPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {

                                                                if (binding.NewPassword.getText().toString().equals(binding.ConfirmPassword.getText().toString())) {

                                                                    if (task.isSuccessful()) {
                                                                        Log.d("ttt", "Password updated");
                                                                        Toast.makeText(ChangePasswordActivity.this, R.string.password_updated, Toast.LENGTH_SHORT).show();
                                                                        finish();
                                                                    } else {
                                                                        Log.d("ttt", "Error password not updated");
                                                                    }
                                                                } else {
                                                                    Toast.makeText(getApplicationContext(), R.string.please_make_sure_the_password_matches, Toast.LENGTH_SHORT).show();
                                                                }


                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                            @Override
                                                            public void onFailure(@NonNull Exception e) {

                                                                Log.d("ttt", e.getMessage());
                                                            }
                                                        });
                                                    } else {
                                                        Toast.makeText(ChangePasswordActivity.this, R.string.please_confirm_the_old_password, Toast.LENGTH_SHORT).show();
                                                    }

                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(getBaseContext(), getString(R.string.error) + e.getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            });


                                        }
                                    }
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("ttt", e.getMessage());
                    }
                });

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cancel, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_cancel:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}