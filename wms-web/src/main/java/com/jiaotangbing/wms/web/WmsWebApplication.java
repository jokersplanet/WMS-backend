package com.jiaotangbing.wms.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@ComponentScan({"com.jiaotangbing.wms.*"})
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WmsWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WmsWebApplication.class, args);
    }

}
