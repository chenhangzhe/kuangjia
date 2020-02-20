package com.example.zuoye.fragment.classify;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zuoye.R;
import com.example.zuoye.base.BaseFragment;
import com.example.zuoye.interfaces.catalog.CatalogContract;
import com.example.zuoye.model.bean.CatalogItem;
import com.example.zuoye.model.bean.CatalogListBean;
import com.example.zuoye.model.bean.CatalogTabBean;
import com.example.zuoye.presenter.classify.CatalogPersenter;

import java.util.ArrayList;
import java.util.List;

import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.QTabView;
import q.rorbin.verticaltablayout.widget.TabView;

public class FenleiFragment extends BaseFragment<CatalogContract.View, CatalogContract.Persenter> implements CatalogContract.View, VerticalTabLayout.OnTabSelectedListener {
    private VerticalTabLayout tab;
    private TextView desc;
    private TextView title;
    private RecyclerView recyShop;
    private ImageView img;
    private ArrayList<String> titles;
    private ArrayList<CatalogItem> list;
    private ClassifyAdapter classifyAdapter;
    private List<CatalogTabBean.DataBean.CategoryListBean> categoryList;

    @Override
    protected int getLayout() {
        return R.layout.fragment_fenlei;
    }

    @Override
    protected void initView(View view) {

        tab = (VerticalTabLayout) view.findViewById(R.id.verticalTab);
        desc = (TextView) view.findViewById(R.id.desc);
        title = (TextView) view.findViewById(R.id.txt_title);
        img = (ImageView) view.findViewById(R.id.img);
        recyShop = (RecyclerView) view.findViewById(R.id.recy_classify);

        titles = new ArrayList<>();
        list = new ArrayList<>();

        classifyAdapter = new ClassifyAdapter(list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyShop.setLayoutManager(gridLayoutManager);
        recyShop.setAdapter(classifyAdapter);
        tab.addOnTabSelectedListener(this);
    }
    //创建竖向tablayout导航栏
    TabAdapter tabAdapter = new TabAdapter() {
        @Override
        public int getCount() {
            return titles.size();
        }

        @Override
        public ITabView.TabBadge getBadge(int position) {
            return null;
        }

        @Override
        public ITabView.TabIcon getIcon(int position) {
            return null;
        }

        @Override
        public ITabView.TabTitle getTitle(int position) {
            QTabView.TabTitle title = new QTabView.TabTitle.Builder()
                    .setContent(titles.get(position))//设置数据   也有设置字体颜色的方法
                    .build();
            return title;
        }

        @Override
        public int getBackground(int position) {
            return Color.parseColor("#D81B60");
        }
    };

    @Override
    protected void initData() {
        //加载数据
        persenter.getCatalogTabData();

    }

    @Override
    protected CatalogContract.Persenter createPersenter() {
        return new CatalogPersenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String err) {
        Toast.makeText(context, err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getCatalogTabDataReturn(CatalogTabBean result) {
        categoryList = result.getData().getCategoryList();
        titles.clear();
        CatalogTabBean.DataBean.CurrentCategoryBean category = result.getData().getCurrentCategory();
        //tab标题赋值
        for (CatalogTabBean.DataBean.CategoryListBean bean : categoryList) {
            titles.add(bean.getName());
        }
        tab.setTabAdapter(tabAdapter);
        refreshtoright(category.getBanner_url(),category.getFront_desc(),category.getName()+"分类");
        //清空集合数据
        list.clear();
        for (CatalogTabBean.DataBean.CurrentCategoryBean.SubCategoryListBean bean : result.getData().getCurrentCategory().getSubCategoryList()) {
            CatalogItem item = new CatalogItem();
            item.id = bean.getId();
            item.img = bean.getWap_banner_url();
            item.name = bean.getName();
            list.add(item);
        }
        classifyAdapter.notifyDataSetChanged();
    }


    @Override
    public void getCatalogListReturn(CatalogListBean result) {
        CatalogListBean.DataBean.CurrentCategoryBean category = result.getData().getCurrentCategory();
        refreshtoright(category.getBanner_url(),category.getFront_desc(),category.getName()+"分类");
        list.clear();
        for (CatalogListBean.DataBean.CurrentCategoryBean.SubCategoryListBean bean : result.getData().getCurrentCategory().getSubCategoryList()) {
            CatalogItem item = new CatalogItem();
            item.id = bean.getId();
            item.img = bean.getWap_banner_url();
            item.name = bean.getName();
            list.add(item);
        }
        classifyAdapter.notifyDataSetChanged();
    }
    //刷新右边界面
    private void refreshtoright(String img_url, String dsc, String titl) {
        if (!TextUtils.isEmpty(img_url)){
            Glide.with(context).load(img_url).into(img);
        }
        title.setText(titl);
        desc.setText(dsc);
    }

    @Override
    public void onTabSelected(TabView tab, int position) {
        if (categoryList != null){
            int id = categoryList.get(position).getId();
            persenter.getCatalogList(id);
        }
    }

    @Override
    public void onTabReselected(TabView tab, int position) {

    }
}
