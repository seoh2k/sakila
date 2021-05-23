package com.gd.sakila.vo;

import lombok.Data;

@Data // 럼복
public class Page {
	private int rowPerPage;
	private int beginRow;
	private String searchWord;
}