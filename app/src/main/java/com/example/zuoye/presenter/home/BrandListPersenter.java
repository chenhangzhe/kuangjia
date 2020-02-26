package com.example.zuoye.presenter.home;

import com.example.zuoye.interfaces.home.BrandListContract;
import com.example.zuoye.model.CommonSubscriber;
import com.example.zuoye.model.bean.BrandListBean;
import com.example.zuoye.model.http.HttpManager;
import com.example.zuoye.presenter.BasePersenter;
import com.example.zuoye.utils.RxUtils;

public class BrandListPersenter extends BasePersenter<BrandListContract.View>implements BrandListContract.Persenter {
    @Override
    public void getBrandList(int page, int size) {
        addSubscribe(HttpManager.getShouyeApi().getBrandList(page,size)
                .compose(RxUtils.<BrandListBean> rxScheduler())
                .subscribeWith(new CommonSubscriber<BrandListBean>(mView){
                    @Override
                    public void onNext(BrandListBean result) {
                        mView.getBrandlistretrn(result);
                    }
                }));
    }
}
