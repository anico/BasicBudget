package com.anico.display;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.anico.model.Bill;
import com.anico.model.Entity;

@SuppressWarnings("serial")
public class BillEdit extends JFrame{

	private Entity entity;
	
	private String seperator;
	
	private DisplayRunner mainView;
	private AddBill addBill;
	private DeleteBill deleteBill;
	
	private JButton save;
	private JButton close;
	private JButton add;
	private JButton delete;
	
	private JLabel title;
	private JLabel billTotal;

	private JTable billTable;
	private DefaultTableModel tableModel;
	
	public BillEdit(DisplayRunner mainView, Entity entity){
		super();
		this.entity = entity;
		this.mainView = mainView;
		seperator = "";
		generateTable();
		for(int i = 0; i < 25; i++){
			seperator += "_";
		}
		initialize();
	}

	private void initialize(){
		save = new JButton("Save");
		close = new JButton("Close");
		add = new JButton("Add New Bill");
		delete = new JButton("Delete A Bill");
		
		title = new JLabel("Bills for " + entity.getName() + ":");
		billTotal = new JLabel("Total Monthly Payment: ...$" + mainView.getFormatter().format(entity.getBillTotal()));
		
		setListeners();
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5,5,5,5);
		int gridy = 0;
		c.gridx = 0;
		c.gridy = gridy++;
		c.gridwidth = 2;
		this.getContentPane().add(title,c);
		c.gridwidth = 1;
		
		c.gridy = gridy++;
		c.gridwidth = 2;
		this.getContentPane().add(new JLabel(seperator),c);
		c.gridwidth = 1;

		c.gridwidth = 3;
		c.gridy = gridy++;
		JScrollPane pane = new JScrollPane(billTable);
		this.getContentPane().add(pane,c);
		c.gridwidth = 1;
		
		c.gridy = gridy++;
		this.getContentPane().add(close,c);
		c.gridx = 1;
		this.getContentPane().add(delete,c);
		c.gridx = 2;
		c.insets = new Insets(5,50,5,5);
		this.getContentPane().add(billTotal,c);
		c.insets = new Insets(5,5,5,5);
		c.gridx = 0;
		c.gridy = gridy++;
		this.getContentPane().add(save,c);
		c.gridx = 1;
		this.getContentPane().add(add,c);
		
		
		
		this.pack();
		this.setLocationRelativeTo(null);
		save.setVisible(true);
		close.setVisible(true);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	private void setListeners(){
		
		add.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				loadAddBill();
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
		
		save.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				saveData();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
			
		});
		
		close.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				closeWindow();
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
		});

		delete.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				loadDeleteBill();
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
	}

	private void setLabels(){
		billTotal.setText("Total Monthly Payment: ...$" + mainView.getFormatter().format(entity.getBillTotal()));
	}
	
	private void generateTable(){
		
		tableModel = new DefaultTableModel();
		
		Object[][] data = new Object[entity.getBills().size()][3];
		tableModel.addColumn("Source");
		tableModel.addColumn("Monthly Payment");
		tableModel.addColumn("Due Date");
		
		for(int i = 0; i < entity.getBills().size(); i++){
			Bill bill = entity.getBills().get(i);
			data[i][0] = bill.getDescription();
			data[i][1] = ""+bill.getMonthlyPayment();
			data[i][2] = ""+bill.getDueDate();
			tableModel.addRow(data[i]);
		}
		billTable = new JTable(tableModel);
		billTable.setPreferredScrollableViewportSize(new Dimension(500, 120));
		billTable.setMinimumSize(new Dimension(500,120));
        billTable.setFillsViewportHeight(true);
	}
	
	private void loadAddBill(){
		addBill = new AddBill(entity, this);
		addBill.setVisible(true);
	}
	
	public void saveAddBill(Bill bill){
		addBill.setVisible(false);
		addBill.dispose();
		Vector<Object> row = new Vector<Object>();
		row.add(bill.getDescription());
		row.add(""+bill.getMonthlyPayment());
		row.add(""+bill.getDueDate());
		tableModel.addRow(row);
		tableModel.fireTableRowsInserted(entity.getBills().size()-1,entity.getBills().size()-1);
		
		mainView.save();
		setLabels();
	}
	
	public void closeAddBill(){
		addBill.setVisible(false);
		addBill.dispose();
	}
	
	private void loadDeleteBill(){
		deleteBill = new DeleteBill(entity,this);
		deleteBill.setVisible(true);
	}
	
	public void saveDelete(Bill bill){
		
		int rowIndex = -1;
		for(int i = 0; i < tableModel.getRowCount(); i++){
			if( ((String) tableModel.getValueAt(i, 0)).equals(bill.getDescription()) ){
				rowIndex = i;
				break;
			}
		}
		tableModel.removeRow(rowIndex);
		tableModel.fireTableRowsDeleted(rowIndex,rowIndex);
		mainView.save();
		setLabels();
		closeDelete();
	}
	
	public void closeDelete(){
		deleteBill.setVisible(false);
		deleteBill.dispose();
	}
	
	private void saveData(){
		List<Bill> bills = new ArrayList<Bill>();
		for(int i = 0; i < billTable.getRowCount(); i++){
			Bill tmp = new Bill();
			tmp.setDescription((String) billTable.getValueAt(i, 0));
			tmp.setMonthlyPayment(Double.valueOf((String) billTable.getValueAt(i, 1)));
			tmp.setDueDate(Short.valueOf((String) billTable.getValueAt(i, 2)));
			bills.add(tmp);
		}
		entity.setBills(bills);
		entity.calculateIncome();
		billTotal.setText("Total Monthly Payment: ...$" + mainView.getFormatter().format(entity.getBillTotal()));
		
		mainView.save();
	}

	private void closeWindow(){
		mainView.closeBillWindow();
	}
}
