/*
 * Copyright (C) 20015 MaiNaEr All rights reserved
 */
package com.mainaer.flux.ui;

import android.app.Application;

import com.mainaer.flux.comment.okhttp.OKHttpConfig;
import com.mainaer.flux.comment.okhttp.OKHttpManager;

import java.io.File;

import okhttp3.Cache;

/**
 * 类/接口描述
 *
 * @author wangjian
 * @date 2016/3/28.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 自定义缓存目录和大小
        File cacheFile = new File(getCacheDir(), "okcache");
        Cache cache = new Cache(cacheFile, 100 * 1024 * 1024);// 100mb

        // 程序初始化时，初始okhttp配置
        OKHttpConfig OKHttpConfig = new OKHttpConfig.Builder()
            .setConnectTimeout(10).setReadTimeout(10).setWriteTimeout(10).setCacheTime(1000).setCache(cache).build();
        OKHttpManager.init(this, OKHttpConfig);
    }
}
