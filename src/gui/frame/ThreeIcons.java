package gui.frame;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import gui.frame.userframe.UserFrame;

import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ThreeIcons
{
	private static ThreeIcons[] singleInstance;
	
	public static ThreeIcons getInstance(int i)
	{
		if(singleInstance == null)
		{
			singleInstance = new ThreeIcons[] {new ThreeIcons(0), new ThreeIcons(1)};
		}
		return singleInstance[i];
		
	}
	
	JPanel mainPanel;
	public JLabel lblNewLabel_3_2;
	
	public JPanel getThreeIcons()
	{
		return mainPanel;		
	}
	
	private ThreeIcons(int i)
	{
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(60,60, 60));
		mainPanel.setBounds(1100,0,260,50);
		mainPanel.setLayout(null);
		
		
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent e) 
			{
				lblNewLabel_3.setOpaque(true);
				lblNewLabel_3.setBackground(Color.RED);
			}
			@Override
			public void mouseExited(MouseEvent e)
			{
				lblNewLabel_3.setOpaque(false);
				lblNewLabel_3.setBackground(Color.gray);
			}
		});
		lblNewLabel_3.setIcon(new ImageIcon(ThreeIcons.class.getResource("/img/close .png")));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(208, 0, 52, 55);
		mainPanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("");
		lblNewLabel_3_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				
			}
			public void mouseEntered(MouseEvent e) 
			{
				lblNewLabel_3_1.setOpaque(true);
				lblNewLabel_3_1.setBackground(new Color(30,30,30));
			}
			@Override
			public void mouseExited(MouseEvent e)
			{
				lblNewLabel_3_1.setOpaque(false);
				lblNewLabel_3_1.setBackground(Color.gray);
			}
			
		});
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setIcon(new ImageIcon(ThreeIcons.class.getResource("/img/squares.png")));
		lblNewLabel_3_1.setForeground(Color.WHITE);
		lblNewLabel_3_1.setBounds(151, 0, 50, 55);
		mainPanel.add(lblNewLabel_3_1);
		
		lblNewLabel_3_2 = new JLabel("");
		lblNewLabel_3_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if(i==0)
					MainFrame.getInstance().setMinimize();
				if(i==1)
					UserFrame.getInstance().setMinimize();
			}
			public void mouseEntered(MouseEvent e) 
			{
				lblNewLabel_3_2.setOpaque(true);
				lblNewLabel_3_2.setBackground(new Color(30,30,30));
			}
			@Override
			public void mouseExited(MouseEvent e)
			{
				lblNewLabel_3_2.setOpaque(false);
				lblNewLabel_3_2.setBackground(Color.gray);
			}
		});
		lblNewLabel_3_2.setIcon(new ImageIcon(ThreeIcons.class.getResource("/img/minus.png")));
		lblNewLabel_3_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_2.setBounds(103, 0, 42, 50);
		mainPanel.add(lblNewLabel_3_2);
	}
}
