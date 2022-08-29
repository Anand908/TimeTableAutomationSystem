package gui;

import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import database.cached.UserDatabase;
import gui.grid.timetable.TimeTableCard;

public class PeriodStrip
{	
	private static PeriodStrip[] singleInstance;			
	public static PeriodStrip getInstance(int i)
	{
		if(singleInstance == null)
		{
			singleInstance = new PeriodStrip[] {new PeriodStrip(),new PeriodStrip(),new PeriodStrip()};
		}
		return singleInstance[i];				
	}
			
	JPanel columnPanel;
	private ArrayList<TimeTableCard> m = new ArrayList<TimeTableCard>();

	private SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm\n aa");
	
	public JPanel getColumns()
	{
		return columnPanel;		
	}
	
	private PeriodStrip()
	{
		columnPanel = new JPanel();
		columnPanel.setBackground(Color.white);
		columnPanel.setBounds(0, 30, 129, 442);
		columnPanel.setBorder(new MatteBorder(2, 0, 0, 0, (Color) Color.WHITE));
		columnPanel.setLayout(new BoxLayout(columnPanel, BoxLayout.Y_AXIS));		
		
	}
	public void removeCards()
	{
		columnPanel.removeAll();
		columnPanel.repaint();
		//mondayPanel.revalidate();		
		m.clear();
	}
	public void addCards(int numberOfRow)
	{
		ArrayList<String> time = getTime(false);
		for (int i = 0; i<numberOfRow; i++)
		{
			TimeTableCard card = new TimeTableCard();
			card.getSubject().setFont(new Font("Tahoma", Font.BOLD, 18));
			card.setSubjectText(i+1+"");
			card.setTeacherText(time.get(i).split("[\n]")[0]);
			card.setVenueText(time.get(i).split("[\n]")[1]);
			columnPanel.add(card.getTimeTableCard());
			m.add(card);
			
		}	
		for(TimeTableCard p: m)
		{
			System.out.println(p);
		}
	}
	
	public ArrayList<String> getTime(boolean isLunchTime) 
	{
		ArrayList<String> time = new ArrayList<String>();
		Date endTime = (Date) UserDatabase.getInstance().getStartTime().clone();
		String startTime = "";
		for(int i=0;i<=UserDatabase.getInstance().getRow();i++)
		{
			endTime.setMinutes(endTime.getMinutes()+5);
			startTime = timeFormat.format(endTime).replaceAll("[AMPM\n]", "");
			endTime.setHours(endTime.getHours()+UserDatabase.getInstance().getClassDuration().getHours());
			endTime.setMinutes(endTime.getMinutes()+UserDatabase.getInstance().getClassDuration().getMinutes());
			
			if(i+1==UserDatabase.getInstance().getLunchPeriod())
			{
				if(isLunchTime)
					time.add(startTime+"- "+timeFormat.format(endTime).replaceAll("[\n]", ""));
			}
			else
				time.add(startTime+"- "+timeFormat.format(endTime));
			
		}
		/*date1.setMinutes(date1.getMinutes()+5);
		String time = timeFormat.format(date1);
		if(timeFormat.format(date1).equals("12:25 PM"))
			date1.setMinutes(date1.getMinutes()+100);
		else
			date1.setMinutes(date1.getMinutes()+45);
		time = time.replaceAll("[AMPM\n]", "");
		time = time+"- "+timeFormat.format(date1);*/
		return time;
	}

	public void addPanelDetails(int panelNumber,String subject,String teacher)
	{
		/*
		m.get(panelNumber-1).setSubject(subject);
		m.get(panelNumber-1).setTeacher(teacher);
		*/
	}
}
