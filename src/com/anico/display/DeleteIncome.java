package com.anico.display;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.anico.model.Income;
import com.anico.model.Entity;

@SuppressWarnings("serial")
public class DeleteIncome extends JFrame{

	private Entity entity;
	private IncomeEdit incomeEdit;
	
	private JLabel title;
	private JLabel incomeLabel;
	
	private JButton delete;
	private JButton cancel;
	
	private JComboBox<Income> incomes;
	
	public DeleteIncome(Entity entity, IncomeEdit incomeEdit){
		super();
		this.entity = entity;
		this.incomeEdit = incomeEdit;
		initialize();
	}
	
	private void initialize(){
		
		title = new JLabel("Delete Income for " + entity.getName());
		incomeLabel = new JLabel("Choose Income to Remove");
		
		incomes = new JComboBox<Income>();
		for(Income income : entity.getIncomes()){
			incomes.addItem(income);
		}
		
		delete = new JButton("Delete");
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
		this.getContentPane().add(incomeLabel,c);
		
		c.gridy = 2;
		this.getContentPane().add(incomes,c);
		
		c.gridy = 3;
		this.getContentPane().add(delete,c);
		
		c.gridx = 1;
		this.getContentPane().add(cancel,c);
		
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	private void setListeners(){
		
		delete.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				delete((Income)incomes.getSelectedItem());
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
	
	private void delete(Income income){
		
		entity.getIncomes().remove(income);
		entity.calculateIncome();
		
		incomeEdit.saveDelete(income);
	}
	
	private void cancel(){
		incomeEdit.closeDelete();
	}
}
