package com.github.blackshadowwalker.spring.retrofit;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import retrofit2.Retrofit;

import java.lang.annotation.Annotation;

import static org.springframework.util.Assert.notNull;

/**
 * @Author : BlackShadowWalker
 * @Date : 2016-09-02
 */
public class RetrofitServiceScannerConfigurer implements BeanDefinitionRegistryPostProcessor, InitializingBean {

    private String basePackage;
    private Retrofit retrofit;

    private Class<? extends Annotation> annotationClass = RetrofitService.class;//for Filter
    private Class<?> markerInterface;//for Filter

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        notNull(basePackage, "Property 'basePackages' is required");
        notNull(retrofit, "Property 'retrofit' is required");
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        RetrofitServiceScanner scanner = new RetrofitServiceScanner(registry);
        scanner.setAnnotationClass(this.annotationClass);
        scanner.setMarkerInterface(this.markerInterface);
        scanner.setRetrofit(retrofit);
        scanner.registerFilters();
        scanner.scan(basePackage);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

}
