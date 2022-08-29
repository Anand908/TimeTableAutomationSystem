package gui.grid.days;

import java.util.ArrayList;

public class DaysList 
{
	private static DaysList [] singleInstance;
	public static DaysList getInstance(int i)
	{
		if(singleInstance == null)
		{
			singleInstance = new DaysList[] {new DaysList(),new DaysList(),new DaysList()};
		}
		return singleInstance[i];				
	}
	
	private ArrayList<DayPanelCardList> dpcl = new ArrayList<DayPanelCardList>();
	public ArrayList<DayPanelCardList> getDaysArrayList()	{		return dpcl;	}
	private int numberOfCol;
	public int getNumberOfCol() { return numberOfCol;	}

	private DaysList()
	{
		generatingDaysList(6);
		generatingDaysPanel(143, 30, 129, 442);
	}
	
	public void generatingDaysList(int max)
	{
		for(int count=0;count<max;count++)
		{
			dpcl.add(new DayPanelCardList());
		}
	}
	
	public void generatingDaysPanel(int x,int y,int width, int length)
	{
		int xPadding=0;
		for(DayPanelCardList local:dpcl)
		{
			local.creatingPanel(xPadding+x, y, width, length);
			xPadding=xPadding+width+2;
		}
	}
	
	public void generatingDaysCards(int numberOfCol, int numberOfRow)
	{
		this.numberOfCol = numberOfCol;
		if(numberOfCol==5)
		{
			for(int i=0;i<dpcl.size()-1;i++)
			{
				dpcl.get(i).creatingList(numberOfRow);
			}
		}
		else
		{
			for(DayPanelCardList local:dpcl)
			{
				local.creatingList(numberOfRow);
			}
		}
	}
	public void removingAllCards()
	{
		for(DayPanelCardList local:dpcl)
		{
			local.removingCards();
		}
	}
	public void removingAllLists()
	{
		for(DayPanelCardList local:dpcl)
		{
			local.removingList();
		}
	}
}
