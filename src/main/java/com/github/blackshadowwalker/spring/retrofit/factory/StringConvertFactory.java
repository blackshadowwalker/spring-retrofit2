package com.github.blackshadowwalker.spring.retrofit.factory;

import com.github.blackshadowwalker.spring.retrofit.exception.RetrofitRpcInvockException;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.*;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import static com.google.gson.internal.$Gson$Types.getRawType;

/**
 * Created by hoopa on 2017/5/20.
 */
public class StringConvertFactory extends Converter.Factory {

    public static StringConvertFactory create() {
        StringConvertFactory f = new StringConvertFactory();
        return f;
    }

    public Converter<ResponseBody, String> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (String.class == type) {
            return StringConverter.INSTANCE;
        }
        return null;
    }

    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return null;
    }

    public Converter<?, String> stringConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return null;
    }

}
