package com.gd.sakila.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.controller.HomeController;
import com.gd.sakila.mapper.CommentMapper;
import com.gd.sakila.vo.Board;
import com.gd.sakila.vo.Comment;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional // 예외가 발생하면 실행 단위가 지속된다.
public class CommentService {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired CommentMapper commentMapper;
	
	// 댓글 추가 액션
	public int addComment(Comment comment) { // 0이면 입력 실패, 1이면 입력 성공
		// 서비스에서 받은 매개변수 디버깅
		logger.debug("▶▶▶▶▶ addComment comment: " + comment);
		// 쿼리 실행
		int row = commentMapper.insertComment(comment);
		//쿼리 실행결과 디버깅
		logger.debug("▶▶▶▶▶ addComment row: " + row);
		// 리턴
		return row;
	}
	
	// 댓글 삭제 액션
	public int removeComment(int commentId) {
		// 서비스에서 받은 매개변수 디버깅
		logger.debug("▶▶▶▶▶ removeBoard commentId: " + commentId);
		// 쿼리 실행
		int row = commentMapper.deleteCommentByCommentId(commentId);
		//쿼리 실행결과 디버깅
		logger.debug("▶▶▶▶▶ removeComment() row: " + row); 
		// 리턴
		return row;
	}
}
