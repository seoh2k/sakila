package com.gd.sakila.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.sakila.service.StaffService;
import com.gd.sakila.vo.StaffListView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class StaffController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired StaffService staffService;

	@GetMapping("/getStaffList")
	public String getStaffList(Model model,
			@RequestParam(value="currentPage", defaultValue = "1") int currentPage,
			@RequestParam(value = "rowPerPage", defaultValue = "10") int rowPerPage,
			@RequestParam(value="searchWord", required = false)String searchWord) {

		logger.debug("currentPage: "+ currentPage);
		logger.debug("rowPerPage: "+ rowPerPage);
		logger.debug("searchWord: "+ searchWord);

		List<StaffListView> staffList = staffService.getStaffList(currentPage, rowPerPage, searchWord);

		logger.debug("▶▶▶▶▶▶▶ StaffList Size : "+staffList.size());

		model.addAttribute("searchWord", searchWord);
		model.addAttribute("currentPage", currentPage);
		//model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("staffList", staffList);

		return "getStaffList";
	}
}