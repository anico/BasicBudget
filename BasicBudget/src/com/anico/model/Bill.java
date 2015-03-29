package com.anico.model;

public class Bill {

	private double monthlyPayment;
	private short dueDate;
	private String description;
	
	public Bill(){
		monthlyPayment = 0;
		dueDate = 1;
		description = "";
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
