package com.mainaer.flux.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
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
 * @date 2016/3/28.
 */
public class MainActivity extends AppCompatActivity {

    TextView textView;

    private WeatherCreator weatherCreator;
    private Dispatcher dispatcher;
    private WeatherStore weatherStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
                Snackbar.make(view, "加载中...", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        textView = (TextView) findViewById(R.id.tv_text);

        // 获得分发器，并注册到creator和store中，做数据分发使用
        dispatcher = Dispatcher.getInstance();
        weatherCreator = new WeatherCreator(dispatcher);
        weatherStore = new WeatherStore(dispatcher);

    }

    @Override
    protected void onResume() {
        super.onResume();
        // 注册后才能调用界面中的onLoadSuccess
        dispatcher.register(this);
        // 注册后才能将数据分发到store中
        dispatcher.register(weatherStore);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // ===========此处必须要进行解除注册============
        dispatcher.unRegister(this);
        dispatcher.unRegister(weatherStore);
    }

    public void next(View view) {
        ShangHaiWeatherActivity.go(this);
    }

    private void loadData() {
        // 开始加载时可显示progressbar
        textView.setText("");
        weatherCreator.loadData("广州");
    }

    // 添加注解接受store通知
    @Subscribe
    public void onLoadSuccess(LoadSuccessEvent event) {
        // 加载成功时可隐藏progressbar
        Weather weather = weatherStore.getWeather();
        textView.setText(weather.status + "\n" + weather.date + "\n" +
            weather.results.get(0).currentCity + "\n" + weather.results.get(0).pm25 + "\n" +
            weather.results.get(0).weather_data.get(0).dayPictureUrl);
    }

    // 添加注解接受store通知
    @Subscribe
    public void onLoadFailure(LoadFailEvent event) {
        // 加载失败时可隐藏progressbar
        Toast.makeText(this, weatherStore.getErrorInfo(), Toast.LENGTH_SHORT).show();
    }
}
