package com.example.zuoye.interfaces.login;


import com.example.zuoye.interfaces.IBaseView;
import com.example.zuoye.interfaces.IPersenter;
import com.example.zuoye.model.bean.UserBean;

public interface LoginContract {
    interface View extends IBaseView {
        void loginReturn(UserBean result);
    }

    interface Persenter extends IPersenter<View> {
        void login(String nickname,String password);
    }
}
