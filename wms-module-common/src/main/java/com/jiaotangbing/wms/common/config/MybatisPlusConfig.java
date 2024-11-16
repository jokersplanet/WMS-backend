package com.jiaotangbing.wms.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author lwh
 * @create 2024-11-12 23:16
 * Mybatis Plus 配置文件
 */
@Configuration
@MapperScan("com.jiaotangbing.wms.common.domain.mapper")
public class MybatisPlusConfig {
}
