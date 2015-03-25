package com.anico.model;

import java.util.ArrayList;
import java.util.List;

public class Entity {
	
	private int id;
	
	private double grossIncome;
	private double netIncome;
	private double potentialIncome;
	private double allowance;
	private double disposableIncome;
	
	private double billTotal;
	
	private double debtTotal;
	private double debtAmountTotal;
	
	private List<Income> incomes;
	private List<Debt> debts;
	private List<Bill> bills;
	private ExpenseLog log;
	private String name;
	
	public Entity(){
		id = -1;
		allowance = -1;
		incomes = new ArrayList<Income>();
		debts = new ArrayList<Debt>();
		bills = new ArrayList<Bill>();
		log = new ExpenseLog();
		setName("New Person");
	}
	
	public void calculateIncome(){
		grossIncome = 0;
		billTotal = 0;
		debtTotal = 0;
		debtAmountTotal = 0;
		
		for(Income income : incomes){
			grossIncome += (income.getAmount()*income.getFrequency());
		}
		netIncome = grossIncome;
		for(Bill bill : bills){
			netIncome -= bill.getMonthlyPayment();
			billTotal += bill.getMonthlyPayment();
		}
		potentialIncome = netIncome;
		for(Debt debt : debts){
			netIncome -= debt.getMonthlyPayment();
			debtTotal += debt.getMonthlyPayment();
			debtAmountTotal += debt.getPrinciple();
		}
	}
	
	public List<Income> getIncomes() {
		return incomes;
	}
	public void setIncomes(List<Income> incomes) {
		this.incomes = incomes;
	}
	public List<Debt> getDebts() {
		return debts;
	}
	public void setDebts(List<Debt> debts) {
		this.debts = debts;
	}
	public List<Bill> getBills() {
		return bills;
	}
	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}
	public ExpenseLog getLog() {
		return log;
	}
	public void setLog(ExpenseLog log) {
		this.log = log;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getGrossIncome() {
		return grossIncome;
	}

	public void setGrossIncome(double grossIncome) {
		this.grossIncome = grossIncome;
	}

	public double getNetIncome() {
		return netIncome;
	}

	public void setNetIncome(double netIncome) {
		this.netIncome = netIncome;
	}

	public double getPotentialIncome() {
		return potentialIncome;
	}

	public void setPotentialIncome(double potentialIncome) {
		this.potentialIncome = potentialIncome;
	}

	public double getBillTotal() {
		return billTotal;
	}

	public void setBillTotal(double billTotal) {
		this.billTotal = billTotal;
	}

	public double getDebtTotal() {
		return debtTotal;
	}

	public void setDebtTotal(double debtTotal) {
		this.debtTotal = debtTotal;
	}

	public double getDebtAmountTotal() {
		return debtAmountTotal;
	}

	public void setDebtAmountTotal(double debtAmountTotal) {
		this.debtAmountTotal = debtAmountTotal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAllowance() {
		return allowance;
	}

	public void setAllowance(double allowance) {
		this.allowance = allowance;
	}

	public double getDisposableIncome() {
		return disposableIncome;
	}

	public void setDisposableIncome(double disposableIncome) {
		this.disposableIncome = disposableIncome;
	}
	public ExpenseLog getExpenseLog(){
		return log;
	}
	public void setExpenseLog(ExpenseLog log){
		this.log = log;
	}
	
	@Override
	public String toString(){
		return name;
	}

}
