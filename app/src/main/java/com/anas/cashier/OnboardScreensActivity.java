package com.anas.cashier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.anas.cashier.Adapters.OnboardAdapter;
import com.anas.cashier.Classes.Item_Onboarding;
import com.anas.cashier.Interface.OnClickButtonsBoarding;
import com.anas.cashier.Interface.OnClickNextOnboard;
import com.anas.cashier.databinding.ActivityOnboardingScreensBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class OnboardScreensActivity extends AppCompatActivity implements OnClickButtonsBoarding, OnClickNextOnboard {

    private ActivityOnboardingScreensBinding binding;
    private OnboardAdapter adapter;
    private SharedPreferences sharedPreferences ;
    private SharedPreferences.Editor myEdit ;
    private FirebaseUser user;
    private String email;
    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityOnboardingScreensBinding.inflate(getLayoutInflater());
        user= FirebaseAuth.getInstance().getCurrentUser();
        sp= getSharedPreferences("EmailSharedPreferences", Context.MODE_PRIVATE);
        email=sp.getString("Email",null);
        setContentView(binding.getRoot());

        if (email!=null){
            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
        }

        setupOnboardItems();
        setupOnboardIndicators();

        binding.viewPager.setAdapter(adapter);

        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                setCurrentOnboardIndicator(position);
            }
        });

//        binding.next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (binding.viewPager.getCurrentItem()+1<adapter.getItemCount()){
//                    binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem()+1);
//                }else{
//                   SheetLoginFragment loginFragment=new SheetLoginFragment();
//                   loginFragment.show(getSupportFragmentManager(),null);
//                }
//            }
//        });
    }


    private void setupOnboardItems(){

        List<Item_Onboarding> list=new ArrayList<>();
        //////////WelCome_1//////////
        Item_Onboarding item_Welcome_1=new Item_Onboarding();
        item_Welcome_1.setTitle(getString(R.string.About_app));
        item_Welcome_1.setDescription(getString(R.string.It_is_an_application));
        item_Welcome_1.setImage(R.drawable.welcome_1);
        //////////WelCome_2//////////
        Item_Onboarding item_Welcome_2=new Item_Onboarding();
        item_Welcome_2.setTitle(getString(R.string.Our_message));
        item_Welcome_2.setDescription(getString(R.string.Employing_the_latest));
        item_Welcome_2.setImage(R.drawable.welcome_2);
        //////////WelCome_3//////////
        Item_Onboarding item_Welcome_3=new Item_Onboarding();
        item_Welcome_3.setTitle(getString(R.string.Welcome_to_our));
        item_Welcome_3.setDescription(null);
        item_Welcome_3.setImage(R.drawable.welcome_3);



        list.add(item_Welcome_1);
        list.add(item_Welcome_2);
        list.add(item_Welcome_3);


        adapter=new OnboardAdapter(list,this, this);


    }

    private void setupOnboardIndicators(){
        ImageView[] imageViews=new ImageView[adapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8,0,8,0);
        for (int i =0 ;i<imageViews.length;i++){
            imageViews[i]=new ImageView(getApplicationContext());
            imageViews[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),R.drawable.onboarding_inactivte
            ));
            imageViews[i].setLayoutParams(layoutParams);
            binding.linear.addView(imageViews[i]);
        }
    }

    private void setCurrentOnboardIndicator(int index){
        int childCount=binding.linear.getChildCount();
        for (int i=0;i<childCount;i++){
            ImageView imageView=(ImageView) binding.linear.getChildAt(i);
            if (i==index){
                imageView.setImageDrawable(ContextCompat
                        .getDrawable(getApplicationContext(),R.drawable.onboarding_activte));
            }else {
                imageView.setImageDrawable(ContextCompat
                        .getDrawable(getApplicationContext(),R.drawable.onboarding_inactivte));
            }
        }

//        if (index==adapter.getItemCount()-1){
//            binding.next.setVisibility(View.GONE);
//        }else{
//            binding.next.setVisibility(View.VISIBLE);
//            binding.next.setText("Next");
//        }
    }

    @Override
    public void OnClickCreateAccount() {


        SheetLoginFragment loginFragment=new SheetLoginFragment();
        loginFragment.show(getSupportFragmentManager(),null);
        sharedPreferences= getSharedPreferences("MySharedPref",MODE_PRIVATE);
        myEdit = sharedPreferences.edit();
        myEdit.putInt("Key",1);
        myEdit.commit();

    }

    @Override
    public void OnClickLogin() {
        SheetLoginFragment loginFragment=new SheetLoginFragment();
        loginFragment.show(getSupportFragmentManager(),null);
        sharedPreferences= getSharedPreferences("MySharedPref",MODE_PRIVATE);
        myEdit = sharedPreferences.edit();
        myEdit.putInt("Key",2);
        myEdit.commit();

    }

    @Override
    public void OnClickNext() {
        if (binding.viewPager.getCurrentItem()+1<adapter.getItemCount()){
            binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem()+1);
        }else{
            SheetLoginFragment loginFragment=new SheetLoginFragment();
            loginFragment.show(getSupportFragmentManager(),null);
        }
    }
}