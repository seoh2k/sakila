package com.gd.sakila.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.sakila.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
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
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("rowPerPage", rowPerPage);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("beginRow", beginRow);
		
		return "getCustomerList";
	}
}
