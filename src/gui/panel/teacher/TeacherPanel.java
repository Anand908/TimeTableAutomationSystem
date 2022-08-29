package gui.panel.teacher;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import database.cached.DatabaseCached;
import functionality.TeacherFunction;
import utility.TableInfo;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class TeacherPanel
{
		private static TeacherPanel singleInstance;
		
		public static TeacherPanel getInstance()
		{
			if(singleInstance == null)
			{
				singleInstance = new TeacherPanel();
			}
			return singleInstance;
		}
		
		// --- Object References ---//
		TableInfo ti = TableInfo.getInstance();
		TeacherFunction tf;
		DatabaseCached dc;
		public void setTeacherReference(TeacherFunction tf,DatabaseCached dc)
		{
			this.tf = tf;
			this.dc = dc;
			System.out.println("TeacherPanel: TeacherFunction Reference set: "+tf);
		}
		
		JPanel mainPanel;
		public JTextField fName;
		public JTextField lName;
		public JTextField collegeID;
		public JTextField email;
		private JTable table;		
		public JScrollPane scrollPane;
		public String selectedRowPK;
		private String selectedCellValue;
		public int selectedColumn;
		public int selectedRowNumber;
		public String selectedColumnName;
		public DefaultTableModel model;
		public JLabel fName_label;
		public JLabel lName_label;
		public JLabel collegeId_label;
		public JLabel email_label;
		public JComboBox namePrefix;

		
		
		public JPanel getTeacherPanel()
		{
			return mainPanel;		
		}
		
		private TeacherPanel()
		{
			mainPanel = new JPanel();
			mainPanel.setBackground(Color.WHITE);
			mainPanel.setBounds(80,50,1280,630);
			mainPanel.setLayout(null);
			
			JLabel label_Email = new JLabel("Email:");
			label_Email.setHorizontalAlignment(SwingConstants.LEFT);
			label_Email.setFont(new Font("Calibri", Font.BOLD, 18));
			label_Email.setBounds(47, 414, 120, 39);
			mainPanel.add(label_Email);
			
			JLabel label_CollegeID = new JLabel("College ID:");
			label_CollegeID.setHorizontalAlignment(SwingConstants.LEFT);
			label_CollegeID.setFont(new Font("Calibri", Font.BOLD, 18));
			label_CollegeID.setBounds(47, 325, 120, 39);
			mainPanel.add(label_CollegeID);
			
			JLabel label_LName = new JLabel("Last Name:");
			label_LName.setHorizontalAlignment(SwingConstants.LEFT);
			label_LName.setFont(new Font("Calibri", Font.BOLD, 18));
			label_LName.setBounds(47, 234, 120, 39);
			mainPanel.add(label_LName);
			
			JLabel label_FName = new JLabel("First Name:");
			label_FName.setHorizontalAlignment(SwingConstants.LEFT);
			label_FName.setFont(new Font("Calibri", Font.BOLD, 18));
			label_FName.setBounds(47, 125, 144, 39);
			mainPanel.add(label_FName);
			
			JLabel label_LeftSubHeading = new JLabel("Enter the details below to add a Teacher.");
			label_LeftSubHeading.setFont(new Font("Tahoma", Font.PLAIN, 15));
			label_LeftSubHeading.setBounds(26, 99, 392, 25);
			mainPanel.add(label_LeftSubHeading);
			
			JButton addButton = new JButton("ADD");
			addButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					tf.notify("add");
				}
			});
			addButton.setForeground(Color.WHITE);
			addButton.setFont(new Font("Tahoma", Font.BOLD, 20));
			addButton.setBackground(new Color(34, 139, 34));
			addButton.setBounds(253, 536, 150, 40);
			mainPanel.add(addButton);

			namePrefix = new JComboBox(new String[] {"Mr ","Ms ","Mrs ","Er ","Dr ","Prof "});
			namePrefix.setBounds(47, 165, 56, 40);
			mainPanel.add(namePrefix);
			
			fName = new JTextField();
			fName.setToolTipText("");
			fName.setFont(new Font("Tahoma", Font.PLAIN, 14));
			fName.setColumns(10);
			fName.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
			fName.setBackground(Color.WHITE);
			fName.setBounds(113, 165, 290, 40);
			mainPanel.add(fName);
			
			lName = new JTextField();
			lName.setToolTipText("");
			lName.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lName.setColumns(10);
			lName.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
			lName.setBackground(Color.WHITE);
			lName.setBounds(47, 263, 356, 40);
			mainPanel.add(lName);
			
			collegeID = new JTextField();
			collegeID.setToolTipText("");
			collegeID.setFont(new Font("Tahoma", Font.PLAIN, 14));
			collegeID.setColumns(10);
			collegeID.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
			collegeID.setBackground(Color.WHITE);
			collegeID.setBounds(47, 353, 356, 40);
			mainPanel.add(collegeID);
			
			email = new JTextField();
			email.setToolTipText("");
			email.setFont(new Font("Tahoma", Font.PLAIN, 14));
			email.setColumns(10);
			email.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
			email.setBackground(Color.WHITE);
			email.setBounds(47, 448, 356, 40);
			mainPanel.add(email);
			
			JSeparator separator = new JSeparator();
			separator.setOrientation(SwingConstants.VERTICAL);
			separator.setForeground(Color.DARK_GRAY);
			separator.setBackground(Color.DARK_GRAY);
			separator.setBounds(459, 80, 17, 507);
			mainPanel.add(separator);
			
			JLabel title = new JLabel("Teacher Section");
			title.setFont(new Font("Tahoma", Font.BOLD, 26));
			title.setHorizontalAlignment(SwingConstants.CENTER);
			title.setBounds(374, 10, 317, 65);
			mainPanel.add(title);
			
			JButton refreshBtn = new JButton("Refresh");
			refreshBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					tf.notify("refresh");
				}
			});
			refreshBtn.setBounds(1133, 123, 85, 21);
			mainPanel.add(refreshBtn);
			
			JButton btnEdit = new JButton("Edit");
			btnEdit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					tf.notify("edit");
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
					tf.notify("remove");
				}
			});
			btnRemove.setBounds(1138, 501, 90, 35);
			mainPanel.add(btnRemove);

			scrollPane = new JScrollPane();
			scrollPane.setBounds(532, 153, 691, 338);
			mainPanel.add(scrollPane);		
			
			table = new JTable(ti.getTeacherDefaultRow(),ti.getTeacherTableColumn());
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
					if(sortBox.getSelectedItem().toString().equals("Name"))
						tf.filterAscending(1, table, model);
					
					if(sortBox.getSelectedItem().toString().equals("EmployeeID"))
						tf.filterAscending(3, table, model);
				}
			});
			sortBox.setModel(new DefaultComboBoxModel(new String[] {"Name", "EmployeeID"}));
			sortBox.setBounds(600, 122, 77, 22);
			mainPanel.add(sortBox);
			
			JButton btnNewButton = new JButton("Commit");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tf.notify("Commit");
				}
			});
			btnNewButton.setBounds(1138, 578, 85, 21);
			mainPanel.add(btnNewButton);
			
			fName_label = new JLabel();
			fName_label.setForeground(Color.RED);
			fName_label.setFont(new Font("Tahoma", Font.PLAIN, 12));
			fName_label.setBounds(47, 210, 392, 20);
			mainPanel.add(fName_label);
			
			lName_label = new JLabel();
			lName_label.setForeground(Color.RED);
			lName_label.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lName_label.setBounds(47, 305, 392, 20);
			mainPanel.add(lName_label);
			
			collegeId_label = new JLabel();
			collegeId_label.setForeground(Color.RED);
			collegeId_label.setFont(new Font("Tahoma", Font.PLAIN, 12));
			collegeId_label.setBounds(47, 395, 392, 20);
			mainPanel.add(collegeId_label);
			
			email_label = new JLabel();
			email_label.setForeground(Color.RED);
			email_label.setFont(new Font("Tahoma", Font.PLAIN, 12));
			email_label.setBounds(47, 489, 392, 20);
			mainPanel.add(email_label);
			
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
			tf.notify("refresh");
		}
}
