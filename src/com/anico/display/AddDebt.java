package com.anico.display;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.anico.model.Debt;
import com.anico.model.Entity;

@SuppressWarnings("serial")
public class AddDebt extends JFrame{
	
	private Entity entity;
	private DebtEdit debtEdit;
	
	private JLabel title;
	private JLabel descriptionLabel;
	private JLabel principleLabel;
	private JLabel paymentLabel;
	private JLabel interestLabel;
	private JLabel dateLabel;
	
	private JTextField descriptionField;
	private JTextField principleField;
	private JTextField paymentField;
	private JTextField interestField;
	private JTextField dateField;
	
	private JButton save;
	private JButton cancel;
	
	public AddDebt(Entity entity, DebtEdit debtEdit){
		super();
		this.entity = entity;
		this.debtEdit = debtEdit;
		initialize();
		setListeners();
	}
	
	private void initialize(){
		
		title = new JLabel("Add New Debt");
		descriptionLabel = new JLabel("Debt Source: ");
		principleLabel = new JLabel("Principle Amount: ");
		paymentLabel = new JLabel("Monthly Payment: ");
		interestLabel = new JLabel("Annual Interest Rate: ");
		dateLabel = new JLabel("Due Date");
		
		descriptionField = new JTextField();
		principleField = new JTextField();
		paymentField = new JTextField();
		interestField = new JTextField();
		dateField = new JTextField();
		
		save = new JButton("Save");
		cancel = new JButton("Cancel");
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5,5,5,5);
		c.fill=GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		
		this.getContentPane().add(title,c);
		
		c.gridy = 1;
		this.getContentPane().add(descriptionLabel,c);
		c.gridx = 1;
		this.getContentPane().add(descriptionField,c);
		
		c.gridx = 0;
		c.gridy = 2;
		this.getContentPane().add(principleLabel,c);
		c.gridx = 1;
		this.getContentPane().add(principleField,c);
		
		c.gridx = 0;
		c.gridy = 3;
		this.getContentPane().add(paymentLabel,c);
		c.gridx = 1;
		this.getContentPane().add(paymentField,c);
		
		c.gridx = 0;
		c.gridy = 4;
		this.getContentPane().add(interestLabel,c);
		c.gridx = 1;
		this.getContentPane().add(interestField,c);
		
		c.gridx = 0;
		c.gridy = 5;
		this.getContentPane().add(dateLabel,c);
		c.gridx = 1;
		this.getContentPane().add(dateField,c);
		
		c.gridx = 0;
		c.gridy = 6;
		this.getContentPane().add(save,c);
		c.gridx = 1;
		this.getContentPane().add(cancel,c);
		
		this.pack();
		this.setLocationRelativeTo(null);
		
		this.setVisible(true);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	private void setListeners(){
		
		save.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				save();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	
		cancel.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				cancel();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	private void save(){
		Debt debt = new Debt();
		debt.setDescription(descriptionField.getText());
		debt.setPrinciple(Double.valueOf(principleField.getText()));
		debt.setMonthlyPayment(Double.valueOf(paymentField.getText()));
		debt.setInterestRate(Double.valueOf(interestField.getText()));
		debt.setDueDate(Short.valueOf(dateField.getText()));
		
		entity.getDebts().add(debt);
		entity.calculateIncome();
		
		debtEdit.saveAddDebt(debt);
	}
	
	private void cancel(){
		debtEdit.closeAddDebt();
	}

}
