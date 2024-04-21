
package com.ken.taroserver.config;

import com.ken.taroserver.interceptor.JwtTokenUserInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;
import java.util.List;

//@EnableSwagger2WebMvc
@Configuration
@Slf4j
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private JwtTokenUserInterceptor jwtTokenUserInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        log.info("開始註冊自定義攔截器...");
        registry.addInterceptor(jwtTokenUserInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/**/signup")
                .excludePathPatterns("/**/signin")
                .excludePathPatterns("/doc.html")
                .excludePathPatterns("/doc.html/**")
                .excludePathPatterns(
                    List.of(
                        "/swagger-ui/**",
                        "/doc.html/**",
                        "/swagger-resources/**",
                        "/webjars/**",
                        "/v3/**")
                );
    }

    @Bean
    public Docket docket1() {
        log.info("生成接口文檔");
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("Taro接口文檔")
                .description("taro social 接口文檔")
                .version("2.0")
                .build();
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("登入接口")
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ken.taroserver.controller"))
//                .apis(RequestHandlerSelectors.basePackage("com.ken"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("開始設置靜態資源映射...");
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


}
