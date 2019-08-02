package com.dchoe.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.dchoe.portal"})
@EnableMongoRepositories("com.dchoe.portal.repositories")
public class PortalApplication {
	public static void main(String[] args) {
	    SpringApplication.run(PortalApplication.class, args);
	}
}
