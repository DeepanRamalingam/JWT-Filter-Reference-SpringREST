package com.stackroute.myfirstspringbootrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.myfirstspringbootrest.exceptions.EmployeeWithIDAlreadyPresentException;
import com.stackroute.myfirstspringbootrest.exceptions.EmployeeWithTheIDNotPresentException;
import com.stackroute.myfirstspringbootrest.model.Employee;
import com.stackroute.myfirstspringbootrest.service.EmployeeService;

@RestController
@RequestMapping(value = "/")
public class EmployeeController {
	
	@Autowired
	private EmployeeService empService;

//	private Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
//	@RequestMapping(value = "/",method = RequestMethod.GET)
//	@GetMapping(value = "/")
//	public Map<String, String> homeHandler() {
//		
//		Map<String, String> map = new HashMap<>();
//		map.put("fname", "Deepan");
//		map.put("age", "35");
//		map.put("company", "stackroute");
//		return map;
//	}
	
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployeesHandler() {
//		logger.debug("get all employees started its execution");
		List<Employee> employees =  empService.getAllEmployees();
		return new ResponseEntity<List<Employee>>(employees,HttpStatus.OK);
	}
	
	@PostMapping(value = "/employees")
	public ResponseEntity<?> addNewEmployee( @RequestBody Employee employee){
		
		ResponseEntity<?> responseEntity;
		
		try {
			Employee newEmployee = empService.addNewEmployee(employee);
			responseEntity = new ResponseEntity<Employee>(newEmployee,HttpStatus.CREATED);
		} catch (EmployeeWithIDAlreadyPresentException e) {
			responseEntity = new ResponseEntity<String>("Failed to create the resource",HttpStatus.CONFLICT);
		}
		
		return responseEntity;
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<?> getEmployeeByIdHandler( @PathVariable("id") int empId){
//		logger.error("fatal error");
//		logger.info("getEmployedByIDHandler started ists execution with arguments: "+empId);
		
		ResponseEntity<?> responseEntity;
		
		try {
			Employee employee = empService.getEmployeeById(empId);
			responseEntity = new ResponseEntity<Employee>(employee,HttpStatus.OK);
		} catch (EmployeeWithTheIDNotPresentException e) {
//			logger.warn("Exception "+e+" is thrown in getEmployeeByIdHander");
			responseEntity = new ResponseEntity<String>("Employee Not Found",HttpStatus.NOT_FOUND);
		}
		
//		logger.info("getEmployeeByIDHandler completed its execution");
//		logger.info("value returned by getEmployeeByIDHander: "+responseEntity.toString());
		return responseEntity;
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<?> updateEmployee(@PathVariable("id") int empId, @RequestBody Employee employee){
		
ResponseEntity<?> responseEntity;
		
		try {
			employee.setId(empId);
			Employee updatedEmployee = empService.updateEmployeeById(employee);
			responseEntity = new ResponseEntity<Employee>(updatedEmployee,HttpStatus.OK);
		} catch (EmployeeWithTheIDNotPresentException e) {
			responseEntity = new ResponseEntity<String>("Employee Not Found",HttpStatus.NOT_FOUND);
		}
		
		return responseEntity;
		
	}
	
//	add a handler for delete an employee by its id
//	invoke the corresponding service method using the id for deleting
//	if the service method doesn't throw any exception return the response as employee deleted with the status as ok
//	else if service method throws the exception catch the exception and return an error response with status as failed
	
	@GetMapping("/employees/byName")
	public ResponseEntity<?> getEmployeesByNameHandler(@RequestParam("fname") String fname){
		System.out.println("Employees By Fname invoked");
		ResponseEntity<?> responseEntity;
		List<Employee> employees = empService.getEmployeesByFname(fname);
		responseEntity = new ResponseEntity<List<Employee>>(employees,HttpStatus.OK);
		return responseEntity;
	}

}

//resource
//response headers
//response status

//response entity


//	/employees --  GET
//	/employees -- POST
//	/employees/{id} -- GET
//	/employees/{id} -- PUT --- 
//  /employees/{id} -- DELETE






//spring-orm   --- version
//hibernate -core --- version
//apache-dbcb2
//mysql

//starter-data-jpa




// get all employees
//add employee
//get by id
//get employee by company
//get employee by salary
//delete employee
//update employee

//Account - hibernate mappings

//mongodb

//testing all the layers 
//CI
//cors


//Microservice



