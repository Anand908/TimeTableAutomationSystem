package gui.panel.userpanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import database.cached.UserDatabase;
import functionality.UserFunction;
import gui.SetGUI;
import gui.frame.userframe.UserFrame;
import start.StartApp;
import utility.TableInfo;

public class TimeDataPanel 
{
	
	// --- Single Instance ---//
	
	private static TimeDataPanel singleInstance;
		
	public static TimeDataPanel getInstance()
	{
		if(singleInstance == null)
		{
			singleInstance = new TimeDataPanel();
		}
		return singleInstance;
	}

	private JPanel mainPanel;
	public JSpinner classStart;
	public JSpinner classDuration;
	public JSpinner lunchPeriod;
	public JSpinner lunchDuration;
	public JCheckBox checkbox;
	public JButton backButton;
	public JButton submitButton;
	private UserFunction uf;
	
	public JPanel getPanel() 
	{
		return mainPanel;
	}

	private TimeDataPanel()
	{
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBounds(300,50,1060, 677);
		mainPanel.setBorder(new MatteBorder(0,2,2,2,new Color(60, 60, 60)));
		mainPanel.setLayout(null);
		
		JLabel clockImg = new JLabel();
		clockImg.setIcon(new ImageIcon(UserDataPanel.class.getResource("/img/clock.png")));
		clockImg.setBounds(455, 21, 150, 150);
		mainPanel.add(clockImg);
		
		JLabel nameLable = new JLabel("Class Start Time : ");
		nameLable.setHorizontalAlignment(SwingConstants.LEFT);
		nameLable.setFont(new Font("Calibri", Font.BOLD, 18));
		nameLable.setBounds(168, 203, 184, 39);
		mainPanel.add(nameLable);

		Date time = new Date();
		Date duration = new Date(0,0,0,0,45);
		
		classStart = new JSpinner(new SpinnerDateModel(time,null,null,Calendar.HOUR_OF_DAY));
		classStart.setEditor(new JSpinner.DateEditor(classStart, "hh:mm aa"));
		classStart.setFont(new Font("Tahoma", Font.BOLD, 20));
		classStart.setBounds(362, 203, 161, 40);
		((DefaultEditor) classStart.getEditor()).getTextField().setHorizontalAlignment(JTextField.CENTER);
		mainPanel.add(classStart);
		
		classDuration = new JSpinner(new SpinnerDateModel(duration,null,null,Calendar.HOUR_OF_DAY));
		classDuration.setEditor(new JSpinner.DateEditor(classDuration, "HH:mm"));
		((DefaultEditor) classDuration.getEditor()).getTextField().setHorizontalAlignment(JTextField.CENTER);
		classDuration.setFont(new Font("Tahoma", Font.BOLD, 20));
		classDuration.setBounds(729, 203, 161, 40);
		mainPanel.add(classDuration);
		
		JLabel nameLable_1 = new JLabel("Class Time Duration : ");
		nameLable_1.setHorizontalAlignment(SwingConstants.LEFT);
		nameLable_1.setFont(new Font("Calibri", Font.BOLD, 18));
		nameLable_1.setBounds(542, 203, 184, 39);
		mainPanel.add(nameLable_1);
		
		lunchPeriod = new JSpinner(new SpinnerNumberModel(5,2,6,1));
		((DefaultEditor) lunchPeriod.getEditor()).getTextField().setHorizontalAlignment(JTextField.CENTER);
		lunchPeriod.setFont(new Font("Tahoma", Font.BOLD, 20));
		lunchPeriod.setBounds(362, 285, 161, 40);
		mainPanel.add(lunchPeriod);
		
		JLabel nameLable_2 = new JLabel("Lunch Period Number : ");
		nameLable_2.setHorizontalAlignment(SwingConstants.LEFT);
		nameLable_2.setFont(new Font("Calibri", Font.BOLD, 18));
		nameLable_2.setBounds(168, 285, 184, 39);
		mainPanel.add(nameLable_2);
		
		lunchDuration = new JSpinner(new SpinnerDateModel(duration,null,null,Calendar.HOUR_OF_DAY));
		lunchDuration.setEditor(new JSpinner.DateEditor(lunchDuration, "HH:mm"));
		((DefaultEditor) lunchDuration.getEditor()).getTextField().setHorizontalAlignment(JTextField.CENTER);
		lunchDuration.setFont(new Font("Tahoma", Font.BOLD, 20));
		lunchDuration.setBounds(729, 285, 161, 40);
		mainPanel.add(lunchDuration);
		
		JLabel nameLable_3 = new JLabel("Lunch Time Duration : ");
		nameLable_3.setHorizontalAlignment(SwingConstants.LEFT);
		nameLable_3.setFont(new Font("Calibri", Font.BOLD, 18));
		nameLable_3.setBounds(542, 285, 184, 39);
		mainPanel.add(nameLable_3);
		
		checkbox = new JCheckBox("Want to add this Table in Time Table");
		checkbox.setSelected(UserDatabase.getInstance().isAddMetaData());
		checkbox.setBackground(Color.WHITE);
		checkbox.setFont(new Font("Tahoma", Font.BOLD, 14));
		checkbox.setBounds(168, 359, 277, 23);
		mainPanel.add(checkbox);
		
		JTable table = new JTable(
				new String[][] {
					{"Loading","Loading","Loading","Loading",},
					{"Loading","Loading","Loading","Loading",},
					{"Loading","Loading","Loading","Loading",}
				},
				new String[] {
					"Course Code","Course Title","Credit","Name of Teacher/Class"	
				});
		table.setRowHeight(43);
		table.getTableHeader().setBackground(Color.GRAY);
		table.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 18));
		table.getTableHeader().setPreferredSize(new Dimension(100, 43));
		table.setFont(new Font("Calibri", Font.PLAIN, 18));
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setEnabled(false);
		JScrollPane scrollPane = new JScrollPane(table);
		table.getColumnModel().getColumn(0).setPreferredWidth(135);
		table.getColumnModel().getColumn(1).setPreferredWidth(224);
		table.getColumnModel().getColumn(2).setPreferredWidth(135);
		table.getColumnModel().getColumn(3).setPreferredWidth(224);
		scrollPane.setBounds(168, 400, 722, 175);
		mainPanel.add(scrollPane);
		
		
		submitButton = new JButton("SUBMIT");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				uf.addTimeData();
				uf.serializeData();
				
				SetGUI.getInstance().activateMainFrame();
			}
		});
		submitButton.setForeground(Color.WHITE);
		submitButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		submitButton.setBackground(new Color(34, 139, 34));
		submitButton.setBounds(830, 610, 150, 40);
		mainPanel.add(submitButton);
		
		backButton = new JButton("BACK");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				UserFrame.getInstance().activateTimeTableStylePanel();
			}
		});
		backButton.setForeground(Color.WHITE);
		backButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		backButton.setBackground(new Color(34, 139, 34));
		backButton.setBounds(80, 610, 150, 40);
		mainPanel.add(backButton);
	}

	public void setUserFunctionReference(UserFunction uf) 
	{
		this.uf = uf;
	}
}
