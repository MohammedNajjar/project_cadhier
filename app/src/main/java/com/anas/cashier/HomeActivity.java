package com.anas.cashier;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anas.cashier.Classes.User;
import com.anas.cashier.databinding.ActivityHomeBinding;
import com.anas.cashier.ui.billing.BillingFragment;
import com.anas.cashier.ui.casher.CashierFragment;
import com.anas.cashier.ui.debts.DebtsFragment;
import com.anas.cashier.ui.inventory.InventoryFragment;
import com.anas.cashier.ui.logout.LogoutFragment;
import com.anas.cashier.ui.setting.SettingFragment;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity implements LogoutFragment.OnClickButtonYes,
        LogoutFragment.OnClickButtonNo {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomeBinding binding;
    private DrawerLayout drawer;
    private TextView NameShop, NameOwn;
    private ImageView ImageOwn;
    private String email;
    private SharedPreferences sp;
    private LogoutFragment logoutFragment;
    public static SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        sp = getSharedPreferences("EmailSharedPreferences", Context.MODE_PRIVATE);
        editor=sp.edit();
        email = sp.getString("Email", null);
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarHome.toolbar);
        binding.appBarHome.toolbar.setNavigationIcon(null);
        drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_casher, R.id.nav_inventory, R.id.nav_billing, R.id.nav_debts, R.id.nav_settings)
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        if(savedInstanceState==null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_home,
                    new CashierFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_casher);
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.nav_casher:
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_home,
                                new CashierFragment()).commit();
                        binding.appBarHome.titleToolbar.setText(getString(R.string.Cashier));
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_inventory:
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_home,
                                new InventoryFragment()).commit();
                        binding.appBarHome.titleToolbar.setText(getString(R.string.Inventory));
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_billing:
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_home,
                                new BillingFragment()).commit();
                        binding.appBarHome.titleToolbar.setText(getString(R.string.Billing));
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_debts:
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_home,
                                new DebtsFragment()).commit();
                        binding.appBarHome.titleToolbar.setText(getString(R.string.Debts));
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_settings:
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_home,
                                new SettingFragment()).commit();
                        binding.appBarHome.titleToolbar.setText(getString(R.string.Settings));
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_logout:
                        logoutFragment=new LogoutFragment();
                        logoutFragment.show(getSupportFragmentManager(),null);
                        break;
                }

                return true;
            }
        });

        //////////////////////////////////////////////////////////////////
        View view = navigationView.getHeaderView(0);
        NameOwn = view.findViewById(R.id.NameOwner);
        NameShop = view.findViewById(R.id.NameShop);
        ImageOwn = view.findViewById(R.id.image_own);

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
                                            NameShop.setText(user.getNameShop());
                                            NameOwn.setText(user.getNameOwn());
                                            Glide.with(getBaseContext()).load(user.getImage()).placeholder(R.drawable.ic_user).into(ImageOwn);
                                            CircleImageView d=binding.appBarHome.imageOwnApp;
                                            Glide.with(getBaseContext())
                                                    .load(user.getImage())
                                                    .into(ImageOwn);

                                            Glide.with(getBaseContext())
                                                    .load(user.getImage())
                                                    .into(d);

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


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void OnClickYes() {
        editor.clear();
        editor.apply();
        finish();
        logoutFragment.dismiss();
    }

    @Override
    public void OnClickNo() {
        logoutFragment.dismiss();
    }
}