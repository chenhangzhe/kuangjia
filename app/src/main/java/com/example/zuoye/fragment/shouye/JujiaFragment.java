package com.example.zuoye.fragment.shouye;

import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zuoye.R;
import com.example.zuoye.base.BaseFragment;
import com.example.zuoye.interfaces.IPersenter;
import com.example.zuoye.interfaces.home.HomeContract;
import com.example.zuoye.model.bean.ShouYeBean;
import com.example.zuoye.presenter.ShouyePresenter;

import butterknife.BindView;

public class JujiaFragment extends BaseFragment implements HomeContract.View{
    @BindView(R.id.rv_jujia)
    RecyclerView rvJujia;

    @Override
    protected int getLayout() {
        return R.layout.fragment_jujia;
    }

    @Override
    protected void initView(View view) {
        rvJujia.setLayoutManager(new GridLayoutManager(context,2));

    }

    @Override
    protected void initData() {
        ((ShouyePresenter)persenter).getshouye();
    }

    @Override
    protected IPersenter createPersenter() {
        return new ShouyePresenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String err) {

    }

    @Override
    public void getshouyeReturn(ShouYeBean shouYeBean) {
        ShouYeBean.DataBean data = shouYeBean.getData();

    }
}
