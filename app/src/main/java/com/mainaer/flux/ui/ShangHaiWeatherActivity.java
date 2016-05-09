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
package com.mainaer.flux.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.mainaer.flux.actioncreators.WeatherCreator;
import com.mainaer.flux.comment.dispatcher.Dispatcher;
import com.mainaer.flux.comment.event.LoadFailEvent;
import com.mainaer.flux.comment.event.LoadSuccessEvent;
import com.mainaer.flux.model.Weather;
import com.mainaer.flux.stores.WeatherStore;
import com.mainaer.retrofit_flux_demo.R;

import org.greenrobot.eventbus.Subscribe;

/**
 * 类/接口描述
 *
 * @author wangjian
 * @date 2016/5/6 .
 */
public class ShangHaiWeatherActivity extends AppCompatActivity {


    TextView textView;

    private WeatherCreator weatherCreator;
    private Dispatcher dispatcher;
    private WeatherStore weatherStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main2);

        textView = (TextView) findViewById(R.id.tv_text);

        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        dispatcher.register(this);
        dispatcher.register(weatherStore);
    }

    @Override
    protected void onPause() {
        super.onPause();
        dispatcher.unRegister(this);
        dispatcher.unRegister(weatherStore);
    }


    private void initView() {
        dispatcher = Dispatcher.getInstance();
        weatherCreator = new WeatherCreator(dispatcher);
        weatherStore = new WeatherStore(dispatcher);

        loadData();
    }

    private void loadData() {
        textView.setText("");
        weatherCreator.loadData("上海");
    }

    @Subscribe
    public void onLoadSuccess(LoadSuccessEvent event) {
        Weather weather = weatherStore.getWeather();
        textView.setText(weather.status + "\n" + weather.date + "\n" +
            weather.results.get(0).currentCity + "\n" + weather.results.get(0).pm25 + "\n" +
            weather.results.get(0).weather_data.get(0).dayPictureUrl);
    }

    @Subscribe
    public void onLoadFailure(LoadFailEvent event) {
        Toast.makeText(this, weatherStore.getErrorInfo(), Toast.LENGTH_SHORT).show();
    }

    public static void go(Context context) {
        Intent intent = new Intent(context, ShangHaiWeatherActivity.class);
        context.startActivity(intent);
    }

}
