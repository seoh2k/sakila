package com.gd.sakila.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.mapper.LanguageMapper;
import com.gd.sakila.vo.Language;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service // 붙어있어야 객체가 만들어짐
@Transactional // spring에 트랜잭션기능이 있다. 어떤 메서드를 실행하다가 에러가뜨면 그 메서드가 있는 서비스 롤백
public class LanguageService {
	@Autowired LanguageMapper languageMapper;
	public List<Language> getLanguageList(){
		return languageMapper.selectLanguageList();
	}
}
