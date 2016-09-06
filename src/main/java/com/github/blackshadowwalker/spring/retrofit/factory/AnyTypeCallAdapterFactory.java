package com.github.blackshadowwalker.spring.retrofit.factory;

import com.github.blackshadowwalker.spring.retrofit.exception.RetrofitRpcInvockException;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

/**
 * Author : BlackShadowWalker
 * Date   : 2016-09-02
 */
public class AnyTypeCallAdapterFactory extends CallAdapter.Factory {

    Set<Class<?>> exclude = new HashSet<Class<?>>();

    public AnyTypeCallAdapterFactory() {
        exclude.add(Call.class);
    }

    public static AnyTypeCallAdapterFactory create(Class<?>... classes) {
        AnyTypeCallAdapterFactory f = new AnyTypeCallAdapterFactory();
        for (Class<?> cls : classes) {
            f.exclude.add(cls);
        }
        return f;
    }

    public class AnyTypeCallAdapter<T> implements CallAdapter<T> {
        Type returnType;

        AnyTypeCallAdapter(Type returnType) {
            this.returnType = returnType;
        }

        @Override
        public Type responseType() {
            return returnType;
        }

        @Override
        public T adapt(Call call) {
            try {
                Response<T> resp = call.execute();
                return resp.body();
            } catch (IOException e) {
                throw new RetrofitRpcInvockException(e);
            }
        }
    }

    @Override
    public CallAdapter<?> get(final Type returnType, Annotation[] annotations, Retrofit retrofit) {
        Class<?> cls = getRawType(returnType);
        if (exclude.contains(cls)) {
            return null;
        }
        return new AnyTypeCallAdapter<Type>(returnType);
    }

}
