package gui.frame;

import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class Clock 
{
private static Clock singleInstance;
	
	public static Clock getInstance()
	{
		if(singleInstance == null)
		{
			singleInstance = new Clock();
		}
		return singleInstance;
		
	}
	private JPanel clockStrip;
	 JLabel clock;
	private JLabel date;
	
	
	
	
				public JPanel getClockStrip()
				{
					
					return clockStrip;		
				}
				
				private Clock()
				{
					
					clockStrip= new JPanel();			
					clockStrip.setBackground(new Color(30,30,30));
					clockStrip.setBounds(1050, 0, 246, 50);
					clockStrip.setLayout(null);					
					
					
					clock = new JLabel("CLOCK: 12:45");
					clock.setHorizontalAlignment(SwingConstants.RIGHT);
					clock.setBounds(15, 0, 216, 17);
					clockStrip.add(clock);
					clock.setForeground(Color.white);
					clock.setFont(new Font("Times New Roman", Font.BOLD, 14));
					
					date = new JLabel("01-02-2021");
					date.setHorizontalAlignment(SwingConstants.RIGHT);
					date.setForeground(Color.WHITE);
					date.setFont(new Font("Times New Roman", Font.BOLD, 14));
					date.setBounds(10, 20, 231, 17);
					clockStrip.add(date);
					
					JSeparator separator = new JSeparator();
					separator.setOrientation(SwingConstants.VERTICAL);
					separator.setBounds(251, 5, 8, 28);
					clockStrip.add(separator);
					clockThread();
					
					
					
				}
				
				public void clockThread()
				{
					new Thread(new Runnable()	
					
					{
						public void run()
						{
							System.out.println("Clock thread is running...");
							try
							{
							
								while(true)
								{	
									 
									clock.setText(new SimpleDateFormat("hh:mm a").format(new Date()).toString());
									date.setText(new SimpleDateFormat("dd-MM-yyyy ").format(new Date()).toString());
									
								}
							}
							catch(Exception x)
							{
								System.out.println("EXCEPTION");
							}
						}
				}).start();
						
				}
}
					
