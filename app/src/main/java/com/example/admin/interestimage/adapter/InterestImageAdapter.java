package com.example.admin.interestimage.adapter;

import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.admin.interestimage.R;
import com.example.admin.interestimage.bean.InterestImage;

import java.util.List;

public class InterestImageAdapter extends BaseQuickAdapter<InterestImage,BaseViewHolder> {

    public InterestImageAdapter(int layoutResId, @Nullable List<InterestImage> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, InterestImage item) {
//      helper.setImageResource(R.id.interest_image,R.drawable.startpage);
        Glide.with(mContext).load(item.getImageUrl()).into((ImageView)helper.getView(R.id.interest_image));

    }
}
