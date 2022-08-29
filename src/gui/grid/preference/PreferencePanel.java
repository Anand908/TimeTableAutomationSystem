package gui.grid.preference;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;
import java.awt.Font;

public class PreferencePanel
{

	private static PreferencePanel singleInstance;			
	public static PreferencePanel getInstance()
	{
		if(singleInstance == null)
		{
			singleInstance = new PreferencePanel();
		}
		return singleInstance;				
	}
			
	JPanel mainPanel;
	
	
	
	
	
	public JPanel getPreference()
	{
		return mainPanel;		
	}
	
	private PreferencePanel()
	{
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.LIGHT_GRAY);
		mainPanel.setBounds(984, 138, 274, 457);
		mainPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(200,200,200,150));
		panel.setBounds(0, 0, 274, 457);
		panel.setLayout(null);
		mainPanel.add(panel);
		
		JLabel lblNewLabel = new JLabel("Coming Soon");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 45));
		lblNewLabel.setBounds(8, 0, 260, 457);
		panel.add(lblNewLabel);
		
		mainPanel.add(PreferenceTime.getInstance().getTime());
		mainPanel.add(PreferencePeriods.getInstance().getPeriods());
		mainPanel.add(PreferenceDays.getInstance().getDays());
		
	}

}
