package com.gd.sakila.service;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.gd.sakila.mapper.BoardfileMapper;
import com.gd.sakila.vo.Boardfile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional // 쪼개질 수 없는 하나의 단위작업, 한번에 이루어지는 작업의 단위
public class BoardfileService {
	@Autowired BoardfileMapper boardfileMapper; 
	
	// boardOne에서 파일 추가
	// MultipartFile은 스프링에서 업로드한 파일을 표현할 때 사용, 업로드한 파일의 이름, 실제 데이터, 파일 크기 등을 구할 수 있다.
	public int addBoardfile(MultipartFile multipartFile, int boardId) {
		
		// 1) 물리적 파일 저장
		File temp = new File("");
		// 프로젝트 경로
		String path = temp.getAbsolutePath(); 
		// 확장자
		// lastIndexOf: 문자열에서 탐색하는 문자열이 마지막으로 등장하는 위치에 대한 index를 반환한다.
		int p = multipartFile.getOriginalFilename().lastIndexOf(".");
		String ext = multipartFile.getOriginalFilename().substring(p); // substring: 확장자 앞 .의 앞을 잘라낸다.
		// 확장자를 제외한 파일 이름
		// UUID: 업로드된 파일명의 중복을 방지하기 위해 파일명을 변경할 때 사용.
		// randomUUID: 유일한 식별자를 생성한다.
		// toString: 반환되는 UUID가 객체이므로 문자열 표현을 얻기 위해 사용하는 메서드
		String prename = UUID.randomUUID().toString().replace("-", "");
		File file = new File(path + "\\src\\main\\webapp\\resource\\" + prename+ext);
		try {
			multipartFile.transferTo(file); // transferTo: 업로드한 파일을 특정파일로 저장하고 싶을 때 사용
		} catch (Exception e) {
			throw new RuntimeException(); //강제로 RuntimeException 예외 발생시키기
		}  

		// 2) db 저장
		Boardfile boardfile = new Boardfile();
		boardfile.setBoardId(boardId);
		boardfile.setBoardfileName(prename+ext);
		boardfile.setBoardfileSize(multipartFile.getSize());
		boardfile.setBoardfileType(multipartFile.getContentType());
		
		int row = boardfileMapper.insertBoardfile(boardfile);
		return row; 
	}
	
	// 추가된 파일 삭제
	public int removeBoardfileOne(Boardfile boardfile) {
		log.debug("▶▶▶▶▶ removeBoardfileOne boardfile: " + boardfile);
		
		// 1) 물리적 파일 삭제
		File temp = new File("");
		String path = temp.getAbsolutePath(); // 현재 프로젝트의 폴더위치 구하기 위해서
		File file = new File(path + "\\src\\main\\webapp\\resource" + boardfile.getBoardfileName());
		if(file.exists()){
			log.debug("▶▶▶▶▶ removeBoardfileOne if문...");
			file.delete();
		}
		// 2) db 삭제
		int row = boardfileMapper.deleteBoardfileOne(boardfile.getBoardfileId());
		log.debug("▶▶▶▶▶ 삭제성공(1), 삭제실패(0): " + row);
		
		return row;
	}
}
