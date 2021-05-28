package com.gd.sakila.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gd.sakila.vo.Language;

@Mapper
public interface LanguageMapper {
	List<Language> selectLanguageList();
}
