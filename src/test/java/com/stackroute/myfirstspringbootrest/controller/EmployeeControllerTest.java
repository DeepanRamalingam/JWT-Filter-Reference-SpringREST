package com.stackroute.myfirstspringbootrest.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.myfirstspringbootrest.exceptions.EmployeeWithIDAlreadyPresentException;
import com.stackroute.myfirstspringbootrest.exceptions.EmployeeWithTheIDNotPresentException;
import com.stackroute.myfirstspringbootrest.model.Employee;
import com.stackroute.myfirstspringbootrest.service.EmployeeService;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

	private MockMvc mockMvc;
	
	@InjectMocks
	private EmployeeController employeeController;
	
	@Mock
	private EmployeeService employeeService;
	
	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
	}
	
	@Test
	public void testingGetEmployeeByHandlerForValidEmployee() throws Exception {
		
		Employee employee = new Employee(400, "Deepan", 5000, null);
		when(employeeService.getEmployeeById(400)).thenReturn(employee);
	
		mockMvc.perform(MockMvcRequestBuilders.get("/employees/400").contentType(MediaType.APPLICATION_JSON))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().is(200))
			.andExpect(MockMvcResultMatchers.jsonPath("$.id", is(equalTo(400))));
	}
	
	@Test
	public void testingGetEmployeeByHandlerForInValidEmployee() throws Exception {
		
		when(employeeService.getEmployeeById(400)).thenThrow(EmployeeWithTheIDNotPresentException.class);
		mockMvc.perform(MockMvcRequestBuilders.get("/employees/400").contentType(MediaType.APPLICATION_JSON))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().is(404))
			.andExpect(MockMvcResultMatchers.content().string("Employee Not Found"));
	}
	
	
	@Test
	public void testingGetAllEmployeesHandler() throws Exception {
		
		Employee employeeOne = new Employee(505, "Peter", 80000, null);
		Employee employeeTwo = new Employee(506, "Daniel", 120000, null);
		
		List<Employee> employees = new ArrayList<>();
		employees.add(employeeOne);
		employees.add(employeeTwo);
		
		when(employeeService.getAllEmployees()).thenReturn(employees);
		mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().is(200))
			.andExpect(MockMvcResultMatchers.jsonPath("$.length()", is(equalTo(2))))
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].fname", is(equalTo("Peter"))));
	}
	
	@Test
	public void testingAddNewEmployeeHandlerForInvalidEmployee() throws Exception {
		
		Employee employee = new Employee(400, "Deepan", null, null);
		
		ObjectMapper mapper = new ObjectMapper();
		String employeeJsonString = mapper.writeValueAsString(employee);
		
		when(employeeService.addNewEmployee(any())).thenThrow(EmployeeWithIDAlreadyPresentException.class);
		
		mockMvc.perform(post("/employees")
				.contentType(MediaType.APPLICATION_JSON)
				.content(employeeJsonString))
		.andDo(print())
		.andExpect(status().is(409))
		.andExpect(content().string("Failed to create the resource"));
		
	}
	
	@Test
	public void testingAddNewEmployeeHandlerForValidEmployee() throws Exception {
		
		Employee employee = new Employee(400, "Deepan", null, null);
		
		ObjectMapper mapper = new ObjectMapper();
		String employeeJsonString = mapper.writeValueAsString(employee);
		
		when(employeeService.addNewEmployee(any())).thenReturn(employee);
		
		mockMvc.perform(post("/employees")
				.contentType(MediaType.APPLICATION_JSON)
				.content(employeeJsonString))
		.andDo(print())
		.andExpect(status().is(201))
		.andExpect(jsonPath("$.id", is(equalTo(400))));
		
	}
}
