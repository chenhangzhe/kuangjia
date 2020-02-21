package com.example.zuoye.fragment.activity;

import android.widget.ImageView;
import android.widget.TextView;

;

import com.bumptech.glide.Glide;
import com.example.zuoye.R;
import com.example.zuoye.adapter.BaseAdapter;
import com.example.zuoye.model.bean.CategoryListBean;

import java.util.List;

public class CategroyAdapter extends BaseAdapter {
    public CategroyAdapter(List mDatas) {
        super(mDatas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_goodlist_item;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {
        CategoryListBean.DataBeanX.GoodsListBean bean = (CategoryListBean.DataBeanX.GoodsListBean) mDatas.get(positon);
        ImageView img = (ImageView)holder.getView(R.id.img_icon);
        Glide.with(mContext).load(bean.getList_pic_url()).into(img);
        TextView txtName = (TextView)holder.getView(R.id.txt_name);
        txtName.setText(bean.getName());
        TextView txtPrice = (TextView)holder.getView(R.id.txt_price);
        txtPrice.setText(bean.getRetail_price()+"元起");
    }


}
