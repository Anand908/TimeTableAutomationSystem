package gui.panel.lab;

import java.awt.Color;


import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import functionality.LabFunction;
import functionality.SubjectFunction;
import utility.TableInfo;


public class LabPanel
{
	private static LabPanel singleInstance;
		
		public static LabPanel getInstance()
		{
			if(singleInstance == null)
			{
				singleInstance = new LabPanel();
			}
			return singleInstance;
			
		}
		
		TableInfo ti = TableInfo.getInstance();
		LabFunction lf;		
		public void setLabReference(LabFunction lf)
		{
			this.lf = lf;
			System.out.println("LabPanel: LabFunction Reference set: "+lf);
		}
		
		JPanel mainPanel;
		public JTextField labName;
		public JTextField labNumber;
		private JTable table;
		public JScrollPane scrollPane;
		private String selectedRowPK;
		private String selectedCellValue;
		private int selectedColumn;
		public int selectedRowNumber;
		public String selectedColumnName;
		public DefaultTableModel model;
		public JLabel label_LabName;
		public JLabel label_LabNumber;
		public JComboBox labType;
		public JLabel label_LabType;

		
		
		public JPanel getLabs()
		{
			return mainPanel;		
		}
		
		private LabPanel()
		{
			mainPanel = new JPanel();
			mainPanel.setBackground(Color.WHITE);
			mainPanel.setBounds(80,50,1280,630);
			mainPanel.setLayout(null);
			
			JButton button_Add_Labs = new JButton("ADD");
			button_Add_Labs.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					lf.notify("add");
				}
			});
			button_Add_Labs.setForeground(Color.WHITE);
			button_Add_Labs.setFont(new Font("Tahoma", Font.BOLD, 20));
			button_Add_Labs.setBackground(new Color(34, 139, 34));
			button_Add_Labs.setBounds(253, 494, 150, 40);
			mainPanel.add(button_Add_Labs);
			
			JLabel label_LabNumber_Labs = new JLabel("Lab Number:");
			label_LabNumber_Labs.setFont(new Font("Calibri", Font.BOLD, 18));
			label_LabNumber_Labs.setBounds(47, 256, 120, 39);
			mainPanel.add(label_LabNumber_Labs);
			
			JLabel label_LabName_Labs = new JLabel("Lab Incharge Name:");
			label_LabName_Labs.setFont(new Font("Calibri", Font.BOLD, 18));
			label_LabName_Labs.setBounds(47, 154, 356, 39);
			mainPanel.add(label_LabName_Labs);
			
			JLabel label_LeftSubHeading = new JLabel("Enter the details below to Quick  add a Lab.");
			label_LeftSubHeading.setFont(new Font("Tahoma", Font.PLAIN, 15));
			label_LeftSubHeading.setBounds(26, 119, 392, 25);
			mainPanel.add(label_LeftSubHeading);
			
			JSeparator separator = new JSeparator();
			separator.setOrientation(SwingConstants.VERTICAL);
			separator.setForeground(Color.DARK_GRAY);
			separator.setBackground(Color.DARK_GRAY);
			separator.setBounds(459, 80, 17, 507);
			mainPanel.add(separator);
			
			labName = new JTextField();
			labName.setToolTipText("");
			labName.setFont(new Font("Tahoma", Font.PLAIN, 14));
			labName.setColumns(10);
			labName.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
			labName.setBackground(Color.WHITE);
			labName.setBounds(47, 185, 356, 40);
			mainPanel.add(labName);
			
			labNumber = new JTextField();
			labNumber.setToolTipText("");
			labNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
			labNumber.setColumns(10);
			labNumber.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
			labNumber.setBackground(Color.WHITE);
			labNumber.setBounds(47, 286, 356, 40);
			mainPanel.add(labNumber);
			
			JLabel title = new JLabel("Labs Section");
			title.setFont(new Font("Tahoma", Font.BOLD, 26));
			title.setHorizontalAlignment(SwingConstants.CENTER);
			title.setBounds(374, 10, 317, 65);
			mainPanel.add(title);
			
			JButton refreshBtn = new JButton("Refresh");
			refreshBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					lf.notify("refresh");
					
				}
			});
			refreshBtn.setBounds(1133, 123, 85, 21);
			mainPanel.add(refreshBtn);
			
			JButton btnEdit = new JButton("Edit");
			btnEdit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					lf.notify("edit");
				}
			});
			btnEdit.setBounds(1020, 501, 85, 35);
			mainPanel.add(btnEdit);
			
			/*
			 * JButton btnGenerate = new JButton("Generate");
			 * btnGenerate.addActionListener(new ActionListener() { public void
			 * actionPerformed(ActionEvent arg0) { } }); btnGenerate.setBounds(1138, 501,
			 * 90, 35); mainPanel.add(btnGenerate);
			 */
			
			JButton btnRemove = new JButton("Remove");
			btnRemove.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					lf.notify("remove");
				}
			});
			btnRemove.setBounds(1138, 501, 90, 35);
			mainPanel.add(btnRemove);
			
			scrollPane = new JScrollPane();
			
			scrollPane.setBounds(532, 153, 691, 338);
			mainPanel.add(scrollPane);
			
			table = new JTable(ti.getLabDefaultRow(),ti.getLabTableColumn());
			table.setAutoCreateRowSorter(true);
			scrollPane.setViewportView(table);
			
	        model = new DefaultTableModel();
	        table.setModel(model);
			
			JLabel lblNewLabel = new JLabel("Sort By :");
			lblNewLabel.setBounds(532, 126, 60, 14);
			mainPanel.add(lblNewLabel);
			
			JComboBox sortBox = new JComboBox();
			sortBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(sortBox.getSelectedItem().toString().equals("LabName"))
						lf.filterAscending(1, table, model);
					
					if(sortBox.getSelectedItem().toString().equals("LabID"))
						lf.filterAscending(2, table, model);
				}
			});
			sortBox.setModel(new DefaultComboBoxModel(new String[] {"LabName", "LabID"}));
			sortBox.setBounds(600, 122, 77, 22);
			mainPanel.add(sortBox);
			
			JButton btnNewButton = new JButton("Commit");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					lf.notify("commit");
				}
			});
			btnNewButton.setBounds(1138, 578, 85, 21);
			mainPanel.add(btnNewButton);
			
			label_LabName = new JLabel();
			label_LabName.setForeground(Color.RED);
			label_LabName.setFont(new Font("Tahoma", Font.PLAIN, 12));
			label_LabName.setBounds(47, 225, 392, 25);
			mainPanel.add(label_LabName);
			
			label_LabNumber = new JLabel();
			label_LabNumber.setForeground(Color.RED);
			label_LabNumber.setFont(new Font("Tahoma", Font.PLAIN, 12));
			label_LabNumber.setBounds(47, 326, 392, 25);
			mainPanel.add(label_LabNumber);
			
			JLabel lblLabType = new JLabel("Lab Type:");
			lblLabType.setFont(new Font("Calibri", Font.BOLD, 18));
			lblLabType.setBounds(47, 355, 120, 39);
			mainPanel.add(lblLabType);
			
			labType = new JComboBox(new String[] {"-SELECT-"});
			labType.setToolTipText("");
			labType.setFont(new Font("Tahoma", Font.PLAIN, 14));
			labType.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
			labType.setBackground(Color.WHITE);
			labType.setBounds(47, 393, 356, 40);
			mainPanel.add(labType);
			
			label_LabType = new JLabel();
			label_LabType.setForeground(Color.RED);
			label_LabType.setFont(new Font("Tahoma", Font.PLAIN, 12));
			label_LabType.setBounds(47, 433, 392, 25);
			mainPanel.add(label_LabType);
		}
		
		public void activateTableActionListener()
		{
			table.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mousePressed(MouseEvent e) 
				{
		            selectedCellValue = table.getValueAt(table.getSelectedRow() , table.getSelectedColumn()).toString();
					selectedRowNumber = Integer.parseInt(table.getValueAt(table.getSelectedRow() , 0).toString());
					selectedColumn = table.getSelectedColumn();
					selectedColumnName = selectedColumn == 0?table.getColumnName(2):table.getColumnName(selectedColumn);
		            System.out.println(selectedCellValue);
		            System.out.println("Row Number: "+selectedRowNumber);
		            System.out.println("SelectedColumn: "+selectedColumn+" "+selectedColumnName);	            
		            System.out.println("Action listener activated.");
				}
			});
		}
		
		public void refresh()
		{
			lf.notify("refresh");
		}
}
