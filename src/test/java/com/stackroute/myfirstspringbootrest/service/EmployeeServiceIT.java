package com.stackroute.myfirstspringbootrest.service;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.stackroute.myfirstspringbootrest.exceptions.EmployeeWithIDAlreadyPresentException;
import com.stackroute.myfirstspringbootrest.exceptions.EmployeeWithTheIDNotPresentException;
import com.stackroute.myfirstspringbootrest.model.Employee;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeServiceIT {
	
	
	@Autowired
	private EmployeeService employeeService;
	
	@Test
	@Order(2)
	public void testingGetEmployeeByIdForValidEmployee() throws EmployeeWithTheIDNotPresentException, EmployeeWithIDAlreadyPresentException {
		
		
		int input = 506;
		
		Employee actual = employeeService.getEmployeeById(input); 
		Assertions.assertNotNull(actual,"should return the employee object for valid employee");
		Assertions.assertEquals(actual.getId(), 506,"id of actual employee object and the expected should be equal for valid employee");
	}
	
	
	@Test
	@Order(1)
	public void testingAddNewEmployeeWithNewEmployee() throws EmployeeWithIDAlreadyPresentException, EmployeeWithTheIDNotPresentException {
		
		Employee employee = new Employee(506, "Deepan", 5000, LocalDate.of(2016, 9, 9));
		
		Employee actual = employeeService.addNewEmployee(employee);
		
		Assertions.assertNotNull(actual);
		Assertions.assertEquals(employee.getId(), actual.getId());
	}
}

//public Employee addNewEmployee(Employee employee) throws EmployeeWithIDAlreadyPresentException {
//	
//	Optional<Employee> empOptional =  empRepo.findById(employee.getId());
//	
//	if (empOptional.isEmpty()) {
//		
//		empRepo.save(employee);
//		return employee;
//	}
//	
//	throw new EmployeeWithIDAlreadyPresentException();
//}






