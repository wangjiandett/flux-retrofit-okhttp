/*
 * Copyright (C) 20015 MaiNaEr All rights reserved
 */
package com.mainaer.flux.comment.action;


import com.mainaer.flux.comment.okhttp.OKHttpManager;
import com.mainaer.flux.comment.dispatcher.Dispatcher;

import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * action 生成器，进行网络请求等操作，通过dispatcher发送封装有数据的action
 *
 * @author wangjian
 * @date 2016/1/27.
 */
public abstract class ActionCreator {

    protected Dispatcher mDispatcher;

    public ActionCreator(Dispatcher dispatcher) {
        this.mDispatcher = dispatcher;
    }

    public void dispatchAction(String type) {
        mDispatcher.dispatch(new Action(type));
    }

    public void dispatchAction(Action action) {
        mDispatcher.dispatch(action);
    }

    // ------------------------执行网络操作--------------------------------
    public static final String API_BASE_URL = "http://api.map.baidu.com/telematics/v3/";

    //http://api.map.baidu.com/telematics/v3/weather?location=%E5%98%89%E5%85%B4&output=json&ak=5slgyqGDENN7Sy7pw29IUvrZ

    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(API_BASE_URL).addConverterFactory(
        GsonConverterFactory.create());

    protected <S> S createService(Class<S> serviceClass) {
        OkHttpClient client = OKHttpManager.getOkHttpClient();
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }
}
