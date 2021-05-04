package com.adintech.farmersguide.viewModel;

import com.adintech.farmersguide.Util.interfaces.APIResponseListener;
import com.adintech.farmersguide.Util.network.RetroInterface;
import com.adintech.farmersguide.Models.Weather;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherRepository {


    public void doGetWeatherData(String lat, String lng, final APIResponseListener apiResponseListener, final Integer requestID) {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .addHeader("Accept", "application/json")
                        .addHeader("Content-Type", "application/json")
                        .addHeader("x-rapidapi-key", "763c92d417msh51055e00d8f6c37p17fcfajsn607f8998e23e")
                        .addHeader("x-rapidapi-host", "weatherbit-v1-mashape.p.rapidapi.com")
                        .addHeader("useQueryString", "true").build();
                return chain.proceed(request);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://weatherbit-v1-mashape.p.rapidapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        RetroInterface retroInterface = retrofit.create(RetroInterface.class);
        Call<Weather> weatherCall = retroInterface.weatherList(lat, lng);

        weatherCall.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {

                if (response.isSuccessful()) {
                    Weather weather = response.body();
                    apiResponseListener.onSuccess(weather, requestID);

                } else {
                    apiResponseListener.onFailure(new Exception(), requestID);
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                apiResponseListener.onFailure(new Exception(), requestID);
            }
        });

    }
}
