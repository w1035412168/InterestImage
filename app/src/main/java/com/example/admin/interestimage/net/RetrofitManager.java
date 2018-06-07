package com.example.admin.interestimage.net;

import com.example.admin.interestimage.bean.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private static volatile RetrofitManager mInstance=null;
    private Retrofit mRetrofit;
    private RetrofitManager(){
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                .build();

        mRetrofit=new Retrofit.Builder()
                .baseUrl(Constants.HOST_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static RetrofitManager getInstance(){
        if (mInstance==null){
            if (mInstance==null)
                synchronized (RetrofitManager.class) {
                    mInstance = new RetrofitManager();
                }
        }
        return mInstance;
    }

    public <T> T getService(Class<T> tClass){
        return mRetrofit.create(tClass);
    }
}
