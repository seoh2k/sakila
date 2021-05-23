package com.gd.sakila.mapper;

import java.util.List;


import org.apache.ibatis.annotations.Mapper; // CountryMapper.xml에서 selectCountryList라는 쿼리를 찾는다

import com.gd.sakila.vo.Country;
import com.gd.sakila.vo.Page;
// 인터페이스는 내부에 메소드 본체를 가질 수 없다. 필요한 메소드 헤더만 정의하고 이를 구현하는 클래스에서 본체를 구현할 수 있다.
@Mapper
public interface CountryMapper { 
	List<Country> selectCountryList(Page page); // 이메소드가 사용할 쿼리가 필요하다 -> 매퍼 안에 만든다
	int selectCountryTotal();
}