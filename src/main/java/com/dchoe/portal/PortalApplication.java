package com.dchoe.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.dchoe.portal.repositories.PortalRepository;
import com.dchoe.portal.model.User;

@SpringBootApplication
public class PortalApplication {

	public static void main(String[] args) {

	    SpringApplication.run(PortalApplication.class, args);
	}
}
