/*
 * Copyright 2014-2016 Retrofit-flux-demo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mainaer.flux.comment;

import com.mainaer.flux.comment.action.Action;
import com.mainaer.flux.comment.action.ActionCreator;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 类/接口描述
 *
 * @author wangjian
 * @date 2016/5/5 .
 */
public class CallBack<T> implements Callback<T> {

    protected ActionCreator mActionCreator;

    public CallBack(ActionCreator creator) {
        this.mActionCreator = creator;
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onFail(t.getMessage());
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        // 请求成功，code==200才是我们实际需要的结果
        if (response.isSuccessful() && response.code() == 200) {
            T data = response.body();
            if (data != null) {
                onSuccess(data);
            }
            else {
                ResponseBody responseBody = response.errorBody();
                if (responseBody != null) {
                    try {
                        onFail(responseBody.string());
                    } catch (IOException e) {
                        onFail(e.getMessage());
                        e.printStackTrace();
                    }
                }
                else {
                    onFail("responseBody is null");
                }
            }
        }
        else {
            onFail("response is failure");
        }
    }

    /**
     * 请求成功将数据分发到store中处理
     *
     * @param response
     */
    protected void onSuccess(T response) {
        mActionCreator.dispatchAction(new Action(Action.ACTION_LOADING_SUCCESS, response, null));
    }

    /**
     * 请求失败，将错误信息分发到store中处理
     *
     * @param error
     */
    protected void onFail(String error) {
        mActionCreator.dispatchAction(new Action(Action.ACTION_LOADING_FAIL, null, error));
    }
}
