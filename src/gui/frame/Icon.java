package gui.frame;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Icon
{
	private static Icon singleInstance;
	
	public static Icon getInstance()
	{
		if(singleInstance == null)
		{
			singleInstance = new Icon();
		}
		return singleInstance;
		
	}
	
	JPanel mainPanel;
	
	public JPanel getIcon()
	{
		return mainPanel;		
	}
	
	private Icon()
	{
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(60, 60, 60));
		mainPanel.setBounds(0,0,60,50);
		mainPanel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(Ribbon.class.getResource("/img/list.png")));
		lblNewLabel_1.setBounds(0, 0, 80, 50);
		mainPanel.add(lblNewLabel_1);
	}
}
