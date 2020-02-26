package com.example.zuoye.presenter.home;

import com.example.zuoye.interfaces.home.BrandListContract;
import com.example.zuoye.model.CommonSubscriber;
import com.example.zuoye.model.bean.BrandListDetailBean;
import com.example.zuoye.model.bean.BrandTopImgBean;
import com.example.zuoye.model.http.HttpManager;
import com.example.zuoye.presenter.BasePersenter;
import com.example.zuoye.utils.RxUtils;

public class BrandDetailPersenter extends BasePersenter<BrandListContract.BrandDetailView>implements BrandListContract.BrandDetailPersenter {
    @Override
    public void getBrandTopImg(int id) {
        addSubscribe(HttpManager.getShouyeApi().getBrandTopImg(id)
                .compose(RxUtils.<BrandTopImgBean> rxScheduler())
                .subscribeWith(new CommonSubscriber<BrandTopImgBean>(mView){
                    @Override
                    public void onNext(BrandTopImgBean result) {
                        mView.getBrandTopImgReturn(result);
                    }
                }));
    }

    @Override
    public void getBrandListDetail(int id, int page, int size) {
        addSubscribe(HttpManager.getShouyeApi().getBrandListDetail(id,page,size)
                .compose(RxUtils.<BrandListDetailBean> rxScheduler())
                .subscribeWith(new CommonSubscriber<BrandListDetailBean>(mView){
                    @Override
                    public void onNext(BrandListDetailBean result) {
                        mView.getBrandListDetailReturn(result);
                    }
                }));
    }
}
