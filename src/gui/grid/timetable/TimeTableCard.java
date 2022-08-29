package gui.grid.timetable;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;

public class TimeTableCard
{
	
	private JPanel mainPanel;
	private JLabel teacher;
	private JLabel subject;
	private JLabel venue;
	// boolean flag Variable
	private boolean[] isFull = {false,false,false};
	
	public void setTeacherText(String teacher) {		this.teacher.setText(teacher);	isFull[0]=true;	}
	public void setSubjectText(String subject) {		this.subject.setText(subject);	isFull[1]=true;	}
	public void setVenueText(String venue) {		this.venue.setText(venue);	isFull[2]=true;	}
	public void resetIsFull()	{	isFull[0]=false;	isFull[1]=false; isFull[2]=false;	}
	
	public JPanel getTimeTableCard()	{		return mainPanel;		}
	public JLabel getTeacher()	{		return teacher;	}
	public JLabel getSubject()	{		return subject;	}
	public JLabel getVenue()	{		return venue;	}
	public boolean[] getIsFull()	{		return isFull;	}

	//static int cardCount= 1;
	
	
	
	public TimeTableCard()
	{
		mainPanel = new JPanel();
		mainPanel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		mainPanel.setBackground(Color.LIGHT_GRAY);
		mainPanel.setBounds(0,0,129,51);
		mainPanel.setLayout(null);
		
		this.subject = new JLabel();
		this.subject.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.subject.setHorizontalAlignment(SwingConstants.CENTER);
		this.subject.setBounds(10, 0, 109, 17);
		mainPanel.add(this.subject);
		
		this.teacher = new JLabel();
		this.teacher.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.teacher.setHorizontalAlignment(SwingConstants.CENTER);
		this.teacher.setBounds(10, 15, 109, 17);
		mainPanel.add(this.teacher);
		
		this.venue = new JLabel();
		this.venue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.venue.setHorizontalAlignment(SwingConstants.CENTER);
		this.venue.setBounds(10, 30, 109, 17);
		mainPanel.add(this.venue);
		//System.out.println(cardCount);
		//cardCount++;		
	}
}
