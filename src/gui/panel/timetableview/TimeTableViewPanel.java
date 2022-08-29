package gui.panel.timetableview;

import java.awt.Color;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import database.cached.DatabaseCached;
import database.cached.UserDatabase;
import function.grid.automation.GridAutomationFunction;
import function.grid.random.Randomizer;
import functionality.GridFunction;
import functionality.TeacherFunction;
import functionality.TimeTableViewFunction;
import gui.grid.preference.PreferencePanel;
import gui.grid.timetable.TimeTablePanel;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTabbedPane;



public class TimeTableViewPanel
{
	private static TimeTableViewPanel singleInstance;			
	public static TimeTableViewPanel getInstance()
	{
		if(singleInstance == null)
		{
			singleInstance = new TimeTableViewPanel();
		}
		return singleInstance;				
	}
			
	private JPanel panel;
//	private JLabel statusLabel;
//	public JComboBox comboBox;
	
	private GridAutomationFunction gaf;
	public JComboBox teacherComboBox;
	public JComboBox classComboBox;
//	public JComboBox subjectComboBox;
	
	TimeTableViewFunction tvf;
	public JComboBox sectionComboBox;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public JRadioButton teacherCheckButton;
	public JRadioButton classCheckButton;
	public JLabel title;

	public JRadioButton labCheckButton;

	public JComboBox labComboBox;
	public void setGridReference(TimeTableViewFunction tvf)
	{
		this.tvf = tvf;
		System.out.println("TeacherPanel: TeacherFunction Reference set: "+tvf);
	}
	
	public JPanel getGrid()
	{
		return panel;		
	}
	
	public void setGridAutomationFunction(GridAutomationFunction gaf) 
	{
		this.gaf = gaf;
	}
	
	private TimeTableViewPanel()
	{
		panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setBounds(80,50,1280,630);
		panel.setLayout(null);
		
		/*comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String[] temp = comboBox.getSelectedItem().toString().split("X");
				int numberOfColumn = Integer.parseInt(temp[0]);
				int numberOfRow = Integer.parseInt(temp[1]);
				
				
				gaf.removeCards(1);
				gaf.removeList(1);
				//addAllCards();
				gaf.addDaysCards(numberOfColumn,numberOfRow,1);
				gaf.addPeriodCards(numberOfRow,1);
//				statusLabel.setText(comboBox.getSelectedItem()+" grid generated.");
//				statusLabel.setForeground(Color.GREEN);
				TimeTablePanel.getInstance(1).refresh();
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"5X6", "5X7", "5X8", "5X9", "6X6", "6X7", "6X8", "6X9"}));
		comboBox.setBounds(161, 38, 139, 22);
		panel.add(comboBox);*/
			
		
		JLabel labelTeacher = new JLabel("Teacher");
		labelTeacher.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelTeacher.setBounds(51, 76, 89, 22);
		panel.add(labelTeacher);
		
		JLabel hover = new JLabel();
		hover.setHorizontalAlignment(SwingConstants.CENTER);
		hover.setFont(new Font("Century", Font.PLAIN, 14));
		hover.setBackground(Color.WHITE);
		hover.setOpaque(true);
		hover.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.DARK_GRAY, Color.GRAY, Color.DARK_GRAY, Color.GRAY));
		hover.setVisible(false);
		panel.add(hover);
		
		teacherComboBox = new JComboBox();
		teacherComboBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) 
			{
				String name = tvf.getHoverData(teacherComboBox.getSelectedItem().toString());
				hover.setText(name);
				hover.setBounds(e.getX()+160, 100, name.length()*9, 25);
				hover.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				hover.setVisible(false);
			}
		});
		teacherComboBox.setBounds(161, 76, 139, 21);
		panel.add(teacherComboBox);
		
		sectionComboBox = new JComboBox();
		sectionComboBox.setModel(new DefaultComboBoxModel(new String[] {"-Select-"}));
		sectionComboBox.setBounds(752, 76, 61, 21);
		panel.add(sectionComboBox);
		
		JLabel lblSection = new JLabel("Section");
		lblSection.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSection.setBounds(678, 76, 61, 22);
		panel.add(lblSection);
		
		classComboBox = new JComboBox();
		classComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sectionComboBox.setModel(new DefaultComboBoxModel(tvf.fillComboBox(classComboBox.getSelectedItem().toString())));				
			}
			
		});
		classComboBox.setBounds(596, 76, 61, 21);
		panel.add(classComboBox);
		
		JLabel labelClass = new JLabel("Class");
		labelClass.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelClass.setBounds(500, 76, 61, 22);
		panel.add(labelClass);
		
		/*subjectComboBox = new JComboBox();
		subjectComboBox.setModel(new DefaultComboBoxModel(new String[] {"JAVA", "English", "Commerce"}));
		subjectComboBox.setBounds(633, 76, 139, 21);
		panel.add(subjectComboBox);
		
		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSubject.setBounds(572, 76, 61, 22);
		panel.add(lblSubject);
		
		JLabel labelTabelFormat = new JLabel("Grid Size:");
		labelTabelFormat.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelTabelFormat.setBounds(51, 36, 100, 22);
		panel.add(labelTabelFormat);*/
		
		JLabel lblPreferences = new JLabel("Preferences");
		lblPreferences.setHorizontalAlignment(SwingConstants.CENTER);
		lblPreferences.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPreferences.setBounds(1010, 106, 198, 22);
		panel.add(lblPreferences);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(Color.DARK_GRAY);
		separator.setBackground(Color.DARK_GRAY);
		separator.setBounds(957, 94, 17, 507);
		panel.add(separator);
		
