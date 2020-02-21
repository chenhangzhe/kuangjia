package com.example.zuoye.model.apis;




import com.example.zuoye.model.bean.CatalogListBean;
import com.example.zuoye.model.bean.CatalogTabBean;
import com.example.zuoye.model.bean.CategoryListBean;
import com.example.zuoye.model.bean.CategoryTabBean;
import com.example.zuoye.model.bean.LoginBean;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MyApi {

    @POST("user/login")
    @FormUrlEncoded
    Flowable<LoginBean> login(@Field("username") String username, @Field("password") String password);

    //获取分类导航数据接口
    @GET("catalog/index")
    Flowable<CatalogTabBean> getCatalogTabData();

    //获取列表选中的数据
    @GET("catalog/current")
    Flowable<CatalogListBean> getCatalogList(@Query("id") int id);
    //获取分类Tab数据的接口goods/category { id: this.data.id }
    @GET("goods/category")
    Flowable<CategoryTabBean> getCategoryTab(@Query("id") int id);

    //商品分类列表数据goods/list{categoryId: that.data.id, page: that.data.page, size: that.data.size}
    @GET("goods/list")
    Flowable<CategoryListBean> getCategoryList(@Query("categoryId") int categoryId, @Query("page") int page, @Query("size") int size);


}
