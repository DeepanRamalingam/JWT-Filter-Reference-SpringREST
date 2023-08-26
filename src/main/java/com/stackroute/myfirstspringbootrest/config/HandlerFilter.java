package com.stackroute.myfirstspringbootrest.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HandlerFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletResponse res = (HttpServletResponse)response;
		Map<String, String> errorResponse = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			chain.doFilter(request, res);
		}catch (Exception e) {
			
			errorResponse.put("message", "Missing Header or Invalid token"); 
			errorResponse.put("error", e.toString());
			String errorResponseString = mapper.writeValueAsString(errorResponse);
			res.setStatus(HttpStatus.FORBIDDEN.value());
			res.getWriter().write(errorResponseString);
//			chain.doFilter(request, res);
		}
	}

}
