package com.example.zuoye.presenter.classify;

import com.example.zuoye.interfaces.categroy.CategoryConstract;
import com.example.zuoye.model.CommonSubscriber;
import com.example.zuoye.model.bean.CategoryListBean;
import com.example.zuoye.model.bean.CategoryTabBean;
import com.example.zuoye.model.http.HttpManager;
import com.example.zuoye.presenter.BasePersenter;
import com.example.zuoye.utils.RxUtils;

public class CategroyPersenter extends BasePersenter<CategoryConstract.View> implements CategoryConstract.Persenter {
    @Override
    public void getCategoryTab(int id) {
        addSubscribe(HttpManager.getMyApi().getCategoryTab(id)
        .compose(RxUtils.<CategoryTabBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<CategoryTabBean>(mView) {
            @Override
            public void onNext(CategoryTabBean categoryTabBean) {
                mView.getCategoryTabReturn(categoryTabBean);
            }
        }));
    }

    @Override
    public void getGoodsList(int categoryId, int page, int size) {
        addSubscribe(HttpManager.getMyApi().getCategoryList(categoryId,page,size)
                .compose(RxUtils.<CategoryListBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<CategoryListBean>(mView) {
                    @Override
                    public void onNext(CategoryListBean categoryListBean) {
                        mView.getGoodsListReturn(categoryListBean);
                    }
                }));
    }
}
