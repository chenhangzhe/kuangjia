package com.example.zuoye.adapter.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zuoye.R;
import com.example.zuoye.adapter.BaseAdapter;
import com.example.zuoye.model.bean.BrandListBean;

import java.util.List;

public class BrandListAdapter extends BaseAdapter {
    public BrandListAdapter(List mDatas) {
        super(mDatas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.brand_item;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {
        BrandListBean.DataBeanX.DataBean  bean = (BrandListBean.DataBeanX.DataBean) o;
        ImageView img = (ImageView) holder.getView(R.id.img);
        TextView text = (TextView) holder.getView(R.id.text);
        Glide.with(mContext).load(bean.getApp_list_pic_url()).into(img);
        text.setText(bean.getName()+"|"+bean.getFloor_price()+"元起");

    }

}
