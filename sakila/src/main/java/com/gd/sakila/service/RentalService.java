package com.gd.sakila.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.controller.HomeController;
import com.gd.sakila.mapper.FilmMapper;
import com.gd.sakila.mapper.RentalMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class RentalService {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired RentalMapper rentalMapper;
	@Autowired FilmMapper filmMapper;
	
	// 대여 리스트
	public List<Map<String, Object>> getRentalList(Map<String, Object> map){
		logger.debug("▶▶▶▶▶ getRentalList() map: "+map);
		
		return rentalMapper.selectRentalList(map);
	}
	
	// 페이징
	public int getRentalTotal(Map<String, Object> map) {
		logger.debug("▶▶▶▶▶ getRentalTotal() map: "+map);
		
		return rentalMapper.selectRentalTotal(map);
	}
	
	/* 대여
	 * 1. rental insert
	 * 2. inventoryId에 해당하는 amount(rental_rate) 가져오기
	 * 3. payment insert
	 */
	
	// 영화 대여
	public void addRental(Map<String, Object> map) {
		logger.debug("▶▶▶▶▶ addRental() map: "+map);
		
		// 1. 
		rentalMapper.insertRental(map);
		
		// 2.
		int inventoryId = (Integer)map.get("inventoryId");
		logger.debug("▶▶▶▶▶ addRental() inventoryId: "+inventoryId);
		double amount = filmMapper.selectRentalRate(inventoryId);
		logger.debug("▶▶▶▶▶ addRental() amount: "+amount);
		map.put("amount", amount);
		
		// 3.
		rentalMapper.insertPayment(map);
	}
	
	// 영화 반납
	public void removeRental(int customerId) {                           
		logger.debug("▶▶▶▶▶ removeRental() customerId: "+customerId);                                          
	}
}
