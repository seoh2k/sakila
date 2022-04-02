package com.gd.sakila.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.controller.HomeController;
import com.gd.sakila.mapper.InventoryMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class InventoryService {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired InventoryMapper inventoryMapper;
	
	// 영화별 재고 리스트
	public List<Map<String,Object>> getInventoryIdList(int filmId){
		logger.debug("▶▶▶▶▶ getFilmTitleList() inventoryId: "+filmId);
		return inventoryMapper.selectInventoryIdList(filmId);
	}
	
	public int removeInventory(int inventoryId) {
		logger.debug("▶▶▶▶▶ removeInventory() map: "+inventoryId);
		return inventoryMapper.deleteInventory(inventoryId);
	}
	
	public int addInventory(Map<String, Object> map) {
		logger.debug("▶▶▶▶▶ addInventory() map: "+map);
		return inventoryMapper.insertInventory(map);
	}
	
	public List<Map<String, Object>> getInventoryList(Map<String, Object> map){
		logger.debug("▶▶▶▶▶ getInventoryList() map: "+map);
		return inventoryMapper.selectInventoryList(map);
	}
	
	public int getInventoryTotal(Map<String, Object> map) {
		logger.debug("▶▶▶▶▶ getInventoryTotal() map: "+map);
		return inventoryMapper.selectInventoryTotal(map);
	}
}
