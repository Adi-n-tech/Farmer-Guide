package com.adintech.farmersguide.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.adintech.farmersguide.R;
import com.adintech.farmersguide.databinding.ActivityDashboardBinding;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    //variables
    private ActivityDashboardBinding mActivityDashboardBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityDashboardBinding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);

        Initialize();
    }

    private void Initialize() {

        //on click
        mActivityDashboardBinding.cardVideos.setOnClickListener(this::onClick);
        mActivityDashboardBinding.cropsCard.setOnClickListener(this::onClick);
        mActivityDashboardBinding.cardModernInstruments.setOnClickListener(this::onClick);
        mActivityDashboardBinding.cardExpertAdvice.setOnClickListener(this::onClick);
        mActivityDashboardBinding.cardGovScheme.setOnClickListener(this::onClick);
        mActivityDashboardBinding.cardFarmingMethod.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.card_modern_instruments:
                 intent = new Intent(DashboardActivity.this, ModernInstrumentsActivity.class);
                startActivity(intent);
                break;
            case R.id.card_videos:
                 intent = new Intent(DashboardActivity.this, YoutubeVideosActivity.class);
                startActivity(intent);
                break;
            case R.id.cropsCard:
                 intent = new Intent(DashboardActivity.this, CropListActivity.class);
                startActivity(intent);
                break;
            case R.id.card_expert_advice:
                 intent = new Intent(DashboardActivity.this, ExpertCallService.class);
                startActivity(intent);
                break;
            case R.id.card_gov_scheme:
                 intent = new Intent(DashboardActivity.this, GovermentSchemeActivity.class);
                startActivity(intent);
                break;
            case R.id.card_farming_method:
                 intent = new Intent(DashboardActivity.this, ModernfarmingMethod.class);
                startActivity(intent);
                break;
        }
    }
}