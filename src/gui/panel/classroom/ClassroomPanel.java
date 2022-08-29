package gui.panel.classroom;

import java.awt.Color;


import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import functionality.ClassRoomFunction;
import functionality.SubjectFunction;
import utility.TableInfo;

public class ClassroomPanel
{
	private static ClassroomPanel singleInstance;			
	public static ClassroomPanel getInstance()
	{
		if(singleInstance == null)
		{
			singleInstance = new ClassroomPanel();
		}
		return singleInstance;				
	}
			
	// --- Object References ---//
	TableInfo ti = TableInfo.getInstance();
	ClassRoomFunction cf;		
	public void setClassReference(ClassRoomFunction cf)
	{
		this.cf = cf;
		System.out.println("ClassPanel: ClassFunction Reference set: "+cf);
	}
	
	JPanel mainPanel;
//	public JTextField classNumber;
//	public JTextField className;
	public String classId;
	public String section;
	private JTable table1;
	public JScrollPane scrollPane1;
	public JTable table2;
	public JScrollPane scrollPane2;
	private String selectedCellValue; 
	private String selectedRowPK;
	private int selectedColumn; 
	public int selectedRowNumber;
	public String selectedColumnName;
	
	private JCheckBox secA;
	private JCheckBox secB;
	private JCheckBox secC;
	private JCheckBox secD;
	private JCheckBox secE;
	public DefaultTableModel model;
	
	
	
	
	public JPanel getClassRooms()
	{
		return mainPanel;		
	}
	
