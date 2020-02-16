package com.example.zuoye.fragment;

import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zuoye.R;
import com.example.zuoye.adapter.MineAdapter;
import com.example.zuoye.base.BaseFragment;
import com.example.zuoye.interfaces.IPersenter;

import java.util.ArrayList;

import butterknife.BindView;

public class MineFragment extends BaseFragment {
    @BindView(R.id.rv_mine)
    RecyclerView rvMine;

    @Override
    protected int getLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View view) {
        rvMine.setLayoutManager(new GridLayoutManager(context,3));
        ArrayList<String> strings = new ArrayList<>();
        strings.add("我的订单");
        strings.add("优惠券");
        strings.add("礼品卡");
        strings.add("我的收藏");
        strings.add("我的足迹");
        strings.add("会员福利");
        strings.add("地址管理");
        strings.add("账号安全");
        strings.add("联系客服");
        strings.add("帮助中心");
        strings.add("意见反馈");
        MineAdapter adapter = new MineAdapter(strings, context);
        rvMine.setAdapter(adapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected IPersenter createPersenter() {
        return null;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String err) {

    }
}
