package com.example.admin.interestimage.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.interestimage.R;
import com.example.admin.interestimage.bean.InterestImage;
import com.example.admin.interestimage.loader.GlideImageLoader;
import com.example.admin.interestimage.net.HttpUtil;
import com.youth.banner.Banner;

import org.greenrobot.eventbus.Subscribe;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoeFragment extends Fragment {
    @BindView(R.id.banner) Banner mBanner;
    private View mView;
    private List<String> mImageList;
    public MoeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView=inflater.inflate(R.layout.fragment_moe, container, false);
        ButterKnife.bind(this,mView);
        init();
        return mView;
    }

    private void init() {
        mImageList=new ArrayList<>();


        HttpUtil.getInstance().getInterestImage(new Subscriber<List<InterestImage>>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(List<InterestImage> interestImages) {
                for (InterestImage i:interestImages)
                    mImageList.add(i.getImageUrl());
                mBanner.setImages(mImageList)
                        .setImageLoader(new GlideImageLoader())
                        .start();
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

}
