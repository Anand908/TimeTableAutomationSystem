package gui.frame;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class BottomStrip
{
	
	private static BottomStrip singleInstance;
	
	public static BottomStrip getInstance()
	{
		if(singleInstance == null)
		{
			singleInstance = new BottomStrip();
		}
		return singleInstance;
		
	}
	
	JPanel mainPanel;
	
	JLabel helper;
	
	
	public JPanel getBottomStrip()
	{
		return mainPanel;		
	}
	
	private BottomStrip()
	{
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(30,30,30));
		mainPanel.setBounds(0,710,1360,50);
		mainPanel.setLayout(null);
		
		mainPanel.add(Clock.getInstance().getClockStrip());
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(1299, 8, 8, 27);
		mainPanel.add(separator);
	}

}
