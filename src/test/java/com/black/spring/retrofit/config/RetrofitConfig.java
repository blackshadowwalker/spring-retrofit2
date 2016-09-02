package com.black.spring.retrofit.config;

import com.black.spring.retrofit.EnableRetrofit;
import com.black.spring.retrofit.RetrofitServiceScannerConfigurer;
import com.black.spring.retrofit.bean.ResponseDto;
import com.black.spring.retrofit.factory.AnyTypeCallAdapterFactory;
import com.black.spring.retrofit.factory.CustomCallAdapterFactory;
import com.black.spring.retrofit.interceptor.LogInterceptor;
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
    public RetrofitServiceScannerConfigurer config(@Qualifier("retrofitDefault") Retrofit defaultRetrofit) {
        RetrofitServiceScannerConfigurer configurer = new RetrofitServiceScannerConfigurer();
        configurer.setBasePackage("com.black.spring.retrofit.service");
        configurer.setRetrofit(defaultRetrofit);
        return configurer;
    }
}
