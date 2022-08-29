package gui.panel.userpanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.MatteBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import database.cached.UserDatabase;
import function.grid.automation.GridAutomationFunction;
import functionality.UserFunction;
import gui.frame.userframe.UserFrame;
import gui.grid.timetable.TimeTablePanel;

public class TimeTableStylePanel 
{

	// --- Single Instance ---//
	
	private static TimeTableStylePanel singleInstance;
		
	public static TimeTableStylePanel getInstance()
	{
		if(singleInstance == null)
		{
			singleInstance = new TimeTableStylePanel();
		}
		return singleInstance;
	}

	private JPanel mainPanel;
	private GridAutomationFunction gaf = new GridAutomationFunction();
	public JLabel titleCheck;
	public JTextPane text;
	public JComboBox comboBox;
	public JButton backButton;
	public JButton nextButton;
	private UserFunction uf;
	public JPanel getPanel() 
	{
		return mainPanel;
	}

	private TimeTableStylePanel() 
	{
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBounds(300,50,1060, 677);
		mainPanel.setBorder(new MatteBorder(0,2,2,2,new Color(60, 60, 60)));
		mainPanel.setLayout(null);	
		
		titleCheck = new JLabel();
		titleCheck.setForeground(Color.RED);
		titleCheck.setFont(new Font("Tahoma", Font.PLAIN, 12));
		titleCheck.setBounds(809, 87, 184, 20);
		mainPanel.add(titleCheck);
		
		text = new JTextPane();
		text.addFocusListener(new FocusListener() {
			
			@Override
			public void focusGained(FocusEvent e) 
			{
				if(text.getText().equals("Enter The University/College/School Name OR Title")) 
				{
					text.setText("");
					text.setFont(new Font("Times New Roman", Font.BOLD, 20));
					text.setForeground(new Color(60, 60, 60));					
				}
			}
			@Override
			public void focusLost(FocusEvent e) 
			{
				if(text.getText().equals("")) 
				{
					text.setText("Enter The University/College/School Name OR Title");
					text.setFont(new Font("Times New Roman", Font.BOLD, 32));	
					text.setForeground(new Color(60, 60, 60,110));				
				}
			}
			
		});
		StyledDocument documentStyle =  text.getStyledDocument();
		SimpleAttributeSet centerAttribute = new SimpleAttributeSet();
		StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
		documentStyle.setParagraphAttributes(0, documentStyle.getLength(), centerAttribute, false);
		try	{
			UserDatabase.getInstance().getTimeTableTitle().equals("");
			text.setText(UserDatabase.getInstance().getTimeTableTitle());
			text.setForeground(new Color(60, 60, 60));
			text.setFont(new Font("Times New Roman", Font.BOLD, 20));
		}
		catch(Exception e)
		{
			text.setText("Enter The University/College/School Name OR Title");
			text.setForeground(new Color(60, 60, 60,150));
			text.setFont(new Font("Times New Roman", Font.BOLD, 32));
		}
		text.setBounds(125, 10, 800, 100);
		text.setBorder(new MatteBorder(2,2,2,2,new Color(60, 60, 60)));
		mainPanel.add(text);
		
		JLabel labelTabelFormat = new JLabel("Time Table Grid Size :");
		labelTabelFormat.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelTabelFormat.setBounds(125, 120, 150, 22);
		mainPanel.add(labelTabelFormat);
		
		gaf.addDaysCards(6,8,1);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String[] temp = comboBox.getSelectedItem().toString().split("X");
				int numberOfColumn = Integer.parseInt(temp[0]);
				int numberOfRow = Integer.parseInt(temp[1]);
				
				if(numberOfColumn==6)
					TimeTablePanel.getInstance(2).getTimeTablePanel().setLocation(-10, 120);
				else
					TimeTablePanel.getInstance(2).getTimeTablePanel().setLocation(60, 120);
					
				gaf.removeCards(2);
				gaf.removeList(2);
				gaf.addDaysCards(numberOfColumn,numberOfRow,2);
				TimeTablePanel.getInstance(2).refresh();
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"5X6", "5X7", "5X8", "5X9", "6X6", "6X7", "6X8", "6X9"}));
		comboBox.setBounds(300, 120, 139, 22);
		comboBox.setSelectedIndex(6);
		mainPanel.add(comboBox);
		
		mainPanel.add(TimeTablePanel.getInstance(2).getTimeTablePanel());
		
		nextButton = new JButton("NEXT");
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if(uf.addTimeTableStyleData())
				{
					UserFrame.getInstance().activateTimeDataPanel();
				}
			}
		});
		nextButton.setForeground(Color.WHITE);
		nextButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		nextButton.setBackground(new Color(34, 139, 34));
		nextButton.setBounds(830, 610, 150, 40);
		mainPanel.add(nextButton);
		
		backButton = new JButton("BACK");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				UserFrame.getInstance().activateUserDataPanel();
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
