package by.zti.main;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class MainWindow extends JFrame {
	private JButton add_button, remove_button, edite_button;
	private JScrollPane scroll_pane;
	private JTable table;
	

	public MainWindow(){
		super("Main Window");
		setSize(700, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(true);
		setLayout(new FlowLayout());
		
		add_button = new JButton("Add new");
		remove_button = new JButton("Remove");
		edite_button = new JButton("Edite");
		
		edite_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()!=-1){
					new EditProfileWindow(MainComponent.getProfiles().get(table.getSelectedRow()));
				}
			}
		});
		
		add_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new NewProfileWindow();			}
		});
		
		remove_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()!=-1){
					MainComponent.getProfiles().remove(table.getSelectedRow());
					update();
					MainComponent.saveData();
				}
			}
		});
		
		table = new JTable(new MyJTableModel());
		scroll_pane = new JScrollPane(table);
		
		add(add_button);
		add(remove_button);
		add(edite_button);
		add(scroll_pane);
	}
	
	public void update(){
		table.setModel(new MyJTableModel());
		this.repaint();
	}
	
	class MyJTableModel implements TableModel{
		private ArrayList<TableModelListener> listeners = new ArrayList<TableModelListener>();
		
		@Override
		public int getRowCount() {
			return MainComponent.getProfiles().size();
		}

		@Override
		public int getColumnCount() {
			return 3;
		}

		@Override
		public String getColumnName(int columnIndex) {
			String name = "";
			switch(columnIndex){
				case 0: name = "Nick";
					break;
				case 1: name = "Password";
					break;
				case 2: name = "URL";
					break;
			}
			return name;
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return new String("").getClass();
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return true;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			String string = "";
			switch (columnIndex){
				case 0: string = MainComponent.getProfiles().get(rowIndex).getNick();
					break;
				case 1: string = MainComponent.getProfiles().get(rowIndex).getPass();
					break;
				case 2: string = MainComponent.getProfiles().get(rowIndex).getUrl();
					break;
			}
			return string;
		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			switch (columnIndex){
			case 0: MainComponent.getProfiles().get(rowIndex).setNick((String)aValue);
				break;
			case 1: MainComponent.getProfiles().get(rowIndex).setPass((String)aValue);
				break;
			case 2: MainComponent.getProfiles().get(rowIndex).setUrl((String)aValue);
				break;
			}
		}

		@Override
		public void addTableModelListener(TableModelListener l) {
			listeners.add(l);
		}

		@Override
		public void removeTableModelListener(TableModelListener l) {
			listeners.remove(l);
		}
		
	}

}
