package com.adintech.farmersguide.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.adintech.farmersguide.Models.ModernInstrument;
import com.adintech.farmersguide.Models.ModernInstrumentresponce;
import com.adintech.farmersguide.R;
import com.adintech.farmersguide.Util.Utility;
import com.adintech.farmersguide.databinding.ActivityModernInstrumentsBinding;
import com.adintech.farmersguide.model.YoutubeResponse;
import com.adintech.farmersguide.views.adapter.YoutubeVideoAdapter;
import com.adintech.farmersguide.views.adapters.ModernIntrumetnAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ModernInstrumentsActivity extends AppCompatActivity {
 private ActivityModernInstrumentsBinding mActivityModernInstrumentsBinding;
 private RecyclerView mRecyclerView;
 private ArrayList<ModernInstrument> mArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityModernInstrumentsBinding = DataBindingUtil.setContentView(this,R.layout.activity_modern_instruments);

        Initialize();
    }

    private void Initialize() {
        mRecyclerView = mActivityModernInstrumentsBinding.RecycleView;


        String jsonString = Utility.loadJSONFromAsset(this, "doGetmodernInstruments");
        ModernInstrumentresponce response = new Gson().fromJson(jsonString, ModernInstrumentresponce.class);

        mArrayList = response.getModernInstruments();

        setYoutubeDataAdapter();
    }

    private void setYoutubeDataAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        ModernIntrumetnAdapter intrumetnAdapter = new ModernIntrumetnAdapter(this,mArrayList);
        mRecyclerView.setAdapter(intrumetnAdapter);
    }
}