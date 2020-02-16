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

public class RenqiRvAdapter extends RecyclerView.Adapter<RenqiRvAdapter.ViewHolder> {
    private Context context;
    private List<ShouYeBean.DataBean.HotGoodsListBean> datas;

    public RenqiRvAdapter(Context context, List<ShouYeBean.DataBean.HotGoodsListBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_rv_renqi, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ShouYeBean.DataBean.HotGoodsListBean hotGoodsListBean = datas.get(position);
        Glide.with(context).load(hotGoodsListBean.getList_pic_url()).into(holder.img);
        holder.name.setText(hotGoodsListBean.getName());
        holder.price.setText(hotGoodsListBean.getRetail_price());
        holder.brief.setText(hotGoodsListBean.getGoods_brief());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView name;
        private TextView brief;
        private TextView price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_renqi);
            brief = itemView.findViewById(R.id.brief_renqi);
            name = itemView.findViewById(R.id.name_renqi);
            price = itemView.findViewById(R.id.price_renqi);
        }
    }
}
