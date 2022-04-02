package com.gd.sakila;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gd.sakila.controller.HomeController;

import jdk.internal.org.jline.utils.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebFilter(urlPatterns = "/admin/*")
public class LoginFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 요청 전
		logger.debug("▶▶▶▶▶ LoginFilter 요청 전");
		HttpSession session = null;
		if(request instanceof HttpServletRequest) {
			session = ((HttpServletRequest)request).getSession();
		}
		/*
		if(session.getAttribute("loginStaff") == null) {
			if(response instanceof HttpServletResponse) {
				((HttpServletResponse)response).sendRedirect("/");
			}
			return;
		}
		*/
	
		chain.doFilter(request, response);
		logger.debug("▶▶▶▶▶ LoginFilter 요청 후");
		// 요청 후
	}
}
