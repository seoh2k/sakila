package com.gd.sakila;

import javax.servlet.annotation.WebFilter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ServletComponentScan //  필터를 사용하기 위해서, 서블릿도 스캔할 수 있게 하기위해서
@EnableScheduling
public class SakilaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SakilaApplication.class, args);
		//@Controller, @Mapper, @Service,...
	}

}
