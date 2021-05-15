package com.adintech.farmersguide.views.activities;

import android.os.Bundle;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adintech.farmersguide.Models.Farmingmethod;
import com.adintech.farmersguide.Models.ModernFarmingresponce;
import com.adintech.farmersguide.R;
import com.adintech.farmersguide.Util.Utility;
import com.adintech.farmersguide.Util.constant.AppConstants;
import com.adintech.farmersguide.databinding.ActivityModernfarmingMethodBinding;
import com.adintech.farmersguide.views.adapters.FarmingMethodAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ModernFarmingMethodActivity extends AppCompatActivity {

    // variables
    private ActivityModernfarmingMethodBinding mModernFarmingMethodBinding;
    private ArrayList<Farmingmethod> mArrayList;
    private FarmingMethodAdapter mFarmingMethodAdapter;

    // widgets
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(R.string.modern_farming_method);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mModernFarmingMethodBinding = DataBindingUtil.setContentView(this, R.layout.activity_modernfarming_method);
        search();
        Initialize();
    }

    private void Initialize() {
        String jsonString;
        mRecyclerView = mModernFarmingMethodBinding.RecycleView;
        if (Utility.getCurrentLocale(this) == AppConstants.ENGLISH_LANG_CODE) {
             jsonString = Utility.loadJSONFromAsset(this, "doGetModernMethod");
        }else if (Utility.getCurrentLocale(this)==AppConstants.HINDI_LANG_CODE){
            jsonString = Utility.loadJSONFromAsset(this, "doGetModernMethodHindi");
        }else {
            jsonString = Utility.loadJSONFromAsset(this, "doGetModernMethodMar");
        }
        ModernFarmingresponce response = new Gson().fromJson(jsonString, ModernFarmingresponce.class);
        mArrayList = response.getFarmingmethod();
        doConfigureSearchBarView();
        setUpModernFarmingAdapter();
    }


    private void search() {
        mModernFarmingMethodBinding.searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

        ArrayList<Farmingmethod> filterList = new ArrayList<>();

        for (Farmingmethod farmingmethod : mArrayList) {
            if (farmingmethod.getTitle().toLowerCase().contains(newText.toLowerCase())) {
                filterList.add(farmingmethod);
            }
            mFarmingMethodAdapter.filterdlist(filterList);
            mFarmingMethodAdapter.notifyDataSetChanged();
        }
    }

    private void doConfigureSearchBarView() {
        mModernFarmingMethodBinding.searchview.setIconifiedByDefault(false);
        mModernFarmingMethodBinding.searchview.setFocusable(false);
        mModernFarmingMethodBinding.searchview.setQueryHint(getString(R.string.searchfarmingmethod));
    }

    private void setUpModernFarmingAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mFarmingMethodAdapter = new FarmingMethodAdapter(this, mArrayList);
        mRecyclerView.setAdapter(mFarmingMethodAdapter);
    }
}