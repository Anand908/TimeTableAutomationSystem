package gui.grid.days;

import java.util.ArrayList;

import gui.grid.timetable.TimeTableCard;

public class DayPanelCardList extends DayPanel
{
	private ArrayList<TimeTableCard> cards = new ArrayList<TimeTableCard>();
	private boolean dayCheck = false;

	//Card List Code------->
	public void creatingList(int numOfCards)
	{
		//System.out.println("Inside creating List");
		for(int i=0;i<numOfCards;i++)
		{
			cards.add(addingCards());
		}
	}
	public void removingList()
	{
		cards.clear();
	}
	public ArrayList<TimeTableCard> getCardList()
	{
		return cards;
	}
	public boolean getDayCheck() 
	{
		return dayCheck;
	}
	public void setDayCheck(boolean dayCheck) 
	{
		this.dayCheck = dayCheck;
	}
}
