package com.gd.sakila.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.mapper.InventoryMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class InventoryService {
	@Autowired InventoryMapper inventoryMapper;
	
	// 영화별 재고 리스트
	public List<Map<String,Object>> getInventoryIdList(int filmId){
		log.debug("▶▶▶▶▶ getFilmTitleList() inventoryId: "+filmId);
		return inventoryMapper.selectInventoryIdList(filmId);
	}
	
	public int removeInventory(int inventoryId) {
		log.debug("▶▶▶▶▶ removeInventory() map: "+inventoryId);
		return inventoryMapper.deleteInventory(inventoryId);
	}
	
	public int addInventory(Map<String, Object> map) {
		log.debug("▶▶▶▶▶ addInventory() map: "+map);
		return inventoryMapper.insertInventory(map);
	}
	
	public List<Map<String, Object>> getInventoryList(Map<String, Object> map){
		log.debug("▶▶▶▶▶ getInventoryList() map: "+map);
		return inventoryMapper.selectInventoryList(map);
	}
	
	public int getInventoryTotal(Map<String, Object> map) {
		log.debug("▶▶▶▶▶ getInventoryTotal() map: "+map);
		return inventoryMapper.selectInventoryTotal(map);
	}
}
