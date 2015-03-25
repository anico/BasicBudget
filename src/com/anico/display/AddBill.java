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

import com.anico.model.Bill;
import com.anico.model.Entity;

@SuppressWarnings("serial")
public class AddBill extends JFrame{
	
	private Entity entity;
	private BillEdit billEdit;
	
	private JLabel title;
	private JLabel descriptionLabel;
	private JLabel paymentLabel;
	private JLabel dateLabel;
	
	private JTextField descriptionField;
	private JTextField paymentField;
	private JTextField dateField;
	
	private JButton save;
	private JButton cancel;
	
	public AddBill(Entity entity, BillEdit billEdit){
		super();
		this.entity = entity;
		this.billEdit = billEdit;
		initialize();
		setListeners();
	}
	
	private void initialize(){
		
		title = new JLabel("Add Monthly Bill");
		descriptionLabel = new JLabel("Bill Source: ");
		paymentLabel = new JLabel("Monthly Payment: ");
		dateLabel = new JLabel("Due Date");
		
		descriptionField = new JTextField();
		paymentField = new JTextField();
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
		this.getContentPane().add(paymentLabel,c);
		c.gridx = 1;
		this.getContentPane().add(paymentField,c);
		
		c.gridx = 0;
		c.gridy = 3;
		this.getContentPane().add(dateLabel,c);
		c.gridx = 1;
		this.getContentPane().add(dateField,c);
		
		c.gridx = 0;
		c.gridy = 4;
		this.getContentPane().add(save,c);
		c.gridx = 1;
		this.getContentPane().add(cancel,c);
		
		descriptionLabel.setVisible(true);
		descriptionField.setVisible(true);
		paymentLabel.setVisible(true);
		paymentField.setVisible(true);
		dateLabel.setVisible(true);
		dateField.setVisible(true);
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
		Bill bill = new Bill();
		bill.setDescription(descriptionField.getText());
		bill.setMonthlyPayment(Double.valueOf(paymentField.getText()));
		bill.setDueDate(Short.valueOf(dateField.getText()));
		
		entity.getBills().add(bill);
		entity.calculateIncome();
		
		billEdit.saveAddBill(bill);
	}
	
	private void cancel(){
		billEdit.closeAddBill();
	}

}
