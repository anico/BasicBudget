package com.anico.display;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.anico.model.Entity;
import com.anico.util.XMLUtil;

public class DisplayRunner{
	
	private List<Entity> people;
	private HashMap<String, Integer> map;
	private DecimalFormat formatter;
	private JFrame mainView;
	private IncomeEdit incomeWindow;
	private DebtEdit debtWindow;
	private BillEdit billWindow;
	
	private JComboBox<Entity> entityCombo;
	private JButton newEntity;
	
	private JLabel grossIncome;
	private JLabel netIncome;
	private JLabel potentialIncome;
	
	private JButton loadIncomes;
	private JButton loadBills;
	private JButton loadDebts;
	
	public static void main(String[] args){

		DisplayRunner displayRunner = new DisplayRunner();
		displayRunner.initialize();
	}

	public DisplayRunner(){
		people = new ArrayList<Entity>();
		formatter = new DecimalFormat();
		formatter.setMaximumFractionDigits(2);
		formatter.setRoundingMode(RoundingMode.HALF_UP);
	}
	
	private void initialize(){
		
		people = XMLUtil.load();
		map = new HashMap<String, Integer>();
		for(int i = 0; i < people.size(); i++){
			map.put(people.get(i).getName(), i);
		}
		
		entityCombo = new JComboBox<Entity>();

		for(Entity entity : people){
			entityCombo.addItem(entity);
		}
		entityCombo.setSize(100, 25);
		entityCombo.setEditable(true);
		entityCombo.setVisible(true);
		
		newEntity = new JButton("New");
		newEntity.setSize(100, 50);
		newEntity.setVisible(true);;
		
		grossIncome = new JLabel();
		netIncome = new JLabel();
		potentialIncome = new JLabel();
		
		setLabels();
		
		loadIncomes = new JButton("Incomes");
		loadBills = new JButton("Bills");
		loadDebts = new JButton("Debts");
		
		setListeners();
		
		mainView = new JFrame();
		mainView.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5,5,5,5);
		
		c.gridx = 0;
		mainView.getContentPane().add(entityCombo,c);
		
		c.gridx = 1;
		mainView.getContentPane().add(newEntity);
		
		c.gridx = 0;
		c.gridy = 1;
		mainView.getContentPane().add(grossIncome,c);
		
		c.gridy=2;
		mainView.getContentPane().add(netIncome,c);
		
		c.gridy = 3;
		mainView.getContentPane().add(potentialIncome,c);
		
		c.gridy = 4;
		mainView.getContentPane().add(loadIncomes,c);
		
		c.gridx = 1;
		mainView.getContentPane().add(loadBills,c);
		
		c.gridx = 2;
		mainView.getContentPane().add(loadDebts,c);
		
		mainView.pack();
		mainView.setLocationRelativeTo(null);
		
		mainView.setVisible(true);
		grossIncome.setVisible(true);
		netIncome.setVisible(true);
		potentialIncome.setVisible(true);
		loadIncomes.setVisible(true);
		loadBills.setVisible(true);
		loadDebts.setVisible(true);
		
		mainView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	private void setListeners(){
		
		entityCombo.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("comboBoxChanged")){
					setLabels();
				}
			}
			
		});
		
		loadIncomes.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				loadIncomeWindow(((Entity) entityCombo.getSelectedItem()));
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
		
		loadBills.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				loadBillWindow(((Entity) entityCombo.getSelectedItem()));
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
		
		loadDebts.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				loadDebtWindow(((Entity) entityCombo.getSelectedItem()));
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
		grossIncome.setText("Gross Income: $" + formatter.format(((Entity) entityCombo.getSelectedItem()).getGrossIncome()));
		netIncome.setText("Net Income: $" + formatter.format(((Entity) entityCombo.getSelectedItem()).getNetIncome()));
		potentialIncome.setText("Potential Income: $" + formatter.format(((Entity) entityCombo.getSelectedItem()).getPotentialIncome()));
		if(mainView != null){
			mainView.pack();
			mainView.setLocationRelativeTo(null);
		}
	}
	
	private void loadIncomeWindow(Entity entity){
		incomeWindow = new IncomeEdit(this,entity);
		incomeWindow.addWindowListener(new FrameListener());
		incomeWindow.setVisible(true);
		mainView.setVisible(false);
	}
	
	public void closeIncomeWindow(){
		incomeWindow.setVisible(true);
		incomeWindow.dispose();
		setLabels();
		mainView.setVisible(true);
	}
	
	private void loadBillWindow(Entity entity){
		billWindow = new BillEdit(this,entity);
		billWindow.addWindowListener(new FrameListener());
		billWindow.setVisible(true);
		mainView.setVisible(false);
	}
	
	public void closeBillWindow(){
		billWindow.setVisible(false);
		billWindow.dispose();
		setLabels();
		mainView.setVisible(true);
	}
	
	private void loadDebtWindow(Entity entity){
		debtWindow = new DebtEdit(this,entity);
		debtWindow.addWindowListener(new FrameListener());
		debtWindow.setVisible(true);
		mainView.setVisible(false);
	}
	
	public void closeDebtWindow(){
		debtWindow.setVisible(false);
		debtWindow.dispose();
		setLabels();
		mainView.setVisible(true);
	}
	
	public void save(){
		XMLUtil.save(people);
		setLabels();
	}
	
	public DecimalFormat getFormatter(){
		return formatter;
	}
	
	private class FrameListener implements WindowListener{

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void windowClosed(WindowEvent e) {
			setLabels();
			mainView.setVisible(true);
		}

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
