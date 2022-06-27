package com.anas.cashier;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.anas.cashier.Interface.ListProductAdd;
import com.anas.cashier.RoomDB.MyViewModel;
import com.anas.cashier.RoomDB.Tables.Bill;
import com.anas.cashier.RoomDB.Tables.Products;
import com.anas.cashier.databinding.ActivityAddProductBinding;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class AddProductActivity extends AppCompatActivity implements
        ZXingScannerView.ResultHandler {

    private ActivityAddProductBinding binding;
    private MyViewModel viewModel;
    private String type;
    private String email;
    private SharedPreferences sp;
    private long index;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        sp = getSharedPreferences("EmailSharedPreferences", Context.MODE_PRIVATE);
        email = sp.getString("Email", null);
        setContentView(binding.getRoot());


        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.spinner_category,
                R.layout.color_spinner_layout);
        adapter.setDropDownViewResource(R.layout.color_dropdown_apinner);
        binding.spDepartment.setAdapter(adapter);

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        binding.toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_icons__arrows_bill));

        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                onBackPressed();
            }
        });

        binding.spDepartment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                type = binding.spDepartment.getSelectedItem().toString();
                index = binding.spDepartment.getSelectedItemId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(binding.etBarCode.getText().toString())) {
                    Toast.makeText(getApplicationContext(), getString(R.string.please_enter_bar_code), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(binding.etName.getText().toString())) {
                    Toast.makeText(getApplicationContext(), getString(R.string.please_enter_name), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(binding.etQuantity.getText().toString())) {
                    Toast.makeText(getApplicationContext(), getString(R.string.please_enter_quantity), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(binding.etPrice.getText().toString())) {
                    Toast.makeText(getApplicationContext(), getString(R.string.please_enter_price_buy), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(binding.etPriceSelling.getText().toString())) {
                    Toast.makeText(getApplicationContext(), getString(R.string.please_enter_price_selling), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(binding.etDescription.getText().toString())) {
                    Toast.makeText(getApplicationContext(), getString(R.string.please_enter_description), Toast.LENGTH_SHORT).show();
                    return;
                }
                String description = binding.etDescription.getText().toString();
                String barcode = binding.etBarCode.getText().toString();


                String buy = binding.etPrice.getText().toString();
                String Name = binding.etName.getText().toString();
                String selling = binding.etPriceSelling.getText().toString();
                Date date = new Date();
                String quantity = binding.etQuantity.getText().toString();

                viewModel.getList(email, Long.parseLong(binding.etBarCode.getText().toString()), new ListProductAdd() {
                    @Override
                    public void ListProduct(boolean boo) {
                        Log.d("e",boo+"");
                        if (!boo){
                            Bill bill=new Bill(Name,date,1,Double.parseDouble(buy),Double.parseDouble(selling),Integer.parseInt(quantity),email);
                            viewModel.InsertBill(bill);
                            //I am Will Edit The Product
                            Products product = new Products(Name, description, Long.parseLong(barcode), type, date,
                                    Integer.parseInt(quantity), Double.parseDouble(buy), Double.parseDouble(selling), email, index);
                            viewModel.InsertProducts(product);
                            finish();
                        }
                        else if (boo){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(AddProductActivity.this, R.string.the_product_already_added, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                    }
                });
            }
        });

        Dexter.withActivity(AddProductActivity.this).withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        binding.codeScanner.setResultHandler(AddProductActivity.this);
                        binding.codeScanner.startCamera();
                        binding.codeScanner.setAutoFocus(true);
                        binding.codeScanner.setSoundEffectsEnabled(true);
                        binding.codeScanner.setContentDescription("Barcode Scanner..");
                        binding.codeScanner.setClickable(true);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(getApplicationContext(), R.string.you_must_accept_this_permission, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                }).check();

    }

    @Override
    public void handleResult(Result rawResult) {

        ToneGenerator tg = new ToneGenerator(AudioManager.STREAM_NOTIFICATION, 100);
        tg.startTone(ToneGenerator.TONE_PROP_BEEP);
        if (rawResult != null) {

            viewModel.getProducts(email).observe(AddProductActivity.this, new Observer<List<Products>>() {
                @Override
                public void onChanged(List<Products> products) {

                    if (products.size() != 0) {
                        for (int i = 0; i < products.size(); i++) {
                            if (Long.parseLong(rawResult.getText()) != products.get(i).getBarCode()) {
                                binding.etBarCode.setText(rawResult.getText());
                                binding.codeScanner.stopCamera();
                            }
                        }
                    } else {
                        binding.etBarCode.setText(rawResult.getText());
                    }
                }
            });

        }
        binding.codeScanner.resumeCameraPreview(this);

    }
}