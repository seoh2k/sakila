package com.gd.sakila.vo;

import lombok.Data;
// @Data: toString 오버라이드 되어있다.
@Data 
public class Board {
	private int boardId;
	private String boardPw;
	private String boardTitle;
	private String boardContent;
	private int staffId;
	private String insertDate;
	private String lastUpdate;
}
