package com.example.zuoye.presenter;

import com.example.zuoye.base.BasePersenter;
import com.example.zuoye.interfaces.home.HomeContract;
import com.example.zuoye.model.CommonSubscriber;
import com.example.zuoye.model.bean.ShouYeBean;
import com.example.zuoye.model.http.HttpManager;
import com.example.zuoye.utils.RxUtils;

public class ShouyePresenter extends BasePersenter<HomeContract.View> implements HomeContract.Persenter{

    @Override
    public void getshouye() {
        addSubscribe(HttpManager.getShouyeApi().getShouyeData()
                .compose(RxUtils.<ShouYeBean> rxScheduler())
                .subscribeWith(new CommonSubscriber<ShouYeBean>(mView){
                    @Override
                    public void onNext(ShouYeBean result) {
                        mView.getshouyeReturn(result);
                    }
                }));
    }
}
