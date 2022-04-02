package com.gd.sakila.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.controller.HomeController;
import com.gd.sakila.mapper.SalesMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class SalesService {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired SalesMapper salesMapper;
	
	// 베스트 셀러 빌린횟수 top10
	public List<Map<String, Object>> getBestSellerTop10(){
		return salesMapper.selectBestSellerTop10();
	}
	
	// 카테고리별 매출
	public List<Map<String, Object>> getSalesByCategoryList(){
		return salesMapper.selectSalesByCategoryList();
	}
	
	// 월별 매출 목록
	public List<Map<String, Object>> getMonthlySalesList(Map<String, Object> map){
		logger.debug("▶▶▶▶▶ getMonthlySalesList() map: "+map);
		return salesMapper.selectMonthlySalesList(map);
	}
}
