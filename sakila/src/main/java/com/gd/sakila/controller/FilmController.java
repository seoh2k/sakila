package com.gd.sakila.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.sakila.service.FilmService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class FilmController {
	@Autowired FilmService filmService;
	
	@GetMapping("/getFilmList")
	public String getFilmList(Model model, @RequestParam(name="categoryName", required = false) String categoryName) {
		log.debug("▶▶▶▶▶ getFilmList categoryName: "+ categoryName);
		
		// 카테고리를 선택하지 않고 검색했을 때 
		if(categoryName != null && categoryName.equals("")) {
			categoryName = null;
		}
		
		Map<String, Object> map = filmService.getFilmList(categoryName); // 16개 또는 null
		model.addAttribute("filmList", map.get("filmList"));
		model.addAttribute("categoryNameList", map.get("categoryNameList"));
		model.addAttribute("categoryName", categoryName);
		return "getFilmList";
	}
	
	@GetMapping("/getFilmOne")
	public String getFilmOne() {
		filmService.getFilmOne(1, 1);
		return "getFilmOne";
	}
}
