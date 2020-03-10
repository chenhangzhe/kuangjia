package com.example.zuoye.model.apis;

import com.example.zuoye.model.bean.BannerInfoTopBean;
import com.example.zuoye.model.bean.BrandListBean;
import com.example.zuoye.model.bean.BrandListDetailBean;
import com.example.zuoye.model.bean.BrandTopImgBean;
import com.example.zuoye.model.bean.CardListBean;
import com.example.zuoye.model.bean.NewDataBean;
import com.example.zuoye.model.bean.NewHotCardListBean;
import com.example.zuoye.model.bean.RegisterBean;
import com.example.zuoye.model.bean.ShouYeBean;
import com.example.zuoye.model.bean.UserBean;
import com.example.zuoye.model.bean.ZhuantiBean;


import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ShouyeApi {
    @GET("index")
    Flowable<ShouYeBean> getShouyeData();

    @GET("api/topic/list")
    Flowable<ZhuantiBean> getZhuantiData(@Query("page") int page,@Query("size") int size);
    //制造商列表页列表
    @GET("api/brand/list")
    Flowable<BrandListBean> getBrandList(@Query("page") int page, @Query("size") int size);

    //制造商详情页顶部图片
    @GET("api/brand/detail")
    Flowable<BrandTopImgBean> getBrandTopImg(@Query("id") int id);

    //制造商详情页列表数据
    @GET("api/goods/list")
    Flowable<BrandListDetailBean> getBrandListDetail(@Query("brandId") int id, @Query("page")int page, @Query("size")int size);

    //新品 人气商品顶部数据
    @GET("api/goods/hot")
    Flowable<BannerInfoTopBean> getBannerInfoTop();

    //新品商品列表数据
    @GET("api/goods/list")
    Flowable<NewDataBean> getNewData(@Query("isNew") int isNew, @Query("page") int page, @Query("size")int size,
                                     @Query("order") String order, @Query("sort") String sort, @Query("categoryId") int id);

    //人气商品列表数据
    @GET("api/goods/list")
    Flowable<NewDataBean> getHotData(@Query("isHot") int isHot,@Query("page") int page,@Query("size")int size,
                                     @Query("order") String order,@Query("sort") String sort,@Query("categoryId") int id);

    //登录
    @POST("api/auth/login")
    Flowable<UserBean> login(@Field("nickname") String nickname, @Field("password") String password);

    //注册
    @POST("api/auth/register")
    Flowable<RegisterBean> register(@Field("nickname") String nickname, @Field("password") String password);
    //获取购物车数据
    @GET("api/cart/index")
    Flowable<CardListBean> getCardList();

    //添加到购物车
    @POST("api/cart/add")
    @FormUrlEncoded
    Flowable<NewHotCardListBean> cardList(@Field("goodsId") int  goodsId, @Field("number") int number, @Field("productId") int productId);
}

