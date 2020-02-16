package com.example.zuoye.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zuoye.R;

import java.util.ArrayList;

public class MineAdapter extends RecyclerView.Adapter<MineAdapter.Vh> {
    private ArrayList<String> list;
    private Context con;

    public MineAdapter(ArrayList<String> list, Context con) {
        this.list = list;
        this.con = con;
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(con, R.layout.item_me, null);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Vh holder, int position) {
        holder.tv.setText(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Vh extends RecyclerView.ViewHolder {
        TextView tv;
        public Vh(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_me_item_text);
        }
    }


}
