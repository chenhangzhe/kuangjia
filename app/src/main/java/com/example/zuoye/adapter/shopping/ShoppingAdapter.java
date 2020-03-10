package com.example.zuoye.adapter.shopping;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zuoye.R;
import com.example.zuoye.adapter.BaseAdapter;
import com.example.zuoye.model.bean.CardListBean;


import java.util.List;

public class ShoppingAdapter extends BaseAdapter {


    public ShoppingAdapter(List mDatas) {
        super(mDatas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_shop;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {
        ImageView img = (ImageView) holder.getView(R.id.item_img_shop);
        TextView txt_name = (TextView) holder.getView(R.id.item_name_shop);
        TextView txt_price = (TextView) holder.getView(R.id.item_price_shop);
        TextView txt_nums = (TextView) holder.getView(R.id.item_number_shop);

        CardListBean.DataBean.CartListBean cartListBean = (CardListBean.DataBean.CartListBean) o;

        Glide.with(mContext).load(cartListBean.getList_pic_url()).into(img);
        txt_name.setText(cartListBean.getGoods_name());
        txt_price.setText(cartListBean.getMarket_price()+"");
        txt_nums.setText(cartListBean.getNumber()+"");
    }


}
