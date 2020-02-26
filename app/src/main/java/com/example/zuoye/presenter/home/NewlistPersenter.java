package com.example.zuoye.presenter.home;

import com.example.zuoye.interfaces.home.HomeContract;
import com.example.zuoye.model.CommonSubscriber;
import com.example.zuoye.model.bean.BannerInfoTopBean;
import com.example.zuoye.model.bean.NewDataBean;
import com.example.zuoye.model.http.HttpManager;
import com.example.zuoye.presenter.BasePersenter;
import com.example.zuoye.utils.RxUtils;

public class NewlistPersenter extends BasePersenter<HomeContract.NewHotView>implements HomeContract.NewHotPersenter {
    @Override
    public void getBannerInfoTop() {
        addSubscribe(HttpManager.getShouyeApi().getBannerInfoTop()
                .compose(RxUtils.<BannerInfoTopBean> rxScheduler())
                .subscribeWith(new CommonSubscriber<BannerInfoTopBean>(mView){
                    @Override
                    public void onNext(BannerInfoTopBean result) {
                        mView.getBannerInfoTopReturn(result);
                    }
                }));
    }

    @Override
    public void getNewData(int isNew, int page, int size, String order, String sort, int id) {
        addSubscribe(HttpManager.getShouyeApi().getNewData(isNew,page,size,order,sort,id)
                .compose(RxUtils.<NewDataBean> rxScheduler())
                .subscribeWith(new CommonSubscriber<NewDataBean>(mView){
                    @Override
                    public void onNext(NewDataBean result) {
                        mView.getNewDataReturn(result);
                    }
                }));
    }

    @Override
    public void getHotData(int isHot, int page, int size, String order, String sort, int id) {
        addSubscribe(HttpManager.getShouyeApi().getHotData(isHot,page,size,order,sort,id)
                .compose(RxUtils.<NewDataBean> rxScheduler())
                .subscribeWith(new CommonSubscriber<NewDataBean>(mView){
                    @Override
                    public void onNext(NewDataBean result) {
                        mView.getHotDataReturn(result);
                    }
                }));
    }
}
