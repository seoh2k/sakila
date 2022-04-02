package com.gd.sakila.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.sakila.service.CommentService;
import com.gd.sakila.vo.Comment;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin") // get과 post 모두 받는다, 모든 매핑 앞에 붙는다
public class CommentController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired CommentService commentService;
	
	// 댓글 추가 
	@PostMapping("/addComment")
	public String addComment(Comment comment) {
		//컨트롤러 매개변수 디버깅
		logger.debug("▶▶▶▶▶ addComment comment: " + comment);
		//서비스에 추가 요청
		int row = commentService.addComment(comment);
		//서비스 실행 결과 디버깅
		logger.debug("▶▶▶▶▶ addComment row: " + row);
		// 리다이렉트: 원래 페이지로 이동을 위해  boardId를 함께 넘겨준다
		return "redirect:/admin/getBoardOne?boardId="+comment.getBoardId();
	}
	
	// 댓글 삭제 
	@GetMapping("/removeComment")
	public String removeComment(@RequestParam(value = "commentId", required = true) int commentId, 
								@RequestParam(value = "boardId", required = true) int boardId) {
		//컨트롤러 매개변수 디버깅
		logger.debug("▶▶▶▶▶ removeComment commentId: " + commentId);
		logger.debug("▶▶▶▶▶ removeComment boardId: " + boardId);
		// 서비스에 삭제 요청
		int row = commentService.removeComment(commentId);
		// 서비스 실행 결과 디버깅
		logger.debug("▶▶▶▶▶ removeComment row: " + row);
		// 리다이렉트: 원래 페이지로 이동을 위해  boardId를 함께 넘겨준다
		return "redirect:/admin/getBoardOne?boardId="+boardId;
	}
}
