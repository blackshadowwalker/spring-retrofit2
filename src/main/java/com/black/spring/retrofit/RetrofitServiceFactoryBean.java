package com.black.spring.retrofit;

import org.springframework.beans.factory.FactoryBean;
import retrofit2.Retrofit;

/**
 * @Author : BlackShadowWalker
 * @Date : 2016-09-02
 */
public class RetrofitServiceFactoryBean<T> implements FactoryBean<T> {

    private Class<T> serviceInterface;
    private Retrofit retrofit;

    @Override
    public T getObject() throws Exception {
        return retrofit.create(serviceInterface);
    }

    @Override
    public Class<?> getObjectType() {
        return serviceInterface;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setServiceInterface(Class<T> serviceInterface) {
        this.serviceInterface = serviceInterface;
    }

    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }
}
