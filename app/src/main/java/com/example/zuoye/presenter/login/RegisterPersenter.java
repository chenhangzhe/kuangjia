package com.example.zuoye.presenter.login;


import com.example.zuoye.base.BasePersenter;
import com.example.zuoye.interfaces.login.RegisterContract;
import com.example.zuoye.model.CommonSubscriber;
import com.example.zuoye.model.bean.RegisterBean;
import com.example.zuoye.model.http.HttpManager;
import com.example.zuoye.utils.RxUtils;

public class RegisterPersenter extends BasePersenter<RegisterContract.View> implements RegisterContract.Persenter {
    @Override
    public void getVerify(String name,String password) {
        addSubscribe(HttpManager.getInstance().getShouyeApi().register(name, password)
                .compose(RxUtils.<RegisterBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<RegisterBean>(mView){

                    @Override
                    public void onNext(RegisterBean result) {
                        mView.getRegisterBeanReturn(result);
                    }
                }));
    }
}
