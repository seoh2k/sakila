package com.gd.sakila.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gd.sakila.vo.Boardfile;

@Mapper
public interface BoardfileMapper { // 매핑파일(xml)에 있는 sql 쿼리문을 호출하기 위한 인터페이스.
	int deleteBoardfileOne(int boardfileId);
	int insertBoardfile(Boardfile boardfile);
	List<Boardfile> selectBoardfileByBoardId(int boardId);
	int deleteBoardfileByBoardId(int boardId);
}
