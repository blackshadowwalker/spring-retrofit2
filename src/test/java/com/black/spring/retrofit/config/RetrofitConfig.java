package com.black.spring.retrofit.config;

import com.black.spring.retrofit.EnableRetrofit;
import com.black.spring.retrofit.RetrofitServiceScannerConfigurer;
import com.black.spring.retrofit.interceptor.LogInterceptor;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
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
    public OkHttpClient okhttpClient(LogInterceptor logInterceptor){
        return new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .addInterceptor(logInterceptor)
                .build();
    }

    @Bean
    public Retrofit retrofitDefault(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl("http://127.0.0.1:8081")
                .addConverterFactory(FastJsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Bean
    public Retrofit retrofit8082(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl("http://127.0.0.1:8082")
                .addConverterFactory(FastJsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Bean
    public RetrofitServiceScannerConfigurer config(@Qualifier("retrofitDefault") Retrofit defaultRetrofit) {
        RetrofitServiceScannerConfigurer configurer = new RetrofitServiceScannerConfigurer();
        configurer.setBasePackage("com.black.spring.retrofit.service");
        configurer.setRetrofit(defaultRetrofit);
        return configurer;
    }
}
