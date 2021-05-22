package com.gd.sakila.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.gd.sakila.mapper.BoardMapper;
import com.gd.sakila.mapper.BoardfileMapper;
import com.gd.sakila.mapper.CommentMapper;
import com.gd.sakila.vo.Board;
import com.gd.sakila.vo.BoardForm;
import com.gd.sakila.vo.Boardfile;
import com.gd.sakila.vo.Comment;
import com.gd.sakila.vo.Page;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional // 예외가 발생하면 실행 단위가 지속된다.
public class BoardService {
	@Autowired BoardMapper boardMapper; /// 스캔 객체를 가지고 있으면 대입ㅇ르 해주겠다
	@Autowired BoardfileMapper boardfileMapper;
	@Autowired CommentMapper commentMapper;
	
	// 수정 액션
	public int modifyBoard(Board board) {
		log.debug("▶▶▶▶▶ modifyBoard param: " + board.toString()); 
		return boardMapper.updateBoard(board);
	}
	
	// 삭제 액션
	public int removeBoard(Board board) {
		log.debug("▶▶▶▶▶ removeBoard param: " + board); 
		
		// 1) 게시글 삭제( FK를 지정하지 않거나, FK를 delete no action...
		int boardRow = boardMapper.deleteBoard(board); // 0이면 댓글이 삭제가 안된다
		if(boardRow == 0) {
			return 0;
		}
				
		// 2) 댓글 삭제
		int commentRow = commentMapper.deleteCommentByBoardId(board.getBoardId());
		log.debug("▶▶▶▶▶ removeBoard() commentRow: " + commentRow); 
		
		// 3) 물리적 파일 삭제 (/resource/안에 파일)
		List<Boardfile> boardfileList = boardfileMapper.selectBoardfileByBoardId(board.getBoardId());
		if(boardfileList != null) {
			for(Boardfile f : boardfileList) {
				File temp = new File(""); // 프로젝트 폴더에 빈파일이 만들어진다.
				// getAbsolutePath: 파일의 절대 경로를 반환해주는 함수, 입력된 절대경로를 그대로 표현한다.
				String path = temp.getAbsolutePath(); // 프로젝트 폴더 // 경로를 매번 바꿔주기 힘드니까 설정
				File file = new File(path+"\\src\\main\\webapp\\resource\\"+f.getBoardfileName());
				file.delete();
			}
		}
		
		// 4) 파일 테이블 행 삭제
		int boardfileRow = boardfileMapper.deleteBoardfileByBoardId(board.getBoardId());
				
		return boardRow;
	}
	
	// 추가 액션
	public void addBoard(BoardForm boardForm) { // 0이면 입력 실패, 1이면 입력 성공
		// boardForm --> board, boardfile
		log.debug("▶▶▶▶▶ boardForm: " + boardForm); 
		
		// 1) 
		Board board = boardForm.getBoard(); // boardId가 null
		log.debug("▶▶▶▶▶ board: " + board.getBoardId());  // 0
		boardMapper.insertBoard(board); 
		// 입력 시 만들어진 key값을 리턴받아야 한다. -> 리턴받을 수 없다. -> 매개변수 board의 boardId값을 변경해준다.
		log.debug("▶▶▶▶▶ board: " + board.getBoardId()); // auto increment로 입력된 값
		
		// 2) 
		List<MultipartFile> list = boardForm.getBoardfile();
		if(list != null) {
			for(MultipartFile f : list) {
				Boardfile boardfile = new Boardfile();
				boardfile.setBoardId(board.getBoardId()); // auto increment로 입력된 값
				// test.TXT - newname.txt
				String originalFilename = f.getOriginalFilename();
				int p = originalFilename.lastIndexOf("."); // 4
				String ext = originalFilename.substring(p).toLowerCase(); // .txt
				String prename = UUID.randomUUID().toString().replace("-", "");
				
				String filename = prename+ext;
				boardfile.setBoardfileName(filename); // 이슈 >>> 중복으로 인해 덮어쓰기 가능
				boardfile.setBoardfileSize(f.getSize());
				boardfile.setBoardfileType(f.getContentType());
				log.debug("▶▶▶▶▶ boardfile: " + boardfile);
				
				// 2-1)
				boardfileMapper.insertBoardfile(boardfile); 
				
				// 2-2) 
				// 파일을 저장
				try {
					File temp = new File(""); // 프로젝트 폴더에 빈파일이 만들어진다.
					String path = temp.getAbsolutePath(); // 프로젝트 폴더 // 경로를 매번 바꿔주기 힘ㄷ니까 설정
					f.transferTo(new File(path+"\\src\\main\\webapp\\resource\\"+filename));
				} catch (Exception e) {
					throw new RuntimeException(); // 트라이캐치 안해도 에러가 발생하지 않는다.
				} 
			}
		}
	}
	
	// 1) 상세보기 + 2) 댓글 목록, 수정 폼
	public Map<String, Object> getBoardOne(int boardId) { // 전체적으로 통일하기 위해서 만든다
		log.debug("▶▶▶▶▶ modifyBoard param: " + boardId); 
		// 1) 상세보기
		Map<String, Object> boardMap = boardMapper.selectBoardOne(boardId);
		log.debug("▶▶▶▶▶ boardMap: " + boardMap); 
		
		// 2) boardfile 목록 --> 기존의 겟보드원을 사용함
		List<Boardfile> boardfileList = boardfileMapper.selectBoardfileByBoardId(boardId);
		
		// 3) 댓글 목록
		List<Comment> commentList = commentMapper.selectCommentListByBoard(boardId);
		log.debug("commentList size() : "+ commentList.size());
		
		Map<String, Object> map = new HashMap<>();
		map.put("boardMap", boardMap);
		map.put("boardfileList", boardfileList);
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
