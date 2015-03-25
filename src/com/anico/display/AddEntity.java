package com.anico.display;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.anico.model.Entity;

public class AddEntity extends JFrame{

	private List<Entity> people;
	private DisplayRunner mainView;
	
	private JLabel title;
	private JLabel name;
	private JLabel allowance;
	
	private JTextField nameField;
	private JTextField allowanceField;
	
	private JButton add;
	private JButton cancel;
	
	public AddEntity(List<Entity> people, DisplayRunner mainView){
		super();
		this.people = people;
		this.mainView = mainView;
		initialize();
	}
	
	private void initialize(){
		
		title = new JLabel("Add a New Person");
		name = new JLabel("Name:");
		allowance = new JLabel("Monthly Allowance:");
		
		nameField = new JTextField();
		allowanceField = new JTextField();
		
		add = new JButton("Save");
		cancel = new JButton("Cancel");
		setListeners();
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.HORIZONTAL;
		
		c.gridx = 0;
		c.gridy = 0;
		this.getContentPane().add(title,c);
		
		c.gridy = 1;
		this.getContentPane().add(name,c);
		
		c.gridx = 1;
		this.getContentPane().add(nameField,c);
		
		c.gridx = 0;
		c.gridy = 2;
		this.getContentPane().add(allowance,c);
		
		c.gridx = 1;
		this.getContentPane().add(allowanceField,c);
		
		c.gridx = 0;
		c.gridy = 3;
		this.getContentPane().add(add,c);
		
		c.gridx = 1;
		this.getContentPane().add(cancel,c);
		
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	private void setListeners(){
		
	}
	
	private void save(){
		
	}
	
	private void cancel(){
		this.setVisible(false);
	}
}
