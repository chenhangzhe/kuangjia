package com.example.zuoye.interfaces.home;

import com.example.zuoye.interfaces.IBaseView;
import com.example.zuoye.interfaces.IPersenter;
import com.example.zuoye.model.bean.ZhuantiBean;

public interface ZhuantiContract {
    interface View extends IBaseView {
        void getZhuantiResult(ZhuantiBean result);
    }

    interface Persenter extends IPersenter<View> {
        void getZhuantibean(int page,int size);
    }
}
