package com.anico.model;

import java.util.ArrayList;
import java.util.List;

public class ExpenseLog {

	private List<Expense> expenses;
	private double total;
	private double monthTotal;
	private String description;
	
	public ExpenseLog(){
		expenses = new ArrayList<Expense>();
		total = 0;
		monthTotal = 0;
		description = "";
	}
	
	public List<Expense> getExpenses() {
		return expenses;
	}
	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public double getMonthTotal() {
		return monthTotal;
	}

	public void setMonthTotal(double monthTotal) {
		this.monthTotal = monthTotal;
	}
}
