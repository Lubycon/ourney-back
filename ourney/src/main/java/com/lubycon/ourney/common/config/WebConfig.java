package com.lubycon.ourney.common.config;

import com.lubycon.ourney.common.Constants;
import com.lubycon.ourney.common.config.interceptor.JwtAuthInterceptor;
import com.lubycon.ourney.common.config.interceptor.UserArgumentResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final JwtAuthInterceptor jwtAuthInterceptor;
    private final UserArgumentResolver userArgumentResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(jwtAuthInterceptor)
                .addPathPatterns("/trips**","/users/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(userArgumentResolver);
    }

    // Swagger 허용
    @Override public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "POST")
                .exposedHeaders(Constants.JWT_ACCESS_TOKEN,Constants.JWT_REFRESH_TOKEN)
                .maxAge(3000);
    }
}
