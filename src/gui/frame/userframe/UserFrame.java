package gui.frame.userframe;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.frame.Ribbon;
import gui.panel.userpanel.TimeDataPanel;
import gui.panel.userpanel.TimeTableStylePanel;
import gui.panel.userpanel.UserDataPanel;

public class UserFrame 
{
	// --- Single Instance ---//
	
	private static UserFrame singleInstance;
	
	public static UserFrame getInstance()
	{
		if(singleInstance == null)
		{
			singleInstance = new UserFrame();
		}
		return singleInstance;
	}
	
	public JFrame frame;
	private JPanel panel;
	
	private UserFrame() 
	{
		frame = new JFrame();	
		frame.setUndecorated(true);
		frame.setTitle("TAS");	
		frame.getContentPane();
		frame.setSize(1060, 727);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		ImageIcon icon = new ImageIcon(UserFrame.class.getResource("/img/timetable1.png"));
		frame.setIconImage(icon.getImage());
		
		panel = new JPanel();
		panel.setBounds(-300,0, 1360, 727);	
		panel.setBackground(new Color(30, 30, 30));
		panel.setLayout(null);
		frame.getContentPane().add(panel);
		
		JLabel title = new JLabel("    Time Table Automation");
		title.setIcon(new ImageIcon(UserFrame.class.getResource("/img/list.png")));
		title.setFont(new Font("Times New Roman", Font.BOLD, 25));
		title.setForeground(Color.WHITE);
		title.setBounds(320, 5, 350, 40);
		Ribbon.getInstance(1).getRibbon().add(title);	
				
		frame.setVisible(false);
	}
	
	//---- Add Panels -----//

	public void addRibbon()
	{
		panel.add(Ribbon.getInstance(1).getRibbon());
	}
	public void addUserDataPanel()
	{
		panel.add(UserDataPanel.getInstance().getPanel());
	}
	public void addTimeTableStylePanel()
	{
		panel.add(TimeTableStylePanel.getInstance().getPanel());
	}
	public void addTimeDataPanel()
	{
		panel.add(TimeDataPanel.getInstance().getPanel());
	}
	
	// --- Activators ---//
	
	public void activateUserDataPanel() 
	{
		UserDataPanel.getInstance().getPanel().setVisible(true);
		TimeTableStylePanel.getInstance().getPanel().setVisible(false);
		TimeDataPanel.getInstance().getPanel().setVisible(false);
	}
	
	public void activateTimeTableStylePanel() 
	{
		UserDataPanel.getInstance().getPanel().setVisible(false);
		TimeTableStylePanel.getInstance().getPanel().setVisible(true);
		TimeDataPanel.getInstance().getPanel().setVisible(false);
	}
	
	public void activateTimeDataPanel() 
	{
		UserDataPanel.getInstance().getPanel().setVisible(false);
		TimeTableStylePanel.getInstance().getPanel().setVisible(false);
		TimeDataPanel.getInstance().getPanel().setVisible(true);
	}

	public void setMinimize() 
	{
		frame.setState(JFrame.ICONIFIED);
	}
}
