package com.SpringSecurityApp.SpringSecurityApp;

import com.SpringSecurityApp.SpringSecurityApp.entities.User;
import com.SpringSecurityApp.SpringSecurityApp.services.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringSecurityAppApplicationTests {

    @Autowired
    private JwtService jwtService;

	@Test
	void contextLoads() {
        User user= new User(4L,"sumanta@gmail.com","1234","Sumanta");
        String token= jwtService.generateToken(user);

        System.out.println(token);
        Long id=jwtService.getUserIdFromToken(token);
        System.out.println(id);
	}

}
