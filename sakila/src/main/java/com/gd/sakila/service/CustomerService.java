package com.gd.sakila.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.mapper.CustomerMapper;
import com.gd.sakila.mapper.FilmMapper;
import com.gd.sakila.vo.Customer;
import com.gd.sakila.vo.CustomerList;
import com.gd.sakila.vo.Page;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class CustomerService {
	@Autowired CustomerMapper customerMapper;
	@Autowired FilmMapper filmMapper;
	
	
	// 영화 대여
	public int addRental(Map<String, Object> map) {
		log.debug("▶▶▶▶▶ addRental() map: "+map);
		
		int invnetoryId = (Integer)map.get(invnetoryId);
				
		double amount = filmMapper.selectAmount(invnetoryId);
		log.debug("▶▶▶▶▶ addRental() amount: "+amount);
		
		return customerMapper.insertRental(map);
	}
	
	// 고객 추가
	public int addCustomer(Customer customer) {
		log.debug("▶▶▶▶▶ addCustomer() customer: "+customer);
		return customerMapper.insertCustomer(customer);
	}
	
	// 고객 상세보기
	public Map<String, Object> getCustomerOne(Integer ID){
		log.debug("▶▶▶▶▶ getCustomerOne() ID: "+ID);
		return customerMapper.selectCustomerOne(ID);
	}
	
	// 블랙리스트
	public List<Map<String, Object>> getBlackCustomerList(){
		return customerMapper.selectBlackCustomerList();
	}
	
	// 고객 리스트
	public List<CustomerList> getCustomerList(Map<String, Object> map){
		log.debug("▶▶▶▶▶ getCustomerList() map: "+map);
		
		return customerMapper.selectCustomerList(map);
	}
	
	// 페이징
	public int getCustomerTotal(String searchWord) {
		log.debug("▶▶▶▶▶ getCustomerTotal() searchWord: "+searchWord);
		
		return customerMapper.selectCustomerTotal(searchWord);
	}
	
	// 휴면 고객 수정
	public void modifyCustomerActiveByScheduler() {
		log.debug("▶▶▶▶▶ modifyCustomerActiveByScheduler() 실행");
		int row = customerMapper.updateCustomerActiveByScheduler();
		log.debug("▶▶▶▶▶ 휴면고객 처리 행수: " + row);
		
	}
}
