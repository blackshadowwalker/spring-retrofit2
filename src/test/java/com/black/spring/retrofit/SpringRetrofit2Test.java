package com.black.spring.retrofit;

import com.black.spring.retrofit.bean.Page;
import com.black.spring.retrofit.bean.ResponseDto;
import com.black.spring.retrofit.bean.User;
import com.black.spring.retrofit.mvc.Server;
import com.black.spring.retrofit.service.MyRpc2Service;
import com.black.spring.retrofit.service.MyRpcService;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import retrofit2.Call;
import retrofit2.Response;

import java.util.List;

/**
 * Created by ASUS on 9/2 0002.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class SpringRetrofit2Test {
    private static Logger log = LoggerFactory.getLogger(Server.class);

    @Autowired
    MyRpcService myRpcService;

    @Autowired
    MyRpc2Service myRpc2Service;

    Server server;

    @Before
    public void init() throws Exception {
        server = new Server();
        server.start();
    }

    @Test
    public void testRetrofitRpcCall() throws Exception {
        Response<ResponseDto<Page<List<User>>>> response = myRpcService.queryUserList(10, 1).execute();
        Assert.assertTrue(response.message(), response.isSuccessful());
        ResponseDto<Page<List<User>>> resp = response.body();
        Assert.assertNotNull(resp);
        Assert.assertEquals(resp.getMsg(), 200, resp.getCode());
        Assert.assertNotNull(resp.getData());
        Assert.assertNotNull(resp.getData().getResult());
        List<User> userList = resp.getData().getResult();
        Assert.assertEquals(2, userList.size());
        Assert.assertEquals(1L, userList.get(0).getId().longValue());
        Assert.assertEquals(2L, userList.get(1).getId().longValue());
    }

    @Test
    public void testResponseDtoCallAdapter() {
        ResponseDto<Page<List<User>>> resp = myRpcService.queryUserList2(10, 1);
        Assert.assertNotNull(resp);
        Assert.assertEquals(resp.getMsg(), 200, resp.getCode());
        Assert.assertNotNull(resp.getData());
        Assert.assertNotNull(resp.getData().getResult());
        List<User> userList = resp.getData().getResult();
        Assert.assertEquals(2, userList.size());
        Assert.assertEquals(1L, userList.get(0).getId().longValue());
        Assert.assertEquals(2L, userList.get(1).getId().longValue());
    }

    @Test
    public void testAnyCallAdapter() {
        Page<List<User>> page = myRpcService.queryUserList3(10, 1);
        List<User> userList = page.getResult();
        Assert.assertNotNull(userList);
        Assert.assertEquals(2, userList.size());
        Assert.assertEquals(1L, userList.get(0).getId().longValue());
        Assert.assertEquals(2L, userList.get(1).getId().longValue());
    }

    @Test
    public void testRetrofitRpc2Call() throws Exception {
        Response<ResponseDto<String>> response = myRpc2Service.hello("world").execute();
        Assert.assertTrue(response.message(), response.isSuccessful());
        ResponseDto<String> resp = response.body();
        Assert.assertNotNull(resp);
        Assert.assertEquals(resp.getMsg(), 200, resp.getCode());
        Assert.assertEquals("Hello world", resp.getData());
    }

    @After
    public void clean() throws Exception {
        server.stop();
    }
}
