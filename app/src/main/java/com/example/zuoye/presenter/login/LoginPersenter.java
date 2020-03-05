package com.example.zuoye.presenter.login;


import com.example.zuoye.interfaces.login.LoginContract;
import com.example.zuoye.model.CommonSubscriber;
import com.example.zuoye.model.bean.UserBean;
import com.example.zuoye.model.http.HttpManager;
import com.example.zuoye.presenter.BasePersenter;
import com.example.zuoye.utils.RxUtils;

public class LoginPersenter extends BasePersenter<LoginContract.View> implements LoginContract.Persenter {
    @Override
    public void login(String nickname, String password) {
        addSubscribe(HttpManager.getInstance().getShouyeApi().login(nickname,password)
                .compose(RxUtils.<UserBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<UserBean>(mView){

                    @Override
                    public void onNext(UserBean userBean) {
                        if(userBean.getErrno() == 0){
                            mView.loginReturn(userBean);
                        }else{
                            mView.showError(userBean.getErrmsg());
                        }
                    }
                }));
    }
}
