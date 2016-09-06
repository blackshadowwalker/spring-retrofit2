package com.github.blackshadowwalker.spring.retrofit;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Author : BlackShadowWalker
 * Date   : 2016-09-02
 */
@Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(value = { java.lang.annotation.ElementType.TYPE })
@Documented
@Import({RetrofitConfigure.class})
public @interface EnableRetrofit {
}
