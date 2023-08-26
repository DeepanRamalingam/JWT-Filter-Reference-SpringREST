package com.stackroute.myfirstspringbootrest.service;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.stackroute.myfirstspringbootrest.exceptions.EmployeeWithTheIDNotPresentException;
import com.stackroute.myfirstspringbootrest.model.Employee;
import com.stackroute.myfirstspringbootrest.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
	
	@InjectMocks
	private EmployeeService employeeService;
	
	@Mock
	private EmployeeRepository employeeRepository;
	
	
	@Test
	public void testingGetEmployeeByIdForValidEmployee() throws EmployeeWithTheIDNotPresentException {
		
		int input = 400;
		Employee expected = new Employee(400, "Deepan", 2000, null);
		Optional<Employee> empOptional = Optional.of(expected);
		
		Mockito.when(employeeRepository.findById(400)).thenReturn(empOptional);
		
		Employee actual = employeeService.getEmployeeById(input); 
		Assertions.assertNotNull(actual);
		Assertions.assertEquals(actual.getId(), expected.getId());
		
		Mockito.verify(employeeRepository,Mockito.times(1)).findById(400);
	}
	
	@Test
	public void testingGetEmployeeByIdForInvalidEmployee() throws EmployeeWithTheIDNotPresentException {
		
		int input = 400;
//		Employee expected = new Employee(400, "Deepan", 2000, null);
		Optional<Employee> empOptional = Optional.empty();
		
		Mockito.when(employeeRepository.findById(input)).thenReturn(empOptional);
		
		Assertions.assertThrows(EmployeeWithTheIDNotPresentException.class, ()->{
			employeeService.getEmployeeById(input);
		});
		
		Mockito.verify(employeeRepository,Mockito.times(1)).findById(400);
	}


}
