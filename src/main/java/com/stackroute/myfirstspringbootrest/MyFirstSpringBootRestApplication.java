package com.stackroute.myfirstspringbootrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.stackroute.myfirstspringbootrest.config.HandlerFilter;
import com.stackroute.myfirstspringbootrest.config.JWTFilter;

@SpringBootApplication
@EnableAspectJAutoProxy
public class MyFirstSpringBootRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyFirstSpringBootRestApplication.class, args);
	}
	
	
	@Bean
	public FilterRegistrationBean<JWTFilter> getJWTFilterRegistrationBean(){
		
		FilterRegistrationBean<JWTFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new JWTFilter());
		filterRegistrationBean.setOrder(2);
		filterRegistrationBean.addUrlPatterns("/employees/*");
		return filterRegistrationBean;
	}
	
	@Bean
	public FilterRegistrationBean<HandlerFilter> getHandlerFilterRegistrationBean(){
		
		FilterRegistrationBean<HandlerFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new HandlerFilter());
		filterRegistrationBean.setOrder(1);
		filterRegistrationBean.addUrlPatterns("/employees/*");
		return filterRegistrationBean;
	}

} 
