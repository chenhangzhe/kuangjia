package com.example.zuoye.model.apis;




import com.example.zuoye.model.bean.CatalogListBean;
import com.example.zuoye.model.bean.CatalogTabBean;
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


}
