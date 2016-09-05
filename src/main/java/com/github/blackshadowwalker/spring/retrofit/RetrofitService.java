package com.github.blackshadowwalker.spring.retrofit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author : BlackShadowWalker
 * @Date : 2016-09-02
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RetrofitService {

    /**
     * Retrofit name, if empty use default from {@link RetrofitServiceScannerConfigurer#retrofit}
     * @return
     */
    String retrofit() default "";

}
