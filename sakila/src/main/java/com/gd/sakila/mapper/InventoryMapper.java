package com.gd.sakila.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InventoryMapper {
	// 영화를 선택 시 해당 재고
	List<Map<String, Object>> selectInventoryIdList(int filmId);
	
	// 재고 삭제
	int deleteInventory(int inventoryId);
	
	// 재고 추가
	int insertInventory(Map<String, Object> map);
	
	// 재고 리스트
	List<Map<String, Object>> selectInventoryList(Map<String, Object> map);
	
	// 페이징
	int selectInventoryTotal(Map<String, Object> map);
}
