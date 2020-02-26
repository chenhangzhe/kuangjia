package com.example.zuoye.interfaces.home;

import com.example.zuoye.interfaces.IBaseView;
import com.example.zuoye.interfaces.IPersenter;
import com.example.zuoye.model.bean.BrandListBean;
import com.example.zuoye.model.bean.BrandListDetailBean;
import com.example.zuoye.model.bean.BrandTopImgBean;

public interface BrandListContract {
    interface View extends IBaseView{
        void getBrandlistretrn(BrandListBean result);
    }
    interface Persenter extends IPersenter<View> {
        void getBrandList(int page, int size);
    }

    interface BrandDetailView extends IBaseView{
        void getBrandTopImgReturn(BrandTopImgBean result);

        void getBrandListDetailReturn(BrandListDetailBean result);
    }
    interface BrandDetailPersenter extends IPersenter<BrandDetailView>{
        void getBrandTopImg(int id);

        void getBrandListDetail(int id,int page,int size);
    }
}
