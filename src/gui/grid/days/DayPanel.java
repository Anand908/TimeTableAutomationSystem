package gui.grid.days;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import gui.grid.timetable.TimeTableCard;

public class DayPanel
{
	JPanel panel;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void creatingPanel(int x,int y,int width,int length)
	{
		//System.out.println("Inside creating Panels");
		panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setBorder(new MatteBorder(2, 0, 0, 0, (Color) Color.WHITE));
		panel.setBounds(x, y, width, length);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	}
	public JPanel getPanel()
	{
		return panel;		
	}
	
	public TimeTableCard addingCards()
	{
		//System.out.println("Inside adding Cards");
		TimeTableCard card = new TimeTableCard();
		panel.add(card.getTimeTableCard());
		return card;
	}
	public void removingCards()
	{
		panel.removeAll();
		panel.repaint();
	}
}
