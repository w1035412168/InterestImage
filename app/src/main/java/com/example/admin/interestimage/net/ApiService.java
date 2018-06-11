package com.example.admin.interestimage.net;

import com.example.admin.interestimage.bean.InterestImage;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("getinterest.php")
    Flowable<HttpResult<List<InterestImage>>> getInterestImage();
    @GET("getinterest.php")
    Call<HttpResult<List<InterestImage>>> getInterest();

}
