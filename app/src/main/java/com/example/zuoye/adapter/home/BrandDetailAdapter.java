package com.example.zuoye.adapter.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zuoye.R;
import com.example.zuoye.adapter.BaseAdapter;
import com.example.zuoye.interfaces.home.BrandListContract;
import com.example.zuoye.model.bean.BrandListBean;
import com.example.zuoye.model.bean.BrandListDetailBean;

import java.util.List;

public class BrandDetailAdapter extends BaseAdapter {
    public BrandDetailAdapter(List mDatas) {
        super(mDatas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_detai;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {
        BrandListDetailBean.DataBeanX.DataBean bean = (BrandListDetailBean.DataBeanX.DataBean) o;
        ImageView img = (ImageView) holder.getView(R.id.img_item_brand_detail);
        TextView title = (TextView) holder.getView(R.id.name_item_brand_detail);
        TextView price = (TextView) holder.getView(R.id.name_item_brand_detail);
        Glide.with(mContext).load(bean.getList_pic_url()).into(img);
        title.setText(bean.getName());
        price.setText("￥"+bean.getRetail_price());
    }


}
