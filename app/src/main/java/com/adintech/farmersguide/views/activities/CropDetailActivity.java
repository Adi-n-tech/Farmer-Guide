package com.adintech.farmersguide.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.adintech.farmersguide.Models.Crop;
import com.adintech.farmersguide.Models.Disease;
import com.adintech.farmersguide.R;
import com.adintech.farmersguide.Util.constant.AppConstants;
import com.adintech.farmersguide.databinding.ActivityCropDetailBinding;
import com.adintech.farmersguide.views.adapter.CropDiseaseAdapter;
import com.adintech.farmersguide.views.adapter.ImageViewPagerRecyclerAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator3;

public class CropDetailActivity extends AppCompatActivity {

    //variables
    private ActivityCropDetailBinding mBinding;
    private ArrayList<String> mFavPlacesImageList = new ArrayList<>();
    private ImageViewPagerRecyclerAdapter mImageViewPagerRecyclerAdapter;
    private ArrayList<Disease> mCropDiseaseList;
    private Crop crop;

    //widgets
    private ViewPager2 mViewPager;
    private CircleIndicator3 mIndicator;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_crop_detail);

        Initialize();

    }

    private void Initialize() {
        mCollapsingToolbarLayout = mBinding.collapsingToolbar;
        mAppBarLayout = mBinding.appbar;
        mToolbar = mBinding.animToolbar;
        mViewPager = mBinding.pager;
        mIndicator = mBinding.indicator;

        //get crop list
        Intent intent = getIntent();
        crop = intent.getParcelableExtra(AppConstants.INTENT_KEYS.CROP);
        mCropDiseaseList = crop.getDiseaseList();

        //set title
        mBinding.titleBar.setText(crop.getTitle());

        //set data
        mBinding.title.setText(crop.getTitle());
        mBinding.description.setText(crop.getDescription());
        mBinding.landDescription.setText(crop.getLandPrepration());
        mBinding.soilDescription.setText(crop.getSoil());
        mBinding.timeOfSowing.setText(crop.getTimeOfSowing());


        mFavPlacesImageList = crop.getRelatedImages();
        mFavPlacesImageList.add(crop.getImageUrl());

        mBinding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        setDataOnView();
        setCropDiseaseAdapter();
    }

    private void setCropDiseaseAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        mBinding.cropDiseaseRecycleView.setLayoutManager(linearLayoutManager);
        CropDiseaseAdapter cropDiseaseAdapter = new CropDiseaseAdapter(this, mCropDiseaseList);
        mBinding.cropDiseaseRecycleView.setAdapter(cropDiseaseAdapter);
    }

    private void setDataOnView() {
        //show toolbar title on collapse mode and hide toolbar title in expand mode
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    //set action bar title
                    mCollapsingToolbarLayout.setTitle("Crops");
                    isShow = true;
                } else if (isShow) {
                    mCollapsingToolbarLayout.setTitle("  ");
                    //careful there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });

        //set adapter on view pager to show photo of product.
        if (mFavPlacesImageList != null) {
            mImageViewPagerRecyclerAdapter = new ImageViewPagerRecyclerAdapter(this, mFavPlacesImageList);
            mViewPager.setAdapter(mImageViewPagerRecyclerAdapter);
            mIndicator.setViewPager(mViewPager);
        }
    }
}