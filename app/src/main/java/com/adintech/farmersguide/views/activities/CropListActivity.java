package com.adintech.farmersguide.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;

import com.adintech.farmersguide.Models.Crop;
import com.adintech.farmersguide.Models.CropListResponse;
import com.adintech.farmersguide.R;
import com.adintech.farmersguide.Util.Utility;
import com.adintech.farmersguide.databinding.ActivityCropListBinding;
import com.adintech.farmersguide.views.adapter.CropsAdapters;
import com.google.gson.Gson;

import java.util.ArrayList;

public class CropListActivity extends AppCompatActivity {
    private ActivityCropListBinding mAtivityCropListBinding;
    private ArrayList<Crop> mCropList = new ArrayList<>();
    private CropsAdapters mCropsAdapters;
    private GridLayoutManager mGridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAtivityCropListBinding = DataBindingUtil.setContentView(this, R.layout.activity_crop_list);
        getSupportActionBar().setTitle(R.string.crop);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Initialize();
    }

    private void Initialize() {
        String jsonString = Utility.loadJSONFromAsset(this, "cropsList");
        CropListResponse cropListResponse = new Gson().fromJson(jsonString, CropListResponse.class);

        mCropList =cropListResponse.getCrops();

        mGridLayoutManager = new GridLayoutManager(this,2);
        mAtivityCropListBinding.RecycleView.setLayoutManager(mGridLayoutManager);
        mCropsAdapters = new CropsAdapters(mCropList, this);
        mAtivityCropListBinding.RecycleView.setAdapter(mCropsAdapters);
    }
}