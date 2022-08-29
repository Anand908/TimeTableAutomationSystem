package function.grid.automation;

import gui.PeriodStrip;

public class GridAutomationFunction 
{
	public void addDaysCards(int numberOfColumn,int numberOfRow, int i)
	{
		gui.grid.days.DaysList.getInstance(i).generatingDaysCards(numberOfColumn, numberOfRow);
	}
	public void addPeriodCards(int numberOfRow, int i)
	{
		PeriodStrip.getInstance(i).addCards(numberOfRow);
	}
	public void removeCards(int i)
	{
		PeriodStrip.getInstance(i).removeCards();
		gui.grid.days.DaysList.getInstance(i).removingAllCards();
	}
	public void removeList(int i)
	{
		PeriodStrip.getInstance(i).removeCards();
		gui.grid.days.DaysList.getInstance(i).removingAllLists();
	}
	
}
