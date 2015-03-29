package com.anico.model;

public class Debt {
	
	private double principle;
	private double interestRate;
	private double monthlyPayment;
	private short dueDate;
	private String description;
	
	public Debt(){
		principle = 0;
		interestRate = 0;
		monthlyPayment = 0;
		dueDate = 1;
		description = "";
	}
	
	public double getPrinciple() {
		return principle;
	}
	public void setPrinciple(double principle) {
		this.principle = principle;
	}
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	public double getMonthlyPayment() {
		return monthlyPayment;
	}
	public void setMonthlyPayment(double monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}
	public short getDueDate() {
		return dueDate;
	}
	public void setDueDate(short dueDate) {
		this.dueDate = dueDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString(){
		return description;
	}

}
