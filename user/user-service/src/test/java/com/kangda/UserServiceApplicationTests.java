package com.kangda;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceApplicationTests {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Test
	public void contextLoads() {
		System.out.println("123");
		System.out.println("123456的加密密码："+passwordEncoder.encode("123456"));
		System.out.println("123");
	}

}
