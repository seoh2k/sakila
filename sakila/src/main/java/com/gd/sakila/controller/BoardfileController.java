package com.gd.sakila.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.gd.sakila.service.BoardfileService;
import com.gd.sakila.vo.Boardfile;

import jdk.internal.org.jline.utils.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin") // get과 post 모두 받는다, 모든 매핑 앞에 붙는다
public class BoardfileController {
	@Autowired BoardfileService boardfileService;
	
	// 파일 추가 폼
	@GetMapping("/addBoardfile") // addBoardfile 메서드 요청
	public String addBoardfile(Model model, @RequestParam(value = "boardId", required = true) int boardId) {
		model.addAttribute("boardId", boardId);
		return "addBoardfile"; // /WEB-INF/view/addBoardfile.jsp으로 포워딩
	}
	// 파일 추가 액션
	@PostMapping("/addBoardfile")
	public String addBoardfile(MultipartFile multipartFile, @RequestParam(value = "boardId", required = true) int boardId) {
		log.debug("▶▶▶▶▶addBoardd boardId: " + boardId);
		log.debug("▶▶▶▶▶addBoardd multipartFile: " + multipartFile);
		boardfileService.addBoardfile(multipartFile, boardId);
		return "redirect:/admin/getBoardOne?boardId="+boardId;
	}
	
	// 추가된 파일 삭제
	@GetMapping("/removeBoardfile")
	public String removeBoardfileOne(Boardfile boardfile) {
		// 글쓴이가 아니라도 삭제가 가능하다
		boardfileService.removeBoardfileOne(boardfile);
		return "redirect:/admin/getBoardOne?boardId="+boardfile.getBoardId();
	}
}
