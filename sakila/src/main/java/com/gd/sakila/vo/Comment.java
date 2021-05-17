package com.gd.sakila.vo;

import lombok.Data;
//@Data: toString 오버라이드 되어있다.
@Data 
public class Comment {
	int commentId;
	int boardId;
	String username;
	String commentContent;
	String insertDate;
}
