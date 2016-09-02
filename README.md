# spring-retrofit

![Version](https://img.shields.io/badge/Version-0.0.1-blue.svg)

![jdk    ](https://img.shields.io/badge/Jdk-1.7+-blue.svg)

![OKHttp3](https://img.shields.io/badge/OKHttp3-3.3.0-005555.svg)
![Retrofit](https://img.shields.io/badge/Retrofit-2.1.0-005555.svg)
![Spring ](https://img.shields.io/badge/Spring-4.2.5.RELEASE-blue.svg)

## Usage

maven
```xml
<dependency>
    <groupId>com.black.spring</groupId>
    <artifactId>spring-retrofit2</artifactId>
    <version>2.10.</version>
</dependency>
```

config

```java
@Configuration
@EnableRetrofit
public class RetrofitConfig {

    @Bean
    public RetrofitServiceScannerConfigurer config(Retrofit retrofit) {
        RetrofitServiceScannerConfigurer configurer = new RetrofitServiceScannerConfigurer();
        configurer.setBasePackage("com.black.spring.retrofit.service");
        configurer.setRetrofit(retrofit);
        return configurer;
    }

}
```

### Multi RPC Server

#### S1: Define Multi Retrofit

```
@Bean
public Retrofit retrofitDefault(OkHttpClient okHttpClient) {
    return new Retrofit.Builder()
            .baseUrl("http://127.0.0.1:8081")
            .addConverterFactory(FastJsonConverterFactory.create())
            .client(okHttpClient)
            .build();
}

@Bean
public Retrofit retrofit8082(OkHttpClient okHttpClient) {
    return new Retrofit.Builder()
            .baseUrl("http://127.0.0.1:8082")
            .addConverterFactory(FastJsonConverterFactory.create())
            .client(okHttpClient)
            .build();
}
```

#### S2: Define annotations at retrofit service

```
@RetrofitService(retrofit = "retrofit8082")
public interface MyRpcService {
    ...
}
```


## More

More Detail see the testCase.