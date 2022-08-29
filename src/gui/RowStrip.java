package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import gui.grid.timetable.TimeTableCard;

public class RowStrip
{
	private static RowStrip[] singleInstance;			
	public static RowStrip getInstance(int i)
	{
		if(singleInstance == null)
		{
			singleInstance = new RowStrip[] {new RowStrip(),new RowStrip()};
		}
		return singleInstance[i];				
	}
			
	JPanel columnPanel;
	
	
	public JPanel getRow()
	{
		return columnPanel;		
	}
	
	private RowStrip()
	{
		columnPanel = new JPanel();
		columnPanel.setBackground(Color.LIGHT_GRAY);
		columnPanel.setBounds(0, 0, 927, 30);
		columnPanel.setLayout(null);
		
		JLabel lecture = new JLabel("Period");
		lecture.setBackground(Color.GRAY);
		lecture.setOpaque(true);
		lecture.setHorizontalAlignment(SwingConstants.CENTER);
		lecture.setFont(new Font("Tahoma", Font.BOLD, 14));
		lecture.setBounds(0, 0, 129, 30);
		columnPanel.add(lecture);
		
		JLabel lecture_1 = new JLabel("Monday");
		lecture_1.setOpaque(true);
		lecture_1.setHorizontalAlignment(SwingConstants.CENTER);
		lecture_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lecture_1.setBackground(Color.GRAY);
		lecture_1.setBounds(143, 0, 129, 30);
		columnPanel.add(lecture_1);
		
		JLabel lecture_2 = new JLabel("Tuesday");
		lecture_2.setOpaque(true);
		lecture_2.setHorizontalAlignment(SwingConstants.CENTER);
		lecture_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lecture_2.setBackground(Color.GRAY);
		lecture_2.setBounds(274, 0, 129, 30);
		columnPanel.add(lecture_2);
		
		JLabel lecture_3 = new JLabel("Wednesday");
		lecture_3.setOpaque(true);
		lecture_3.setHorizontalAlignment(SwingConstants.CENTER);
		lecture_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lecture_3.setBackground(Color.GRAY);
		lecture_3.setBounds(405, 0, 129, 30);
		columnPanel.add(lecture_3);
		
		JLabel lecture_4 = new JLabel("Thursday");
		lecture_4.setOpaque(true);
		lecture_4.setHorizontalAlignment(SwingConstants.CENTER);
		lecture_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lecture_4.setBackground(Color.GRAY);
		lecture_4.setBounds(536, 0, 129, 30);
		columnPanel.add(lecture_4);
		
		JLabel lecture_5 = new JLabel("Friday");
		lecture_5.setOpaque(true);
		lecture_5.setHorizontalAlignment(SwingConstants.CENTER);
		lecture_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lecture_5.setBackground(Color.GRAY);
		lecture_5.setBounds(667, 0, 129, 30);
		columnPanel.add(lecture_5);
		
		JLabel lecture_6 = new JLabel("Saturday");
		lecture_6.setOpaque(true);
		lecture_6.setHorizontalAlignment(SwingConstants.CENTER);
		lecture_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		lecture_6.setBackground(Color.GRAY);
		lecture_6.setBounds(798, 0, 129, 30);
		columnPanel.add(lecture_6);
		
	}
	public void removeCards()
	{
		columnPanel.removeAll();
		columnPanel.repaint();
		//mondayPanel.revalidate();		
	}
	public void addCards(int numberOfRow)
	{
		// Row vs Col
		int y_axis = 23;
		for (int i = 0; i<numberOfRow; i++)
		{
			columnPanel.add(new TimeTableCard().getTimeTableCard());
			y_axis +=47; 
		}		
	}
}
