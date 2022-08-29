package gui.panel.advance;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import database.DatabaseManager;
import database.SubjectClassFunction;
import functionality.ClassRoomFunction;
import functionality.LabFunction;
import functionality.SubjectFunction;
import functionality.TeacherFunction;
import gui.frame.DashBoard;
import gui.frame.Footer;
import functionality.AdvanceFunction;
import utility.TableInfo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;

public class AdvancePanel
{
	private static AdvancePanel singleInstance;
	
	public static AdvancePanel getInstance()
	{
		if(singleInstance == null)
		{
			singleInstance = new AdvancePanel();
		}
		return singleInstance;
		
	}
	
	ClassRoomFunction cf;		
	public void setClassReference(ClassRoomFunction cf)
	{
		this.cf = cf;
		System.out.println("AdvancePanel: ClassFunction Reference set: "+cf);
	}	
	
	LabFunction lf;		
	public void setLabReference(LabFunction lf)
	{
		this.lf = lf;
		System.out.println("AdvancePanel: LabFunction Reference set: "+lf);
	}
	
	SubjectFunction sf;		
	public void setSubjectReference(SubjectFunction sf)
	{
		this.sf = sf;
		System.out.println("AdvancePanel: SubjectFunction Reference set: "+sf);
	}
	
	TeacherFunction tf;		
	public void setTeacherReference(TeacherFunction tf)
	{
		this.tf = tf;
		System.out.println("AdvancePanel: TeacherFunction Reference set: "+tf);
	}
	
	TableInfo ti = TableInfo.getInstance();
	
	SubjectClassFunction scf;		
	public void setSubjectClassReference(SubjectClassFunction scf)
	{
		this.scf = scf;
		System.out.println("AdvancePanel: ClassFunction Reference set: "+scf);
	}	
	
	AdvanceFunction tsf;		
	public void setTeacherSubReference(AdvanceFunction tsf)
	{
		this.tsf = tsf;
		System.out.println("AdvancePanel: ClassFunction Reference set: "+scf);
	}	
	
	JPanel mainPanel;
	public JTable table_1;
	public JTable table_2;
	public JComboBox teacherSearch;
	public JComboBox subjectSearch;
	public JComboBox subject;
	public JComboBox teacher;
	private String selectedRowPK1;
	private String selectedRowPK2;

	public JScrollPane scrollPane_1;
	public JScrollPane scrollPane_2;

	public JComboBox section;

	public JComboBox classId;

	private String selectedCellValue;
	public int selectedColumn;
	public int selectedRowNumber;
	public String selectedColumnName;
	
	public DefaultTableModel table_1m;
	public DefaultTableModel table_2m;
	
	
	public JPanel getAdvance()
	{
		return mainPanel;		
	}
	
	private AdvancePanel()
	{		
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBounds(80,50,1280,630);
		mainPanel.setLayout(null);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(Color.DARK_GRAY);
		separator_1.setBackground(Color.DARK_GRAY);
		separator_1.setBounds(852, 78, 17, 507);
		mainPanel.add(separator_1);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(30, 400, 793, 177);
		mainPanel.add(scrollPane_1);

		table_1m = new DefaultTableModel();
		table_1m.setDataVector(ti.getTeacherSubDefaultRow(),ti.getTeacherSubTableColumn());

		TableRowSorter<DefaultTableModel> table1Sorter = new TableRowSorter<DefaultTableModel>(table_1m);
		
		table_1 = new JTable();
		table_1.setModel(table_1m);	
		table_1.setAutoCreateRowSorter(true);
		scrollPane_1.setViewportView(table_1);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(879, 400, 372, 177);
		mainPanel.add(scrollPane_2);
		
		table_2m = new DefaultTableModel();
		table_2m.setDataVector(ti.getTeacherSubDefaultRow(),ti.getTeacherSubTableColumn());

		TableRowSorter<DefaultTableModel> table2Sorter = new TableRowSorter<DefaultTableModel>(table_2m);
		
		table_2 = new JTable();
		table_2.setModel(table_2m);
		table_2.setAutoCreateRowSorter(true);
		scrollPane_2.setViewportView(table_2);
		
		JPanel setClass = new JPanel();
		setClass.setBounds(30,150,793,150);
		setClass.setBackground(Color.GRAY);
		setClass.setBorder(new MatteBorder(6,6,6,6,(Color) Color.LIGHT_GRAY));
		setClass.setLayout(null);
		mainPanel.add(setClass);
		
		JButton btnNewButton_1_1 = new JButton("Remove");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				tsf.notify("remove");
			}
		});
		btnNewButton_1_1.setBounds(130, 350, 85, 32);
		mainPanel.add(btnNewButton_1_1);
		
		JButton commit = new JButton("Commit");
		commit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				tsf.notify("commit");
			}
		});
		commit.setBounds(30, 350, 85, 32);
		mainPanel.add(commit);
		
		JButton btnNewButton_1_2 = new JButton("Save");
		btnNewButton_1_2.setBounds(650, 90, 85, 32);
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				System.out.println("Saving Data");
				tsf.notify("add");
			}
		});
		setClass.add(btnNewButton_1_2);
		
