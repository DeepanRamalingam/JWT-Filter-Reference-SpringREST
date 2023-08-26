package com.stackroute.myfirstspringbootrest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.myfirstspringbootrest.exceptions.EmployeeWithIDAlreadyPresentException;
import com.stackroute.myfirstspringbootrest.exceptions.EmployeeWithTheIDNotPresentException;
import com.stackroute.myfirstspringbootrest.model.Employee;
import com.stackroute.myfirstspringbootrest.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository empRepo;
	
	public List<Employee> getAllEmployees(){
		
		return empRepo.findAll();
	}
	
	public Employee addNewEmployee(Employee employee) throws EmployeeWithIDAlreadyPresentException {
		
		Optional<Employee> empOptional =  empRepo.findById(employee.getId());
		
		if (empOptional.isEmpty()) {
			
			empRepo.save(employee);
			return employee;
		}
		
		throw new EmployeeWithIDAlreadyPresentException();
	}
	
	public Employee getEmployeeById(int id) throws EmployeeWithTheIDNotPresentException {
		Optional<Employee> empOptional =  empRepo.findById(id);
		if(empOptional.isPresent()) {
			return empOptional.get();
		}
		throw new EmployeeWithTheIDNotPresentException();
	}
	
	public Employee updateEmployeeById(Employee employee) throws EmployeeWithTheIDNotPresentException {
		
		Optional<Employee> empOptional =  empRepo.findById(employee.getId());
		
		if(empOptional.isPresent()) {
			empRepo.save(employee);
			return employee;
		}
		
		throw new EmployeeWithTheIDNotPresentException();		
	}

	public List<Employee> getEmployeesByFname(String fname) {
	
		return empRepo.findByFname(fname);
	}
	
	
// implement the service method for deleting
//	find the employee is present or not
//	if the employee is present delete using corresponding repo method
//	else throw employee not found exception

}
