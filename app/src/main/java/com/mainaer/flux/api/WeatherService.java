package com.mainaer.flux.api;


import com.mainaer.flux.model.Weather;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;


public interface WeatherService {

    // get 请求可使用如下方式
    // 在weather后面拼接请求参数
    @GET("weather")
    Call<Weather> getWeather(@Query("location") String location, @Query("output") String output,
                             @Query("ak") String ak);

    //=================================================================
    // 以下使用方式来自官网http://square.github.io/retrofit/
    // 直接请求接口
    @GET("weather/list")
    Call<Weather> getWeather();

    // 不用注解形式，手动拼接参数
    @GET("weather/list?sort=desc")
    Call<Weather> getWeathers();

    // 注解形式，替换{id}中的值来构建url
    @GET("weather/{id}/weathers")
    Call<List<Weather>> getWeather(@Path("id") int weatherId);

    // 注解形式，替换{id}中的值来构建url,并拼接请求参数
    @GET("weather/{id}/weathers")
    Call<List<Weather>> getWeather(@Path("id") int weatherId, @Query("sort") String sort);

    // 注解形式，替换{id}中的值来构建url,并拼接多个请求参数
    @GET("weather/{id}/weathers")
    Call<List<Weather>> getWeather(@Path("id") int weatherId, @QueryMap Map<String, String> options);

    //======================================================
    // post 请求方式如下

    // 以下是自定义请求体
    // 指定自定义请求体需要使用一个转换器，转换改造实例自定义body。如果没有添加转换器,只能使用RequestBodycan。
    @POST("Weather/new")
    Call<Weather> createUser(@Body Weather user);

    // forum表单提交方式，用@Field添加表单中的请求字段
    @FormUrlEncoded// 对请求url进行编码
    @POST("Weather/edit")
    Call<Weather> updateWeather(@Field("first_name") String first, @Field("last_name") String last);

    // forum表单提交方式，用@FieldMap添加多个请求字段类似于@QueryMap
    @FormUrlEncoded// 对请求url进行编码
    @POST("Weather/edit")
    Call<Weather> updateWeather(@FieldMap Map<String, String> options);


    //======================================================
    // PUT 多部分，分块请求方式如下

    // 多部分请求时使用@Part注释声明,同时我们也可以继承RequestBody实现我们自定义的requestbody
    @Multipart
    @PUT("Weather/photo")
    Call<Weather> updateWeather(@Part("photo") RequestBody photo, @Part("description") RequestBody description);

    // 多部分请求时使用@PartMap注释声明。
    @Multipart
    @PUT("Weather/photo")
    Call<Weather> updateWeathers(@PartMap Map<String, RequestBody> options);

    //======================================================
    // 定义的请求头使用@Headers注解
    // 每个请求的header不会相互覆盖，即使有同名的名称的请求也会添加到请求的request中去的。
    // 如果请求头中对应的值是null，此header就回被忽略了

    @Headers("Cache-Control: max-age=640000")// 自定义缓存时间
    @GET("widget/list")
    Call<List<Weather>> widgetList();

    // 添加多个请求头
    @Headers({
        "Accept: application/vnd.github.v3.full+json",
        "User-Agent: Retrofit-Sample-App"
    })
    @GET("users/{username}")
    Call<Weather> getUser(@Path("username") String username);

}
