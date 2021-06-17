package com.gd.sakila.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.sakila.service.CustomerService;
import com.gd.sakila.service.RentalService;
import com.gd.sakila.vo.Customer;
import com.gd.sakila.vo.CustomerList;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class CustomerController {
	@Autowired CustomerService customerService;
	@Autowired RentalService rentalService;
	
	@GetMapping("/addCustomer")
	public String addCustomer() {
		return "addCustomer"; // 포워딩
	}
	
	@PostMapping("/addCustomer")
	public String addCustomer(Customer customer) {
		log.debug("▶▶▶▶▶ addCustomer() customer: "+customer);
		customerService.addCustomer(customer);
		return "redirect:/admin/getCustomerList";
	}
	
	@GetMapping("/getCustomerOne")
	public String getCustomerOne(Model model, 
			@RequestParam(value="ID", required = true) Integer ID,
			@RequestParam(value="currentPage", defaultValue = "1") int currentPage,
			@RequestParam(value="rowPerPage", defaultValue = "10") int rowPerPage,
			@RequestParam(value="searchWord", required = false) String searchWord) {
		log.debug("▶▶▶▶▶ getCustomerOne() ID: "+ID);
		log.debug("▶▶▶▶▶ getCustomerOne() currentPage: "+currentPage);
		log.debug("▶▶▶▶▶ getCustomerOne() rowPerPage: "+rowPerPage);
		log.debug("▶▶▶▶▶ getCustomerOne() searchWord: "+searchWord);
		
		int rentalTotal = rentalService.getRentalTotal(searchWord);
		int lastPage = (int)(Math.ceil((double)rentalTotal / rowPerPage));
		int beginRow = (currentPage-1) * rowPerPage;
		
		log.debug("▶▶▶▶▶ getCustomerOne() rentalTotal: "+rentalTotal);
		log.debug("▶▶▶▶▶ getCustomerOne() lastPage: "+lastPage);
		log.debug("▶▶▶▶▶ getCustomerOne() beginRow: "+beginRow);
		
		Map<String, Object> map = new HashMap<>();
		map.put("beginRow", beginRow);
		map.put("rowPerPage", rowPerPage);
		map.put("searchWord", searchWord);
		
		// 고객 상세보기
		Map<String, Object> customerOne = customerService.getCustomerOne(ID);
		log.debug("▶▶▶▶▶ getCustomerOne() customerOne: "+customerOne);
		// 대여리스트
		List<Map<String, Object>> rentalList = rentalService.getRentalList(map);
		log.debug("▶▶▶▶▶ getCustomerOne() rentalList: "+rentalList);
		
		model.addAttribute("customerOne", customerOne);
		model.addAttribute("ID", ID);
		model.addAttribute("rentalList", rentalList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("rowPerPage", rowPerPage);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("beginRow", beginRow);
		
		return "getCustomerOne";
	}
	
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
		List<Map<String, Object>> blackCustomerList = customerService.getBlackCustomerList();
		model.addAttribute("customerList", customerList);
		model.addAttribute("blackCustomerList", blackCustomerList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("rowPerPage", rowPerPage);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("beginRow", beginRow);
		
		return "getCustomerList";
	}
}
