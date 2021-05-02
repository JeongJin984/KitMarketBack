package com.siy.siyresource;

import com.siy.siyresource.common.SimpleListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SiyResourceApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication();
        //app.addListeners(new SimpleListener());
        app.run(SiyResourceApplication.class, args);
    }

}