//		JButton btnNewButton_1_3 = new JButton("Generate");
//		btnNewButton_1_3.setBounds(1170, 266, 90, 32);
//		mainPanel.add(btnNewButton_1_3);
		
		JButton btnNewButton_1_4 = new JButton("Refresh");
		btnNewButton_1_4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tsf.notify("refresh");
			}
			
		});
		btnNewButton_1_4.setBounds(1161, 268, 90, 32);
		mainPanel.add(btnNewButton_1_4);
		
		JLabel lblNewLabel = new JLabel("Teacher:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
//		lblNewLabel.setBackground(Color.GRAY);
//		lblNewLabel.setOpaque(true);
		lblNewLabel.setBounds(40, 25, 100, 17);
		setClass.add(lblNewLabel);
		
		JLabel hover = new JLabel();
		hover.setHorizontalAlignment(SwingConstants.CENTER);
		hover.setFont(new Font("Century", Font.PLAIN, 14));
		hover.setBackground(Color.WHITE);
		hover.setOpaque(true);
		hover.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.DARK_GRAY, Color.GRAY, Color.DARK_GRAY, Color.GRAY));
		hover.setVisible(false);
		
		JLabel lblNewLabel_1 = new JLabel("Subject:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
//		lblNewLabel_1.setBackground(Color.GRAY);
//		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBounds(210, 25, 61, 17);
		setClass.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Class:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(395, 25, 61, 17);
		setClass.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Section:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(550, 27, 86, 13);
		setClass.add(lblNewLabel_3);
		
		teacher = new JComboBox(new String [] {"Loading..."});		
		teacher.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) 
			{
				try {
					String name = tsf.getHoverData(teacher.getSelectedItem().toString(),0);
					hover.setText(name);
					hover.setBounds(e.getX()+40, 80, name.length()*9, 25);
					setClass.add(hover);
					hover.setVisible(true);
				} 
				catch (Exception e1) {}
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				hover.setVisible(false);
			}
		});
		teacher.setBounds(40, 50, 120, 21);
		setClass.add(teacher);
		
		subject = new JComboBox(new String [] {"Loading..."});
		subject.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) 
			{
				try {
					String name = tsf.getHoverData(subject.getSelectedItem().toString(),3);
					hover.setText(name);
					hover.setBounds(e.getX()+200, 80, name.length()*8, 25);
					setClass.add(hover);
					hover.setVisible(true);
				} 
				catch (Exception e1) {}
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				hover.setVisible(false);
			}
		});
		subject.setBounds(210, 50, 120, 21);
		setClass.add(subject);
		
		classId = new JComboBox(new String [] {"Loading.."});
		classId.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				section.setModel(new DefaultComboBoxModel(tsf.fillComboBox(classId.getSelectedItem().toString())));
			}
		});
		classId.setBounds(395, 50, 100, 21);
		setClass.add(classId);
		
		section = new JComboBox(new String [] {"Loading..."});
		section.setBounds(550, 50, 100, 21);
		setClass.add(section);
		
		subjectSearch = new JComboBox();		
		subjectSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) 
			{
				try {
					String name = tsf.getHoverData(subjectSearch.getSelectedItem().toString(),3);
					hover.setText(name);
					hover.setBounds(e.getXOnScreen()-(name.length()*12), 320, name.length()*8, 25);
					mainPanel.add(hover);
					hover.setVisible(true);
				} 
				catch (Exception e1) {}
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				hover.setVisible(false);
			}
		});
		subjectSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				tsf.dataFilter(subjectSearch.getSelectedItem().toString(), table_1, table1Sorter);
			}
		});
		subjectSearch.setBounds(693, 354, 131, 21);
		mainPanel.add(subjectSearch);
		
		JLabel label_MiddleSubHeading_SubjectTeacher = new JLabel("Add Teachers to Particular Subject");
		label_MiddleSubHeading_SubjectTeacher.setHorizontalAlignment(SwingConstants.LEFT);
		label_MiddleSubHeading_SubjectTeacher.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_MiddleSubHeading_SubjectTeacher.setBounds(30, 115, 346, 26);
		mainPanel.add(label_MiddleSubHeading_SubjectTeacher);
		
		JLabel lbl_ListOfTeachers = new JLabel("List of Teachers for Subject :");
		lbl_ListOfTeachers.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_ListOfTeachers.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_ListOfTeachers.setBounds(483, 349, 204, 26);
		mainPanel.add(lbl_ListOfTeachers);
		
		teacherSearch = new JComboBox();
		teacherSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) 
			{
				try {
					String name = tsf.getHoverData(teacherSearch.getSelectedItem().toString(),0);
					hover.setText(name);
					hover.setBounds(e.getXOnScreen()-(name.length()*12), 320, name.length()*8, 25);
					mainPanel.add(hover);
					hover.setVisible(true);
				} 
				catch (Exception e1) {}
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				hover.setVisible(false);
			}
		});
		teacherSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				tsf.dataFilter(teacherSearch.getSelectedItem().toString(), table_2, table2Sorter);
			}
		});
		
		teacherSearch.setBounds(1120, 355, 131, 21);
		mainPanel.add(teacherSearch);
		
		JLabel lbl_RightSec_Adv_TeachingLoad = new JLabel("Teaching Load");
		lbl_RightSec_Adv_TeachingLoad.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_RightSec_Adv_TeachingLoad.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbl_RightSec_Adv_TeachingLoad.setBounds(1000, 142, 281, 25);
		mainPanel.add(lbl_RightSec_Adv_TeachingLoad);
		
		JLabel lbl_RightSec_Adv_SelectATeacher = new JLabel("Select a Teacher to display load :");
		lbl_RightSec_Adv_SelectATeacher.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_RightSec_Adv_SelectATeacher.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_RightSec_Adv_SelectATeacher.setBounds(894, 350, 223, 26);
		mainPanel.add(lbl_RightSec_Adv_SelectATeacher);
		
		JLabel title = new JLabel("Advance Section");
		title.setFont(new Font("Tahoma", Font.BOLD, 26));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(451, 10, 317, 65);
		mainPanel.add(title);
	}
	
	public void activateTableActionListener()
	{
		table_1.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) 
			{
	            selectedCellValue = table_1.getValueAt(table_1.getSelectedRow() , table_1.getSelectedColumn()).toString();
				selectedRowNumber = Integer.parseInt(table_1.getValueAt(table_1.getSelectedRow() , 0).toString());
				selectedColumn = table_1.getSelectedColumn();
				selectedColumnName = selectedColumn == 0?table_1.getColumnName(2):table_1.getColumnName(selectedColumn);
	            System.out.println(selectedCellValue);
	            System.out.println("Row Number: "+selectedRowNumber);
	            System.out.println("SelectedColumn: "+selectedColumn+" "+selectedColumnName);	            
	            System.out.println("Action listener activated.");
			}
		});
	}
	public void refresh()
	{
		tsf.notify("refresh");
	}

	
}
