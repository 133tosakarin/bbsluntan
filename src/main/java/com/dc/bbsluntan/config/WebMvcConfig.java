package com.dc.bbsluntan.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * @author DengChao
 */
@Slf4j
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {


    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("开始进行静态资源映射");
        registry.addResourceHandler("/back/**").addResourceLocations("classpath:/back/");
        registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
/*    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("过滤");

        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/front/")
                .addResourceLocations("classpath:/back/");

        registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/back/**").addResourceLocations("classpath:/back/");
        registry.addResourceHandler("/front/css/**").addResourceLocations("classpath:/front/css/");
        registry.addResourceHandler("/front/js/**").addResourceLocations("classpath:/front/js/");
        registry.addResourceHandler("/front/js/jquery/**").addResourceLocations("classpath:/front/js/jquery");

        registry.addResourceHandler("/front/images/**").addResourceLocations("classpath:/front/s/images");
        registry.addResourceHandler("/front/node_modules/**").addResourceLocations("classpath:/front/js/node_modules");
        registry.addResourceHandler("/front/node_modules/bootstrap/**").
                addResourceLocations("classpath:/front/js/node_modules/bootstrap");
        registry.addResourceHandler("front/node_modules/build/**").addResourceLocations("classpath:/front/node_modules/build");
        registry.addResourceHandler("front/node_modules/speed/**").addResourceLocations("classpath:/front/node_modules/speed");
        registry.addResourceHandler("front/node_modules/build/src/**").addResourceLocations("classpath:/front/node_modules/src");
        registry.addResourceHandler("/front/node_modules/font-awesome/**").
                addResourceLocations("classpath:/front/js/node_modules/font-awesome");
        registry.addResourceHandler("/front/node_modules/jquery/**").addResourceLocations("classpath:/front/js/node_modules/jquery");
        registry.addResourceHandler("/front/picture/**").addResourceLocations("classpath:/front/picture/");
        registry.addResourceHandler("/front/fonts/**").addResourceLocations("classpath:/front/fonts/");
        WebMvcConfigurer.super.addResourceHandlers(registry);

    }*/

    /*@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor()
                .addPathPatterns("/**")
                .excludePathPatterns("/login/**")
                //3、允许访问localhost:8080/static/**，使得这个路径不会被拦截器拦截
                .excludePathPatterns("/static/**");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //1、添加资源处理器路径 即每次访问静态资源都得添加"/static/",例如localhost:8080/static/j1.jpg
        //若registry.addResourceHandler("/s/**") 则必须访问localhost:8080/s/j1.jpg
        registry.addResourceHandler("/static/**")
                //2、添加了资源处理器路径后对应的映射资源路径
                .addResourceLocations("classpath:/static/");
    }*/
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        //log.info("扩展消息转换器...");
        //创建消息转换器对象
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        //设置对象转换器，底层使用Jackson将Java对象转为json
        messageConverter.setObjectMapper(new JacksonObjectMapper());
        //将上面的消息转换器对象追加到mvc框架的转换器集合中
        //转换器是有优先级顺序的，这里我们把自己定义的消息转换器设置为第一优先级，所以会优先使用我们的转换器来进行相关数据进行转换，如果我们的转换器没有匹配到相应的数据来转换，那么就会去寻找第二个优先级的转换器，以此类推
        converters.add(0, messageConverter);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //是否发送Cookie
                .allowCredentials(true)
                //放行哪些原始域
                .allowedOriginPatterns("*")
                .allowedMethods(new String[]{"GET", "POST", "PUT", "DELETE"})
                .allowedHeaders("*")
                .exposedHeaders("*");

    }
}
