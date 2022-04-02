package com.gd.sakila.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.sakila.service.FilmService;
import com.gd.sakila.service.LanguageService;
import com.gd.sakila.vo.Category;
import com.gd.sakila.vo.FilmForm;
import com.gd.sakila.vo.Language;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class FilmController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	FilmService filmService;
	@Autowired
	LanguageService langaugeService;

	@GetMapping("/addFilm")
	public String addFilm(Model model) {
		logger.debug("▶▶▶▶▶ addFilm model: " + model); 
		// category list
		List<Category> categoryList = filmService.getCategoryList(); // CAtegoryService에서 받자
		List<Language> languageList = langaugeService.getLanguageList();
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("languageList", languageList);
		return "addFilm";
	}

	// 1. 컨맨드 타입 사용한다.
	// 2. 매개변수가 다르면 리퀘스트 파람 쓴다.
	// 3.
	@PostMapping("/addFilm") // 기본(값)타입 매개변수의 이름과 name이 같으면 매핑
	public String addFilm(FilmForm filmForm) { // 참조타입은 필드명과 name이 같으면 매핑시킨다.
		logger.debug("▶▶▶▶▶ addFilm filmForm: " + filmForm); 
		int filmId = filmService.addFilm(filmForm);
		return "redirect:/admin/getFilmOne?FID=" + filmId;
	}

	@GetMapping("modifyFilmActor")
	public String modifyFilmActor(@RequestParam(value = "filmId", required = true) int filmId,
			@RequestParam(value = "ck") int[] ck) {
		logger.debug("▶▶▶▶▶modifyFilmActor filmId: " + filmId);
		logger.debug("▶▶▶▶▶modifyFilmActor ck length: " + ck.length);
		// service - mapper
		// delete from film_actor where film_id = #{filmId}
		// for{ }
		// insert into(actor_id, film_id) values(#{ck[0]}, #{filmId})
		return "redirect:/admin/getFilmOne?FID=" + filmId;
	}

	@GetMapping("/getFilmActorListByFilm")
	public String getFilmActorListByFilm(Model model, @RequestParam(value = "filmId", required = true) int filmId) {
		List<Map<String, Object>> list = filmService.getFilmActorListByFilm(filmId);
		logger.debug("list size() :" + list.size()); // 200
		model.addAttribute("filmActorList", list);
		return "getFilmActorListByFilm";
	}

	@GetMapping("/getFilmOne")
	public String getFilmOne(Model model, @RequestParam(value = "FID", required = true) int FID) {
		logger.debug("▶▶▶▶▶getFilmOne FID: " + FID);

		Map<String, Object> filmMap = filmService.getFilmOne(FID);
		int firstStoreFilmCount = filmService.getFilmCount(FID, 1);
		int secondStoreFilmCount = filmService.getFilmCount(FID, 2);

		logger.debug("▶▶▶▶▶getFilmOne map: " + filmMap);
		logger.debug("▶▶▶▶▶getFilmOne firstStoreFilmCount: " + firstStoreFilmCount);
		logger.debug("▶▶▶▶▶getFilmOne secondStoreFilmCount: " + secondStoreFilmCount);

		model.addAttribute("filmMap", filmMap);
		model.addAttribute("firstStoreFilmCount", firstStoreFilmCount);
		model.addAttribute("secondStoreFilmCount", secondStoreFilmCount);

		return "getFilmOne";
	}

	@GetMapping("/getFilmList")
	public String getFilmList(Model model, @RequestParam(name = "categoryName", required = false) String categoryName,
			@RequestParam(name = "price", required = false) Double price,
			@RequestParam(name = "title", required = false) String title,
			@RequestParam(name = "rating", required = false) String rating,
			@RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
			@RequestParam(name = "rowPerPage", defaultValue = "10") int rowPerPage,
			@RequestParam(name = "actors", required = false) String actors) {
		logger.debug("▶▶▶▶▶ getFilmList categoryName: " + categoryName);
		logger.debug("▶▶▶▶▶ getFilmList price: " + price);
		logger.debug("▶▶▶▶▶ getFilmList title: " + title);
		logger.debug("▶▶▶▶▶ getFilmList rating: " + rating);
		logger.debug("▶▶▶▶▶ getFilmList currentPage: " + currentPage);
		logger.debug("▶▶▶▶▶ getFilmList actors: " + actors);

		// 카테고리를 선택하지 않고 검색했을 때 버그 수정
		if (categoryName != null && categoryName.equals("")) {
			categoryName = null;
		}
		//
		if (price != null && price == 0) {
			price = null;
		}
		if (title != null && title.equals("")) {
			title = null;
		}
		if (rating != null && rating.equals("")) {
			rating = null;
		}
		if (actors != null && actors.equals("")) {
			actors = null;
		}

		Map<String, Object> map = filmService.getFilmList(categoryName, price, title, rating, currentPage, rowPerPage,
				actors); // 16개 또는 null
		model.addAttribute("filmList", map.get("filmList"));
		model.addAttribute("categoryList", map.get("categoryList"));
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
