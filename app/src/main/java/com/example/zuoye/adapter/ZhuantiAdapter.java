package com.example.zuoye.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zuoye.R;
import com.example.zuoye.model.bean.ZhuantiBean;

import java.util.List;

public class ZhuantiAdapter extends RecyclerView.Adapter<ZhuantiAdapter.ViewHolder> {
    private Context context;
    private List<ZhuantiBean.DataBeanX.DataBean> datas;

    public ZhuantiAdapter(Context context, List<ZhuantiBean.DataBeanX.DataBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.fragment_zhaunti, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ZhuantiBean.DataBeanX.DataBean dataBean = datas.get(position);
        Glide.with(context).load(dataBean.getScene_pic_url()).into(holder.img);
        holder.title.setText(dataBean.getTitle());
        holder.subtitle.setText(dataBean.getSubtitle());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView title;
        private TextView subtitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_rv_zhuanti);
            title = itemView.findViewById(R.id.title_zhaunti);
            subtitle = itemView.findViewById(R.id.subtitle);
        }
    }
}
