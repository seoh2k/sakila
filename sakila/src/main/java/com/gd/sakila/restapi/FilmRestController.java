package com.gd.sakila.restapi;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gd.sakila.controller.RentalController;
import com.gd.sakila.mapper.RentalMapper;
import com.gd.sakila.service.FilmService;
import com.gd.sakila.service.InventoryService;
import com.gd.sakila.service.RentalService;

@RestController
public class FilmRestController {
	@Autowired FilmService filmService;
	@Autowired InventoryService inventoryService;
	@Autowired RentalMapper rentalMapper;
	
	@GetMapping("/getFilmTitleList")
	public List<Map<String,Object>> getFilmTitleList() {
		return filmService.getFilmTitleList();
	}
	
	@GetMapping("/getInventoryIdList")
	public List<Map<String,Object>> getInventoryIdList(@RequestParam(value="filmId") int filmId) {
		return inventoryService.getInventoryIdList(filmId);
	}
	
	@GetMapping("/getRentalByCustomer")
	public List<Map<String,Object>> getRentalByCustomer(
			@RequestParam(value="customerId") int customerId) {
		return rentalMapper.selectRentalByCustomer(customerId);
	}
}