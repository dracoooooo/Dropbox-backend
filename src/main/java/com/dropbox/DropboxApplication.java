package com.dropbox;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com/dropbox/mapper")
@SpringBootApplication
public class DropboxApplication {

	public static void main(String[] args) {
		SpringApplication.run(DropboxApplication.class, args);
	}

}
