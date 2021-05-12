package com.gd.sakila.vo;

import lombok.Data;

@Data // 럼복
public class PageParam {
	private int rowPerPage;
	private int beginRow;
}
