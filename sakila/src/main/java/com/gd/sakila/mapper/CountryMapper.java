package com.gd.sakila.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper; // CountryMapper.xml에서 selectCountryList라는 쿼리를 찾는다

import com.gd.sakila.vo.Country;
import com.gd.sakila.vo.PageParam;

@Mapper
public interface CountryMapper {
	List<Country> selectCountryList(PageParam pageParam); // 이메소드가 사용할 쿼리가 필요하다 -> 매퍼 안에 만든다
	int selectCountryTotal();
}
