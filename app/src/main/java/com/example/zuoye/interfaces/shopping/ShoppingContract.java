package com.example.zuoye.interfaces.shopping;


import com.example.zuoye.interfaces.IBaseView;
import com.example.zuoye.interfaces.IPersenter;
import com.example.zuoye.model.bean.CardListBean;

public interface ShoppingContract {
    interface View extends IBaseView {
        void getCardListReturn(CardListBean result);
    }
    interface Persenter extends IPersenter<View> {
        void getCardList();
    }
}
