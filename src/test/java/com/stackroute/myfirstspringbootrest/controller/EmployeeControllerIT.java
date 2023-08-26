package com.stackroute.myfirstspringbootrest.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.myfirstspringbootrest.model.Account;
import com.stackroute.myfirstspringbootrest.model.Company;
import com.stackroute.myfirstspringbootrest.model.Employee;

@SpringBootTest
public class EmployeeControllerIT {

	private MockMvc mockMvc;

	@Autowired
	private EmployeeController employeeController;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
	}

	@Test
	public void testingGetEmployeeByHandlerForInValidEmployee() throws Exception {


		mockMvc.perform(MockMvcRequestBuilders.get("/employees/400").contentType(MediaType.APPLICATION_JSON))
		.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().is(404))
		.andExpect(MockMvcResultMatchers.content().string("Employee Not Found"));
	}

	@Test
	public void testingAddEmployeeHandlerForValidEmployee() throws Exception {
		
		List<Integer> scores = new ArrayList<>();
		scores.add(78);
		scores.add(88);
		scores.add(85);
		
		Employee employee = new Employee(400, "Deepan", 34000, LocalDate.now());
		employee.setPerformanceScore(scores);
		employee.setAccount(new Account(0, "ABC", 0));
		employee.setCompany(new Company(120012, "CGI"));

		ObjectMapper mapper = new ObjectMapper();
		String employeeJsonString = mapper.writeValueAsString(employee);

		mockMvc.perform(MockMvcRequestBuilders.get("/employees/400").contentType(MediaType.APPLICATION_JSON))
		.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().is(404))
		.andExpect(MockMvcResultMatchers.content().string("Employee Not Found"));
		
		mockMvc.perform(post("/employees")
				.contentType(MediaType.APPLICATION_JSON)
				.content(employeeJsonString))
		.andDo(print())
		.andExpect(status().is(201))
		.andExpect(jsonPath("$.id", is(equalTo(400))));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/employees/400").contentType(MediaType.APPLICATION_JSON))
		.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().is(200))
		.andExpect(MockMvcResultMatchers.jsonPath("$.id", is(equalTo(400))));
	}
}
