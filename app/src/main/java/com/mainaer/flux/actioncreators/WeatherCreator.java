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
package com.mainaer.flux.actioncreators;

import com.mainaer.flux.api.WeatherService;
import com.mainaer.flux.comment.CallBack;
import com.mainaer.flux.comment.action.ActionCreator;
import com.mainaer.flux.comment.dispatcher.Dispatcher;
import com.mainaer.flux.model.Weather;

/**
 * 类/接口描述
 *
 * @author wangjian
 * @date 2016/5/5 .
 */
public class WeatherCreator extends ActionCreator {

    WeatherService service;

    public WeatherCreator(Dispatcher dispatcher) {
        super(dispatcher);
        service = createService(WeatherService.class);
    }

    public void loadData(String location) {
        //http://api.map.baidu.com/telematics/v3/weather?location=%E5%B9%BF%E5%B7%9E&output=json&ak=FK9mkfdQsloEngodbFl4FeY3
        // 请求数据，并添加自定义回调
        service.getWeather(location, "json", "FK9mkfdQsloEngodbFl4FeY3").enqueue(new CallBack<Weather>(this));
    }
}
