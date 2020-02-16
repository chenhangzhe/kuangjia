package com.example.zuoye.adapter.shouye;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zuoye.R;
import com.example.zuoye.model.bean.ShouYeBean;

import java.util.List;

public class XinpinRvAdapter extends RecyclerView.Adapter<XinpinRvAdapter.ViewHolder> {
    private Context context;
    private List<ShouYeBean.DataBean.NewGoodsListBean> datas;

    public XinpinRvAdapter(Context context, List<ShouYeBean.DataBean.NewGoodsListBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_rv_xinpin, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ShouYeBean.DataBean.NewGoodsListBean newGoodsListBean = datas.get(position);
        Glide.with(context).load(newGoodsListBean.getList_pic_url()).into(holder.img);
        holder.name.setText(newGoodsListBean.getName());
        holder.price.setText(newGoodsListBean.getRetail_price());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView name;
        private TextView price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_xinpin);
            name = itemView.findViewById(R.id.name_xinpin);
            price = itemView.findViewById(R.id.price_xinpin);
        }
    }
}
