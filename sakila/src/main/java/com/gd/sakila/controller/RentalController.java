package com.gd.sakila.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.sakila.service.RentalService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class RentalController {
	@Autowired RentalService rentalService;
	
	// 영화 대여
	@GetMapping("/addRental")
	public String addRental(Model model,
			@RequestParam(value="customerId", required = true) Integer customerId) {
		log.debug("▶▶▶▶▶ addRental() customerId: " + customerId);
		
		model.addAttribute("customerId",customerId);
		
		return "addRental";
	}
	
	@PostMapping("/addRental")
	public String addRental(
			@RequestParam(value="inventoryId", required = true) Integer inventoryId,
			@RequestParam(value="customerId", required = true) Integer customerId,
			@RequestParam(value="staffId", required = true) Integer staffId) {
		log.debug("▶▶▶▶▶ addRental() inventoryId: "+inventoryId);
		log.debug("▶▶▶▶▶ addRental() customerId: "+customerId);
		log.debug("▶▶▶▶▶ addRental() staffId: "+staffId);
		
		Map<String, Object> map = new HashMap<>();
		map.put("inventoryId", inventoryId);
		map.put("customerId", customerId);
		map.put("staffId", staffId);
		
		rentalService.addRental(map);
		
		return "redirect:/admin/getCustomerOne?ID=" +customerId;
	}
	
	// 영화 반납
	@GetMapping("/removeRental")
	public String removeRental(Model model,
			@RequestParam(value="customerId", required = true) Integer customerId) {
		log.debug("▶▶▶▶▶ removeRental() customerId: " + customerId);
		
		model.addAttribute("customerId",customerId);
		return "removeRental";
	}
	
	@PostMapping("/removeRental")
	public String removeRental(Model model,
			@RequestParam(value="rentalId", required = true) Integer rentalId,
			@RequestParam(value="customerId", required = true) Integer customerId) {
		log.debug("▶▶▶▶▶ addRental() rentalId: "+rentalId);
		log.debug("▶▶▶▶▶ addRental() customerId: "+customerId);
		
		rentalService.removeRental(customerId);
		
		return "redirect:/admin/getCustomerOne?ID=" +customerId;
	}
}
