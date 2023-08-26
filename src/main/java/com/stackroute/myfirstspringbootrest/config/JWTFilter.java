package com.stackroute.myfirstspringbootrest.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JWTFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		//		chain.doFilter(request, response);

		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		String authHeader = req.getHeader("Authorization");

		if(req.getMethod().equals("OPTIONS")) {

			res.setStatus(HttpStatus.OK.value());
			chain.doFilter(req, res);
		} else if(authHeader == null || !authHeader.startsWith("Bearer ")) {

			throw new ServletException();
		}

		String token = authHeader.split(" ")[1];
		System.out.println(token);

		//		parsing the token to decrypt using the secret key and the algorithm used to encrypt to read the claims

		String claim = "";
		Claims claims = Jwts.parser()
				.setSigningKey("stackroute")
				.parseClaimsJws(token)
				.getBody();
		claim = claims.getSubject();

		req.setAttribute("claim",claim );
		chain.doFilter(req, res);		

	}

}
