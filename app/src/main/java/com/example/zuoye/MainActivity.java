package com.example.zuoye;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.zuoye.adapter.MainAdapter;
import com.example.zuoye.adapter.home.ViewpagerAdapter;
import com.example.zuoye.fragment.classify.FenleiFragment;
import com.example.zuoye.fragment.MineFragment;
import com.example.zuoye.fragment.ShopFragment;
import com.example.zuoye.fragment.shopping.ShoppingFragment;
import com.example.zuoye.fragment.shouye.ShouyeFragment;
import com.example.zuoye.fragment.ZhuantiFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private ViewPager vp_main;

    private TabLayout tab;
    private ViewpagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


    }


    private void initView() {
        vp_main = (ViewPager) findViewById(R.id.vp_main);
        tab = (TabLayout) findViewById(R.id.tab_main);


        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new ShouyeFragment());
        fragments.add(new ZhuantiFragment());
        fragments.add(new FenleiFragment());
        fragments.add(new ShoppingFragment());
        fragments.add(new MineFragment());

        MainAdapter adapter = new MainAdapter(getSupportFragmentManager(), fragments);
        vp_main.setAdapter(adapter);
        //设置是适配器
      /*  adapter = new ViewpagerAdapter(getSupportFragmentManager(), fragments);
        //设置联动
        vp_main.setAdapter(adapter);*/
        //进行关联
        tab.setupWithViewPager(vp_main);

        tab.getTabAt(0).setText("首页").setIcon(R.drawable.ic_home_selector);
        tab.getTabAt(1).setText("专题").setIcon(R.drawable.ic_topic_selector);
        tab.getTabAt(2).setText("分类").setIcon(R.drawable.ic_sort_selector);
        tab.getTabAt(3).setText("购物车").setIcon(R.drawable.ic_shoping_selector);
        tab.getTabAt(4).setText("我的").setIcon(R.drawable.ic_me_selector);



    }

    @Override
    public void onClick(View view) {



    }
}
