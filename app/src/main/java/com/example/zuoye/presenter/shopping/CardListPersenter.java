package com.example.zuoye.presenter.shopping;


import com.example.zuoye.interfaces.shopping.ShoppingContract;
import com.example.zuoye.model.CommonSubscriber;
import com.example.zuoye.model.bean.CardListBean;
import com.example.zuoye.model.http.HttpManager;
import com.example.zuoye.presenter.BasePersenter;
import com.example.zuoye.utils.RxUtils;

public class CardListPersenter extends BasePersenter<ShoppingContract.View> implements ShoppingContract.Persenter {


    @Override
    public void getCardList() {
        addSubscribe(HttpManager.getShouyeApi().getCardList()
                .compose(RxUtils.<CardListBean> rxScheduler())
                .subscribeWith(new CommonSubscriber<CardListBean>(mView){
                    @Override
                    public void onNext(CardListBean result) {
                        mView.getCardListReturn(result);
                    }
                }));
    }
}
