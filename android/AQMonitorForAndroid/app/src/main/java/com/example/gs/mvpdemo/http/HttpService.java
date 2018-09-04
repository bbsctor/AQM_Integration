package com.example.gs.mvpdemo.http;


import com.example.gs.mvpdemo.base.BaseHttpResult;
import com.example.gs.mvpdemo.bean.HistoryData;
import com.example.gs.mvpdemo.bean.PageUtils;
import com.example.gs.mvpdemo.bean.TokenWrapper;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by GaoSheng on 2016/9/13.
 * 网络请求的接口都在这里
 */

public interface HttpService {
    //登录接口
    @FormUrlEncoded
    @POST("sys/appLogin")
    Observable<BaseHttpResult<TokenWrapper>> login(@Field("username") String username, @Field
            ("password") String pwd);

    @POST("history/historydata/list")
    Observable<BaseHttpResult<PageUtils>> getHistoryData();
}
