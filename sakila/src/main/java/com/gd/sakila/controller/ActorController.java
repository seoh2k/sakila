package com.gd.sakila.controller;

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

import com.gd.sakila.service.ActorService;
import com.gd.sakila.vo.Actor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class ActorController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired ActorService actorService;
	
	@PostMapping("/modifyFilmActor")
	public String modifyFilmActor(@RequestParam(value="filmId", required= true) int filmId,
									@RequestParam(value="ck", required= true) int[] ck) {
		logger.debug("▶▶▶▶▶ modifyFilmActor ck: "+ck);
		logger.debug("▶▶▶▶▶ modifyFilmActor filmId: "+filmId);
		
		actorService.modifyFilmActor(filmId, ck);
		return "redirect:/admin/getFilmOne?filmId="+filmId;
	}
	
	@GetMapping("/addActor")
	public String addActor() {
		return "addActor";
	}
	
	@PostMapping("/addActor")
	public String addActor(Actor actor) {
		logger.debug("addActor actor: "+actor);
		actorService.addActor(actor);
		return "redirect:/admin/getActorList";
	}
	
	@GetMapping("/getActorList") // actor_view
	public String getActorList(Model model,
								@RequestParam(value="currentPage", defaultValue = "1") int currentPage,
								@RequestParam(value="rowPerPage", defaultValue = "10") int rowPerPage,
								@RequestParam(value="searchWord", required = false) String searchWord) {
		logger.debug("▶▶▶▶▶ getActorList currentPage: "+currentPage);
		logger.debug("▶▶▶▶▶ getActorList rowPerPage: "+rowPerPage);
		logger.debug("▶▶▶▶▶ getActorList searchWord: "+searchWord);
		
		Map<String, Object> map = actorService.getActorList(currentPage, rowPerPage, searchWord);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("rowPerPage", rowPerPage);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("actorList", map.get("actorList"));
		return "getActorList";
	}
}
