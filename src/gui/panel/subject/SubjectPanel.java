package gui.panel.subject;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import functionality.SubjectFunction;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import utility.TableInfo;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class SubjectPanel 
{
	private static SubjectPanel singleInstance;	
	public static SubjectPanel getInstance()
	{
		if(singleInstance == null)
		{
			singleInstance = new SubjectPanel();
		}
		return singleInstance;	
	}
	
	// --- Object References ---//
	TableInfo ti = TableInfo.getInstance();
	SubjectFunction sf;		
	public void setSubjectReference(SubjectFunction sf)
	{
		this.sf = sf;
		System.out.println("SubjectPanel: SubjectFunction Reference set: "+sf);
	}
	
	JPanel mainPanel;
	public JTextField subjectCodetxt;
	public JTextField subTitle;
	private JTable table;
	public JScrollPane scrollPane;
	public Integer selectedRowNumber;
	public String selectedCellValue;
	public int selectedColumn;
	public String selectedColumnName;
	public DefaultTableModel model;
	public JComboBox numberLecture;
	public JComboBox numberTutorial;
	public JComboBox numberLabs;
	public JLabel label_Code;
	public JLabel label_Title;
	public JLabel label_Credit;
	
	
	public JPanel getSubjectPanel()
	{
		return mainPanel;		
	}
	
	private SubjectPanel()
	{
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBounds(80,50,1280,630);
		mainPanel.setLayout(null);
		
		subjectCodetxt = new JTextField();
		subjectCodetxt.setToolTipText("");
		
		subjectCodetxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		subjectCodetxt.setColumns(10);
		subjectCodetxt.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		subjectCodetxt.setBackground(Color.WHITE);
		subjectCodetxt.setBounds(47, 187, 356, 40);
		mainPanel.add(subjectCodetxt);
		
		subTitle = new JTextField();
		subTitle.setToolTipText("");
		
		subTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		subTitle.setColumns(10);
		subTitle.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		subTitle.setBackground(Color.WHITE);
		subTitle.setBounds(47, 280, 356, 40);
		mainPanel.add(subTitle);
		
		JLabel label_SubjectCode_Subjects = new JLabel("Subject Code:");
		label_SubjectCode_Subjects.setFont(new Font("Calibri", Font.BOLD, 18));
		label_SubjectCode_Subjects.setBounds(47, 155, 120, 39);
		mainPanel.add(label_SubjectCode_Subjects);
		
		JLabel label_SubjectTitle_Subjects = new JLabel("Subject Title:");
		label_SubjectTitle_Subjects.setFont(new Font("Calibri", Font.BOLD, 18));
		label_SubjectTitle_Subjects.setBounds(47, 250, 120, 39);
		mainPanel.add(label_SubjectTitle_Subjects);
		
		JButton addBtn = new JButton("Add");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sf.notify("add");
			}
		});
		addBtn.setForeground(Color.WHITE);
		addBtn.setFont(new Font("Tahoma", Font.BOLD, 20));
		addBtn.setBackground(new Color(34, 139, 34));
		addBtn.setBounds(253, 494, 150, 40);
		mainPanel.add(addBtn);
		
		JLabel title = new JLabel("Subject Section");
		title.setFont(new Font("Tahoma", Font.BOLD, 26));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(374, 10, 317, 65);
		mainPanel.add(title);
		
		JLabel label_LeftSubHeading = new JLabel("Enter the details below to add a Subject.");
		label_LeftSubHeading.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_LeftSubHeading.setBounds(26, 119, 392, 25);
		mainPanel.add(label_LeftSubHeading);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(Color.DARK_GRAY);
		separator.setBackground(Color.DARK_GRAY);
		separator.setBounds(459, 80, 17, 507);
		mainPanel.add(separator);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(532, 153, 691, 338);
		mainPanel.add(scrollPane);
		
		table = new JTable(ti.getSubjectDefaultRow(),ti.getSubjectTableColumn());	
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);
		
        model = new DefaultTableModel();
        table.setModel(model);
		
		JButton button_Edit_Subjects = new JButton("Edit");
		button_Edit_Subjects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				sf.notify("edit");
			}
		});
		button_Edit_Subjects.setBounds(1020, 501, 85, 35);
		mainPanel.add(button_Edit_Subjects);
		
		JButton button_Refresh_Subjects = new JButton("Refresh");
		button_Refresh_Subjects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				sf.notify("refresh");
			}
		});
		button_Refresh_Subjects.setBounds(1133, 123, 85, 21);
		mainPanel.add(button_Refresh_Subjects);
		
		/*
		 * JButton button_Generate_Subjects = new JButton("Generate");
		 * button_Generate_Subjects.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent arg0) { } });
		 * button_Generate_Subjects.setBounds(1138, 501, 90, 35);
		 * mainPanel.add(button_Generate_Subjects);
		 */
		
		JButton button_Remove_Subjects = new JButton("Remove");
		button_Remove_Subjects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				sf.notify("remove");
			}
		});
		button_Remove_Subjects.setBounds(1138, 501, 90, 35);
		mainPanel.add(button_Remove_Subjects);
		
		JLabel lblNewLabel = new JLabel("Sort By :");
		lblNewLabel.setBounds(532, 126, 60, 14);
		mainPanel.add(lblNewLabel);
		
		JComboBox sortBox = new JComboBox();
		sortBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(sortBox.getSelectedItem().toString().equals("Title"))
					sf.filterAscending(2, table, model);
				
				if(sortBox.getSelectedItem().toString().equals("Code"))
					sf.filterAscending(1, table, model);
			}
		});
		sortBox.setModel(new DefaultComboBoxModel(new String[] {"Title", "Code"}));
		sortBox.setBounds(600, 122, 77, 22);
		mainPanel.add(sortBox);
		
		JButton btnNewButton = new JButton("Commit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sf.notify("commit");
			}
		});
		btnNewButton.setBounds(1138, 578, 85, 21);
		mainPanel.add(btnNewButton);
		
		JLabel label_SubjectTitle_Subjects_1 = new JLabel("Subject Credit:");
		label_SubjectTitle_Subjects_1.setFont(new Font("Calibri", Font.BOLD, 18));
		label_SubjectTitle_Subjects_1.setBounds(47, 350, 120, 39);
		mainPanel.add(label_SubjectTitle_Subjects_1);
		
		numberLecture = new JComboBox(new String[] {"0","1","2","3","4","5"});
		numberLecture.setBounds(47, 410, 54, 22);
		mainPanel.add(numberLecture);
		
		numberTutorial = new JComboBox(new String[] {"0","1","2","3","4","5"});
		numberTutorial.setBounds(125, 410, 54, 22);
		mainPanel.add(numberTutorial);
		
		numberLabs = new JComboBox(new String[] {"0","2","4","6"});
		numberLabs.setBounds(206, 410, 54, 22);
		mainPanel.add(numberLabs);
		
		label_Code = new JLabel();
		label_Code.setForeground(Color.RED);
		label_Code.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_Code.setBounds(47, 227, 392, 25);
		mainPanel.add(label_Code);
		
		label_Title = new JLabel();
		label_Title.setForeground(Color.RED);
		label_Title.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_Title.setBounds(47, 320, 392, 25);
		mainPanel.add(label_Title);
		
		label_Credit = new JLabel();
		label_Credit.setForeground(Color.RED);
		label_Credit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_Credit.setBounds(47, 436, 392, 25);
		mainPanel.add(label_Credit);
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
		sf.notify("refresh");
	}
}
