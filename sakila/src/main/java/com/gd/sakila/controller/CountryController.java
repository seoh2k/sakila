package com.gd.sakila.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.sakila.service.CountryService;

@Controller // 컴포넌트다. 객체가 자동으로 만들어진다. 서블릿처럼 행동할 수 있는 클래스를 자동으로 상속받는다
public class CountryController {
	@Autowired
	private CountryService countryService;
	
	@GetMapping("/countryList")
	public String countryList(Model model, 
								@RequestParam(value="currentPage", defaultValue = "1") int currentPage,
								@RequestParam(value="rowPerPage", defaultValue = "10") int rowPerPage){
		Map<String, Object> map = countryService.getCountryList(currentPage, rowPerPage);
		model.addAttribute("list", map.get("list")); // request.setAttribute와 동일한 기능
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		return "countryList"; // 디폴트 값이 포워딩
	}

}
