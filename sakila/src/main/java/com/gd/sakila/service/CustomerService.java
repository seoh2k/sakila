package com.gd.sakila.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.mapper.CustomerMapper;
import com.gd.sakila.vo.CustomerList;
import com.gd.sakila.vo.Page;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class CustomerService {
	@Autowired CustomerMapper customerMapper;
	
	public List<CustomerList> getCustomerList(Map<String, Object> map){
		log.debug("▶▶▶▶▶ getCustomerList() map: "+map);
		
		return customerMapper.selectCustomerList(map);
	}
	
	public int getCustomerTotal(String searchWord) {
		log.debug("▶▶▶▶▶ getCustomerTotal() searchWord: "+searchWord);
		
		return customerMapper.selectCustomerTotal(searchWord);
	}
	
	public void modifyCustomerActiveByScheduler() {
		log.debug("▶▶▶▶▶ modifyCustomerActiveByScheduler() 실행");
		int row = customerMapper.updateCustomerActiveByScheduler();
		log.debug("▶▶▶▶▶ 휴면고객 처리 행수: " + row);
		
	}
}
