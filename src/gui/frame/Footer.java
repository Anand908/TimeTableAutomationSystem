package gui.frame;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class Footer
{
			private static Footer singleInstance;
			
			public static Footer getInstance()
			{
				if(singleInstance == null)
				{
					singleInstance = new Footer();
				}
				return singleInstance;
				
			}
			
			JPanel mainPanel;
			
			JLabel helper;
			
			
			public JPanel getFooter()
			{
				return mainPanel;		
			}
			
			private Footer()
			{
				mainPanel = new JPanel();
				mainPanel.setBackground(new Color(0, 122, 204));
				mainPanel.setBounds(0,680,1360,30);
				mainPanel.setLayout(null);
				
				helper = new JLabel("");
				helper.setHorizontalAlignment(SwingConstants.CENTER);
				
				helper.setOpaque(true);
				helper.setBounds(47, 0, 178, 30);
				mainPanel.add(helper);
				
				
			}
			
			
}
