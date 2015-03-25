package com.anico.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.anico.model.Bill;
import com.anico.model.Debt;
import com.anico.model.Entity;
import com.anico.model.Expense;
import com.anico.model.ExpenseLog;
import com.anico.model.Income;
import com.anico.xmlutil.model.IXMLAttribute;
import com.anico.xmlutil.model.XMLAttribute;
import com.anico.xmlutil.util.XMLReader;
import com.anico.xmlutil.util.XMLWriter;

public class XMLUtil {
	
	public static boolean modified = false;
	
	private static String path = "C:\\Development\\Files\\BudgetData.xml";
	
	public static void save(List<Entity> people){
		
		XMLWriter writer = new XMLWriter("C:\\Development\\Files\\test.xml");
		for(int i = 0; i < people.size(); i++){
			writer.writeAttribute(generateAttribute(people.get(i)));
		}
		writer.close();
	}
	
	public static List<Entity> load(){
		List<Entity> ret = new ArrayList<Entity>();
		
		XMLReader reader = new XMLReader();

		List<IXMLAttribute> attrs = reader.readFile(path);
		
		for(IXMLAttribute attr : attrs){
			Entity entity = parseEntityData(attr);
			if(entity != null){
				entity.calculateIncome();
				ret.add(entity);
			}
		}
		return ret;
	}

	private static Entity parseEntityData(IXMLAttribute entity){
		
		Entity ret = new Entity();
		
		for(IXMLAttribute attribute : entity.getSubTags()){
			
			XMLAttribute attr = (XMLAttribute) attribute;
			
			if(attr.getName().equals("Name")){
				ret.setName(attr.getValue());
			}else if(attr.getName().equals("Income")){
				ret.getIncomes().add(parseIncomeData(attr));
			}else if(attr.getName().equals("Debt")){
				ret.getDebts().add(parseDebtData(attr));
			}else if(attr.getName().equals("Bill")){
				ret.getBills().add(parseBillData(attr));
			}else if(attr.getName().equals("ExpenseLog")){
				ret.setExpenseLog(parseExpenseLog(attr));
			}else{
				System.err.println("Unknown XML Tag <"+attr.getName()+"> found");
			}
		}
		
		return ret;
	}
	
	private static Income parseIncomeData(XMLAttribute attr){
		
		Income income = new Income();
		
		for(IXMLAttribute attribute : attr.getSubTags()){
			if(attribute.getName().equals("Amount")){
				income.setAmount(Double.parseDouble(attribute.getValue()));
			}else if(attribute.getName().equals("Frequency")){
				income.setFrequency(Short.parseShort(attribute.getValue()));
			}else if(attribute.getName().equals("Description")){
				income.setDescription(attribute.getValue());
			}
			
		}
		
		return income;
	}
	
	private static Debt parseDebtData(XMLAttribute attr){
		
		Debt debt = new Debt();
		
		for(IXMLAttribute attribute : attr.getSubTags()){
			if(attribute.getName().equals("Principle")){
				debt.setPrinciple(Double.parseDouble(attribute.getValue()));
			}else if(attribute.getName().equals("InterestRate")){
				debt.setInterestRate(Double.parseDouble(attribute.getValue()));
			}else if(attribute.getName().equals("MonthlyPayment")){
				debt.setMonthlyPayment(Double.parseDouble(attribute.getValue()));
			}else if(attribute.getName().equals("DueDate")){
				debt.setDueDate(Short.parseShort(attribute.getValue()));
			}else if(attribute.getName().equals("Description")){
				debt.setDescription(attribute.getValue());
			}
			
		}
		
		return debt;
	}
	
	private static Bill parseBillData(XMLAttribute attr){

		Bill bill = new Bill();
		
		for(IXMLAttribute attribute : attr.getSubTags()){
			if(attribute.getName().equals("MonthlyPayment")){
				bill.setMonthlyPayment(Double.parseDouble(attribute.getValue()));
			}else if(attribute.getName().equals("Description")){
				bill.setDescription(attribute.getValue());
			}else if(attribute.getName().equals("DueDate")){
				bill.setDueDate(Short.parseShort(attribute.getValue()));
			}
			
		}
		
		return bill;
	}
	
