package com.example.zuoye.interfaces.home;

import com.example.zuoye.interfaces.IBaseView;
import com.example.zuoye.interfaces.IPersenter;
import com.example.zuoye.model.bean.BannerInfoTopBean;
import com.example.zuoye.model.bean.NewDataBean;
import com.example.zuoye.model.bean.ShouYeBean;

public interface HomeContract {
    interface View extends IBaseView {
        void getshouyeReturn(ShouYeBean shouYeBean);
    }

    interface Persenter extends IPersenter<View> {
        void getshouye();
    }
    interface NewHotView extends IBaseView{
        void getBannerInfoTopReturn(BannerInfoTopBean result);

        void getNewDataReturn(NewDataBean result);

        void getHotDataReturn(NewDataBean result);
    }
    interface NewHotPersenter extends IPersenter<NewHotView>{
        void getBannerInfoTop();

        void getNewData(int isNew,int page, int size,String order,String sort,int id);

        void getHotData(int isHot,int page, int size,String order,String sort,int id);
    }
}
