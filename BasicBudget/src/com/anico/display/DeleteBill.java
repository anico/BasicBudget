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

import com.anico.model.Bill;
import com.anico.model.Entity;

@SuppressWarnings("serial")
public class DeleteBill extends JFrame{

	private Entity entity;
	private BillEdit billEdit;
	
	private JLabel title;
	private JLabel billLabel;
	
	private JButton delete;
	private JButton cancel;
	
	private JComboBox<Bill> bills;
	
	public DeleteBill(Entity entity, BillEdit billEdit){
		super();
		this.entity = entity;
		this.billEdit = billEdit;
		initialize();
	}
	
	private void initialize(){
		
		title = new JLabel("Delete Bill for " + entity.getName());
		billLabel = new JLabel("Choose Bill to Remove");
		
		bills = new JComboBox<Bill>();
		for(Bill Bill : entity.getBills()){
			bills.addItem(Bill);
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
		this.getContentPane().add(billLabel,c);
		
		c.gridy = 2;
		this.getContentPane().add(bills,c);
		
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
				delete((Bill)bills.getSelectedItem());
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
	
	private void delete(Bill bill){
		
		entity.getBills().remove(bill);
		entity.calculateIncome();
		
		billEdit.saveDelete(bill);
	}
	
	private void cancel(){
		billEdit.closeDelete();
	}
}
