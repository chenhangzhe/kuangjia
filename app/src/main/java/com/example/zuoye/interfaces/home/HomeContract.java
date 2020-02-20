package com.example.zuoye.interfaces.home;

import com.example.zuoye.interfaces.IBaseView;
import com.example.zuoye.interfaces.IPersenter;
import com.example.zuoye.model.bean.ShouYeBean;

public interface HomeContract {
    interface View extends IBaseView {
        void getshouyeReturn(ShouYeBean shouYeBean);
    }

    interface Persenter extends IPersenter<View> {
        void getshouye();
    }
}
