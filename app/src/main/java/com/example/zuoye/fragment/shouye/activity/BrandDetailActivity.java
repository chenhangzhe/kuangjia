package com.example.zuoye.fragment.shouye.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zuoye.R;
import com.example.zuoye.adapter.home.BrandListAdapter;
import com.example.zuoye.base.BaseActivity;
import com.example.zuoye.interfaces.home.BrandListContract;
import com.example.zuoye.model.bean.BrandListDetailBean;
import com.example.zuoye.model.bean.BrandTopImgBean;
import com.example.zuoye.presenter.home.BrandDetailPersenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

    public class BrandDetailActivity extends BaseActivity<BrandListContract.BrandDetailView, BrandListContract.BrandDetailPersenter> implements BrandListContract.BrandDetailView {


    @BindView(R.id.img_brand_detail)
    ImageView imgBrandDetail;
    @BindView(R.id.name_brand_detail)
    TextView nameBrandDetail;
    @BindView(R.id.desc_brand_detail)
    TextView descBrandDetail;
    @BindView(R.id.rv_brand_detail)
    RecyclerView rvBrandDetail;
    private BrandListAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_brand_detail;
    }

    @Override
    protected void initView() {
        ArrayList<BrandListDetailBean.DataBeanX.DataBean> dataBeans = new ArrayList<>();
        adapter = new BrandListAdapter(dataBeans);
        rvBrandDetail.setLayoutManager(new GridLayoutManager(context,2));
        rvBrandDetail.setAdapter(adapter);

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        persenter.getBrandTopImg(id);
        persenter.getBrandListDetail(id,1,100);
    }

    @Override
    protected BrandListContract.BrandDetailPersenter createPersenter() {
        return new BrandDetailPersenter();
    }

    @Override
    public void getBrandTopImgReturn(BrandTopImgBean result) {
        Glide.with(this).load(result.getData().getBrand().getList_pic_url()).into(imgBrandDetail);
        nameBrandDetail.setText(result.getData().getBrand().getName());
        descBrandDetail.setText(result.getData().getBrand().getSimple_desc());
    }

    @Override
    public void getBrandListDetailReturn(BrandListDetailBean result) {
        adapter.refresh(result.getData().getData());
    }


}
