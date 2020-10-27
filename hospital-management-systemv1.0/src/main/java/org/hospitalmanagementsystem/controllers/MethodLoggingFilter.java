package org.hospitalmanagementsystem.controllers;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
@WebFilter(urlPatterns = "*.ppl")
public class MethodLoggingFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest myRequest = (HttpServletRequest)request;
		Object user = myRequest.getSession().getAttribute("user");
		if(user != null) {
			chain.doFilter(request,response);
		}else {
			request.setAttribute("message", "You must login first");
			request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
		}
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
}
