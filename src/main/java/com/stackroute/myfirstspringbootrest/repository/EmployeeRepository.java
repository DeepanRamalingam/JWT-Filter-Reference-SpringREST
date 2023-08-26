package com.stackroute.myfirstspringbootrest.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.stackroute.myfirstspringbootrest.model.Employee;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

//	update employee set salary = 12000 where id=304;
	
//	name
	public List<Employee> findByFname(String fname); 
	
	public List<Employee> findByCompanyAndSalary(String company,int salary);
	
	@Query(value = "select * from employee where company=?",nativeQuery = true)
	public List<Employee> searchEmployee(String company);
}
