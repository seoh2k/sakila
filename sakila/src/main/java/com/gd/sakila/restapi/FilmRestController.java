package com.gd.sakila.restapi;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gd.sakila.service.FilmService;
import com.gd.sakila.service.InventoryService;

@RestController
public class FilmRestController {
	@Autowired FilmService filmService;
	
	@GetMapping("/getFilmTitleList")
	public List<Map<String,Object>> getFilmTitleList() {
		return filmService.getFilmTitleList();
	}
}
