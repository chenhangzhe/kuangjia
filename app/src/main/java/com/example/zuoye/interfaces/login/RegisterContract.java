package com.example.zuoye.interfaces.login;


import com.example.zuoye.interfaces.IBaseView;
import com.example.zuoye.interfaces.IPersenter;
import com.example.zuoye.model.bean.RegisterBean;

public interface RegisterContract {
    interface View extends IBaseView {
        void getRegisterBeanReturn(RegisterBean result);
    }

    interface Persenter extends IPersenter<View> {
        void getVerify(String name,String passWord);
    }
}
