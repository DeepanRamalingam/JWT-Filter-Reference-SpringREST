package com.stackroute.myfirstspringbootrest.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@Entity
public class Employee {

	@Id
	private int id;
	
//	@Column(name = "empName",nullable = false)
	private String fname;
	private Integer salary; 
	
	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDate joiningDate;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<Integer> performanceScore;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Account account;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Company company;
	
	public Employee(int id, String fname, Integer salary, LocalDate joiningDate) {
		this.id = id;
		this.fname = fname;
		this.salary = salary;
		this.joiningDate = joiningDate;
	}
	
	public Employee() {}
	

	
	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public List<Integer> getPerformanceScore() {
		return performanceScore;
	}

	public void setPerformanceScore(List<Integer> performanceScore) {
		this.performanceScore = performanceScore;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

//	public String getCompany() {
//		return company;
//	}
//
//	public void setCompany(String company) {
//		this.company = company;
//	}

	public LocalDate getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", fname=" + fname + ", salary=" + salary + ", joiningDate=" + joiningDate
				+ ", performanceScore=" + performanceScore + ", account=" + account + "]";
	}
	
}
