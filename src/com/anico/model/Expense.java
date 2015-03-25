package com.anico.model;

import java.util.Date;

public class Expense {
	private double amount;
	private String description;
	private Date purchaseDate;
	
	public Expense(){
		amount = 0;
		description = "";
		purchaseDate = new Date();
	}
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

}
