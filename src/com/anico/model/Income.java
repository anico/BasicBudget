package com.anico.model;

public class Income {
	private double amount;
	private short frequency;
	private String description;
	
	public Income(){
		amount = 0;
		frequency = 1;
		description = "";
	}
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public short getFrequency() {
		return frequency;
	}
	public void setFrequency(short frequency) {
		this.frequency = frequency;
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
