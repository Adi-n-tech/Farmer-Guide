package com.adintech.farmersguide.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.adintech.farmersguide.Util.api_response_handler.APIResponse;
import com.adintech.farmersguide.Util.interfaces.APIResponseListener;

public class WeatherViewModel extends ViewModel implements APIResponseListener {

    public MutableLiveData<APIResponse> apiResponseMutableLiveData;
    private WeatherRepository weatherRepository;

    public void init() {
        apiResponseMutableLiveData = new MutableLiveData<>();
        weatherRepository = new WeatherRepository();
    }

    public void doGetWeatherData(String lat, String lng, Integer requestID) {
        apiResponseMutableLiveData.setValue(APIResponse.loading(requestID));
        weatherRepository.doGetWeatherData(lat, lng, this, requestID);
    }

    @Override
    public void onSuccess(Object callResponse, Integer requestID) {
        apiResponseMutableLiveData.setValue(APIResponse.success(callResponse, requestID));
    }

    @Override
    public void onFailure(Throwable error, Integer requestID) {
        apiResponseMutableLiveData.setValue(APIResponse.error(error, requestID));
    }
}