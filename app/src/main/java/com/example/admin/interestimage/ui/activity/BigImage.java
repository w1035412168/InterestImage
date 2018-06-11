package com.example.admin.interestimage.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.admin.interestimage.R;
import com.example.admin.interestimage.bean.InterestImage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class BigImage extends AppCompatActivity {
    private ImageView bigImageView;
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onInterestImageReceiver(InterestImage interestImage){
        Glide.with(this).load(interestImage.getImageUrl()).into(bigImageView);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_image);
        bigImageView=findViewById(R.id.big_image);


    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
