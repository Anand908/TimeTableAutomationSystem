package gui.frame;

import java.awt.Color;

import javax.swing.JPanel;

import gui.frame.userframe.UserFrame;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class Ribbon
{
	private static Ribbon[] singleInstance;
	
	public static Ribbon getInstance(int i)
	{
		if(singleInstance == null)
		{
			singleInstance = new Ribbon[] {new Ribbon(0), new Ribbon(1)};
		}
		return singleInstance[i];
		
	}
	
	JPanel mainPanel;
	int screenX;
	int screenY;
	int cursorX;
	int cursorY;
	
	public JPanel getRibbon()
	{
		return mainPanel;		
	}
	
	private Ribbon(int i)
	{
		mainPanel = new JPanel();
		mainPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) 
			{
				 	cursorX = e.getX();
	                cursorY = e.getY();
			}
		});
		mainPanel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) 
			{
				int screenX = e.getXOnScreen();
				int screenY = e.getYOnScreen();
				if(i==0)
					MainFrame.getInstance().frame.setLocation(screenX-cursorX,screenY-cursorY);
				if(i==1)
					UserFrame.getInstance().frame.setLocation(screenX-cursorX,screenY-cursorY);
			}
		});
		mainPanel.setBackground(new Color(60, 60, 60));
		mainPanel.setBounds(0,0,1360,50);
		mainPanel.setLayout(null);
		
		
		
		
		if(i==0)
		{
			mainPanel.add(Icon.getInstance().getIcon());
			mainPanel.add(MenuItem.getInstance().getMenuItem());
		}
		mainPanel.add(ThreeIcons.getInstance(i).getThreeIcons());
		
		JLabel label = new JLabel("New label");
		label.setBounds(1201, 0, 45, 13);
		mainPanel.add(label);
	}
}
