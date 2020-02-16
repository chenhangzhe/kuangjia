package com.example.zuoye.fragment;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.zuoye.R;
import com.example.zuoye.adapter.ShouyeAdapter;
import com.example.zuoye.adapter.shouye.PinpaiRvAdapter;
import com.example.zuoye.adapter.shouye.RenqiRvAdapter;
import com.example.zuoye.adapter.shouye.XinpinRvAdapter;
import com.example.zuoye.adapter.shouye.ZhuantiRvAdapter;
import com.example.zuoye.base.BaseFragment;
import com.example.zuoye.fragment.shouye.JujiaFragment;
import com.example.zuoye.interfaces.IPersenter;
import com.example.zuoye.interfaces.shangcheng.ShouyeContract;
import com.example.zuoye.model.bean.ShouYeBean;
import com.example.zuoye.presenter.ShouyePresenter;
import com.google.android.material.tabs.TabLayout;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ShouyeFragment extends BaseFragment implements ShouyeContract.View {
    @BindView(R.id.banner_shouye)
    Banner bannerShouye;
    @BindView(R.id.tab_shouye)
    TabLayout tabShouye;
    @BindView(R.id.vp_shouye)
    ViewPager vpShouye;
    @BindView(R.id.rv_shouye)
    RecyclerView rvShouye;
    @BindView(R.id.tv1_shouye)
    TextView tv1Shouye;
    @BindView(R.id.tv2_shouye)
    TextView tv2Shouye;
    @BindView(R.id.rv2_shouye)
    RecyclerView rv2Shouye;
    @BindView(R.id.tv3_shouye)
    TextView tv3Shouye;
    @BindView(R.id.rv3_shouye)
    RecyclerView rv3Shouye;
    @BindView(R.id.tv4_shouye)
    TextView tv4Shouye;
    @BindView(R.id.rv4_shouye)
    RecyclerView rv4Shouye;
    private ArrayList<String> strings;
    private ArrayList<String> tabString;
    private ShouyeAdapter adapter;
    private ArrayList<Fragment> fragments;
    private PinpaiRvAdapter rvShouyeAdapter;
    private ArrayList<ShouYeBean.DataBean.BrandListBean> brandListBeans;
    private ArrayList<ShouYeBean.DataBean.NewGoodsListBean> newGoodsListBeans;
    private XinpinRvAdapter xinpinRvAdapter;
    private ArrayList<ShouYeBean.DataBean.HotGoodsListBean> hotGoodsListBeans;
    private RenqiRvAdapter renqiRvAdapter;
    private ZhuantiRvAdapter zhuantiRvAdapter;
    private ArrayList<ShouYeBean.DataBean.TopicListBean> topicListBeans;

    @Override
    protected int getLayout() {
        return R.layout.fragment_shouye;
    }

    @Override
    protected void initView(View view) {
        strings = new ArrayList<>();
        fragments = new ArrayList<>();
        tabString = new ArrayList<>();
        adapter = new ShouyeAdapter(getActivity().getSupportFragmentManager(), tabString, fragments);
        vpShouye.setAdapter(adapter);
        tabShouye.setupWithViewPager(vpShouye);
        pinpai();
        xinpin();
        renqi();
        zhuanti();
    }

    private void zhuanti() {
        tv4Shouye.setText("专题精选");
        rv4Shouye.setLayoutManager(new StaggeredGridLayoutManager(1,DividerItemDecoration.HORIZONTAL));
        topicListBeans = new ArrayList<>();
        zhuantiRvAdapter = new ZhuantiRvAdapter(context, topicListBeans);
        rv4Shouye.setAdapter(zhuantiRvAdapter);
    }

    private void xinpin() {
        tv2Shouye.setText("周一周四·新品发布");
        rv2Shouye.setLayoutManager(new GridLayoutManager(context, 2));
        newGoodsListBeans = new ArrayList<>();
        xinpinRvAdapter = new XinpinRvAdapter(context, newGoodsListBeans);
        rv2Shouye.setAdapter(xinpinRvAdapter);
    }

    private void renqi() {
        tv3Shouye.setText("人气推荐");
        rv3Shouye.setLayoutManager(new LinearLayoutManager(context));
        hotGoodsListBeans = new ArrayList<>();
        renqiRvAdapter = new RenqiRvAdapter(context, hotGoodsListBeans);
        rv3Shouye.setAdapter(renqiRvAdapter);
        rv3Shouye.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
    }

    private void pinpai() {
        tv1Shouye.setText("品牌制造商直供");
        rvShouye.setLayoutManager(new GridLayoutManager(context, 2));
        brandListBeans = new ArrayList<>();
        rvShouyeAdapter = new PinpaiRvAdapter(context, brandListBeans);
        rvShouye.setAdapter(rvShouyeAdapter);
    }

    @Override
    protected void initData() {
        ((ShouyePresenter) persenter).getshouye();
    }

    @Override
    protected IPersenter createPersenter() {
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
        ShouYeBean.DataBean data = shouYeBean.getData();
        setBannerdata(data);
        setPinpai(shouYeBean);
        setXinpin(data);
        setRenqi(data);
        setzhuanti(data);
    }

    private void setzhuanti(ShouYeBean.DataBean data) {
        List<ShouYeBean.DataBean.TopicListBean> topicList = data.getTopicList();
        topicListBeans.addAll(topicList);
        zhuantiRvAdapter.notifyDataSetChanged();
    }

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

    private void setPinpai(ShouYeBean shouYeBean) {
        List<ShouYeBean.DataBean.BrandListBean> brandList = shouYeBean.getData().getBrandList();
        brandListBeans.addAll(brandList);
        rvShouyeAdapter.notifyDataSetChanged();
    }

    private void setBannerdata(ShouYeBean.DataBean data) {
        List<ShouYeBean.DataBean.BannerBean> banner = data.getBanner();
        for (ShouYeBean.DataBean.BannerBean bannerBean : banner) {
            strings.add(bannerBean.getImage_url());
        }
        setbanner(bannerShouye, strings);

        List<ShouYeBean.DataBean.ChannelBean> channel = data.getChannel();
        for (ShouYeBean.DataBean.ChannelBean channelBean : channel) {
            fragments.add(new JujiaFragment());
            tabString.add(channelBean.getName());
        }
        adapter.notifyDataSetChanged();
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
