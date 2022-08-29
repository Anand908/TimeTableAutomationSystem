package gui.grid.preference;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PreferenceTime
{
	
	private static PreferenceTime singleInstance;			
	public static PreferenceTime getInstance()
	{
		if(singleInstance == null)
		{
			singleInstance = new PreferenceTime();
		}
		return singleInstance;				
	}
			
	JPanel mainPanel;
	private JCheckBox timeCheckBox;
	private JCheckBox timeCheckBox2;
	
	
	
	
	
	public JPanel getTime()
	{
		return mainPanel;		
	}
	
	private PreferenceTime()
	{
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.GRAY);
		mainPanel.setBounds(10, 10, 254, 121);
		mainPanel.setLayout(null);
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTime.setBounds(10, 10, 132, 25);
		mainPanel.add(lblTime);
		
		timeCheckBox = new JCheckBox("First Half");
		timeCheckBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				timeCheckBox2.setSelected(false);
			}
		});
		timeCheckBox.setHorizontalAlignment(SwingConstants.LEFT);
		timeCheckBox.setEnabled(false);
		timeCheckBox.setBounds(10, 50, 199, 21);
		mainPanel.add(timeCheckBox);
		
		timeCheckBox2 = new JCheckBox("Second Half");
		timeCheckBox2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				timeCheckBox.setSelected(false);
			}
		});
		timeCheckBox2.setHorizontalAlignment(SwingConstants.LEFT);
		timeCheckBox2.setBounds(10, 83, 199, 21);
		timeCheckBox2.setEnabled(false);
		mainPanel.add(timeCheckBox2);
	}
}
