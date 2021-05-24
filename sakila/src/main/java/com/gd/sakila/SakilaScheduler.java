package com.gd.sakila;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gd.sakila.service.CustomerService;

import jdk.internal.org.jline.utils.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SakilaScheduler {
	@Autowired CustomerService customerService;
	
	// Scheduled 메서드 void 반환, 매개변수 0개
	
	// 0 17 11: 11시 17분, 24: 24일, *: 매달, *: 요일은 상관없다
	@Scheduled(cron = "0 19 11 24 * *")
	public void modifyCustomerActive() {
		customerService.modifyCustomerActiveByScheduler();
		log.debug("▶▶▶▶▶ modifyCustomerActiveByScheduler 스케쥴러 실행 완료!");
	}
}
