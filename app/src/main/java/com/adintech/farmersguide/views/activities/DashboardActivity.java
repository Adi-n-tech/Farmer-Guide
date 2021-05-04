package com.adintech.farmersguide.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.card_modern_instruments:
                Intent card_modern_instruments = new Intent(DashboardActivity.this, ModernInstrumentsActivity.class);
                startActivity(card_modern_instruments);
                break;
            case R.id.card_videos:
                Intent card_videos = new Intent(DashboardActivity.this, YoutubeVideosActivity.class);
                startActivity(card_videos);
                break;
            case R.id.cropsCard:
                Intent cropsCard = new Intent(DashboardActivity.this, CropListActivity.class);
                startActivity(cropsCard);
                break;
        }
    }
}