package gui.grid.timetable;

import java.awt.Color;
import javax.swing.JPanel;

import gui.PeriodStrip;
import gui.RowStrip;
import gui.grid.days.DayPanelCardList;

public class TimeTablePanel
{
	private static TimeTablePanel[] singleInstance;			
	public static TimeTablePanel getInstance(int i)
	{
		if(singleInstance == null)
		{
			singleInstance = new TimeTablePanel [] {new TimeTablePanel(0),new TimeTablePanel(1),new TimeTablePanel(2)};
		}
		return singleInstance[i];				
	}
			
	JPanel panel;
	
	public JPanel getTimeTablePanel()
	{
		return panel;		
	}
	
	private TimeTablePanel(int i)
	{
		panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setBounds(15, 138, 927, 472);
		panel.setLayout(null);
		//mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
	
		panel.add(PeriodStrip.getInstance(i).getColumns());
		for(DayPanelCardList local:gui.grid.days.DaysList.getInstance(i).getDaysArrayList())
		{
			panel.add(local.getPanel());
		}	
	}
	public void addRowStrip(int i)
	{
		panel.add(RowStrip.getInstance(i).getRow());
	}
	public void refresh()
	{
		panel.revalidate();
		panel.repaint();
	}
}
