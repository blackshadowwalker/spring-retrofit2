package com.github.blackshadowwalker.spring.retrofit.service;

import com.github.blackshadowwalker.spring.retrofit.RetrofitService;
import com.github.blackshadowwalker.spring.retrofit.bean.Page;
import com.github.blackshadowwalker.spring.retrofit.bean.ResponseDto;
import com.github.blackshadowwalker.spring.retrofit.bean.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

/**
 * Created by ASUS on 9/2 0002.
 */
@RetrofitService
public interface MyRpcService {

    @GET("/api/users")
    Call<ResponseDto<Page<List<User>>>> queryUserList(@Query("pageSize") Integer pageSize, @Query("pageNum") Integer pageNum);

    @GET("/api/users")
    ResponseDto<Page<List<User>>> queryUserList2(@Query("pageSize") Integer pageSize, @Query("pageNum") Integer pageNum);

    @GET("/api/users/page")
    Page<List<User>> queryUserList3(@Query("pageSize") Integer pageSize, @Query("pageNum") Integer pageNum);

}
