package com.gd.sakila;

import javax.servlet.annotation.WebFilter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan // 필터를 사용하기 위해서
public class SakilaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SakilaApplication.class, args);
		//@Controller, @Mapper, @Service,...
	}

}
