package com.example.zuoye.fragment.shouye.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zuoye.R;
import com.example.zuoye.adapter.BaseAdapter;
import com.example.zuoye.adapter.home.BrandListAdapter;
import com.example.zuoye.base.BaseActivity;
import com.example.zuoye.interfaces.home.BrandListContract;
import com.example.zuoye.model.bean.BrandListBean;
import com.example.zuoye.presenter.home.BrandListPersenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BrandListActivity extends BaseActivity<BrandListContract.View, BrandListContract.Persenter> implements BrandListContract.View {


    @BindView(R.id.rv_barand_list)
    RecyclerView rvBarandList;
    private ArrayList<BrandListBean.DataBeanX.DataBean> list;
    private BrandListAdapter adapter;
    private List<BrandListBean.DataBeanX.DataBean> data;

    @Override
    protected int getLayout() {
        return R.layout.activity_brand_list;
    }

    @Override
    protected void initView() {
        rvBarandList.setLayoutManager(new LinearLayoutManager(this));
        rvBarandList.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));
        list = new ArrayList<>();
        adapter = new BrandListAdapter(list);
        rvBarandList.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                BrandListBean.DataBeanX.DataBean bean = data.get(position);
                int id = bean.getId();
                Intent intent = new Intent(context, BrandDetailActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void initData() {
    persenter.getBrandList(1,100);
    }

    @Override
    protected BrandListContract.Persenter createPersenter() {
        return new BrandListPersenter();
    }

    @Override
    public void getBrandlistretrn(BrandListBean result) {
        data = result.getData().getData();
        list.addAll(data);
        adapter.notifyDataSetChanged();
    }


}
