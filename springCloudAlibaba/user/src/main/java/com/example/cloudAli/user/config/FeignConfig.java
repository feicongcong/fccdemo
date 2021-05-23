package com.example.cloudAli.user.config;

//import com.tuling.mall.feigndemo.interceptor.FeignAuthRequestInterceptor;
import feign.Logger;
import feign.Request;
//import feign.codec.Decoder;
//import feign.codec.Encoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  // 全局配置
public class FeignConfig {
    /**
     * 日志级别
     * 通过源码可以看到日志等级有 4 种，分别是：
     * NONE：不输出日志。(默认)
     * BASIC：只输出请求方法的 URL 和响应的状态码以及接口执行的时间。
     * HEADERS：将 BASIC 信息和请求头信息输出。
     * FULL：输出完整的请求信息。
     *
     *
     * 在yml配置文件中执行 Client 的日志级别才能正常输出日志=DEBUG
     * @return
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * 使用Feign原生的注解配置,原生不支持springmvc,默认为SpringMvcContract
     * @return
     */
//    @Bean
//    public Contract feignContract() {
//        return new Contract.Default();
//    }

    /**
     * 开启Basic认证
     * @return
     */
//    @Bean
//    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
//        return new BasicAuthRequestInterceptor("fox","123456");
//    }

    /**
     * 自定义拦截器
     * @return
     */
//    @Bean
//    public FeignAuthRequestInterceptor feignAuthRequestInterceptor(){
//        return new FeignAuthRequestInterceptor();
//    }
//

    /**
     * 过 Options 可以配置连接超时时间和读取超时时间
     * @return
     */
    @Bean
    public Request.Options options() {
        return new Request.Options(5000, 5000);
    }
//
//    @Bean
//    public Decoder decoder() {
//        return new JacksonDecoder();
//    }
//    @Bean
//    public Encoder encoder() {
//        return new JacksonEncoder();
//    }

}