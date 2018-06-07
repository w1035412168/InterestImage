package com.example.admin.interestimage.net;

import com.example.admin.interestimage.bean.Constants;
import com.example.admin.interestimage.bean.InterestImage;

import org.reactivestreams.Subscriber;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {
    public static void getImage(Callback<HttpResult<List<InterestImage>>> callback){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Constants.HOST_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService=retrofit.create(ApiService.class);
        Call<HttpResult<List<InterestImage>>> call = apiService.getInterest();
        call.enqueue(callback);
    }
    public static void getImage(Subscriber<HttpResult<List<InterestImage>>> subscriber){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Constants.HOST_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService=retrofit.create(ApiService.class);
        Flowable<HttpResult<List<InterestImage>>> flowable=apiService.getInterestImage();
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
