package com.black.spring.retrofit.service;

import com.black.spring.retrofit.RetrofitService;
import com.black.spring.retrofit.bean.Page;
import com.black.spring.retrofit.bean.ResponseDto;
import com.black.spring.retrofit.bean.User;
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

}
