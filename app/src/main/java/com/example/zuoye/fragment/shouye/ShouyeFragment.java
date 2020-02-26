package com.example.zuoye.fragment.shouye;


import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.zuoye.R;
import com.example.zuoye.adapter.home.PinpaiRvAdapter;
import com.example.zuoye.adapter.home.RenqiRvAdapter;
import com.example.zuoye.adapter.home.XinpinRvAdapter;
import com.example.zuoye.base.BaseFragment;
import com.example.zuoye.fragment.shouye.activity.BrandListActivity;
import com.example.zuoye.fragment.shouye.activity.NewHotActivity;
import com.example.zuoye.interfaces.home.HomeContract;
import com.example.zuoye.model.bean.ShouYeBean;
import com.example.zuoye.presenter.home.ShouyePresenter;
import com.google.android.material.tabs.TabLayout;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class ShouyeFragment extends BaseFragment<HomeContract.View, HomeContract.Persenter> implements HomeContract.View {


    private TextView title;
    private RecyclerView recy;
    private TextView newgoodtitle;
    private RecyclerView newgood_recy;
    private TextView txt_hotTitle;
    private RecyclerView hot_recyclerview;
    private Banner ban;
    private TabLayout tab;
    private ArrayList<ShouYeBean.DataBean.BrandListBean> brandListBeans;
    private PinpaiRvAdapter pinpaiRvAdapter;
    private ArrayList<ShouYeBean.DataBean.NewGoodsListBean> newGoodsListBeans;
    private XinpinRvAdapter xinpinRvAdapter;
    private ArrayList<ShouYeBean.DataBean.HotGoodsListBean> hotGoodsListBeans;
    private RenqiRvAdapter renqiRvAdapter;
    private ShouyeAdapter adapter;
    private ViewPager vp;
    private ArrayList<String> strings;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> tabString;
    private ShouYeBean.DataBean data;


    @Override
    protected int getLayout() {
        return R.layout.fragment_shouye;
    }

    @Override
    protected void initView(View view) {
        ban = view.findViewById(R.id.ban);
        tab = view.findViewById(R.id.tab);
        title = view.findViewById(R.id.title);
        newgoodtitle = view.findViewById(R.id.newgoodtitle);
        txt_hotTitle = view.findViewById(R.id.txt_hotTitle);
        recy = view.findViewById(R.id.recy);
        newgood_recy = view.findViewById(R.id.newgood_recy);
        hot_recyclerview = view.findViewById(R.id.hot_recyclerview);
        vp = view.findViewById(R.id.vp_shouye);
        title.setText("品牌制造商直供");
        newgoodtitle.setText("周一周四·新品发布");
        txt_hotTitle.setText("人气推荐");
        onClick();
        initdata();
        pinpai();
        xinpin();
        renqi();
    }

    private void onClick() {
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BrandListActivity.class);
                startActivity(intent);
            }
        });
        newgoodtitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tag = 1;
                int id = data.getNewGoodsList().get(0).getId();
                int categoryid = data.getChannel().get(0).getCategoryid();
                Intent intent = new Intent(context, NewHotActivity.class);
                intent.putExtra("tag",tag);
                intent.putExtra("id",categoryid);
                startActivity(intent);
            }
        });
        txt_hotTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tag1 = 2;
                int categoryid = data.getChannel().get(0).getCategoryid();
                Intent intent = new Intent(context, NewHotActivity.class);
                intent.putExtra("tag",tag1);
                intent.putExtra("id",categoryid);
                startActivity(intent);
            }
        });
    }

    private void initdata() {
        strings = new ArrayList<>();
        fragments = new ArrayList<>();
        tabString = new ArrayList<>();
        adapter = new ShouyeAdapter(getActivity().getSupportFragmentManager(), tabString, fragments);
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);
    }

    private void renqi() {
        hot_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        hotGoodsListBeans = new ArrayList<>();
        renqiRvAdapter = new RenqiRvAdapter(getActivity(), hotGoodsListBeans);
        hot_recyclerview.setAdapter(renqiRvAdapter);
    }

    private void xinpin() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2)/*{
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        }*/;
        newgood_recy.setLayoutManager(gridLayoutManager);
        newGoodsListBeans = new ArrayList<>();
        xinpinRvAdapter = new XinpinRvAdapter(getActivity(), newGoodsListBeans);
        newgood_recy.setAdapter(xinpinRvAdapter);
    }

    private void pinpai() {
        recy.setLayoutManager(new GridLayoutManager(getContext(),2));
        brandListBeans = new ArrayList<>();
        pinpaiRvAdapter = new PinpaiRvAdapter(getActivity(), brandListBeans);
        recy.setAdapter(pinpaiRvAdapter);
    }


    @Override
    protected void initData() {
        persenter.getshouye();
    }

    @Override
    protected HomeContract.Persenter createPersenter() {
        return new ShouyePresenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String err) {

    }

    @Override
    public void getshouyeReturn(ShouYeBean shouYeBean) {
        data = shouYeBean.getData();
        setBannerdata(data);
        setPinpai(shouYeBean);
        setXinpin(data);
        setRenqi(data);

    }
  /*  private void setzhuanti(ShouYeBean.DataBean data) {
        List<ShouYeBean.DataBean.TopicListBean> topicList = data.getTopicList();
        topicListBeans.addAll(topicList);
        zhuantiRvAdapter.notifyDataSetChanged();
    }*/

    private void setRenqi(ShouYeBean.DataBean data) {
        List<ShouYeBean.DataBean.HotGoodsListBean> hotGoodsList = data.getHotGoodsList();
        hotGoodsListBeans.addAll(hotGoodsList);
        renqiRvAdapter.notifyDataSetChanged();
    }

    private void setXinpin(ShouYeBean.DataBean data) {
        List<ShouYeBean.DataBean.NewGoodsListBean> newGoodsList = data.getNewGoodsList();
        newGoodsListBeans.addAll(newGoodsList);
        xinpinRvAdapter.notifyDataSetChanged();
    }

    private void setBannerdata(ShouYeBean.DataBean data) {
        List<ShouYeBean.DataBean.BannerBean> banner = data.getBanner();
        for (ShouYeBean.DataBean.BannerBean bannerBean : banner) {
            strings.add(bannerBean.getImage_url());
        }
        setbanner(ban, strings);

        List<ShouYeBean.DataBean.ChannelBean> channel = data.getChannel();
        for (ShouYeBean.DataBean.ChannelBean channelBean : channel) {
            fragments.add(new JujiaFragment());
            tabString.add(channelBean.getName());
        }
        adapter.notifyDataSetChanged();
    }

    private void setPinpai(ShouYeBean shouYeBean) {
        List<ShouYeBean.DataBean.BrandListBean> brandList = shouYeBean.getData().getBrandList();
        brandListBeans.addAll(brandList);
        pinpaiRvAdapter.notifyDataSetChanged();
    }

    private static void setbanner(Banner bannerShouye, ArrayList<String> strings) {
        bannerShouye.setImages(strings)
                .setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Glide.with(context).load(path).into(imageView);
                    }
                }).start();
    }


}
