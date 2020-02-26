package com.example.zuoye.fragment.shouye.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zuoye.R;
import com.example.zuoye.adapter.BaseAdapter;
import com.example.zuoye.adapter.home.NewlistAdapter;
import com.example.zuoye.base.BaseActivity;
import com.example.zuoye.interfaces.home.HomeContract;
import com.example.zuoye.model.bean.BannerInfoTopBean;
import com.example.zuoye.model.bean.NewDataBean;
import com.example.zuoye.presenter.home.NewlistPersenter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewHotActivity extends BaseActivity<HomeContract.NewHotView, HomeContract.NewHotPersenter> implements HomeContract.NewHotView {


    @BindView(R.id.img_new_hot)
    ImageView imgNewHot;
    @BindView(R.id.name_new_hot)
    TextView nameNewHot;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.rv_new_hot)
    RecyclerView rvNewHot;
    private ArrayList<NewDataBean.DataBeanX.DataBean> newlist;
    private NewlistAdapter newlistAdapter;
    private ArrayList<NewDataBean.DataBeanX.DataBean> hotlist;
    private NewlistAdapter hotadapter;
    private int tag;
    private String ORDER = "asc";
    private String SORT = "default";
    private int id;

    @Override
    protected int getLayout() {
        return R.layout.activity_new_hot;
    }

    @Override
    protected void initView() {
     /* tab.getTabAt(0).setText("综合");
      tab.getTabAt(1).setText("价格");
      tab.getTabAt(2).setText("分类");
      tab.addTab(tab.newTab());*/
      tab.addTab(tab.newTab().setText("综合"),0);
      tab.addTab(tab.newTab().setText("价格"),1);
      tab.addTab(tab.newTab().setText("分类"),2);
        Intent intent = getIntent();
        tag = intent.getIntExtra("tag", 0);
        id = intent.getIntExtra("id", 0);
        rvNewHot.setLayoutManager(new GridLayoutManager(context,2));
        if (tag ==1){
            newlist = new ArrayList<>();
            newlistAdapter = new NewlistAdapter(newlist);
            newlistAdapter.tag = 1;
            rvNewHot.setAdapter(newlistAdapter);
            newlistAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    Toast.makeText(context, "跳转页面", Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            hotlist = new ArrayList<>();
            hotadapter = new NewlistAdapter(hotlist);
            hotadapter.tag = 2;
            rvNewHot.setAdapter(hotadapter);
            hotadapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    Toast.makeText(context, "点击了条目", Toast.LENGTH_SHORT).show();
                }
            });
        }
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        if (tag == 1) {
                            SORT = "default";
                            persenter.getNewData(1, 1, 100, ORDER, SORT, id);
                        } else {
                            SORT = "default";
                            persenter.getHotData(1, 1, 100, ORDER, SORT, id);
                        }
                        break;
                     case 1:
                         SORT = "price";
                         if (tag == 1) {
                             if (ORDER.equals("asc")) {
                                 ORDER = "desc";
                                 persenter.getNewData(1, 1, 100, ORDER, SORT, id);
                             } else {
                                 ORDER = "asc";
                                 persenter.getNewData(1, 1, 100, ORDER, SORT, id);
                             }
                         } else {
                             if (ORDER.equals("asc")) {
                                 ORDER = "desc";
                                 persenter.getHotData(1, 1, 100, ORDER, SORT, id);
                             } else {
                                 ORDER = "asc";
                                 persenter.getHotData(1, 1, 100, ORDER, SORT, id);
                             }
                         }
                        break;
                     case 2:
                         Toast.makeText(context, "别点了没效果", Toast.LENGTH_SHORT).show();
                        break;

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void initData() {
        persenter.getBannerInfoTop();
        if (tag == 1) {
            persenter.getNewData(1, 1, 100, ORDER, SORT, id);
        } else {
            persenter.getHotData(1, 1, 100, ORDER, SORT, id);
        }
    }

    @Override
    protected HomeContract.NewHotPersenter createPersenter() {
        return new NewlistPersenter();
    }

    @Override
    public void getBannerInfoTopReturn(BannerInfoTopBean result) {
        Glide.with(this).load(result.getData().getBannerInfo().getImg_url()).into(imgNewHot);
        nameNewHot.setText(result.getData().getBannerInfo().getName());
    }

    @Override
    public void getNewDataReturn(NewDataBean result) {
        newlist.addAll(result.getData().getData());
        newlistAdapter.notifyDataSetChanged();
    }

    @Override
    public void getHotDataReturn(NewDataBean result) {
        hotlist.addAll(result.getData().getData());
        hotadapter.notifyDataSetChanged();
    }


}
