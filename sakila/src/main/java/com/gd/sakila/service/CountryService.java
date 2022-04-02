package com.gd.sakila.service;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.controller.HomeController;
import com.gd.sakila.mapper.CountryMapper;
import com.gd.sakila.vo.Country;
import com.gd.sakila.vo.Page;

/*
* 스프링 부팅

----- mapper, service 패키지 -----
1. @Mapper
- CountryMapper 서브 클래스 생성 
- 서브 클래스의 객체를 생성

2. @ Service
- CountryService 객체를 만들 때, countryMapper 객체가 있는지 먼저 확인

3. @Autowired
- 존재한다면, 객체가 자동으로 주입됨 (그래서 null pointer exception이 안남)
- 여기서 객체는 의존성 객체라고도 불림
- 이 기능을 DI라고 부름

4. @Transactional
- 트랜잭션을 처리함
- try, catch, commit, rollback, throws 등의 기능을 처리해줌

----- controller 패키지 -----
1. @Controller
- CountryController 클래스의 객체가 만들어 질 때

2. @Autowired
- spring이 만들어 놓은 객체를 자동으로 주입함

3. @GetMapping
- 웹 상에서 countryList라고 요청값이 들어오면

4. countryList(Model model) 메소드를 실행
- model.addAtrribute 는 request.setAttribute 기능을 해줌
 */

@Service
@Transactional // spring에 트랜잭션 기능이 있다. 어떤 메서드를 실행하다가 에러가 뜨면 그 메서드가 있는 서비스 롤백한다.
public class CountryService {
	// 만들어지지 않는 클래스가 존재하고 사용해야 한다면 생성자로 인해 기다려야한다.
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired 
	private CountryMapper countryMapper; // 스프링 기능 1. countryMapper에 객체를 주입(의존성 주입)
	// spring 부팅 -> Mapper라는 에노테이션이 붙어 있으니 CountryMapper의 서브 클래스를 만듬 -> 서브 클래스의 객체를 만듬 -> 서비스 에노테이션이 있으므로 자동으로 CountryService 객체를 만드는데 그 와중에 Autowired가 있으므로 countryMapper를 먼저 찾아서(없으면 만듬) 객체 주입(bean이라고 부름)
	
	public Map<String, Object> getCountryList(int currentPage, int rowPerPage){
		// CountryMapper countryMapper = new CountryMapper(); // 인터페이스 객체 생성 불가
		// 1. 컨트롤러에서 보내어준 매개값을 가공
		int beginRow = (currentPage -1) * rowPerPage;
		Page page = new Page(); // 컨트롤러는 순수하게 요청 처리하는 일만 해야한다.
		page.setBeginRow(beginRow);
		page.setRowPerPage(rowPerPage);
		
		// 2. dao 호출
		List<Country> list = countryMapper.selectCountryList(page); // 서비스 하나에서 여러개의 디에이오를 호출할 수 있다
		int total = countryMapper.selectCountryTotal();
		
		// 3. dao의 반환값을 가공
		int lastPage = total / rowPerPage;
		if(total % rowPerPage != 0) { // 나누어 떨어지지 않으면
			lastPage += 1;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("lastPage", lastPage);
		return map;
	}
}
