package com.dchoe.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.dchoe.portal.repository.portalRepository;
import com.dchoe.portal.model.User;

@SpringBootApplication
public class PortalApplication {
	@Autowired
	portalRepository portalRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(PortalApplication.class, args);
		
		portalRepository.save(new User(1, "dchoe", "Danna", "Choe", "devdanchoe@gmail.com"));
		portalRepository.save(new User(2, "alma", "Alice", "Ma", "alicema@gmail.com"));
		
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (User user : portalRepository.findAll()) {
			System.out.println(user);
		}
		System.out.println();
		
	}
}
