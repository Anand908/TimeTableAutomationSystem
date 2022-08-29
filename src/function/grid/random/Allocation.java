package function.grid.random;

import database.cached.UserDatabase;
import functionality.GridFunction;
import gui.grid.days.DayPanelCardList;
import gui.grid.days.DaysList;

public class Allocation 
{

	private static Allocation singleInstance;			
	public static Allocation getInstance()
	{
		if(singleInstance == null)
		{
			singleInstance = new Allocation();
		}
		return singleInstance;				
	}

	GridFunction gf;
	public void setGridReference(GridFunction gf)
	{
		this.gf = gf;
		System.out.println("TeacherPanel: TeacherFunction Reference set: "+gf);
	}

	public boolean isClash(int randDay, int randPeroid, String classType, Object [][] isClash, String subject) 
	{
		if(DaysList.getInstance(0).getDaysArrayList().get(randDay-1).getDayCheck()==false)
		{
//			System.out.println("This is Code Checking");
			try{isClash[randDay-1][randPeroid-1].equals(null);}
			catch(Exception e1) 
			{
				if(DaysList.getInstance(0).getDaysArrayList().get(randDay-1).getCardList().get(randPeroid-1).getIsFull()[0]==false)
				{
					if(classType.equals("L"))
					{
						DaysList.getInstance(0).getDaysArrayList().get(randDay-1).setDayCheck(true);
						return true;
					}
					else if(classType.equals("P"))
					{
						if(randPeroid != UserDatabase.getInstance().getLunchPeriod())
						{
							try{isClash[randDay-1][randPeroid-2].equals(null);}
							catch(Exception e2)
							{
								try {
									if(DaysList.getInstance(0).getDaysArrayList().get(randDay-1).getCardList().get(randPeroid-2).getIsFull()[0]==false)
									{
										if(LabAllocation.getInstance().getLabAllocated(subject.replaceAll("[0-9]", ""), randDay, randPeroid))
										{
											DaysList.getInstance(0).getDaysArrayList().get(randDay-1).setDayCheck(true);
											return true;
										}
									}
								} catch (Exception e) {	}
							}
						}
					}
				}
			}
		}
		
		return false;
	}
	
	public void startAllocation(String type, int randDay, int randPeroid, String subject, String teacher, String className, String section, int subjectLength) 
	{
		DaysList.getInstance(0).getDaysArrayList().get(randDay-1).getCardList().get(randPeroid-1).setSubjectText(subject);
		DaysList.getInstance(0).getDaysArrayList().get(randDay-1).getCardList().get(randPeroid-1).setTeacherText(gf.getShortName(teacher));
		
		Randomizer.getInstance().slots[randDay-1][randPeroid-1] = subject+"*"+teacher+"*      ";
		Randomizer.getInstance().teacherTimeTable[subjectLength][randDay-1][randPeroid-1] = subject+"*"+className+" "+section+"*      ";
		if(type.equals("P"))
		{
			DaysList.getInstance(0).getDaysArrayList().get(randDay-1).getCardList().get(randPeroid-2).setSubjectText(subject);
			DaysList.getInstance(0).getDaysArrayList().get(randDay-1).getCardList().get(randPeroid-2).setTeacherText(gf.getShortName(teacher));
			DaysList.getInstance(0).getDaysArrayList().get(randDay-1).getCardList().get(randPeroid-2).setVenueText(LabAllocation.getInstance().lab);
			DaysList.getInstance(0).getDaysArrayList().get(randDay-1).getCardList().get(randPeroid-1).setVenueText(LabAllocation.getInstance().lab);
			
			Randomizer.getInstance().slots[randDay-1][randPeroid-1] = subject+"*"+teacher+"*"+LabAllocation.getInstance().lab;
			Randomizer.getInstance().slots[randDay-1][randPeroid-2] = subject+"*"+teacher+"*"+LabAllocation.getInstance().lab;
			
			Randomizer.getInstance().teacherTimeTable[subjectLength][randDay-1][randPeroid-1] = subject+"*"+className+" "+section+"*"+LabAllocation.getInstance().lab;
			Randomizer.getInstance().teacherTimeTable[subjectLength][randDay-1][randPeroid-2] = subject+"*"+className+" "+section+"*"+LabAllocation.getInstance().lab;
			
			Randomizer.getInstance().labs[Randomizer.getInstance().labIndex] = LabAllocation.getInstance().lab;
//			System.out.println("Lab Name Allocated : "+Randomizer.getInstance().labs[Randomizer.getInstance().labIndex]+" & "+Randomizer.getInstance().labIndex);
			Randomizer.getInstance().labTimeTable[Randomizer.getInstance().labIndex][randDay-1][randPeroid-1] = subject+"*"+teacher+"*"+className+" "+section;
			Randomizer.getInstance().labTimeTable[Randomizer.getInstance().labIndex][randDay-1][randPeroid-2] = subject+"*"+teacher+"*"+className+" "+section;
			Randomizer.getInstance().labIndex++;
		}
	}
	
	public void dayStatus()
	{
		for(DayPanelCardList local:gui.grid.days.DaysList.getInstance(0).getDaysArrayList())
		{
			local.setDayCheck(false);
		}
	}
	
	public int getLabLenght(String[] subjectCredit) 
	{
		int practical=0;
		for(String credit : subjectCredit)
		{
	        practical = practical + Character.getNumericValue(credit.charAt(6))/2;
		}
		System.out.println("Practical : "+practical);
		return practical;
		
	}
}
