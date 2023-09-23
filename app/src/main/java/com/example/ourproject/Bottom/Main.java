package com.example.ourproject.Bottom;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.ourproject.HomeActivity;
import com.example.ourproject.Person.PersonActivity;
import com.example.ourproject.R;

import java.util.ArrayList;
import java.util.List;


public class Main extends AppCompatActivity {

    private ViewPager mViewPager;
    private RadioGroup mRadioGroup;
    private RadioButton tab1,tab2,tab3;
    private LocalActivityManager manager;
    private List<View> mViews;   //存放视图

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom);



        manager = new LocalActivityManager(this,true);
        manager.dispatchCreate(savedInstanceState);//必须写上这一行代码，不然会报错

        initView();
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rb_shops) {
                    mViewPager.setCurrentItem(0);
                } else if (i == R.id.rb_shopcar) {
                    mViewPager.setCurrentItem(1);
                } else if (i == R.id.rb_me) {
                    mViewPager.setCurrentItem(2);
                }
            }
        });

    }

    private void initView() {
        //初始化控件
        mViewPager=findViewById(R.id.viewpager);
        mRadioGroup=findViewById(R.id.rg_tab);
        tab1=findViewById(R.id.rb_shops);
        tab2=findViewById(R.id.rb_shopcar);
        tab3=findViewById(R.id.rb_me);


        mViews=new ArrayList<View>();//加载，添加视图
//        manager = new LocalActivityManager(this,true);
//        manager.dispatchCreate(savedInstanceState);//必须写上这一行代码，不然会报错
//
        Intent intentItem = new Intent(Main.this, HomeActivity.class);//这个类的第一个参数是上下文，第二个参数是你需要转化的Activity
        mViews.add(manager.startActivity("shops",intentItem).getDecorView());//将Activity转化为View然后放入View集合

//        mViews.add(LayoutInflater.from(this).inflate(R.layout.shops,null));
        mViews.add(LayoutInflater.from(this).inflate(R.layout.shopcar,null));
//        Intent intentItem2 = new Intent(getApplicationContext(),shops.class);//这个类的第一个参数是上下文，第二个参数是你需要转化的Activity
//        mViews.add(manager.startActivity("shops",intentItem2).getDecorView());//将Activity转化为View然后放入View集合
        Intent intentItem3 = new Intent(getApplicationContext(), PersonActivity.class);//这个类的第一个参数是上下文，第二个参数是你需要转化的Activity
        mViews.add(manager.startActivity("me",intentItem3).getDecorView());//将Activity转化为View然后放入View集合

//        mViews.add(LayoutInflater.from(this).inflate(R.layout.me,null));

        mViewPager.setAdapter(new MyViewPagerAdapter());//设置一个适配器
        //对viewpager监听，让分页和底部图标保持一起滑动
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override   //让viewpager滑动的时候，下面的图标跟着变动
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        tab1.setChecked(true);
                        tab2.setChecked(false);
                        tab3.setChecked(false);
                        break;
                    case 1:
                        tab1.setChecked(false);
                        tab2.setChecked(true);
                        tab3.setChecked(false);
                        break;
                    case 2:
                        tab1.setChecked(false);
                        tab2.setChecked(false);
                        tab3.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //ViewPager适配器
    private class MyViewPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mViews.size();
        }


        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(mViews.get(position));
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(mViews.get(position));
            return mViews.get(position);
        }
    }


}

