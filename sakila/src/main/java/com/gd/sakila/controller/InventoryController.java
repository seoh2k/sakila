package com.gd.sakila.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.sakila.service.InventoryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class InventoryController {
	@Autowired InventoryService inventoryService;
	
	@GetMapping("/removeInventory")
	public String removeInventory(Model model,
				@RequestParam(value="inventoryId", required = false) Integer inventoryId) {
		log.debug("▶▶▶▶▶removeInventory inventoryId: "+inventoryId);
		
		model.addAttribute("inventoryId", inventoryId);
		
		return "removeInventory";
	}
	
	@PostMapping("/removeInventory")
	public String removeInventory(
					@RequestParam(value="inventoryId", required = false) Integer inventoryId,
					@RequestParam(value="filmId", required = false) Integer filmId,
					@RequestParam(value="storeId", required = false) Integer storeId) {
		log.debug("▶▶▶▶▶removeInventory inventoryId: "+inventoryId);
		log.debug("▶▶▶▶▶removeInventory filmId: "+filmId);
		log.debug("▶▶▶▶▶removeInventory storeId: "+storeId);
		
		int row = inventoryService.removeInventory(inventoryId);
		log.debug("▶▶▶▶▶removeInventory row: "+row);
		
		if(row == 0) {
			return "redirect:/admin/removeInventory";
		} 
		return "redirect:/admin/getInventoryList"; // 리다이렉트
	}
	
	@GetMapping("/addInventory")
	public String addInventory() {
		return "addInventory";
	}
	
	@PostMapping("/addInventory")
	public String addInventory(
				@RequestParam(value="filmId", required = false) Integer filmId,
				@RequestParam(value="storeId", required = false) Integer storeId) {
		log.debug("▶▶▶▶▶ addInventory() filmId: "+filmId);
		log.debug("▶▶▶▶▶ addInventory() storeId: "+storeId);
		
		Map<String, Object> map = new HashMap<>();
		map.put("filmId", filmId);
		map.put("storeId", storeId);
		
		int returnMap = inventoryService.addInventory(map);
		log.debug("▶▶▶▶▶ addInventory() returnMap: "+returnMap);
		
		return "redirect:/admin/getInventoryList";
	}
	
	@GetMapping("/getInventoryList")
	public String getInventoryList(Model model, 
									@RequestParam(value="storeId", required = false) Integer storeId,
									@RequestParam(value="currentPage", defaultValue = "1") int currentPage,
									@RequestParam(value="rowPerPage", defaultValue = "10") int rowPerPage,
									@RequestParam(value="searchWord", required = false) String searchWord) {
		log.debug("▶▶▶▶▶ getInventoryList() storeId: "+storeId);
		log.debug("▶▶▶▶▶ getInventoryList() currentPage: "+currentPage);
		log.debug("▶▶▶▶▶ getInventoryList() rowPerPage: "+rowPerPage);
		log.debug("▶▶▶▶▶ getInventoryList() searchWord: "+searchWord);
		
		if (searchWord != null && searchWord.equals("")) { //배우 검색
			searchWord = null;
		}
		if(storeId != null && storeId == 0) { // option value 0 처리
			storeId = null;
		}
		
		int beginRow = (currentPage - 1) * rowPerPage;
		log.debug("▶▶▶▶▶ getInventoryList() beginRow: "+beginRow);
		
		Map<String, Object> map = new HashMap<>();
		map.put("storeId", storeId);
		map.put("beginRow", beginRow);
		map.put("rowPerPage", rowPerPage);
		map.put("searchWord", searchWord);
		
		// 재고 목록
		List<Map<String, Object>> inventoryList = inventoryService.getInventoryList(map);
		
		// 페이징
		int inventoryTotal = inventoryService.getInventoryTotal(map);
		int lastPage = (int)(Math.ceil((double)inventoryTotal/ rowPerPage));
		
		log.debug("▶▶▶▶▶ getInventoryList() inventoryTotal: "+inventoryTotal);
		log.debug("▶▶▶▶▶ getInventoryList() lastPage: "+lastPage);

		model.addAttribute("inventoryList", inventoryList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("rowPerPage", rowPerPage);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("beginRow", beginRow);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("storeId", storeId);
		
		return "getInventoryList";
	}
}
