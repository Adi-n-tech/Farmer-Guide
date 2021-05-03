package com.adintech.farmersguide.views.activities;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.adintech.farmersguide.R;
import com.adintech.farmersguide.Util.api_response_handler.APIResponse;
import com.adintech.farmersguide.Util.constant.AppConstants;
import com.adintech.farmersguide.Util.interfaces.APIResponseHandler;
import com.adintech.farmersguide.databinding.ActivityWeatherBinding;
import com.adintech.farmersguide.model.Weather;
import com.adintech.farmersguide.model.WeatherInfo;
import com.adintech.farmersguide.viewModel.WeatherViewModel;

import java.util.ArrayList;
import java.util.Date;

public class WeatherActivity extends AppCompatActivity implements APIResponseHandler {

    //variables
    private ActivityWeatherBinding mBinding;
    private WeatherViewModel mViewModel;
    private LocationManager locationManager;
    private String latitude, longitude;
    private ArrayList<WeatherInfo> weatherArrayList = new ArrayList<>();

    //constants
    private static final int REQUEST_LOCATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_weather);
        getSupportActionBar().setTitle("Weather");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //---------
        mViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
        mViewModel.init();

        mViewModel.apiResponseMutableLiveData.observe(this, new Observer<APIResponse>() {
            @Override
            public void onChanged(APIResponse apiResponse) {
                onAPIResponseHandler(apiResponse);
            }
        });

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);


        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            getLocation();
        }

        Initialize();
    }

    private void Initialize() {
        String currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());
        mBinding.time.setText(currentDateTimeString);

        mViewModel.doGetWeatherData("21.1461", "79.4043", AppConstants.API_REQUEST.REQUEST_ID_1001);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onAPIResponseHandler(APIResponse apiResponse) {
        switch (apiResponse.status) {
            case LOADING:
                mBinding.progressbar.setVisibility(View.VISIBLE);
                break;
            case SUCCESS:
                mBinding.progressbar.setVisibility(View.GONE);
                switch (apiResponse.requestID) {
                    case AppConstants.API_REQUEST.REQUEST_ID_1001:
                        Weather weather = (Weather) apiResponse.data;
                        weatherArrayList = weather.getWeatherData();

                        for (WeatherInfo weatherInfo : weatherArrayList) {
                            mBinding.degree.setText(weatherInfo.getTemp() + "°C");
                        }
                }
                break;
            case ERROR:
                mBinding.progressbar.setVisibility(View.GONE);
                break;
            default:
                break;
        }

    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (locationGPS != null) {
                double lat = locationGPS.getLatitude();
                double longi = locationGPS.getLongitude();
                latitude = String.valueOf(lat);
                longitude = String.valueOf(longi);
            } else {
                Toast.makeText(this, "Unable to find location.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}