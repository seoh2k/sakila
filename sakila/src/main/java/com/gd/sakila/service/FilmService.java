package com.gd.sakila.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.mapper.FilmMapper;
import com.gd.sakila.vo.FilmList;
import com.gd.sakila.vo.Page;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class FilmService {
	@Autowired FilmMapper filmMapper;
	
	public Map<String, Object> getFilmList(int currentPage, int rowPerPage, String searchWord){
		log.debug("▶▶▶▶▶ getFilmList() currentPage: " + currentPage);
		log.debug("▶▶▶▶▶ getFilmList() rowPerPage: " + rowPerPage);
		log.debug("▶▶▶▶▶ getFilmList() searchWord: " + searchWord);
		
		int filmTotal = filmMapper.selectFilmTotal(searchWord);
		int lastPage = (int)(Math.ceil((double)filmTotal / rowPerPage));
		log.debug("▶▶▶▶▶ getFilmList() filmTotal: " + filmTotal);
		log.debug("▶▶▶▶▶ getFilmList() lastPage: " + lastPage);
		
		Page page = new Page();
		page.setBeginRow((currentPage-1)*rowPerPage);
		page.setRowPerPage(rowPerPage);
		page.setSearchWord(searchWord);
		
		List<FilmList> filmList = filmMapper.selectFilmList(page);
		log.debug("▶▶▶▶▶ getFilmList() filmList: " + filmList);
		
		Map<String, Object> map = new HashMap<>();
		map.put("lastPage", lastPage);
		map.put("filmList", filmList);
		
		return map;
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
