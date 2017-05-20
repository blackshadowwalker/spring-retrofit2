package com.github.blackshadowwalker.spring.retrofit.factory;

import okhttp3.ResponseBody;
import retrofit2.Converter;

import java.io.IOException;

/**
 * Created by hoopa on 2017/5/20.
 */
public class StringConverter implements Converter<ResponseBody, String> {
    static final StringConverter INSTANCE = new StringConverter();

    @Override public String convert(ResponseBody value) throws IOException {
        return value.string();
    }
}
