package com.github.blackshadowwalker.spring.retrofit.config;

import com.github.blackshadowwalker.spring.retrofit.EnableRetrofit;
import com.github.blackshadowwalker.spring.retrofit.RetrofitServiceScannerConfigurer;
import com.github.blackshadowwalker.spring.retrofit.bean.ResponseDto;
import com.github.blackshadowwalker.spring.retrofit.factory.AnyTypeCallAdapterFactory;
import com.github.blackshadowwalker.spring.retrofit.factory.CustomCallAdapterFactory;
import com.github.blackshadowwalker.spring.retrofit.interceptor.LogInterceptor;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by ASUS on 9/1 0001.
 */
@Configuration
@EnableRetrofit
public class RetrofitConfig {

    @Bean
    public OkHttpClient okhttpClient(LogInterceptor logInterceptor) {
        return new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .addInterceptor(logInterceptor)
                .build();
    }

    @Bean
    public Retrofit retrofitDefault(OkHttpClient okHttpClient, CustomCallAdapterFactory respCall, AnyTypeCallAdapterFactory anyTypeCall) {
        return new Retrofit.Builder()
                .baseUrl("http://127.0.0.1:8081")
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(respCall)
                .addCallAdapterFactory(anyTypeCall)
                .client(okHttpClient)
                .build();
    }

    @Bean
    public Retrofit retrofit8082(OkHttpClient okHttpClient, CustomCallAdapterFactory respCall, AnyTypeCallAdapterFactory anyTypeCall) {
        return new Retrofit.Builder()
                .baseUrl("http://127.0.0.1:8082")
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(respCall)
                .addCallAdapterFactory(anyTypeCall)
                .client(okHttpClient)
                .build();
    }

    @Bean
    public CustomCallAdapterFactory responseDtoCallAdapterFactory() {
        return CustomCallAdapterFactory.create(ResponseDto.class);
    }

    @Bean
    public AnyTypeCallAdapterFactory anyTypeCallAdapterFactory() {
        return AnyTypeCallAdapterFactory.create(ResponseDto.class);
    }

    @Bean
    public RetrofitServiceScannerConfigurer retrofitServiceScannerConfigurer(@Qualifier("retrofitDefault") Retrofit defaultRetrofit) {
        RetrofitServiceScannerConfigurer configurer = new RetrofitServiceScannerConfigurer();
        configurer.setBasePackage("com.github.blackshadowwalker.spring.retrofit");
        configurer.setRetrofit(defaultRetrofit);
        return configurer;
    }
}
