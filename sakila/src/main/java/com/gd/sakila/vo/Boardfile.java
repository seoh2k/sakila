package com.gd.sakila.vo;

import lombok.Data;

@Data
public class Boardfile {
	private int boardfileId;
	private int boardId;
	private String boardfileName;
	private String boardfileType;
	private long boardfileSize;
	
	public int getBoardfileId() {
		return boardfileId;
	}
	public void setBoardfileId(int boardfileId) {
		this.boardfileId = boardfileId;
	}
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public String getBoardfileName() {
		return boardfileName;
	}
	public void setBoardfileName(String boardfileName) {
		this.boardfileName = boardfileName;
	}
	public String getBoardfileType() {
		return boardfileType;
	}
	public void setBoardfileType(String boardfileType) {
		this.boardfileType = boardfileType;
	}
	public long getBoardfileSize() {
		return boardfileSize;
	}
	public void setBoardfileSize(long boardfileSize) {
		this.boardfileSize = boardfileSize;
	}
}
