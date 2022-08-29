package function.grid.random;

import functionality.GridFunction;
import gui.grid.days.DaysList;

public class LabAllocation 
{
	private static LabAllocation singleInstance;			
	public static LabAllocation getInstance()
	{
		if(singleInstance == null)
		{
			singleInstance = new LabAllocation();
		}
		return singleInstance;				
	}
	
	protected String lab;
	
	GridFunction gf;
	public void setGridReference(GridFunction gf)
	{
		this.gf = gf;
		System.out.println("TeacherPanel: TeacherFunction Reference set: "+gf);
	}
	
	public boolean getLabAllocated(String labType,int randDay,int randPeriod) 
	{
		Object [] labs = gf.getLabType(labType);
		Object [][] isClash;
		int randLab;
		byte i = 0;
		lab = " ";
		while(true)
		{
			randLab = (int) (Math.random() * labs.length);

			try{

				isClash = gf.getFilledlabs(labs[randLab].toString());
				isClash[randDay-1][randPeriod-1].equals(null);
				isClash[randDay-1][randPeriod-2].equals(null);
				if(i==150)
				{
					return false;
				}
				i++;
			}
			catch(Exception e2)
			{
				lab = labs[randLab].toString();
				return true;
			}
			
			
		}
//		return false;
	}
	
}