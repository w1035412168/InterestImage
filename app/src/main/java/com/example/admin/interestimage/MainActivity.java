package com.example.admin.interestimage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.admin.interestimage.ui.fragment.InterestingFragment;
import com.example.admin.interestimage.ui.fragment.MoeFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, BottomNavigationBar.OnTabSelectedListener {
    private ViewPager mViewPager;
    private List<Fragment> mList;
    private BottomNavigationBar mBottomNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initView();

        init();
    }

    private void init() {
        mList=new ArrayList<>();
        mList.add(new InterestingFragment());
        mList.add(new MoeFragment());


        final ViewPagerAdapter adapter=new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(this);


        mBottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.nav_interesting_selector,"趣图"))
                .addItem(new BottomNavigationItem(R.drawable.nav_cute_selector,"萌图"))
                .setMode(BottomNavigationBar.MODE_FIXED)
                .initialise();
        mBottomNavigationBar.setTabSelectedListener(this);
    }

    private void initView() {
        mViewPager = findViewById(R.id.main_view_pager);
        mBottomNavigationBar=findViewById(R.id.main_bottom_navigation_bar);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
            mBottomNavigationBar.selectTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTabSelected(int position) {
        mViewPager.setCurrentItem(position);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mList.get(position);
        }

        @Override
        public int getCount() {
            return mList.size();
        }
    }
}
