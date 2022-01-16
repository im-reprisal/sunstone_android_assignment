package com.example.sunstone.api;

import static com.example.sunstone.extras.Constants.BASE_URL;

import com.example.kotlin.api.ApiClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitGenerator {

    private static RetrofitGenerator retrofitClient;
    private static Retrofit retrofit;

    private OkHttpClient.Builder builder = new OkHttpClient.Builder();
    private HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

    public RetrofitGenerator() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();
    }
    public static synchronized RetrofitGenerator getInstance(){
        if(retrofitClient==null){
            retrofitClient=new RetrofitGenerator();
        }
        return retrofitClient;
    }
    public ApiClient getApi(){
        return retrofit.create(ApiClient.class);
    }
}