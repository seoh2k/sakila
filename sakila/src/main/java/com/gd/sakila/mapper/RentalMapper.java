package com.gd.sakila.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface RentalMapper {
	// 대여 리스트
	List<Map<String, Object>> selectRentalList(Map<String, Object> map);
	int selectRentalTotal(String searchWord);
		
	// 영화 대여
	int insertRental(Map<String, Object> map);
	int insertPayment(Map<String, Object> map);
}