//		panel.add(PreferencePanel.getInstance().getPreference());
		TimeTablePanel.getInstance(1).addRowStrip(1);
		panel.add(TimeTablePanel.getInstance(1).getTimeTablePanel());
		
		teacherCheckButton = new JRadioButton("");
		buttonGroup.add(teacherCheckButton);
		teacherCheckButton.setBackground(Color.WHITE);
		teacherCheckButton.setBounds(138, 76, 21, 21);
		teacherCheckButton.setSelected(true);
		panel.add(teacherCheckButton);
		
		classCheckButton = new JRadioButton("");
		buttonGroup.add(classCheckButton);
		classCheckButton.setBackground(Color.WHITE);
		classCheckButton.setBounds(569, 77, 21, 21);
		panel.add(classCheckButton);
		
		JButton btnNewButton_1 = new JButton("Show");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Randomizer.getInstance().removeAllAllocation2(1);
				tvf.getTimeTable();
			}
		});
		btnNewButton_1.setBounds(861, 76, 89, 23);
		panel.add(btnNewButton_1);
		
		JButton pdfButton = new JButton("Save As PDF");
		pdfButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tvf.generateTimeTable();
			}
			
		});
		pdfButton.setBounds(1050, 224, 150, 23);
		panel.add(pdfButton);
		
		JButton emailButton = new JButton("Email Time Table");
		emailButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tvf.emailTimeTable();
			}
			
		});
		emailButton.setBounds(1050, 293, 150, 23);
		panel.add(emailButton);
		
		JButton printButton = new JButton("Print Time Table");
		printButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tvf.printTimeTable();
			}
			
		});
		printButton.setBounds(1050, 365, 150, 23);
		panel.add(printButton);
		
		/*
		 * JButton delButton = new JButton("Delete Time Table");
		 * delButton.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) { tvf.notify("remove");
		 * }
		 * 
		 * }); delButton.setBounds(1050, 437, 150, 23); panel.add(delButton);
		 */
		
		title = new JLabel("Time Table");
		title.setFont(new Font("Tahoma", Font.BOLD, 30));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(400, 20, 500, 40);
		panel.add(title);
		
		JLabel labelClass_1 = new JLabel("Lab");
		labelClass_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelClass_1.setBounds(330, 76, 61, 22);
		panel.add(labelClass_1);
		
		labCheckButton = new JRadioButton();
		buttonGroup.add(labCheckButton);
		labCheckButton.setBackground(Color.WHITE);
		labCheckButton.setBounds(379, 77, 21, 21);
		panel.add(labCheckButton);
		
		labComboBox = new JComboBox();
		labComboBox.setBounds(406, 76, 70, 21);
		panel.add(labComboBox);
		
	}
	
	public void addSlotPanels() 
	{
		gaf.removeCards(1);
		gaf.removeList(1);
		gaf.addDaysCards(UserDatabase.getInstance().getColumn(),UserDatabase.getInstance().getRow(),1);
		gaf.addPeriodCards(UserDatabase.getInstance().getRow(),1);
		TimeTablePanel.getInstance(1).refresh();
	}

	public void refresh() 
	{
		tvf.notify("refresh");
	}
}
