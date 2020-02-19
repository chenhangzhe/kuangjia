package com.example.zuoye.fragment.classify;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zuoye.R;
import com.example.zuoye.adapter.BaseAdapter;
import com.example.zuoye.model.bean.CatalogItem;

import java.util.ArrayList;
import java.util.List;

public class ClassifyAdapter extends BaseAdapter {



    public ClassifyAdapter(List mDatas) {
        super(mDatas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.classify_list_item;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {
        CatalogItem bean = (CatalogItem) mDatas.get(positon);
        ImageView img = (ImageView) holder.getView(R.id.img);
        TextView text = (TextView) holder.getView(R.id.text);
        Glide.with(mContext).load(bean.img).into(img);
        text.setText(bean.name);




    }


}
