package gui.panel.grid;

import java.awt.Color;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import database.cached.DatabaseCached;
import database.cached.UserDatabase;
import function.grid.automation.GridAutomationFunction;
import function.grid.random.Randomizer;
import functionality.GridFunction;
import functionality.TeacherFunction;
import gui.RowStrip;
import gui.grid.preference.PreferencePanel;
import gui.grid.timetable.TimeTablePanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class GridPanel
{
	private static GridPanel singleInstance;			
	public static GridPanel getInstance()
	{
		if(singleInstance == null)
		{
			singleInstance = new GridPanel();
		}
		return singleInstance;				
	}
			
	private JPanel panel;
//	private JLabel statusLabel;
//	private JComboBox comboBox;
	
	private GridAutomationFunction gaf;
//	public JComboBox teacherComboBox;
	public JComboBox classComboBox;
//	public JComboBox subjectComboBox;
	
	GridFunction gf;
	public JComboBox sectionComboBox;
	public void setGridReference(GridFunction gf)
	{
		this.gf = gf;
		System.out.println("TeacherPanel: TeacherFunction Reference set: "+gf);
	}
	
	public JPanel getGrid()
	{
		return panel;		
	}
	
	public void setGridAutomationFunction(GridAutomationFunction gaf) 
	{
		this.gaf = gaf;
	}
	
	private GridPanel()
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
				
				
				gaf.removeCards(0);
				gaf.removeList(0);
				//addAllCards();
				gaf.addDaysCards(numberOfColumn,numberOfRow,0);
				gaf.addPeriodCards(numberOfRow,0);
//				statusLabel.setText(comboBox.getSelectedItem()+" grid generated.");
//				statusLabel.setForeground(Color.GREEN);
				TimeTablePanel.getInstance(0).refresh();
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"5X6", "5X7", "5X8", "5X9", "6X6", "6X7", "6X8", "6X9"}));
		comboBox.setBounds(161, 38, 139, 22);
		panel.add(comboBox);*/
		
		
		/*JButton btnNewButton = new JButton("Generate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{			
				removeCards();
				removeMList();
				addAllCards();
				statusLabel.setText(comboBox.getSelectedItem()+" grid generated.");
				statusLabel.setForeground(Color.GREEN);
				TimeTablePanel.getInstance().refresh();
				
			}
		});
		btnNewButton.setBounds(295, 39, 115, 21);
		panel.add(btnNewButton);*/
		
		
		
		/*JLabel labelTeacher = new JLabel("Teacher");
		labelTeacher.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelTeacher.setBounds(51, 76, 100, 22);
		panel.add(labelTeacher);
		
		teacherComboBox = new JComboBox();
		teacherComboBox.setBounds(161, 76, 139, 21);
		panel.add(teacherComboBox);*/
		
		sectionComboBox = new JComboBox();
		sectionComboBox.setModel(new DefaultComboBoxModel(new String[] {"-Select-"}));
		sectionComboBox.setBounds(368, 75, 61, 21);
		panel.add(sectionComboBox);
		
		JLabel lblSection = new JLabel("Section :");
		lblSection.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSection.setBounds(278, 75, 61, 22);
		panel.add(lblSection);
		
		classComboBox = new JComboBox();
		classComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					sectionComboBox.setModel(new DefaultComboBoxModel(gf.fillComboBox(classComboBox.getSelectedItem().toString())));
				}
				catch(Exception ne) {}
			}
			
		});
		classComboBox.setBounds(161, 76, 69, 21);
		panel.add(classComboBox);
		
		JLabel labelClass = new JLabel("Class : ");
		labelClass.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelClass.setBounds(51, 76, 61, 22);
		panel.add(labelClass);
		
		/*subjectComboBox = new JComboBox();
		subjectComboBox.setModel(new DefaultComboBoxModel(new String[] {"JAVA", "English", "Commerce"}));
		subjectComboBox.setBounds(633, 76, 139, 21);
		panel.add(subjectComboBox);
		
		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSubject.setBounds(572, 76, 61, 22);
		panel.add(lblSubject);
		
		statusLabel = new JLabel("");
		statusLabel.setForeground(Color.green);
		statusLabel.setBounds(30, 105, 200, 20);
		panel.add(statusLabel);
		
		JLabel labelTabelFormat = new JLabel("Grid Size :");
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
		
		panel.add(PreferencePanel.getInstance().getPreference());
		TimeTablePanel.getInstance(0).addRowStrip(0);
		panel.add(TimeTablePanel.getInstance(0).getTimeTablePanel());

		JButton save = new JButton("Save");
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gf.notify("add");
				
			}
			
		});
		save.setBounds(646, 73, 89, 23);
		panel.add(save);
		
		JButton commit = new JButton("Commit");
		commit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gf.notify("commit");
			}
			
		});
		commit.setBounds(787, 73, 89, 23);
		panel.add(commit);
		JButton btnNewButton_1 = new JButton("Allot");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//PeriodStrip.getInstance().addPanelDetails(1, "1st Period","8:30 AM");
				String className = classComboBox.getSelectedItem().toString();
				String section = sectionComboBox.getSelectedItem().toString();
				//Randomizer.getInstance().startAlloation(new String[]{subject},teacher,0, null, null);
				Randomizer.getInstance().removeAllAllocation2(0);
//				Randomizer.getInstance().startAlloation2(subject, teacher, 0, null, null, numberOfColumn);
				Randomizer.getInstance().generateValues(className, section);
			}
		});
		btnNewButton_1.setBounds(509, 73, 89, 23);
		panel.add(btnNewButton_1);
		
	}

	public void refresh() 
	{
		gf.notify("refresh");
	}
	
	public void addSlotPanels() 
	{
		gaf.removeCards(0);
		gaf.removeList(0);
		gaf.addDaysCards(UserDatabase.getInstance().getColumn(),UserDatabase.getInstance().getRow(),0);
		gaf.addPeriodCards(UserDatabase.getInstance().getRow(),0);
		TimeTablePanel.getInstance(0).refresh();
	}
}