	private static ExpenseLog parseExpenseLog(XMLAttribute attr){
		
		ExpenseLog log = new ExpenseLog();
		
		for(IXMLAttribute attribute : attr.getSubTags()){
			if(attribute.getName().equals("Expense")){
				log.getExpenses().add(parseExpense(attribute));
			}else if(attribute.getName().equals("Description")){
				log.setDescription(attr.getValue());
			}
		}
		
		return log;
	}
	
	@SuppressWarnings("deprecation")
	private static Expense parseExpense(IXMLAttribute attr){
		
		Expense expense = new Expense();
		
		for(IXMLAttribute attribute : attr.getSubTags()){
			if(attribute.getName().equals("Amount")){
				expense.setAmount(Double.parseDouble(attribute.getValue()));
			}else if(attribute.getName().equals("Description")){
				expense.setDescription(attribute.getValue());
			}else if(attribute.getName().equals("PurchaseDate")){
				expense.setPurchaseDate(new Date(attribute.getValue()));
			}
		}
		
		return expense;
	}

	private static XMLAttribute generateAttribute(Entity entity){
		XMLAttribute attr = new XMLAttribute();
		
		attr.setName("Entity");
		attr.getSubTags().add(new XMLAttribute("Name", entity.getName()));
		for(Income income : entity.getIncomes()){
			attr.getSubTags().add(generateAttribute(income));
		}
		for(Debt debt : entity.getDebts()){
			attr.getSubTags().add(generateAttribute(debt));
		}
		for(Bill bill : entity.getBills()){
			attr.getSubTags().add(generateAttribute(bill));
		}
		attr.getSubTags().add(generateAttribute(entity.getExpenseLog()));
		
		return attr;
	}
	
	private static XMLAttribute generateAttribute(Income income){
		XMLAttribute attr = new XMLAttribute();
		
		attr.setName("Income");
		attr.getSubTags().add(new XMLAttribute("Amount", ""+income.getAmount()));
		attr.getSubTags().add(new XMLAttribute("Frequency", ""+income.getFrequency()));
		attr.getSubTags().add(new XMLAttribute("Description", income.getDescription()));
		
		return attr;
	}
	
	private static XMLAttribute generateAttribute(Debt debt){
		XMLAttribute attr = new XMLAttribute();
		
		attr.setName("Debt");
		attr.getSubTags().add(new XMLAttribute("Principle", ""+debt.getPrinciple()));
		attr.getSubTags().add(new XMLAttribute("InterestRate", ""+debt.getInterestRate()));
		attr.getSubTags().add(new XMLAttribute("MonthlyPayment", ""+debt.getMonthlyPayment()));
		attr.getSubTags().add(new XMLAttribute("DueDate", ""+debt.getDueDate()));
		attr.getSubTags().add(new XMLAttribute("Description", debt.getDescription()));
		
		return attr;
	}
	
	private static XMLAttribute generateAttribute(Bill bill){
		XMLAttribute attr = new XMLAttribute();
		
		attr.setName("Bill");
		attr.getSubTags().add(new XMLAttribute("MonthlyPayment", ""+bill.getMonthlyPayment()));
		attr.getSubTags().add(new XMLAttribute("DueDate", ""+bill.getDueDate()));
		attr.getSubTags().add(new XMLAttribute("Description", bill.getDescription()));
		
		return attr;
	}
	
	private static XMLAttribute generateAttribute(ExpenseLog log){
		XMLAttribute attr = new XMLAttribute();
		
		attr.setName("ExpenseLog");
		attr.getSubTags().add(new XMLAttribute("Total", ""+log.getTotal()));
		attr.getSubTags().add(new XMLAttribute("Description", log.getDescription()));
		for(Expense expense : log.getExpenses()){
			attr.getSubTags().add(generateAttribute(expense));
		}
		
		return attr;
	}
	
	private static XMLAttribute generateAttribute(Expense expense){
		XMLAttribute attr = new XMLAttribute();
		
		attr.setName("Expense");
		attr.getSubTags().add(new XMLAttribute("Amount",""+expense.getAmount()));
		attr.getSubTags().add(new XMLAttribute("Description",expense.getDescription()));
		attr.getSubTags().add(new XMLAttribute("PurchaseDate",expense.getPurchaseDate().toString()));
		
		return attr;
	}

	public static void main(String[] args){
		List<Entity> data = load();
		save(data);
	}
}
