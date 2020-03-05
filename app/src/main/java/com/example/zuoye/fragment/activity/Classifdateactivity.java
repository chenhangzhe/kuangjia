package com.example.zuoye.fragment.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zuoye.R;
import com.example.zuoye.base.BaseActivity;
import com.example.zuoye.interfaces.catalog.CatalogContract;
import com.example.zuoye.interfaces.categroy.CategoryConstract;
import com.example.zuoye.model.bean.CatalogListBean;
import com.example.zuoye.model.bean.CatalogTabBean;
import com.example.zuoye.model.bean.CategoryListBean;
import com.example.zuoye.model.bean.CategoryTabBean;
import com.example.zuoye.presenter.classify.CategroyPersenter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Classifdateactivity extends BaseActivity<CategoryConstract.View, CategoryConstract.Persenter> implements CategoryConstract.View {


    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_desc)
    TextView txtDesc;
    @BindView(R.id.txtLayout)
    LinearLayout txtLayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private ArrayList<CategoryListBean.DataBeanX.GoodsListBean> goodsListBeans;
    private CategroyAdapter categroyAdapter;
    private List<CategoryTabBean.DataBean.BrotherCategoryBean> beans;

    @Override
    protected int getLayout() {
        return R.layout.activity_classifdateactivity;
    }

    @Override
    protected void initView() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        goodsListBeans = new ArrayList<>();
        categroyAdapter = new CategroyAdapter(goodsListBeans);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerview.setLayoutManager(gridLayoutManager);
        recyclerview.setAdapter(categroyAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int id = (int) tab.getTag();
                CategoryTabBean.DataBean.BrotherCategoryBean bean = beans.get(tab.getPosition());
                txtTitle.setText(bean.getName());
                txtDesc.setText(bean.getFront_name());
                persenter.getGoodsList(id,1,1000);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void initData() {
        int id = getIntent().getIntExtra("id", 0);
        persenter.getCategoryTab(id);
    }

    @Override
    protected CategoryConstract.Persenter createPersenter() {
        return new CategroyPersenter()  ;
    }


    @Override
    public void getCategoryTabReturn(CategoryTabBean result) {
        CategoryTabBean.DataBean.CurrentCategoryBean category = result.getData().getCurrentCategory();
        beans = result.getData().getBrotherCategory();
        int position = 0;
        for (int i = 0; i < result.getData().getBrotherCategory().size(); i++) {
            CategoryTabBean.DataBean.BrotherCategoryBean bean = result.getData().getBrotherCategory().get(i);
            TabLayout.Tab tab = tabLayout.newTab();
            tab.setText(bean.getName());
            tab.setTag(bean.getId());
            tabLayout.addTab(tab);
            if (category.getId() == bean.getId()){
                position = i;
            }
        }
        txtTitle.setText(category.getName());
        txtDesc.setText(category.getFront_desc());
        if (position>0){
            tabLayout.getTabAt(position).select();
          //  persenter.getGoodsList(category.getId(),1,1000);
        }
    }

    @Override
    public void getGoodsListReturn(CategoryListBean result) {
        /*goodsListBeans.addAll(result.getData().getGoodsList());
        categroyAdapter.notifyDataSetChanged();*/
        categroyAdapter.refresh(result.getData().getGoodsList());
    }
}
