package com.gd.sakila.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.mapper.ActorMapper;
import com.gd.sakila.vo.Actor;
import com.gd.sakila.vo.Page;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class ActorService {
	@Autowired ActorMapper actorMapper;
	
	public int addActor(Actor actor) {
		log.debug("▶▶▶▶▶actor: "+ actor);
		
		return actorMapper.insertActor(actor);
	}
	
	public Map<String, Object> getActorList(int currentPage, int rowPerPage, String searchWord){
		log.debug("▶▶▶▶▶ getActorList currentPage: "+currentPage);
		log.debug("▶▶▶▶▶ getActorList rowPerPage: "+rowPerPage);
		log.debug("▶▶▶▶▶ getActorList searchWord: "+searchWord);
		
		int actorTotal = actorMapper.selectActorTotal(searchWord);
		log.debug("▶▶▶▶▶ getActorList actorTotal: "+actorTotal);
		
		int lastPage = (int)(Math.ceil((double)actorTotal / rowPerPage));
		log.debug("▶▶▶▶▶ getActorList lastPage: "+lastPage);
		
		Page page = new Page();
		page.setBeginRow((currentPage-1) * rowPerPage);
		page.setRowPerPage(rowPerPage);
		page.setSearchWord(searchWord);
		
		List<Actor> actorList = actorMapper.selectActorInfoList(page);
		log.debug("▶▶▶▶▶ getActorList actorList: "+actorList.size());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lastPage", lastPage);
		map.put("actorList", actorList);
		
		return map;
	}
}
