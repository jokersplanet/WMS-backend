package com.jiaotangbing.wms.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author lwh
 * @create 2024-11-12 22:26
 */
@Configuration
@EnableSwagger2WebMvc
@Profile("dev") // 只在 dev 环境中开启
public class Knife4jAdminConfig {

    @Bean("adminApi")
    public Docket createApiDoc() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInfo())
                // 分组名称
                .groupName("Admin 后台接口")
                .select()
                // 这里指定 Controller 扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.jiaotangbing.wms.admin.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    /**
     * 构建 API 信息
     * @return
     */
    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                .title("wms 仓库管理系统 Admin 后台接口文档") // 标题
                .description("wms 是一款仓库管理系统。") // 描述
                .version("1.0") // 版本号
                .build();
    }
}
