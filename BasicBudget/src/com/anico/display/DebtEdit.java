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

import com.anico.model.Debt;
import com.anico.model.Entity;

@SuppressWarnings("serial")
public class DebtEdit extends JFrame{

	private Entity entity;
	private DisplayRunner mainView;
	private AddDebt addDebt;
	private DeleteDebt deleteDebt;
	
	private String seperator;
	
	private JLabel title;
	private JLabel debtTotal;
	private JLabel debtPaymentTotal;

	private JButton save;
	private JButton close;
	private JButton add;
	private JButton delete;
	
	private JTable debtTable;
	private DefaultTableModel tableModel;
	
	public DebtEdit(DisplayRunner mainView, Entity entity){
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
		delete = new JButton("Delete Debt");
		add = new JButton("Add New Debt");
		debtTotal = new JLabel("Total Debt Principle:...$"+mainView.getFormatter().format(entity.getDebtAmountTotal()));
		debtPaymentTotal = new JLabel("Total Payments:..........$"+mainView.getFormatter().format(entity.getDebtTotal()));
		
		title = new JLabel("Debts for " + entity.getName() + ":");
		
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
		JScrollPane pane = new JScrollPane(debtTable);
		this.getContentPane().add(pane,c);
		c.gridwidth = 1;
		
		c.gridy = gridy++;
		this.getContentPane().add(close,c);
		c.gridx = 1;
		this.getContentPane().add(delete,c);
		c.gridx = 2;
		c.insets = new Insets(5,50,5,5);
		this.getContentPane().add(debtTotal,c);
		c.insets = new Insets(5,5,5,5);
		c.gridx = 0;
		c.gridy = gridy++;
		this.getContentPane().add(save,c);
		c.gridx = 1;
		this.getContentPane().add(add,c);
		c.gridx = 2;
		c.insets = new Insets(5,50,5,5);
		this.getContentPane().add(debtPaymentTotal,c);
		
		
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
				loadAddDebt();
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
		
		close.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				closeWindow();
				
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
	
		delete.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				loadDeleteDebt();
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

	private void setLabels(){
		debtTotal.setText("Total Debt Principle:...$"+mainView.getFormatter().format(entity.getDebtAmountTotal()));
		debtPaymentTotal.setText("Total Payments:..........$"+mainView.getFormatter().format(entity.getDebtTotal()));
	}
	
	private void generateTable(){
		
		tableModel = new DefaultTableModel();
		tableModel.addColumn("Source");
		tableModel.addColumn("Principle");
		tableModel.addColumn("Monthly Payment");
		tableModel.addColumn("Interest Rate %");
		Object[][] data = new Object[entity.getDebts().size()][4];
		for(int i = 0; i < entity.getDebts().size(); i++){
			Debt debt = entity.getDebts().get(i);
			data[i][0] = debt.getDescription();
			data[i][1] = ""+debt.getPrinciple();
			data[i][2] = ""+debt.getMonthlyPayment();
			data[i][3] = ""+debt.getInterestRate();
			tableModel.addRow(data[i]);
		}
		debtTable = new JTable(tableModel);
		debtTable.setPreferredScrollableViewportSize(new Dimension(500, 150));
		debtTable.setMinimumSize(new Dimension(500, 120));
        debtTable.setFillsViewportHeight(true);
	}
	
	private void loadAddDebt(){
		addDebt = new AddDebt(entity,this);
		addDebt.setVisible(true);
	}
	
	public void saveAddDebt(Debt debt){
		addDebt.setVisible(false);
		addDebt.dispose();
		Vector<Object> row = new Vector<Object>();
		row.add(debt.getDescription());
		row.add(""+debt.getPrinciple());
		row.add(""+debt.getMonthlyPayment());
		row.add(""+debt.getInterestRate());
		tableModel.addRow(row);
		tableModel.fireTableRowsInserted(entity.getDebts().size()-1,entity.getDebts().size()-1);
		
		mainView.save();
		setLabels();
	}
	
	public void closeAddDebt(){
		addDebt.setVisible(false);
		addDebt.dispose();
	}
	
	private void loadDeleteDebt(){
		deleteDebt = new DeleteDebt(entity,this);
		deleteDebt.setVisible(true);
	}
	
	public void saveDelete(Debt debt){
		
		int rowIndex = -1;
		for(int i = 0; i < tableModel.getRowCount(); i++){
			if( ((String) tableModel.getValueAt(i, 0)).equals(debt.getDescription()) ){
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
		deleteDebt.setVisible(false);
		deleteDebt.dispose();
	}
	
	private void saveData(){
		List<Debt> debts = new ArrayList<Debt>();
		for(int i = 0; i < debtTable.getRowCount(); i++){
			Debt tmp = new Debt();
			tmp.setDescription((String) debtTable.getValueAt(i, 0));
			tmp.setPrinciple(Double.valueOf((String) debtTable.getValueAt(i, 1)));
			tmp.setMonthlyPayment(Double.valueOf((String) debtTable.getValueAt(i, 2)));
			tmp.setInterestRate(Double.valueOf((String) debtTable.getValueAt(i, 3)));
			debts.add(tmp);
		}
		entity.setDebts(debts);
		entity.calculateIncome();
		debtTotal.setText("Total Debt Principle:...$"+mainView.getFormatter().format(entity.getDebtAmountTotal()));
		mainView.save();
	}
	
	private void closeWindow(){
		mainView.closeDebtWindow();
	}
}
