package com.gd.sakila.vo;

import lombok.Data;

@Data
public class FilmCategory {
	private Category category; // 하나의 카테고리에 여러개의 필름이 들어온다
	private Film film;
	private String lastUpdate;
}
