/*
 * Copyright (C) 20015 MaiNaEr All rights reserved
 */
package com.mainaer.flux.comment.okhttp;

import okhttp3.Cache;

/**
 * OKHttpConfig 自定义配置
 *
 * @author wangjian
 * @date 2016/3/23.
 */
public class OKHttpConfig {

    private long mConnectTimeout;
    private long mReadTimeout;
    private long mWriteTimeout;
    private long mCacheTime;
    private Cache mCache;

    private OKHttpConfig(Builder builder) {
        this.mConnectTimeout = builder.connectTimeout;
        this.mWriteTimeout = builder.writeTimeout;
        this.mReadTimeout = builder.readTimeout;
        this.mCacheTime = builder.cacheTime;
        this.mCache = builder.cache;
    }

    public long getConnectTimeout() {
        return mConnectTimeout;
    }

    public long getReadTimeout() {
        return mReadTimeout;
    }

    public long getWriteTimeout() {
        return mWriteTimeout;
    }

    public long getCacheTime() {
        return mCacheTime;
    }

    public Cache getCache() {
        return mCache;
    }

    public static class Builder {
        private long connectTimeout;
        private long readTimeout;
        private long writeTimeout;
        private long cacheTime;
        private Cache cache;

        /**
         * 设置连接超时时间
         *
         * @param connectTimeoutSeconds unit seconds
         * @return
         */
        public Builder setConnectTimeout(long connectTimeoutSeconds) {
            this.connectTimeout = connectTimeoutSeconds;
            return this;
        }

        /**
         * 设置读取超时时间
         *
         * @param readTimeoutSeconds unit seconds
         * @return
         */
        public Builder setReadTimeout(long readTimeoutSeconds) {
            this.readTimeout = readTimeoutSeconds;
            return this;
        }

        /**
         * 设置写入超时时间
         *
         * @param writeTimeoutSeconds unit seconds
         * @return
         */
        public Builder setWriteTimeout(long writeTimeoutSeconds) {
            this.writeTimeout = writeTimeoutSeconds;
            return this;
        }

        /**
         * 设置缓存时间
         *
         * @param cacheTimeSeconds unit seconds
         * @return
         */
        public Builder setCacheTime(long cacheTimeSeconds) {
            this.cacheTime = cacheTimeSeconds;
            return this;
        }

        /**
         * 设置缓存
         *
         * @param cache
         * @return
         */
        public Builder setCache(Cache cache) {
            this.cache = cache;
            return this;
        }

        public OKHttpConfig build() {
            return new OKHttpConfig(this);
        }
    }
}
