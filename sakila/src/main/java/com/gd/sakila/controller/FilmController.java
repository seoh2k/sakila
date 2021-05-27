package com.gd.sakila.controller;

import java.util.List;
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
	
	@GetMapping("modifyFilmActor")
	public String modifyFilmActor(@RequestParam(value = "filmId", required = true) int filmId,
									@RequestParam(value = "ck") int[] ck) {
		log.debug("▶▶▶▶▶modifyFilmActor filmId: " + filmId);
		log.debug("▶▶▶▶▶modifyFilmActor ck length: " + ck.length);
		// service - mapper
		// delete from film_actor where film_id = #{filmId}
		// for{ }
		// insert into(actor_id, film_id) values(#{ck[0]}, #{filmId})
		return "redirect:/admin/getFilmOne?FID="+filmId;
	}
	
	@GetMapping("/getFilmActorListByFilm")
	public String getFilmActorListByFilm(Model model,  @RequestParam(value = "filmId", required = true) int filmId) {
		List<Map<String, Object>> list = filmService.getFilmActorListByFilm(filmId);
		log.debug("list size() :"+list.size()); // 200
		model.addAttribute("filmActorList", list);
		return "getFilmActorListByFilm"; 
	}
	
	@GetMapping("/getFilmOne")
	public String getFilmOne(Model model, 
								@RequestParam(value="FID", required = true) int FID) {
		log.debug("▶▶▶▶▶getFilmOne FID: " + FID);
		
		Map<String, Object> filmMap = filmService.getFilmOne(FID);
		int firstStoreFilmCount = filmService.getFilmCount(FID, 1);
		int secondStoreFilmCount = filmService.getFilmCount(FID, 2);
		
		log.debug("▶▶▶▶▶getFilmOne map: " + filmMap);
		log.debug("▶▶▶▶▶getFilmOne firstStoreFilmCount: " + firstStoreFilmCount);
		log.debug("▶▶▶▶▶getFilmOne secondStoreFilmCount: " + secondStoreFilmCount);
		
		model.addAttribute("filmMap", filmMap);
		model.addAttribute("firstStoreFilmCount", firstStoreFilmCount);
		model.addAttribute("secondStoreFilmCount", secondStoreFilmCount);
		
		return "getFilmOne";
	}
	
	@GetMapping("/getFilmList")
	public String getFilmList(Model model, 
								@RequestParam(name="categoryName", required = false) String categoryName,
								@RequestParam(name="price", required = false) Double price,
								@RequestParam(name="title", required = false) String title,
								@RequestParam(name="rating", required = false) String rating,
								@RequestParam(name="currentPage", defaultValue="1") int currentPage,
								@RequestParam(name="rowPerPage", defaultValue="10") int rowPerPage,
								@RequestParam(name="actors", required=false) String actors) {
		log.debug("▶▶▶▶▶ getFilmList categoryName: "+ categoryName);
		log.debug("▶▶▶▶▶ getFilmList price: "+ price);
		log.debug("▶▶▶▶▶ getFilmList title: "+ title);
		log.debug("▶▶▶▶▶ getFilmList rating: "+ rating);
		log.debug("▶▶▶▶▶ getFilmList currentPage: "+ currentPage);
		log.debug("▶▶▶▶▶ getFilmList actors: "+ actors);
		
		
		// 카테고리를 선택하지 않고 검색했을 때 버그 수정
		if(categoryName != null && categoryName.equals("")) {
			categoryName = null;
		}
		// 
		if(price != null && price == 0) {
			price = null;
		}
		if(title != null && title.equals("")) {
			title = null;
		}
		if(rating != null && rating.equals("")) {
			rating = null;
		}
		if(actors != null && actors.equals("")) {
			actors = null;
		}
		
		Map<String, Object> map = filmService.getFilmList(categoryName, price, title, rating, currentPage, rowPerPage, actors); // 16개 또는 null
		model.addAttribute("filmList", map.get("filmList"));
		model.addAttribute("categoryNameList", map.get("categoryNameList"));
		model.addAttribute("categoryName", categoryName);
		model.addAttribute("price", price);
		model.addAttribute("title", title);
		model.addAttribute("rating", rating);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("beginRow", map.get("beginRow"));
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("actors", actors);
		
		return "getFilmList";
	}
}
