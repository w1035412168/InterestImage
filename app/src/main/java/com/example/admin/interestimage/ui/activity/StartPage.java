package com.example.admin.interestimage.ui.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Handler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

import com.example.admin.interestimage.MainActivity;
import com.example.admin.interestimage.R;

public class StartPage extends AppCompatActivity {
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start_page);

        mImageView=findViewById(R.id.start_image);
        mImageView.setImageResource(R.drawable.startpage);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(StartPage.this, MainActivity.class));
                StartPage.this.finish();
            }
        },3000);
    }
}
