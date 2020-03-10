package com.example.zuoye.fragment.shopping;


import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.zuoye.R;
import com.example.zuoye.adapter.shopping.ShoppingAdapter;
import com.example.zuoye.base.BaseFragment;
import com.example.zuoye.fragment.login.LoginActivity;
import com.example.zuoye.interfaces.shopping.ShoppingContract;
import com.example.zuoye.model.bean.CardListBean;
import com.example.zuoye.presenter.shopping.CardListPersenter;
import com.example.zuoye.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class ShoppingFragment extends BaseFragment<ShoppingContract.View,ShoppingContract.Persenter> implements ShoppingContract.View, View.OnClickListener {
    @BindView(R.id.shop_recycle)
    RecyclerView shopRecycle;
    @BindView(R.id.shop_danxuan)
    CheckBox shopDanxuan;
    @BindView(R.id.shop_all)
    TextView shopAll;
    @BindView(R.id.shop_jishu)
    TextView shopJishu;
    @BindView(R.id.shop_xiadan)
    Button shopXiadan;
    @BindView(R.id.shop_order)
    TextView shopOrder;
    @BindView(R.id.shop_price)
    TextView shopPrice;
    private ShoppingAdapter shoppingAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_shopping;
    }

    @Override
    protected void initView(View view) {
        shopRecycle.setLayoutManager(new LinearLayoutManager(context));
        ArrayList<CardListBean.DataBean.CartListBean> cartListBeans = new ArrayList<>();
        shoppingAdapter = new ShoppingAdapter(cartListBeans);
        shopRecycle.setAdapter(shoppingAdapter);
        shopOrder.setOnClickListener(this);
    }


    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        if (requestCode == 300) {
            persenter.getCardList();
        }
    }

    @Override
    protected void initData() {
        String token = SharedPreferencesUtil.getInstance().getString("token");
        if (TextUtils.isEmpty(token)) {
            Intent intent = new Intent(context, LoginActivity.class);
            startActivityForResult(intent, 300);
        } else {
            persenter.getCardList();
        }
    }

    @Override
    protected ShoppingContract.Persenter createPersenter() {
        return new CardListPersenter();
    }

    @Override
    public void getCardListReturn(CardListBean result) {
        List<CardListBean.DataBean.CartListBean> cartList = result.getData().getCartList();
        shoppingAdapter.refresh(cartList);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.shop_order:
               if (shopOrder.getText().toString().equals("编辑")){
                   shopOrder.setText("完成");
                   shopXiadan.setText("删除所选");
                   shopPrice.setVisibility(View.GONE);
               }else{
                   shopOrder.setText("编辑");
                   shopXiadan.setText("下单");
                   shopPrice.setVisibility(View.VISIBLE);
               }
                break;
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String err) {

    }
}
