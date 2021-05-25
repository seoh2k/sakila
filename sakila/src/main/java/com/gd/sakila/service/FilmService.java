package com.gd.sakila.service;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.mapper.CategoryMapper;
import com.gd.sakila.mapper.FilmMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class FilmService {
	@Autowired FilmMapper filmMapper;
	@Autowired CategoryMapper categoryMapper;
	
	public Map<String, Object> getFilmList(String categoryName){
		log.debug("▶▶▶▶▶ getFilmList() categoryName: " + categoryName); // 16개 또는 널이 넘어온다
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("categoryName", categoryName);
		
		List<Map<String, Object>> filmList = filmMapper.selectFilmList(paramMap);
		List<String> categoryNameList = categoryMapper.selectCategoryNameList();
		Map<String, Object> returnMap = new HashMap<>();
		
		returnMap.put("filmList", filmList);
		returnMap.put("categoryNameList", categoryNameList);
		
		return returnMap;
	}

	// map <-- film, filmCount
	public Map<String, Object> getFilmOne(int filmId, int storeId){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("filmId", filmId);
		paramMap.put("storeId", storeId);
		int filmCount = 0;
		paramMap.put("filmCount", filmCount);
		
		List<Integer> list = filmMapper.selectFilmInStock(paramMap);
		log.debug("▶▶▶▶▶ getFilmOne() filmCount: " + paramMap.get("filmCount"));
		log.debug("▶▶▶▶▶ getFilmOne() list: " + list);
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		return returnMap;
	}
}
