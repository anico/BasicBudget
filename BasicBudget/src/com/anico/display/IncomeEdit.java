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

import com.anico.model.Entity;
import com.anico.model.Income;

@SuppressWarnings("serial")
public class IncomeEdit extends JFrame{

	private Entity entity;
	private DisplayRunner mainView;
	private AddIncome addIncome;
	private DeleteIncome deleteIncome;
	
	private String seperator;
	
	private JLabel title;
	
	private JButton save;
	private JButton close;
	private JButton add;
	private JButton delete;

	private JTable incomeTable;
	private DefaultTableModel tableModel;
	
	public IncomeEdit(DisplayRunner mainView, Entity entity){
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
		add = new JButton("Add New Income");
		delete = new JButton("Delete Income");
		
		title = new JLabel("Incomes for " + entity.getName() + ":");
		
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
		JScrollPane pane = new JScrollPane(incomeTable);
		this.getContentPane().add(pane,c);
		c.gridwidth = 1;
		
		c.gridy = gridy++;
		this.getContentPane().add(close,c);
		c.gridx = 1;
		this.getContentPane().add(delete,c);
		
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
			public void mouseClicked(MouseEvent e) {
				loadAddIncome();
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
				loadDeleteIncome();
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

	private void generateTable(){
		
		tableModel = new DefaultTableModel();
		tableModel.addColumn("Source");
		tableModel.addColumn("Amount");
		tableModel.addColumn("Frequency");
		
		Object[][] data = new Object[entity.getIncomes().size()][3];
		for(int i = 0; i < entity.getIncomes().size(); i++){
			Income income = entity.getIncomes().get(i);
			data[i][0] = income.getDescription();
			data[i][1] = ""+income.getAmount();
			data[i][2] = ""+income.getFrequency();
			tableModel.addRow(data[i]);
		}
		incomeTable = new JTable(tableModel);
		incomeTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
		incomeTable.setMinimumSize(new Dimension(500, 70));
        incomeTable.setFillsViewportHeight(true);
	}
	
	private void loadAddIncome(){
		addIncome = new AddIncome(entity, this);
		addIncome.setVisible(true);
	}
	
	public void saveAddIncome(Income income){
		addIncome.setVisible(false);
		addIncome.dispose();
		Vector<Object> row = new Vector<Object>();
		row.add(income.getDescription());
		row.add(""+income.getAmount());
		row.add(""+income.getFrequency());
		tableModel.addRow(row);
		tableModel.fireTableRowsInserted(entity.getIncomes().size()-1,entity.getIncomes().size()-1);
		
		mainView.save();
	}
	
	public void closeAddIncome(){
		addIncome.setVisible(false);
		addIncome.dispose();
	}
	
	private void loadDeleteIncome(){
		deleteIncome = new DeleteIncome(entity,this);
		deleteIncome.setVisible(true);
	}
	
	public void saveDelete(Income income){
	
		int rowIndex = -1;
		for(int i = 0; i < tableModel.getRowCount(); i++){
			if( ((String) tableModel.getValueAt(i, 0)).equals(income.getDescription()) ){
				rowIndex = i;
				break;
			}
		}
		tableModel.removeRow(rowIndex);
		tableModel.fireTableRowsDeleted(rowIndex,rowIndex);
		mainView.save();
		
		closeDelete();
	}
	
	public void closeDelete(){
		deleteIncome.setVisible(false);
		deleteIncome.dispose();
	}
	
	private void saveData(){
		List<Income> incomes = new ArrayList<Income>();
		for(int i = 0; i < incomeTable.getRowCount(); i++){
			Income tmp = new Income();
			tmp.setDescription((String) incomeTable.getValueAt(i, 0));
			tmp.setAmount(Double.valueOf((String) incomeTable.getValueAt(i, 1)));
			tmp.setFrequency(Short.valueOf((String) incomeTable.getValueAt(i, 2)));
			incomes.add(tmp);
		}
		entity.setIncomes(incomes);
		entity.calculateIncome();
		mainView.save();
	}
	
	private void closeWindow(){
		mainView.closeIncomeWindow();
	}
}
