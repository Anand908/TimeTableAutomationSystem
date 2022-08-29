package gui.grid.preference;

import java.awt.Color;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PreferencePeriods
{
	private static PreferencePeriods singleInstance;			
	public static PreferencePeriods getInstance()
	{
		if(singleInstance == null)
		{
			singleInstance = new PreferencePeriods();
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
	
	public JPanel getPeriods()
	{
		return mainPanel;		
	}
	
	private PreferencePeriods()
	{
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.GRAY);
		mainPanel.setBounds(10, 143, 254, 135);
		mainPanel.setLayout(null);
		
		JLabel lblPeriods = new JLabel("Periods");
		lblPeriods.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPeriods.setBounds(10, 10, 85, 17);
		mainPanel.add(lblPeriods);
		
		checkBox1 = new JCheckBox("1");
		checkBox1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				check(1);
			}
		});
		checkBox1.setBounds(10, 33, 49, 21);
		checkBox1.setEnabled(false);
		mainPanel.add(checkBox1);
		
		checkBox2 = new JCheckBox("2");
		checkBox2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				check(2);
			}
		});
		checkBox2.setBounds(85, 33, 49, 21);
		checkBox2.setEnabled(false);
		mainPanel.add(checkBox2);
		
		checkBox3 = new JCheckBox("3");
		checkBox3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				check(3);
			}
		});
		checkBox3.setBounds(157, 33, 49, 21);
		checkBox3.setEnabled(false);
		mainPanel.add(checkBox3);
		
		checkBox4 = new JCheckBox("4");
		checkBox4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				check(4);
			}
		});
		checkBox4.setBounds(10, 67, 49, 21);
		checkBox4.setEnabled(false);
		mainPanel.add(checkBox4);
		
		checkBox5 = new JCheckBox("5");
		checkBox5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				check(5);
			}
		});
		checkBox5.setBounds(85, 67, 49, 21);
		checkBox5.setEnabled(false);
		mainPanel.add(checkBox5);
		
		checkBox6 = new JCheckBox("6");
		checkBox6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				check(6);
			}
		});
		checkBox6.setBounds(157, 67, 49, 21);
		checkBox6.setEnabled(false);
		mainPanel.add(checkBox6);
		
		checkBox7 = new JCheckBox("7");
		checkBox7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				check(7);
			}
		});
		checkBox7.setBounds(10, 102, 49, 21);
		checkBox7.setEnabled(false);
		mainPanel.add(checkBox7);
		
		checkBox8 = new JCheckBox("8");
		checkBox8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				check(8);
			}
		});
		checkBox8.setBounds(85, 102, 49, 21);
		checkBox8.setEnabled(false);
		mainPanel.add(checkBox8);
		
		checkBox9 = new JCheckBox("9");
		checkBox9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				check(9);
			}
		});
		checkBox9.setBounds(157, 102, 49, 21);
		checkBox9.setEnabled(false);
		mainPanel.add(checkBox9);
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
		if(num==7)
		{
			checkBox1.setSelected(false);
			checkBox2.setSelected(false);
			checkBox3.setSelected(false);
			checkBox4.setSelected(false);
			checkBox5.setSelected(false);
			checkBox6.setSelected(false);
			checkBox8.setSelected(false);
			checkBox9.setSelected(false);

		}
		if(num==8)
		{
			checkBox1.setSelected(false);
			checkBox2.setSelected(false);
			checkBox3.setSelected(false);
			checkBox4.setSelected(false);
			checkBox5.setSelected(false);
			checkBox6.setSelected(false);
			checkBox7.setSelected(false);
			checkBox9.setSelected(false);

		}
		if(num==9)
		{
			checkBox1.setSelected(false);
			checkBox2.setSelected(false);
			checkBox3.setSelected(false);
			checkBox4.setSelected(false);
			checkBox5.setSelected(false);
			checkBox6.setSelected(false);
			checkBox7.setSelected(false);
			checkBox8.setSelected(false);

		}
	}

}
