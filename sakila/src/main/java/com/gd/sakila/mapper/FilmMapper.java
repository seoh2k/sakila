package com.gd.sakila.mapper;

import java.util.List;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FilmMapper {
	List<Integer> selectFilmInStock(Map<String, Object> map);
	List<Map<String, Object>> selectFilmList(Map<String, Object> map);
	int selectFilmTotal(Map<String, Object> paramMap);
	Map<String, Object> selectFilmOne(int FID);
	List<Map<String, Object>> selectFilmActorListByFilm(int filmId);
}
