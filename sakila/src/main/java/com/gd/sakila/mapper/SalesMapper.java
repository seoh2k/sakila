package com.gd.sakila.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SalesMapper {
	// 베스트 셀러 빌린횟수 top10
	List<Map<String, Object>> selectBestSellerTop10();
	// 카테고리별 매출
	List<Map<String, Object>> selectSalesByCategoryList();
	// 월별 매출 목록
	List<Map<String, Object>> selectMonthlySalesList(Map<String, Object> map);
}
