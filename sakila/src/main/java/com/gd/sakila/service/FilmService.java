package com.gd.sakila.service;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.mapper.CategoryMapper;
import com.gd.sakila.mapper.FilmMapper;
import com.gd.sakila.vo.Category;
import com.gd.sakila.vo.Film;
import com.gd.sakila.vo.FilmForm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class FilmService {
	@Autowired FilmMapper filmMapper;
	@Autowired CategoryMapper categoryMapper;
	
	// film title List
	public List<Map<String,Object>> getFilmTitleList(){
		return filmMapper.selectFilmTitleList();
	}
	
	/*
	 * param : film 입력 폼
	 * return : 입력된 filmId 값
	 */
	
	// CategoryService.class 로 이동해야 함
	public List<Category> getCategoryList(){
		return categoryMapper.selectCategoryList();
	}
	
	public int addFilm(FilmForm filmForm) {
		log.debug("▶▶▶▶▶ addFilm filmForm: " + filmForm); 
		Film film = filmForm.getFilm();
		log.debug("▶▶▶▶▶ addFilm film: " + film); 
		filmMapper.insertFilm(film); // filmId가 생성된 후 film.setFilmId(생성된 값) 호출
		
		Map<String, Object> map = new HashMap<>(); // 맵 안에 categoryId, filmId 들어가야함
		map.put("categoryId", filmForm.getCategory().getCategoryId());
		map.put("filmId", film.getFilmId());
		filmMapper.insertFilmCategory(map);
		
		return film.getFilmId();
	}
	
	public List<Map<String, Object>> getFilmActorListByFilm(int filmId){
		return filmMapper.selectFilmActorListByFilm(filmId);
	}
	
	public Map<String, Object> getFilmOne(int FID) { // 전체적으로 통일하기 위해서 만든다
		log.debug("▶▶▶▶▶ getFilmOne FID: " + FID); 
		// 상세보기
		// FID에 맞는 정보를 Map타입 변수에 저장
		Map<String, Object> map = filmMapper.selectFilmOne(FID);
		log.debug("▶▶▶▶▶ getFilmOne map: " + map); 
		
		return map;
	}
	
	public Map<String, Object> getFilmList(String categoryName, Double price, String title, String rating, 
											int currentPage, int rowPerPage, String actors){
		log.debug("▶▶▶▶▶ getFilmList() categoryName: " + categoryName); // 16개 또는 널이 넘어온다
		log.debug("▶▶▶▶▶ getFilmList() price: " + price);
		log.debug("▶▶▶▶▶ getFilmList() title: " + title);
		log.debug("▶▶▶▶▶ getFilmList() rating: " + rating);
		log.debug("▶▶▶▶▶ getFilmList() currentPage: " + currentPage);
		log.debug("▶▶▶▶▶ getFilmList() rowPerPage: " + rowPerPage);
		log.debug("▶▶▶▶▶ getFilmList() actors: " + actors);

		Map<String, Object> paramMap = new HashMap<>();
		int beginRow = (currentPage-1) * rowPerPage;
		paramMap.put("categoryName", categoryName);
		paramMap.put("price", price);
		paramMap.put("title", title);
		paramMap.put("rating", rating);
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("actors", actors);
		
		int filmTotal = filmMapper.selectFilmTotal(paramMap);
		log.debug("▶▶▶▶▶ getFilmList() filmTotal: " + filmTotal);

		int lastPage = filmTotal/rowPerPage;
		if(filmTotal % rowPerPage != 0) {
			lastPage += 1;
		}
		log.debug("▶▶▶▶▶ getFilmList() lastPage: " + lastPage);
		
		List<Map<String, Object>> filmList = filmMapper.selectFilmList(paramMap);
		List<Category> categoryList = categoryMapper.selectCategoryList();
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("filmList", filmList);
		returnMap.put("categoryList", categoryList);
		returnMap.put("filmTotal", filmTotal);
		returnMap.put("beginRow", beginRow);
		returnMap.put("lastPage", lastPage);
		
		return returnMap;
	}

	// map <-- film, filmCount
	public int getFilmCount(int filmId, int storeId){
		log.debug("▶▶▶▶▶ getFilmOne filmId: " + filmId);
		log.debug("▶▶▶▶▶ getFilmOne storeId: " + storeId);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("filmId", filmId);
		paramMap.put("storeId", storeId);
		int filmCount = 0;
		paramMap.put("filmCount", filmCount);
		
		List<Integer> countlist = filmMapper.selectFilmInStock(paramMap); // 프로시저 실행
		filmCount = (int) paramMap.get("filmCount"); // 프로시저에서 out매개변수 filmCount 추출
		log.debug("▶▶▶▶▶ getFilmOne() filmCount: " + filmCount);
		log.debug("▶▶▶▶▶ getFilmOne() countlist: " + countlist);
		
		return filmCount;
	}
}
