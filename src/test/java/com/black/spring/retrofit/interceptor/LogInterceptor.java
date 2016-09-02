package com.black.spring.retrofit.interceptor;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by ASUS on 9/1 0001.
 */
@Component
public class LogInterceptor implements Interceptor {
    private static Logger log = LoggerFactory.getLogger(LogInterceptor.class);

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request req = chain.request();
        log.info("request {} - {}", req.method(), req.url().url());
        Response resp = chain.proceed(req);
        log.info("recevied response");
        return resp;
    }
}