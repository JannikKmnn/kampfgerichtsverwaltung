import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.table.JTableHeader;

public class MyWindow extends JFrame{
	
	JTable table;
	JPanel tablePanel;
	String[] columnnames = {"Spielklasse", "Datum", "Uhrzeit", "Heim", "Gast", "Halle", "Kampfgericht", "ZeitnehmerIn", "SekretärIn"};
	
	public MyWindow(ActionListener listener){
		
		//Frame of the Window
		setTitle("Kampfgerichtsverwaltung");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		//Title Label
		JLabel titleLabel = new JLabel("Kampfgericht - TSV Bargteheide");
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setBorder(BorderFactory.createEmptyBorder(20,0,20,0));
		titleLabel.setOpaque(true);
		titleLabel.setBackground(new Color(102, 0, 0));
		titleLabel.setForeground(Color.white);
		
		//Box Panel
		JPanel upperPanel = new JPanel();
		upperPanel.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
		
		JPanel choosePanel = new JPanel();
		JPanel boxPanel = new JPanel();
		
		upperPanel.setLayout(new GridLayout(1, 2));
		upperPanel.add(choosePanel);
		upperPanel.add(boxPanel);
		
		//Table Panel
		tablePanel = new JPanel();
		tablePanel.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
		tablePanel.setLayout(new GridLayout(2, 1));
		
		//Button Panel
		JPanel btnPanel = new JPanel();
		
		JPanel dwn1 = new JPanel();
		JPanel dwn2 = new JPanel();
		JPanel dwn3 = new JPanel();
		JPanel dwn4 = new JPanel();
		
		btnPanel.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
		btnPanel.setLayout(new GridLayout(1,4));
		btnPanel.add(dwn1);
		btnPanel.add(dwn2);
		btnPanel.add(dwn3);
		btnPanel.add(dwn4);
		
		//Create Panel-Window
		add(titleLabel, BorderLayout.NORTH);
		add(upperPanel, BorderLayout.CENTER);
		add(tablePanel, BorderLayout.SOUTH);
		
		//Create Interior of Panel
		JLabel Lchoose = new JLabel("Wähle Dein Team: ");
		Lchoose.setHorizontalAlignment(JLabel.RIGHT);
		choosePanel.add(Lchoose);
		DBConnection teamlist = new DBConnection();
		JComboBox Bchoose = new JComboBox(teamlist.getTeams().toArray());
		Bchoose.setPrototypeDisplayValue("defaultsizehereffffffffffffff");
		Bchoose.addActionListener(listener);
		boxPanel.add(Bchoose);
		
		//Create Framework for Table
		
		Object[][] defaultdatas = {{"", "", "", "", "", "", "", "", ""}};
		table = new JTable(defaultdatas, columnnames);
		setColumnWidth(table);
		JTableHeader header = table.getTableHeader();
		tablePanel.setLayout(new BorderLayout());
		tablePanel.add(header, BorderLayout.NORTH);
		tablePanel.add(table, BorderLayout.CENTER);
		tablePanel.add(btnPanel, BorderLayout.SOUTH);
		
		//Create Buttons
		
		JButton saveConfig = new JButton("Änderungen speichern");
		JButton reload = new JButton("Aktualisieren");
		saveConfig.setHorizontalAlignment(JLabel.LEFT);
		saveConfig.setHorizontalAlignment(JLabel.RIGHT);
		
		saveConfig.addActionListener(listener);
		reload.addActionListener(listener);
		
		dwn3.add(reload);
		dwn4.add(saveConfig);
		
		
		pack();
		
	}
	
	public void updateTable(Object[][] datas) {
		
		tablePanel.remove(table);
		table = new JTable(datas, columnnames);
		setColumnWidth(table);
		tablePanel.add(table, BorderLayout.CENTER);
		
		pack();
		
	}
	
	public void setColumnWidth(JTable table) {
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		table.getColumnModel().getColumn(4).setPreferredWidth(230);
		table.getColumnModel().getColumn(5).setPreferredWidth(120);
		table.getColumnModel().getColumn(6).setPreferredWidth(120);
		table.getColumnModel().getColumn(7).setPreferredWidth(120);
		table.getColumnModel().getColumn(8).setPreferredWidth(120);
	}
}
