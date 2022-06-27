package com.anas.cashier.ui.casher;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.anas.cashier.Adapters.ListProductAdapter;
import com.anas.cashier.AddProductActivity;
import com.anas.cashier.AllProductsActivity;
import com.anas.cashier.Interface.ListBill;
import com.anas.cashier.Interface.ListProduct;
import com.anas.cashier.Interface.ListProductAdd;
import com.anas.cashier.Interface.PersonList;
import com.anas.cashier.Interface.SellProduct;
import com.anas.cashier.Interface.OnClickImageDeleteProduct;
import com.anas.cashier.Interface.SumDiscountProduct;
import com.anas.cashier.Interface.SumFinalTotalPerson;
import com.anas.cashier.Interface.SumTotal;
import com.anas.cashier.R;
import com.anas.cashier.RoomDB.MyViewModel;
import com.anas.cashier.RoomDB.Tables.Bill;
import com.anas.cashier.RoomDB.Tables.PersonPurchases;
import com.anas.cashier.RoomDB.Tables.Products;
import com.anas.cashier.databinding.FragmentCasherBinding;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class CashierFragment extends Fragment implements OnClickImageDeleteProduct, ZXingScannerView.ResultHandler {

    private FragmentCasherBinding binding;
    private ListProductAdapter adapter;
    private MyViewModel viewModel;
    private String email;
    private SharedPreferences sp;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentCasherBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        sp = getActivity().getSharedPreferences("EmailSharedPreferences", Context.MODE_PRIVATE);
        email = sp.getString("Email", null);
        View root = binding.getRoot();


        adapter = new ListProductAdapter(new ArrayList<>(), this);


        viewModel.getPerson().observe(getActivity(), new Observer<List<PersonPurchases>>() {
            @Override
            public void onChanged(List<PersonPurchases> personPurchases) {
                adapter.setList(personPurchases);
            }
        });


        binding.rv.setAdapter(adapter);
        binding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rv.setHasFixedSize(true);

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AddProductActivity.class));

            }
        });

        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                viewModel.getFilterPerson(s).observe(getActivity(), new Observer<List<PersonPurchases>>() {
                    @Override
                    public void onChanged(List<PersonPurchases> personPurchases) {
                        adapter.setList(personPurchases);
                    }
                });
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                viewModel.getFilterPerson(s).observe(getActivity(), new Observer<List<PersonPurchases>>() {
                    @Override
                    public void onChanged(List<PersonPurchases> personPurchases) {
                        adapter.setList(personPurchases);
                    }
                });
                return true;
            }
        });

        viewModel.SumTotal().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                if (aDouble!=null){
                    binding.tvPrice.setText(aDouble + "");
                }
            }
        });

//        getActivity().runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                viewModel.SumTotalDiscount(new SumDiscountProduct() {
//                    @Override
//                    public void DiscountProduct(double Number) {
//                        binding.tvDiscount.setText("0.04");
//                    }
//                }, email);
//            }
//        });

        binding.tvDiscount.setText("0.04");

        viewModel.SumFinalTotalPerson().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                if (aDouble!=null){
                    binding.tvFinalTotal.setText(aDouble + "");
                }
            }
        });

        binding.print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AllProductsActivity.class));
            }
        });
        Dexter.withActivity(getActivity()).withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        binding.codeScanner.setResultHandler(CashierFragment.this);
                        binding.codeScanner.startCamera();
                        binding.codeScanner.setAutoFocus(true);
                        binding.codeScanner.setSoundEffectsEnabled(true);
                        binding.codeScanner.setContentDescription("Barcode Scanner..");
                        binding.codeScanner.setClickable(true);

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(getActivity(), R.string.you_must_accept_this_permission, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                })
                .check();

        binding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.DeletePerson();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @Override
    public void handleResult(Result rawResult) {

        ToneGenerator tg = new ToneGenerator(AudioManager.STREAM_NOTIFICATION, 100);
        tg.startTone(ToneGenerator.TONE_PROP_BEEP);
        if (rawResult != null) {
            viewModel.getListProduct(new ListProduct() {
                @Override
                public void getListProduct(List<Products> products) {
                    if (products.size() != 0) {

                        viewModel.getList(email, Long.parseLong(rawResult.getText()), new ListProductAdd() {
                            @Override
                            public void ListProduct(boolean boo) {
                                if (boo) {
                                    viewModel.getItemBarCode(Long.parseLong(rawResult.getText()), new SellProduct() {
                                        @Override
                                        public void getProduct(Products products) {

                                            if (products.getQuantity() > 0) {

                                                Date date = new Date();
                                                Bill bill = new Bill(products.getName(), date, 2, products.getPriceBuy(), products.getPriceSelling(), 1, email);
                                                viewModel.InsertBill(bill);

                                                viewModel.getListBill(Long.parseLong(rawResult.getText()), new ListBill() {
                                                    @Override
                                                    public void ItemBill(boolean item) {
                                                        if (item) {
                                                            viewModel.getPersonBar(Long.parseLong(rawResult.getText()), new PersonList() {
                                                                @Override
                                                                public void listPerson(PersonPurchases personPurchases) {
                                                                    PersonPurchases purchases = new PersonPurchases();
                                                                    purchases.setBarcode(Long.parseLong(rawResult.getText()));
                                                                    purchases.setName(personPurchases.getName());
                                                                    purchases.setCategory(personPurchases.getCategory());
                                                                    purchases.setPriceSelling(personPurchases.getPriceSelling() + products.getPriceSelling());
                                                                    purchases.setQuantity(personPurchases.getQuantity() + 1);
                                                                    viewModel.UpdatePerson(purchases);
                                                                    Log.d("tt", "ds");
                                                                }
                                                            });

                                                        } else if (!item) {
                                                            PersonPurchases purchases = new PersonPurchases(products.getBarCode(), products.getName(), products.getType(), products.getPriceSelling(), 1);
                                                            viewModel.InsertPurchases(purchases);
                                                        }
                                                    }
                                                });
                                                Products productUpdate = new Products();
                                                productUpdate.setId(products.getId());
                                                productUpdate.setName(products.getName());
                                                productUpdate.setQuantity(products.getQuantity() - 1);
                                                productUpdate.setDatePurchase(date);
                                                productUpdate.setType(products.getType());
                                                productUpdate.setPriceBuy(products.getPriceBuy());
                                                productUpdate.setDescription(products.getDescription());
                                                productUpdate.setBarCode(products.getBarCode());
                                                productUpdate.setPriceSelling(products.getPriceSelling());
                                                productUpdate.setEmail(products.getEmail());
                                                viewModel.UpdateProducts(productUpdate);

                                                requireActivity().runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        Toast.makeText(getContext(), R.string.Product_sold_out, Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                            } else {
                                                requireActivity().runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        Toast.makeText(getContext(), R.string.Product_Quantity_Finished, Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                            }
                                        }
                                    }, email);
                                } else if (!boo) {
                                    requireActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(getContext(), R.string.This_item_is_not, Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }
                        });
                    } else {
                        requireActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getContext(), R.string.No_items_available, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                }
            }, email);
        }
        binding.codeScanner.resumeCameraPreview(this);

    }

    @Override
    public void OnClickImage(PersonPurchases products) {
        viewModel.DeleteOnePerson(products.getBarcode());
    }
}