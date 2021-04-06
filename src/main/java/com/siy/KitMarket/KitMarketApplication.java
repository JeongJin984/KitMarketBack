package com.siy.KitMarket;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.siy.KitMarket.common.SimpleListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KitMarketApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication();
		//springApplication.addListeners(new SimpleListener());
		springApplication.run(KitMarketApplication.class, args);
	}

	@Bean
	Hibernate5Module hibernate5Module() {
		return new Hibernate5Module();
	}
}
