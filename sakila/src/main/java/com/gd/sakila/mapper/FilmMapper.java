package com.gd.sakila.mapper;

import java.util.List;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gd.sakila.vo.Film;

@Mapper
public interface FilmMapper {
	// 영화 대여 시 매출
	double selectRentalRate(int inventoryId);
	// 영화 제목 리스트
	List<Map<String, Object>> selectFilmTitleList();
	
	int insertFilmCategory(Map<String, Object> map);
	int insertFilm(Film film);
	
	List<Integer> selectFilmInStock(Map<String, Object> map);
	List<Map<String, Object>> selectFilmList(Map<String, Object> map);
	int selectFilmTotal(Map<String, Object> paramMap);
	Map<String, Object> selectFilmOne(int FID);
	List<Map<String, Object>> selectFilmActorListByFilm(int filmId);
	
	// 영화 전체 수
	public int getFilmTotalCount();
}