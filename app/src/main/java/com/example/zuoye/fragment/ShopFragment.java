package com.example.zuoye.fragment;

import android.view.View;

import com.example.zuoye.R;
import com.example.zuoye.base.BaseFragment;
import com.example.zuoye.interfaces.IPersenter;

public class ShopFragment extends BaseFragment {
    @Override
    protected int getLayout() {
        return R.layout.fragment_shop;
    }
    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected IPersenter createPersenter() {
        return null;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String err) {

    }
}
