package com.example.zuoye.presenter.classify;

import android.util.Log;

import com.example.zuoye.interfaces.catalog.CatalogContract;
import com.example.zuoye.model.CommonSubscriber;
import com.example.zuoye.model.bean.CatalogListBean;
import com.example.zuoye.model.bean.CatalogTabBean;
import com.example.zuoye.model.http.HttpManager;
import com.example.zuoye.presenter.BasePersenter;
import com.example.zuoye.utils.RxUtils;

public class CatalogPersenter extends BasePersenter<CatalogContract.View> implements CatalogContract.Persenter{
    @Override
    public void getCatalogTabData() {
        addSubscribe(HttpManager.getMyApi().getCatalogTabData()
        .compose(RxUtils.<CatalogTabBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<CatalogTabBean>(mView) {
            @Override
            public void onNext(CatalogTabBean catalogTabBean) {
                Log.i("111", "onNext: "+catalogTabBean.toString());
                if (catalogTabBean.getErrno() == 0){
                    mView.getCatalogTabDataReturn(catalogTabBean);
                }else {
                    mView.showError(catalogTabBean.getErrmsg());
                }
            }
        }));
    }

    @Override
    public void getCatalogList(int id) {
        addSubscribe(HttpManager.getMyApi().getCatalogList(id)
        .compose(RxUtils.<CatalogListBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<CatalogListBean>(mView) {
            @Override
            public void onNext(CatalogListBean catalogListBean) {
                if (catalogListBean.getErrno() == 0){
                    mView.getCatalogListReturn(catalogListBean);
                }else {
                    mView.showError(catalogListBean.getErrmsg());
                }
            }

        }));
    }
}
