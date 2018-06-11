package com.example.admin.interestimage.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.EventLog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.admin.interestimage.R;
import com.example.admin.interestimage.adapter.InterestImageAdapter;
import com.example.admin.interestimage.bean.InterestImage;
import com.example.admin.interestimage.net.HttpResult;
import com.example.admin.interestimage.net.HttpUtil;
import com.example.admin.interestimage.net.RetrofitUtil;
import com.example.admin.interestimage.ui.activity.BigImage;

import org.greenrobot.eventbus.EventBus;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class InterestingFragment extends Fragment {
    private View mView;
    private RecyclerView mRecyclerView;
    private List<InterestImage> mList;
    private InterestImageAdapter mAdapter;

    public InterestingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView= inflater.inflate(R.layout.fragment_interesting, container, false);
        initView();
        init();
        return mView;
    }

    private void init() {
        mList=new ArrayList<>();
//        for(int i=0;i<10;i++){
//            mList.add(new InterestImage());
//        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter=new InterestImageAdapter(R.layout.interest_item,mList);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                InterestImage interestImage=(InterestImage) adapter.getItem(position);
                EventBus.getDefault().postSticky(interestImage);
                Intent intent=new Intent(getActivity(),BigImage.class);
                startActivity(intent);
            }
        });
//        RetrofitUtil.getImage(new Callback<HttpResult<List<InterestImage>>>() {
//            @Override
//            public void onResponse(Call<HttpResult<List<InterestImage>>> call, Response<HttpResult<List<InterestImage>>> response) {
//                mAdapter.addData(response.body().getData());
//            }
//
//            @Override
//            public void onFailure(Call<HttpResult<List<InterestImage>>> call, Throwable t) {
//
//            }
//        });
        HttpUtil.getInstance().getInterestImage(new Subscriber<List<InterestImage>>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(List<InterestImage> interestImages) {
                Log.i("getdata", "onNext: "+interestImages.toString());
                mAdapter.addData(interestImages);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void initView() {
        mRecyclerView=mView.findViewById(R.id.interesting_recycler_view);

    }

}
