package com.adintech.farmersguide.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;

import com.adintech.farmersguide.Models.Farmingmethod;
import com.adintech.farmersguide.Models.ModernFarmingresponce;
import com.adintech.farmersguide.R;
import com.adintech.farmersguide.Util.Utility;
import com.adintech.farmersguide.databinding.ActivityModernfarmingMethodBinding;
import com.adintech.farmersguide.views.adapters.FarmingMethodAdapter;
import com.adintech.farmersguide.views.adapters.ModernIntrumetnAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ModernfarmingMethod extends AppCompatActivity {
    private ActivityModernfarmingMethodBinding mModernfarmingMethodBinding;
    private ArrayList<Farmingmethod> mArrayList;
    private FarmingMethodAdapter farmingMethodAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(R.string.modern_farming_method);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mModernfarmingMethodBinding= DataBindingUtil.setContentView(this,R.layout.activity_modernfarming_method);
        search();
        Initialize();
    }

    private void search() {
        mModernfarmingMethodBinding.searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });
    }

    private void filter(String newText) {

        ArrayList<Farmingmethod> mArrayList = new ArrayList<>();

        for (Farmingmethod farmingmethod : mArrayList){
            if (farmingmethod.getTitle().toLowerCase().contains(newText.toLowerCase())){
                mArrayList.add(farmingmethod);
            }
            farmingMethodAdapter.filterdlist(mArrayList);
        }
    }
    private void doConfigureSearchBarView() {
        mModernfarmingMethodBinding.searchview.setIconifiedByDefault(false);
        mModernfarmingMethodBinding.searchview.setFocusable(false);
        mModernfarmingMethodBinding.searchview.setQueryHint("Search Farming Method here ..");
    }

    private void Initialize() {
        mRecyclerView = mModernfarmingMethodBinding.RecycleView;


        String jsonString = Utility.loadJSONFromAsset(this, "doGetModernMethod");
        ModernFarmingresponce response = new Gson().fromJson(jsonString, ModernFarmingresponce.class);
        mArrayList = response.getFarmingmethod();
doConfigureSearchBarView();
    setUpModernFarmingAdapter();
    }
    private void setUpModernFarmingAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        farmingMethodAdapter = new FarmingMethodAdapter(this, mArrayList);
        mRecyclerView.setAdapter(farmingMethodAdapter);
    }
}