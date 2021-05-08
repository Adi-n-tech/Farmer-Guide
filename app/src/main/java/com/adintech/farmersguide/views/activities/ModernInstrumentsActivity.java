package com.adintech.farmersguide.views.activities;

import android.os.Bundle;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adintech.farmersguide.Models.ModernInstrument;
import com.adintech.farmersguide.Models.ModernFarmingresponce;
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
    private   ModernIntrumetnAdapter modernIntrumetnAdapter;

    // widgets
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(R.string.weather);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mActivityModernInstrumentsBinding = DataBindingUtil.setContentView(this, R.layout.activity_modern_instruments);

        Initialize();
    }

    private void Initialize() {
        mRecyclerView = mActivityModernInstrumentsBinding.RecycleView;


        String jsonString = Utility.loadJSONFromAsset(this, "doGetmodernInstruments");
        ModernInstrumentresponce response = new Gson().fromJson(jsonString,ModernInstrumentresponce.class);

        mArrayList = response.getModernInstruments();
        Search();
        setUpModernInstrumentAdapter();
        doConfigureSearchBarView();
    }

    private void Search() {
        mActivityModernInstrumentsBinding.searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
    private void doConfigureSearchBarView() {
        mActivityModernInstrumentsBinding.searchview.setIconifiedByDefault(false);
        mActivityModernInstrumentsBinding.searchview.setFocusable(false);
        mActivityModernInstrumentsBinding.searchview.setQueryHint("Search Instrument here ..");
    }

    private void filter(String newText) {
        ArrayList<ModernInstrument> filterlisrt = new ArrayList<>();
        for (ModernInstrument intrumetnAdapter : mArrayList){
            if (intrumetnAdapter.getName().toLowerCase().contains(newText.toLowerCase())){
                filterlisrt.add(intrumetnAdapter);
            }
            modernIntrumetnAdapter.filterdlist(filterlisrt);
        }
    }

    private void setUpModernInstrumentAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        modernIntrumetnAdapter = new ModernIntrumetnAdapter(this, mArrayList);
        mRecyclerView.setAdapter(modernIntrumetnAdapter);
    }
}