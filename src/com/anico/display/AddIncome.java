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

import com.anico.model.Income;
import com.anico.model.Entity;

@SuppressWarnings("serial")
public class AddIncome extends JFrame{
	
	private Entity entity;
	private IncomeEdit incomeEdit;
	
	private JLabel title;
	private JLabel descriptionLabel;
	private JLabel amountLabel;
	private JLabel freqLabel;
	
	private JTextField descriptionField;
	private JTextField amountField;
	private JTextField freqField;
	
	private JButton save;
	private JButton cancel;
	
	public AddIncome(Entity entity, IncomeEdit incomeEdit){
		super();
		this.entity = entity;
		this.incomeEdit = incomeEdit;
		initialize();
		setListeners();
	}
	
	private void initialize(){
		
		title = new JLabel("Add Monthly Income");
		descriptionLabel = new JLabel("Income Source: ");
		amountLabel = new JLabel("Amount: ");
		freqLabel = new JLabel("Frequency: ");
		
		descriptionField = new JTextField();
		amountField = new JTextField();
		freqField = new JTextField();
		
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
		this.getContentPane().add(amountLabel,c);
		c.gridx = 1;
		this.getContentPane().add(amountField,c);
		
		c.gridx = 0;
		c.gridy = 3;
		this.getContentPane().add(freqLabel,c);
		c.gridx = 1;
		this.getContentPane().add(freqField,c);
		
		c.gridx = 0;
		c.gridy = 4;
		this.getContentPane().add(save,c);
		c.gridx = 1;
		this.getContentPane().add(cancel,c);
		
		descriptionLabel.setVisible(true);
		descriptionField.setVisible(true);
		amountLabel.setVisible(true);
		amountField.setVisible(true);
		freqLabel.setVisible(true);
		freqField.setVisible(true);
		save.setVisible(true);
		cancel.setVisible(true);
		
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
		Income income = new Income();
		income.setDescription(descriptionField.getText());
		income.setAmount(Double.valueOf(amountField.getText()));
		income.setFrequency(Short.valueOf(freqField.getText()));
		
		entity.getIncomes().add(income);
		entity.calculateIncome();
		
		incomeEdit.saveAddIncome(income);
	}
	
	private void cancel(){
		incomeEdit.closeAddIncome();
	}

}
