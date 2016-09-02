package com.black.spring.retrofit.factory;

import com.black.spring.retrofit.exception.RetrofitRpcInvockException;
import retrofit2.*;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * @Author : BlackShadowWalker
 * @Date : 2016-09-02
 */
public class CustomCallAdapterFactory<T> extends CallAdapter.Factory {

    CustomCallAdapterFactory(){}

    Class<T> cls;

    public static <T> CustomCallAdapterFactory create(Class<T> cls) {
        CustomCallAdapterFactory f = new CustomCallAdapterFactory();
        f.cls = cls;
        return f;
    }

    @Override
    public CallAdapter<T> get(final Type returnType, Annotation[] annotations, Retrofit retrofit) {
        Class<?> c = getRawType(returnType);
        if ( c != cls) {
            return null;
        }

        return new CallAdapter<T>() {
            @Override
            public Type responseType() {
                return returnType;
            }

            @Override
            public <R> T adapt(Call<R> call) {
                try {
                    Response<R> resp = call.execute();
                    return (T)resp.body();
                } catch (IOException e) {
                    throw new RetrofitRpcInvockException(e);
                }
            }
        };
    }

}
