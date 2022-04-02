package com.gd.sakila.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BoardForm {
	private Board board;
	private List<MultipartFile> boardfile;
	
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public List<MultipartFile> getBoardfile() {
		return boardfile;
	}
	public void setBoardfile(List<MultipartFile> boardfile) {
		this.boardfile = boardfile;
	}
}
