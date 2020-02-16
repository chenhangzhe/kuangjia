package com.example.zuoye.presenter;

import com.example.zuoye.base.BasePersenter;
import com.example.zuoye.interfaces.shangcheng.ZhuantiContract;
import com.example.zuoye.model.CommonSubscriber;
import com.example.zuoye.model.bean.ZhuantiBean;
import com.example.zuoye.model.http.HttpManager;
import com.example.zuoye.utils.RxUtils;

public class ZhuantiPresenter extends BasePersenter<ZhuantiContract.View> implements ZhuantiContract.Persenter {


    @Override
    public void getZhuantibean(int page, int size) {
        addSubscribe(HttpManager.getShouyeApi().getZhuantiData(1,10)
                .compose(RxUtils.<ZhuantiBean> rxScheduler())
                .subscribeWith(new CommonSubscriber<ZhuantiBean>(mView){
                    @Override
                    public void onNext(ZhuantiBean result) {
                        mView.getZhuantiResult(result);
                    }
                }));
    }
}
