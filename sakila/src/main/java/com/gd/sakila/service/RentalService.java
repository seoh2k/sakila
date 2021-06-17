package com.gd.sakila.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.mapper.FilmMapper;
import com.gd.sakila.mapper.RentalMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class RentalService {
	@Autowired RentalMapper rentalMapper;
	@Autowired FilmMapper filmMapper;
	
	// 대여 리스트
	public List<Map<String, Object>> getRentalList(Map<String, Object> map){
		log.debug("▶▶▶▶▶ getRentalList() map: "+map);
		
		return rentalMapper.selectRentalList(map);
	}
	
	// 페이징
	public int getRentalTotal(String searchWord) {
		log.debug("▶▶▶▶▶ getRentalTotal() searchWord: "+searchWord);
		
		return rentalMapper.selectRentalTotal(searchWord);
	}
	
	/* 대여
	 * 1. rental insert
	 * 2. inventoryId에 해당하는 amount(rental_rate) 가져오기
	 * 3. payment insert
	 */
	
	// 영화 대여
	public void addRental(Map<String, Object> map) {
		log.debug("▶▶▶▶▶ addRental() map: "+map);
		
		// 1. 
		rentalMapper.insertRental(map);
		
		// 2.
		int inventoryId = (Integer)map.get("inventoryId");
		log.debug("▶▶▶▶▶ addRental() inventoryId: "+inventoryId);
		double amount = filmMapper.selectRentalRate(inventoryId);
		log.debug("▶▶▶▶▶ addRental() amount: "+amount);
		map.put("amount", amount);
		
		// 3.
		rentalMapper.insertPayment(map);
	}
}
