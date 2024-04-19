package com.gx.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class OauthDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(OauthDemoApplication.class, args);
	}

}
