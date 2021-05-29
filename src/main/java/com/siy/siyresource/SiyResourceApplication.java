package com.siy.siyresource;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.siy.siyresource.common.SimpleListener;
import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
@EnableEurekaClient
@EnableFeignClients
public class SiyResourceApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication();
        //app.addListeners(new SimpleListener());
        app.run(SiyResourceApplication.class, args);
    }

    @Bean
    Hibernate5Module hibernate5Module() {
        return new Hibernate5Module();
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}