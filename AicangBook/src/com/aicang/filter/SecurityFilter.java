package com.aicang.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SecurityFilter implements Filter{

	private FilterConfig config;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		this.config = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		String url = req.getServletPath();
		if(url.endsWith(".jsp") && url.startsWith("/") && !url.endsWith("login_error.jsp") && !url.endsWith("login.jsp")){
			if(session.getAttribute("user") == null){
				resp.sendRedirect(req.getContextPath() + "/admin/login_error.jsp");
			}
		}
		chain.doFilter(req, resp);
//		if(session.getAttribute("user") != null){
//			chain.doFilter(req, resp);
//		} else {
//			resp.sendRedirect(req.getContextPath() + "/login_error.jsp");			
//		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.config = filterConfig;
	}

	
}
