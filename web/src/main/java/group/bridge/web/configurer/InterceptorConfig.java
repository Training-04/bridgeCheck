package group.bridge.web.configurer;

import group.bridge.web.interceptor.PageInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加一个拦截器，拦截以/test为前缀的 url路径
        registry.addInterceptor(new PageInterceptor()).addPathPatterns("/web/**");
    }

}
