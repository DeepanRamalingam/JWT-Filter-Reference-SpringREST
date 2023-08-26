package com.stackroute.myfirstspringbootrest.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Account {
	
	@Id
	private int accno;
	private String bank;
	private double balance;
	public Account(int accno, String bank, double balance) {
		this.accno = accno;
		this.bank = bank;
		this.balance = balance;
	}
	
	@OneToOne(mappedBy = "account")
	@JsonIgnore
	private Employee employee;
	
	public Account() {}
	
	

	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}



	public int getAccno() {
		return accno;
	}

	public void setAccno(int accno) {
		this.accno = accno;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [accno=" + accno + ", bank=" + bank + ", balance=" + balance + "]";
	}
}

//one to one
//one to many
//many to one
//many to many




