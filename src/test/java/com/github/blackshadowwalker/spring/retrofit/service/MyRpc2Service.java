package com.github.blackshadowwalker.spring.retrofit.service;

import com.github.blackshadowwalker.spring.retrofit.RetrofitService;
import com.github.blackshadowwalker.spring.retrofit.bean.ResponseDto;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ASUS on 9/2 0002.
 */
@RetrofitService(retrofit = "retrofit8082")
public interface MyRpc2Service {

    @GET("/api/hello")
    Call<ResponseDto<String>> hello(@Query("name") String name);

}
