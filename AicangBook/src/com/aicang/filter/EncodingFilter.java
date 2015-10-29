package com.aicang.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class EncodingFilter implements Filter {

	private FilterConfig config = null;

	private String defaultCharset = "UTF-8";

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String charset = config.getInitParameter("charset");
		if(charset==null){
			charset = defaultCharset;
		}
		
		req.setCharacterEncoding(charset);
		resp.setCharacterEncoding(charset);
		
		CharacterEncodingRequest characterEnconding = new CharacterEncodingRequest(req);
		chain.doFilter(characterEnconding, resp);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.config = config;
	}

}


/**
 * 包装request类，主要是过滤get方法提交的乱码，重写getParameter方法
 * @author YOU
 *
 */
class CharacterEncodingRequest extends HttpServletRequestWrapper {

	private HttpServletRequest request;

	public CharacterEncodingRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}

	@Override
	public String getParameter(String name) {
		try {
			String value = this.request.getParameter(name);
			if (value == null)
				return null;
			if (this.request.getMethod().equalsIgnoreCase("get")) {
				value = new String(value.getBytes("ISO8859-1"),
						this.request.getCharacterEncoding());
			}
			return value;
		} catch (Exception e) {
			return null;
		}
	}

}