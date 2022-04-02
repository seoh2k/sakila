package com.gd.sakila.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gd.sakila.service.StaffService;
import com.gd.sakila.vo.Staff;

import lombok.extern.slf4j.Slf4j;

@Slf4j // 11열 대신 사용한다
@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired StaffService staffService;
	
	// Logger log = LoggerFactory.getLogger(this.getClass()); // (HomeController.class)
	@GetMapping({"/", "/home", "/index"})
	public String home() {
		// System.out.println("home...");
		logger.debug("test");
		return "home"; // 로그인 만들 예정
	}
	
	@GetMapping("/admin/logout")
	public String logout(HttpSession session, Staff staff) {
		session.invalidate();
		return "redirect:/";
		
	}
	
	@PostMapping("/login")
	public String login(HttpSession session, Staff staff) { // 스프링에서 서블릿 세션을 직접 사용한다, 컨트롤러 메서드의 매개변수는 DI 대상이다.
		logger.debug("▶▶▶▶▶login() param staff : " + staff.toString());
		
		Staff loginStaff = staffService.login(staff);
		logger.debug("▶▶▶▶▶login() return loginStaff : " + loginStaff.toString());
		
		if(loginStaff != null) { // 로그인 실패
			session.setAttribute("loginStaff", loginStaff); // new Staff();
		}
		return "redirect:/";
	}
}
