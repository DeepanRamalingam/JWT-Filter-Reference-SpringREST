package com.stackroute.myfirstspringbootrest.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Company {

	@Id
	private int regno;
	private String name;
	
	@OneToMany(mappedBy = "company")
	@JsonIgnore 
	private List<Employee> employees;
	
	public Company(int regno, String name) {
		this.regno = regno;
		this.name = name;
	}
	
	public Company() {
		
	}

	public int getRegno() {
		return regno;
	}

	public void setRegno(int regno) {
		this.regno = regno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "Company [regno=" + regno + ", name=" + name + ", employees=" + employees + "]";
	}
	
}
