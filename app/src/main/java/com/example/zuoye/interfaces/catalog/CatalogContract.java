package com.example.zuoye.interfaces.catalog;

import com.example.zuoye.interfaces.IBaseView;
import com.example.zuoye.interfaces.IPersenter;
import com.example.zuoye.model.bean.CatalogListBean;
import com.example.zuoye.model.bean.CatalogTabBean;
//契约类
public interface CatalogContract {

    interface View extends IBaseView {
        //定义用来接收获取分类Tab数据返回的方法
        void getCatalogTabDataReturn(CatalogTabBean result);
        //获取指定的列表数据
        void getCatalogListReturn(CatalogListBean result);
    }

    interface Persenter extends IPersenter<View> {
        //加载分类Tab数据
        void getCatalogTabData();
        //获取列表数据
        void getCatalogList(int id);
    }

}
