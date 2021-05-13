package com.adintech.farmersguide.views.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.adintech.farmersguide.Models.SharesPreferences;
import com.adintech.farmersguide.R;
import com.adintech.farmersguide.databinding.ActivityDashboardBinding;

import java.util.HashMap;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    //variables
    public ActivityDashboardBinding mActivityDashboardBinding;
    private DBHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityDashboardBinding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);
        mDbHelper = new DBHelper(this);

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


        SharesPreferences sharesPreferences = new SharesPreferences(this);
        HashMap<String,String>  userDetail = sharesPreferences.getUserDetailFromSession();
        String fullname = userDetail.get(sharesPreferences.NAME);
        String phone = userDetail.get(sharesPreferences.PHONE);
        String Address = userDetail.get(sharesPreferences.ADDRESS);
        String Passward = userDetail.get(sharesPreferences.PASSWARD);
        String RePassward = userDetail.get(sharesPreferences.RE_PASSWARD);
        mActivityDashboardBinding.username.setText(fullname);


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
                 intent = new Intent(DashboardActivity.this, ModernFarmingMethodActivity.class);
                startActivity(intent);
                break;
        }
    }
}