package gui.grid.preference;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PreferenceDays
{
	
	private static PreferenceDays singleInstance;			
	public static PreferenceDays getInstance()
	{
		if(singleInstance == null)
		{
			singleInstance = new PreferenceDays();
		}
		return singleInstance;				
	}
			
	JPanel mainPanel;
	private JCheckBox checkBox1;
	private JCheckBox checkBox2;
	private JCheckBox checkBox3;
	private JCheckBox checkBox4;
	private JCheckBox checkBox5;
	private JCheckBox checkBox6;
	private JCheckBox checkBox7;
	private JCheckBox checkBox8;
	private JCheckBox checkBox9;
	
	public JPanel getDays()
	{
		return mainPanel;		
	}
	
	private PreferenceDays()
	{
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.GRAY);
		mainPanel.setBounds(10, 288, 254, 135);
		mainPanel.setLayout(null);
		
		JLabel lblDays = new JLabel("Days");
		lblDays.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDays.setBounds(10, 10, 85, 17);
		mainPanel.add(lblDays);
		
		checkBox1 = new JCheckBox("Monday");
		checkBox1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				check(1);
			}
		});
		checkBox1.setBounds(10, 33, 106, 21);
		checkBox1.setEnabled(false);
		mainPanel.add(checkBox1);
		
		checkBox2 = new JCheckBox("Tuesday");
		checkBox2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				check(2);
			}
		});
		checkBox2.setBounds(10, 67, 106, 21);
		checkBox2.setEnabled(false);
		mainPanel.add(checkBox2);
		
		checkBox3 = new JCheckBox("Wednesday");
		checkBox3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				check(3);
			}
		});
		checkBox3.setBounds(10, 102, 106, 21);
		checkBox3.setEnabled(false);
		mainPanel.add(checkBox3);
		
		checkBox4 = new JCheckBox("Thursday");
		checkBox4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				check(4);
			}
		});
		checkBox4.setBounds(137, 33, 100, 21);
		checkBox4.setEnabled(false);
		mainPanel.add(checkBox4);
		
		checkBox5 = new JCheckBox("Friday");
		checkBox5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				check(5);
			}
		});
		checkBox5.setBounds(137, 67, 100, 21);
		checkBox5.setEnabled(false);
		mainPanel.add(checkBox5);
		
		checkBox6 = new JCheckBox("Saturday");
		checkBox6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				check(6);
			}
		});
		checkBox6.setBounds(137, 102, 100, 21);
		checkBox6.setEnabled(false);
		mainPanel.add(checkBox6);
		
	}
	
	public void check(int num)
	{
		if(num ==1)
		{
			
			checkBox2.setSelected(false);
			checkBox3.setSelected(false);
			checkBox4.setSelected(false);
			checkBox5.setSelected(false);
			checkBox6.setSelected(false);
			checkBox7.setSelected(false);
			checkBox8.setSelected(false);
			checkBox9.setSelected(false);
		}
		
		if(num==2)
		{
			checkBox1.setSelected(false);
			checkBox3.setSelected(false);
			checkBox4.setSelected(false);
			checkBox5.setSelected(false);
			checkBox6.setSelected(false);
			checkBox7.setSelected(false);
			checkBox8.setSelected(false);
			checkBox9.setSelected(false);
		}
		
		if(num==3) 
		{
			checkBox1.setSelected(false);
			checkBox2.setSelected(false);
			checkBox4.setSelected(false);
			checkBox5.setSelected(false);
			checkBox6.setSelected(false);
			checkBox7.setSelected(false);
			checkBox8.setSelected(false);
			checkBox9.setSelected(false);
		}
		if(num==4)
		{
			checkBox1.setSelected(false);
			checkBox2.setSelected(false);
			checkBox3.setSelected(false);
			checkBox5.setSelected(false);
			checkBox6.setSelected(false);
			checkBox7.setSelected(false);
			checkBox8.setSelected(false);
			checkBox9.setSelected(false);

		}
		if(num==5)
		{
			checkBox1.setSelected(false);
			checkBox2.setSelected(false);
			checkBox3.setSelected(false);
			checkBox4.setSelected(false);
			checkBox6.setSelected(false);
			checkBox7.setSelected(false);
			checkBox8.setSelected(false);
			checkBox9.setSelected(false);

		}
		if(num==6)
		{
			checkBox1.setSelected(false);
			checkBox2.setSelected(false);
			checkBox3.setSelected(false);
			checkBox4.setSelected(false);
			checkBox5.setSelected(false);
			checkBox7.setSelected(false);
			checkBox8.setSelected(false);
			checkBox9.setSelected(false);

		}
		
		
	}

}
