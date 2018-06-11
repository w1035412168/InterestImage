package com.example.admin.interestimage.net;

import com.example.admin.interestimage.bean.InterestImage;
import com.example.admin.interestimage.net.function.HttpResultFunction;

import org.reactivestreams.Subscriber;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HttpUtil {
    private static ApiService mApiService;
    private static volatile HttpUtil mInstance=null;
    private HttpUtil(){
        mApiService=RetrofitManager.getInstance().getService(ApiService.class);
    }
    public static HttpUtil getInstance(){
        if (mInstance==null){
            if (mInstance==null)
                synchronized (HttpUtil.class){
                mInstance=new HttpUtil();
            }
     }
    return mInstance;
    }
    public void getInterestImage(Subscriber<List<InterestImage>> subscriber){
        Flowable<HttpResult<List<InterestImage>>> flowable=mApiService.getInterestImage();
        flowable.map(new HttpResultFunction<List<InterestImage>>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
