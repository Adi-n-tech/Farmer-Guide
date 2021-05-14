package com.adintech.farmersguide.views.activities;

import android.os.Bundle;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adintech.farmersguide.Models.Crop;
import com.adintech.farmersguide.Models.CropListResponse;
import com.adintech.farmersguide.R;
import com.adintech.farmersguide.Util.Utility;
import com.adintech.farmersguide.Util.constant.AppConstants;
import com.adintech.farmersguide.databinding.ActivityCropListBinding;
import com.adintech.farmersguide.views.adapter.CropsAdapters;
import com.google.gson.Gson;

import java.util.ArrayList;

public class CropListActivity extends AppCompatActivity {
    private ActivityCropListBinding mAtivityCropListBinding;
    private ArrayList<Crop> mCropList = new ArrayList<>();
    private CropsAdapters mCropsAdapters;
    private LinearLayoutManager mLinearLayoutManager;
    private String jsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAtivityCropListBinding = DataBindingUtil.setContentView(this, R.layout.activity_crop_list);
        getSupportActionBar().setTitle(R.string.crop);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Initialize();
        Search();
    }

    private void Search() {
        mAtivityCropListBinding.searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

    private void filter(String text) {
        ArrayList<Crop> filterList= new ArrayList<>();
        for (Crop crop : mCropList) {
            if (crop.getTitle().toLowerCase().contains(text.toLowerCase())) {
                filterList.add(crop);
            }
        }
        mCropsAdapters.filterlist(filterList);
    }

    private void doConfigureSearchBarView() {
        mAtivityCropListBinding.searchview.setIconifiedByDefault(false);
        mAtivityCropListBinding.searchview.setFocusable(false);
        mAtivityCropListBinding.searchview.setQueryHint(getString(R.string.searchcrops));
    }


    private void Initialize() {
        if (Utility.getCurrentLocale(this)== AppConstants.ENGLISH_LANG_CODE){
         jsonString = Utility.loadJSONFromAsset(this, "cropsList");
        }else if (Utility.getCurrentLocale(this)== AppConstants.HINDI_LANG_CODE) {
            jsonString = Utility.loadJSONFromAsset(this, "cropsListHindi");
        }
        CropListResponse cropListResponse = new Gson().fromJson(jsonString, CropListResponse.class);

        mCropList = cropListResponse.getCrops();

        doConfigureSearchBarView();

        mLinearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mAtivityCropListBinding.RecycleView.setLayoutManager(mLinearLayoutManager);
        mCropsAdapters = new CropsAdapters(mCropList, this);
        mAtivityCropListBinding.RecycleView.setAdapter(mCropsAdapters);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}