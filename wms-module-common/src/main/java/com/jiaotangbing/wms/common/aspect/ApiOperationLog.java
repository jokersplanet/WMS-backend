package com.jiaotangbing.wms.common.aspect;

import java.lang.annotation.*;

/**
 * @author lwh
 * @create 2024-11-12 21:27
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface ApiOperationLog {

    /**
     * API 功能描述
     *
     * @return
     */
    String description() default "";
}
