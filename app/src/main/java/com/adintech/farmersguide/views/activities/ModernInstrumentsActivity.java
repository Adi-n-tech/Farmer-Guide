package com.adintech.farmersguide.views.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adintech.farmersguide.Models.ModernInstrument;
import com.adintech.farmersguide.Models.ModernInstrumentresponce;
import com.adintech.farmersguide.R;
import com.adintech.farmersguide.Util.Utility;
import com.adintech.farmersguide.databinding.ActivityModernInstrumentsBinding;
import com.adintech.farmersguide.views.adapters.ModernIntrumetnAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ModernInstrumentsActivity extends AppCompatActivity {

    // variables
    private ActivityModernInstrumentsBinding mActivityModernInstrumentsBinding;
    private ArrayList<ModernInstrument> mArrayList;

    // widgets
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityModernInstrumentsBinding = DataBindingUtil.setContentView(this, R.layout.activity_modern_instruments);

        Initialize();
    }

    private void Initialize() {
        mRecyclerView = mActivityModernInstrumentsBinding.RecycleView;


        String jsonString = Utility.loadJSONFromAsset(this, "doGetmodernInstruments");
        ModernInstrumentresponce response = new Gson().fromJson(jsonString, ModernInstrumentresponce.class);

        mArrayList = response.getModernInstruments();

        setUpModernInstrumentAdapter();
    }

    private void setUpModernInstrumentAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        ModernIntrumetnAdapter modernIntrumetnAdapter = new ModernIntrumetnAdapter(this, mArrayList);
        mRecyclerView.setAdapter(modernIntrumetnAdapter);
    }
}