	private ClassroomPanel()
	{
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBounds(80,50,1280,630);
		mainPanel.setLayout(null);
		
		JButton button_AddClassrooms_Classrooms = new JButton("ADD");
		button_AddClassrooms_Classrooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				cf.notify("add");
			}
		});
		button_AddClassrooms_Classrooms.setForeground(Color.WHITE);
		button_AddClassrooms_Classrooms.setFont(new Font("Tahoma", Font.BOLD, 20));
		button_AddClassrooms_Classrooms.setBackground(new Color(34, 139, 34));
		button_AddClassrooms_Classrooms.setBounds(253, 354, 150, 40);
		mainPanel.add(button_AddClassrooms_Classrooms);
		
		JLabel label_ClassNumber_CLassrooms = new JLabel("Select Sections :");
		label_ClassNumber_CLassrooms.setFont(new Font("Calibri", Font.BOLD, 18));
		label_ClassNumber_CLassrooms.setBounds(47, 235, 130, 39);
		mainPanel.add(label_ClassNumber_CLassrooms);
		
		JLabel label_ClassName_Classrooms = new JLabel("Class Id :");
		label_ClassName_Classrooms.setFont(new Font("Calibri", Font.BOLD, 18));
		label_ClassName_Classrooms.setBounds(47, 154, 120, 39);
		mainPanel.add(label_ClassName_Classrooms);
		
		JLabel label_LeftSubHeading = new JLabel("Enter the details below to add a Class.");
		label_LeftSubHeading.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_LeftSubHeading.setBounds(26, 119, 392, 25);
		mainPanel.add(label_LeftSubHeading);
		
		JLabel title = new JLabel("Classroom Section");
		title.setFont(new Font("Tahoma", Font.BOLD, 26));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(374, 10, 317, 65);
		mainPanel.add(title);
		
		String [] classIdList = {"LKG","UKG","1","2","3","4","5","6","7","8","9","10","11","12"};
		
		JComboBox classIdc = new JComboBox(classIdList);
		classIdc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				classId = (String)classIdc.getSelectedItem();
			}
		});
		classIdc.setBounds(47, 200, 200, 30);
		classIdc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mainPanel.add(classIdc);
		
		secA = new JCheckBox("A");
		secA.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				secCheck("A");
			}
		});
		secA.setBounds(50, 270, 40, 40);
		secA.setFont(new Font("Tahoma", Font.BOLD, 14));
		secA.setBackground(Color.white);
		secA.setSelected(true);
		mainPanel.add(secA);
		
		secB = new JCheckBox("B");
		secB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				secCheck("B");
			}
		});
		secB.setBounds(100, 270, 40, 40);
		secB.setFont(new Font("Tahoma", Font.BOLD, 14));
		secB.setBackground(Color.white);
		mainPanel.add(secB);
		
		secC = new JCheckBox("C");
		secC.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				secCheck("C");
			}
		});
		secC.setBounds(150, 270, 40, 40);
		secC.setFont(new Font("Tahoma", Font.BOLD, 14));
		secC.setBackground(Color.white);
		mainPanel.add(secC);
		
		secD = new JCheckBox("D");
		secD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				secCheck("D");
			}
		});
		secD.setBounds(200, 270, 40, 40);
		secD.setFont(new Font("Tahoma", Font.BOLD, 14));
		secD.setBackground(Color.white);
		mainPanel.add(secD);
		
		secE = new JCheckBox("E");
		secE.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				secCheck("E");
			}
		});
		secE.setBounds(250, 270, 40, 40);
		secE.setFont(new Font("Tahoma", Font.BOLD, 14));
		secE.setBackground(Color.white);
		mainPanel.add(secE);
		
		/*classNumber = new JTextField();
		classNumber.setToolTipText("");
		
		classNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		classNumber.setColumns(10);
		classNumber.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		classNumber.setBackground(Color.WHITE);
		classNumber.setBounds(47, 270, 356, 40);
		mainPanel.add(classNumber);
		
		className = new JTextField();
		className.setToolTipText("");
		
		className.setFont(new Font("Tahoma", Font.PLAIN, 14));
		className.setColumns(10);
		className.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		className.setBackground(Color.WHITE);
		className.setBounds(47, 185, 356, 40);
		mainPanel.add(className);*/
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(Color.DARK_GRAY);
		separator.setBackground(Color.DARK_GRAY);
		separator.setBounds(459, 80, 17, 507);
		mainPanel.add(separator);
		
		JButton refreshBtn = new JButton("Refresh");
		refreshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				cf.notify("refresh");
			}
		});
		refreshBtn.setBounds(1133, 123, 85, 21);
		mainPanel.add(refreshBtn);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				cf.notify("edit");
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
				cf.notify("remove");
			}
		});
		btnRemove.setBounds(1138, 501, 90, 35);
		mainPanel.add(btnRemove);
		
		scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(532, 153, 560, 338);
		mainPanel.add(scrollPane1);
		
		table1 = new JTable(ti.getClassDefaultRow(),ti.getClassTableColumn());
		scrollPane1.setViewportView(table1);
		
        model = new DefaultTableModel();
        table1.setModel(model);
		
		scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(1100, 153, 120, 338);
		mainPanel.add(scrollPane2);
		
		table2 = new JTable();		
		table2.setModel(new DefaultTableModel(
			new Object[][] {
				{"Loading..."}
			},
			new String[] {
				"Section"
			}
		));
		scrollPane2.setViewportView(table2);
		
		/*JLabel lblNewLabel = new JLabel("Sort By :");
		lblNewLabel.setBounds(532, 126, 46, 14);
		mainPanel.add(lblNewLabel);
		
		JComboBox sortBox = new JComboBox();
		sortBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		sortBox.setModel(new DefaultComboBoxModel(new String[] {"Name", "EmployeeID"}));
		sortBox.setBounds(600, 122, 77, 22);
		mainPanel.add(sortBox);*/
		
		JButton btnNewButton = new JButton("Commit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cf.notify("commit");
			}
		});
		btnNewButton.setBounds(1138, 578, 85, 21);
		mainPanel.add(btnNewButton);
		
		
	}
	
	public void activateTableActionListener()
	{
		table1.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) 
			{
	            selectedCellValue = table1.getValueAt(table1.getSelectedRow() , table1.getSelectedColumn()).toString();
				selectedRowNumber = Integer.parseInt(table1.getValueAt(table1.getSelectedRow() , 0).toString());
				selectedColumn = table1.getSelectedColumn();
				selectedColumnName = selectedColumn == 0?table1.getColumnName(2):table1.getColumnName(selectedColumn);
	            
				cf.showSection(selectedCellValue);
				
				System.out.println(selectedCellValue);
	            System.out.println("Row Number: "+selectedRowNumber);
	            System.out.println("SelectedColumn: "+selectedColumn+" "+selectedColumnName);	            
	            System.out.println("Action listener activated.");
			}
		});
	}
	
	public void refresh()	
	{
		cf.notify("refresh");
	}
	
	private void secCheck(String sec) 
	{
		if(sec.equals("A")) {
			secA.setSelected(true);
			secB.setSelected(false);
			secC.setSelected(false);
			secD.setSelected(false);
			secE.setSelected(false);
			
			section = "A";
			
			System.out.println("Section "+sec);
		}
		if(sec.equals("B")) {
			secA.setSelected(true);
			secB.setSelected(true);
			secC.setSelected(false);
			secD.setSelected(false);
			secE.setSelected(false);
			
			section = "A,B";
			
			System.out.println("Section "+sec);
		}
		if(sec.equals("C")) {
			secA.setSelected(true);
			secB.setSelected(true);
			secC.setSelected(true);
			secD.setSelected(false);
			secE.setSelected(false);
			
			section = "A,B,C";
			
			System.out.println("Section "+sec);
		}
		if(sec.equals("D")) {
			secA.setSelected(true);
			secB.setSelected(true);
			secC.setSelected(true);
			secD.setSelected(true);
			secE.setSelected(false);
			
			section = "A,B,C,D";
			
			System.out.println("Section "+sec);
		}
		if(sec.equals("E")) {
			secA.setSelected(true);
			secB.setSelected(true);
			secC.setSelected(true);
			secD.setSelected(true);
			secE.setSelected(true);
			
			section = "A,B,C,D,E";
			
			System.out.println("Section "+sec);
		}
	}
	
}

			
			