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
package com.mainaer.flux.model;

import java.util.List;

/**
 * 类/接口描述
 *
 * @author wangjian
 * @date 2016/5/5 .
 */
public class Weather {
    
    public int error;
    public String status;
    public String date;
    
    public List<ResultsEntity> results;

    public static class ResultsEntity {
        public String currentCity;
        public String pm25;

        public List<IndexEntity> index;
        public List<WeatherDataEntity> weather_data;
        
        public static class IndexEntity {
            public String title;
            public String zs;
            public String tipt;
            public String des;
        }
        
        public static class WeatherDataEntity {
            public String date;
            public String dayPictureUrl;
            public String nightPictureUrl;
            public String weather;
            public String wind;
            public String temperature;
        }
    }
}
