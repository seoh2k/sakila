package com.gd.sakila.vo;

import lombok.Data;

@Data // 세터 게터 만들 필요가 없게 한다
public class Country { 
	private int countryId;
	private String country;
	private String lastUpdate;
	
}
