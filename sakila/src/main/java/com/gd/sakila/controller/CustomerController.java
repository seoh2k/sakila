package com.gd.sakila.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.sakila.service.CustomerService;
import com.gd.sakila.vo.CustomerList;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class CustomerController {
	@Autowired CustomerService customerService;
	
	@GetMapping("/getCustomerList")
	public String getCustomerList(Model model,
									@RequestParam(value="currentPage", defaultValue = "1") int currentPage,
									@RequestParam(value="rowPerPage", defaultValue = "10") int rowPerPage,
									@RequestParam(value="searchWord", required = false) String searchWord){
		int customerTotal = customerService.getCustomerTotal(searchWord);
		int lastPage = (int)(Math.ceil((double)customerTotal / rowPerPage));
		int beginRow = (currentPage-1) * rowPerPage;
		
		log.debug("▶▶▶▶▶ getCustomerList() customerTotal: "+customerTotal);
		log.debug("▶▶▶▶▶ getCustomerList() lastPage: "+lastPage);
		log.debug("▶▶▶▶▶ getCustomerList() beginRow: "+beginRow);
		
		Map<String, Object> map = new HashMap<>();
		map.put("beginRow", beginRow);
		map.put("rowPerPage", rowPerPage);
		map.put("searchWord", searchWord);
		
		List<CustomerList> customerList = customerService.getCustomerList(map);
		model.addAttribute("customerList", customerList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("rowPerPage", rowPerPage);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("beginRow", beginRow);
		
		return "getCustomerList";
	}
}
