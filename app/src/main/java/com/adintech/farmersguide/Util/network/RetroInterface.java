
package com.adintech.farmersguide.Util.network;


import com.adintech.farmersguide.Models.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetroInterface {

    @GET("/current/")
    Call<Weather> weatherList(@Query("lat") String lat, @Query("lon") String lng);
}

