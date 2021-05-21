package com.gd.sakila.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.sakila.service.BoardService;
import com.gd.sakila.vo.Board;
import com.gd.sakila.vo.BoardForm;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin") // get과 post 모두 받는다, 모든 매핑 앞에 붙는다
public class BoardController {
	@Autowired
	BoardService boardService;
	
	@GetMapping("/modifyBoard")
	public String modifyBoard(Model model, @RequestParam(value="boardId", required= true) int boardId) {
		log.debug("▶▶▶▶▶modifyBoard param: "+boardId);
		// select
		Map<String, Object> map = boardService.getBoardOne(boardId);
		model.addAttribute("map", map);
		return "modifyBoard";
	}
	
	@PostMapping("/modifyBoard")
	public String modifyBoard(Board board) {
		// update
		int row = boardService.modifyBoard(board);
		log.debug("▶▶▶▶▶update row: "+row);
		return "redirect:/admin/getBoardOne?boardId="+board.getBoardId();
	}
	
	// 리턴타입은 뷰 이름 문자열이다. C -> V
	@GetMapping("/removeBoard")
	// model안에 넣는다
	public String removeBoard(Model model, @RequestParam(value="boardId", required= true) int boardId) { // string인데 자동으로 int로 형변환 된다
		log.debug("▶▶▶▶▶boardId param: "+boardId);
		model.addAttribute("boardId", boardId);
		return "removeBoard"; // 포워딩
	}
	
	// C -> M -> redirect(C)
	@PostMapping("/removeBoard")
	public String removeBoard(Board board) {
		int row = boardService.removeBoard(board);
		log.debug("removeBoard(): "+row);
		if(row == 0) {
			return "redirect:/admin/getBoardOne?boardId="+board.getBoardId();
		} 
		return "redirect:/admin/getBoardList"; // 리다이렉트
	}
	
	// addBoard 띄워준다
	@GetMapping("/addBoard") // addBoard라는 요청이 들어오면 addBoard를 보여준다
	public String addBoard() {
		return "addBoard"; // 뷰 이름
	}
	
	// 입력하려면 보드와 관련된 것 전부 받아야된다
	@PostMapping("/addBoard")
	public String addBoard(BoardForm boardForm) { // Board 타입으로 받는다 // 커맨드 객체: 폼하나의 모양과 같다. 하나의 타입으로 뭉쳐서 받는다.
		log.debug("▶▶▶▶▶boardForm: "+boardForm);
		boardService.addBoard(boardForm); // param Board -> BoardForm으로 변경
		return "redirect:/admin/getBoardList"; // redirect:/: 포워딩 안시키고 sendRedirect 시킨다. 리다이렉트 없으면 다 포워딩 시킨다.
	}
	
	@GetMapping("/getBoardOne")
	public String getBoardOne(Model model, 
								@RequestParam(value="boardId", required = true) int boardId) { // View가 있으면 모델이 존재
		log.debug(" boardId : "+boardId); 
		Map<String, Object> map = boardService.getBoardOne(boardId);
		log.debug(" getBoardOne map: "+map);
		model.addAttribute("boardMap", map.get("boardMap"));
		model.addAttribute("boardfileList", map.get("boardfileList"));
		model.addAttribute("commentList", map.get("commentList"));
		return "getBoardOne";
	}
	
	@GetMapping("/getBoardList")
	public String getBoardList(Model model,
								@RequestParam(value="currentPage", defaultValue="1") int currentPage,
								@RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage,
								@RequestParam(value="searchWord", required = false ) String searchWord) {
		System.out.println(currentPage+"<--currentPage");
		System.out.println(rowPerPage+"<--rowPerPage");
		System.out.println(searchWord+"<--searchWord");
		
		Map<String, Object> map = boardService.getBoardList(currentPage, rowPerPage, searchWord);
		// model.addAttribute("map", map); // map을 통째로 넣으면 view에서 풀어줘야 한다
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("boardList", map.get("boardList"));
		return "getBoardList";
	}
}
