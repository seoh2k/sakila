package com.gd.sakila.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.mapper.BoardMapper;
import com.gd.sakila.mapper.CommentMapper;
import com.gd.sakila.vo.Board;
import com.gd.sakila.vo.Comment;
import com.gd.sakila.vo.Page;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional // 예외가 발생하면 실행 단위가 지속된다.
public class BoardService {
	@Autowired BoardMapper boardMapper; /// 스캔 객체를 가지고 있으면 대입ㅇ르 해주겠다
	@Autowired CommentMapper commentMapper;
	
	// 수정 액션
	public int modifyBoard(Board board) {
		log.debug("▶▶▶▶▶ modifyBoard param: " + board.toString()); 
		return boardMapper.updateBoard(board);
	}
	
	// 삭제 액션
	public int removeBoard(Board board) {
		log.debug("▶▶▶▶▶ removeBoard param: " + board.toString()); 
		// 2) 게시글 삭제( FK를 지정하지 않거나, FK를 delete no action...
		int boardRow = boardMapper.deleteBoard(board); // 0이면 댓글이 삭제가 안된다
		if(boardRow == 0) {
			return 0;
		}
				
		// 1) 댓글 삭제
		int commentRow = commentMapper.deleteCommentByBoardId(board.getBoardId());
		log.debug("▶▶▶▶▶ removeBoard() commentRow: " + commentRow); 
		
		log.debug("▶▶▶▶▶ removeBoard() boardRow: " + boardRow); 
		return boardRow;
	}
	
	// 추가 액션
	public int addBoard(Board board) { // 0이면 입력 실패, 1이면 입력 성공
		return boardMapper.insertBoard(board);
	}
	
	// 1) 상세보기 + 2) 댓글 목록, 수정 폼
	public Map<String, Object> getBoardOne(int boardId) { // 전체적으로 통일하기 위해서 만든다
		log.debug("▶▶▶▶▶ modifyBoard param: " + boardId); 
		// 1) 상세보기
		Map<String, Object> boardMap = boardMapper.selectBoardOne(boardId);
		log.debug("▶▶▶▶▶ boardMap: " + boardMap); 
		// 2) 댓글 목록
		List<Comment> commentList = commentMapper.selectCommentListByBoard(boardId);
		log.debug("commentList size() : "+ commentList.size());
		
		Map<String, Object> map = new HashMap<>();
		map.put("boardMap", boardMap);
		map.put("commentList", commentList);
		
		return map;
	}
	
	public Map<String, Object> getBoardList(int currentPage, int rowPerPage, String searchWord){
		// 1. 
		int boardTotal = boardMapper.selectBoardTotal(searchWord); // searchWord
		 
		/*
		int lastPage = boardTotal / rowPerPage;
		if(boardTotal % rowPerPage != 0) {
			lastPage++;
		}
		*/
		int lastPage = (int)(Math.ceil((double)boardTotal / rowPerPage));
		
		// 2
		Page page = new Page();
		page.setBeginRow((currentPage -1) * rowPerPage);
		page.setRowPerPage(rowPerPage);
		page.setSearchWord(searchWord);
		// 3. 
		List<Board> boardList = boardMapper.selectBoardList(page); // Page
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lastPage", lastPage);
	    map.put("boardList", boardList);

		return map;
	}
}
