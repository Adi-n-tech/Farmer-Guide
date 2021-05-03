package com.adintech.farmersguide.Util.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This is a singleton class where we Create retrofit client and configure it to call web apis using retrofit library.
 */
public class RetrofitClient {

    private static RetrofitClient mInstance;
    private Retrofit retrofit;
    public static final String BASE_URL = "http://jayaastropalm.in/json/";

    /**
     * Create retrofit instance.
     */
    private RetrofitClient() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .addHeader("Accept", "application/json")
                        .addHeader("Content-Type", "application/json")
                        .addHeader("x-rapidapi-key", "763c92d417msh51055e00d8f6c37p17fcfajsn607f8998e23e")
                        .addHeader("x-rapidapi-host", "weatherbit-v1-mashape.p.rapidapi.com")
                        .addHeader("useQueryString", "true").build();
                return chain.proceed(request);
            }
        });

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
    }

    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
