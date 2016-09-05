package com.github.blackshadowwalker.spring.retrofit;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;
import retrofit2.Retrofit;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Set;

/**
 * @Author : BlackShadowWalker
 * @Date : 2016-09-02
 */
public class RetrofitServiceScanner extends ClassPathBeanDefinitionScanner {

    private RetrofitServiceFactoryBean retrofitServiceFactoryBean = new RetrofitServiceFactoryBean();

    private Class<? extends Annotation> annotationClass;//for Filter
    private Class<?> markerInterface;

    private String retrofit;

    public RetrofitServiceScanner(BeanDefinitionRegistry registry) {
        super(registry, false);
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
        if (beanDefinitions.isEmpty()) {
            logger.warn("No Retrofit Service was found in '" + Arrays.toString(basePackages) + "' package. Please check your configuration.");
        } else {
            processBeanDefinitions(beanDefinitions);
        }
        return beanDefinitions;
    }

    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
    }

    private void processBeanDefinitions(Set<BeanDefinitionHolder> beanDefinitions) {
        ScannedGenericBeanDefinition definition;
        for (BeanDefinitionHolder holder : beanDefinitions) {
            definition = (ScannedGenericBeanDefinition) holder.getBeanDefinition();
            AnnotationAttributes annotationAttributes = (AnnotationAttributes)definition.getMetadata().getAnnotationAttributes(RetrofitService.class.getName(), false);

            if (logger.isDebugEnabled()) {
                logger.debug("Creating MapperFactoryBean with name '" + holder.getBeanName()
                        + "' and '" + definition.getBeanClassName() + "' mapperInterface");
            }

            definition.getPropertyValues().add("serviceInterface", definition.getBeanClassName());
            definition.setBeanClass(this.retrofitServiceFactoryBean.getClass());

            String retrofitName = annotationAttributes.getString("retrofit");
            if (retrofitName == null || retrofitName.isEmpty()){
                definition.getPropertyValues().add("retrofit", new RuntimeBeanReference(retrofit));//default retrofit
            } else {
                definition.getPropertyValues().add("retrofit", new RuntimeBeanReference(retrofitName));
            }
        }
    }

    public void registerFilters() {
        // if specified, use the given annotation and / or marker interface
        if (this.annotationClass != null) {
            addIncludeFilter(new AnnotationTypeFilter(this.annotationClass));
        }
        // override AssignableTypeFilter to ignore matches on the actual marker interface
        if (this.markerInterface != null) {
            addIncludeFilter(new AssignableTypeFilter(this.markerInterface) {
                @Override
                protected boolean matchClassName(String className) {
                    return false;
                }
            });
        }
    }

    public void setRetrofitServiceFactoryBean(RetrofitServiceFactoryBean retrofitServiceFactoryBean) {
        this.retrofitServiceFactoryBean = retrofitServiceFactoryBean;
    }

    public void setRetrofit(String retrofit) {
        this.retrofit = retrofit;
    }

    public void setAnnotationClass(Class<? extends Annotation> annotationClass) {
        this.annotationClass = annotationClass;
    }

    public void setMarkerInterface(Class<?> markerInterface) {
        this.markerInterface = markerInterface;
    }
}
