package gui.frame;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import database.cached.UserDatabase;

import java.awt.Font;

public class Body
{
			private static Body singleInstance;
			
			public static Body getInstance()
			{
				if(singleInstance == null)
				{
					singleInstance = new Body();
				}
				return singleInstance;
				
			}
			
			JPanel mainPanel;
			
			public JPanel getBody()
			{
				return mainPanel;		
			}
			
			private Body()
			{
				mainPanel = new JPanel();
				mainPanel.setBackground(new Color(30,30,30));
				mainPanel.setBounds(150,50,1210,630);
				mainPanel.setLayout(null);
				
				JLabel lblNewLabel = new JLabel("");
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel.setIcon(new ImageIcon(Body.class.getResource("/img/folded.png")));
				lblNewLabel.setBounds(494, 199, 175, 215);
				mainPanel.add(lblNewLabel);
				
				JLabel lblNewLabel_1 = new JLabel("TimeTable Automation System");
				lblNewLabel_1.setForeground(Color.WHITE);
				lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
				lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_1.setBounds(229, 136, 699, 79);
				mainPanel.add(lblNewLabel_1);
				
				JLabel lblNewLabel_1_1 = new JLabel("WELCOME "+UserDatabase.getInstance().getName());
				lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_1_1.setForeground(Color.WHITE);
				lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
				lblNewLabel_1_1.setBounds(229, 41, 699, 79);
				mainPanel.add(lblNewLabel_1_1);
		
		

			}
}
