package function.grid.random;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import functionality.GridFunction;
import gui.OptionPaneExample;
import gui.frame.MainFrame;
import gui.grid.days.DayPanelCardList;
import gui.grid.days.DaysList;
import gui.grid.timetable.TimeTableCard;

public class Randomizer 
{
	ArrayList<String> subject = new ArrayList<String>();
	public String slots [][];
	public String [][][] teacherTimeTable;
	public String[][][] labTimeTable;
	public String [] teacher;
	public String [] labs;
	private Object[][] isClash;
	private static Randomizer singleInstance;			
	public static Randomizer getInstance()
	{
		if(singleInstance == null)
		{
			singleInstance = new Randomizer();
		}
		return singleInstance;				
	}
	GridFunction gf;
	private int range;
	private int column;
	public int labIndex;
	public void setGridReference(GridFunction gf)
	{
		this.gf = gf;
		System.out.println("TeacherPanel: TeacherFunction Reference set: "+gf);
	}
	/*public void startAlloation2(String subject,String teacher,int time, int[] period, int[] days , int numberOfColumn)
	{
		if(!DaysList.getInstance().getDaysArrayList().get(0).getCardList().isEmpty())
		{
			//removeOldAllocation();
			// define the range
	        int max = DaysList.getInstance().getDaysArrayList().get(0).getCardList().size();
	        int column = DaysList.getInstance().getDaysArrayList().size();
	        int min = 1;
	        int range = max - min + 1;
	        
	        System.out.println(" max : "+max+" min : "+min+" range : "+range+" Column : "+column);
	        
	        int subjectLength = 27;
	        
	        //generate random numbers within 1 to max TimeTableCards
			while(true)
			{
				int randPeroid = (int) (Math.random() * range) + min;
				System.out.println("Random Period : "+randPeroid);
				//System.out.println(randPeroid);
				//daySelector();
				int randDay = (int) (Math.random() * numberOfColumn) + 1;

				if(DaysList.getInstance().getDaysArrayList().get(randDay-1).getCardList().get(randPeroid-1).getIsFull()[0]==true)
					continue;
				DaysList.getInstance().getDaysArrayList().get(randDay-1).getCardList().get(randPeroid-1).setSubjectText(subject);
				DaysList.getInstance().getDaysArrayList().get(randDay-1).getCardList().get(randPeroid-1).setTeacherText(teacher);

				subjectLength--;
				if(subjectLength==0)
					break;
			}
		}
		else
		{
			new OptionPaneExample("Select Grid Size First","Alert");
		}
	}*/
	
	public void removeAllAllocation2(int i)
	{
		for(DayPanelCardList local:gui.grid.days.DaysList.getInstance(i).getDaysArrayList())
		{
			for(TimeTableCard card:local.getCardList())
			{
				card.getSubject().setText(null);
				card.getTeacher().setText(null);
				card.getVenue().setText(null);
				card.resetIsFull();
			}
		}
	}
	
	public void generateValues(String className, String section) 
	{
		if(gf.isClassExits(className+"*"+section))
		{
			new OptionPaneExample("Time Table Already Created For "+className+section,"Alert");
			return;
		}
		if(!DaysList.getInstance(0).getDaysArrayList().get(0).getCardList().isEmpty())
		{
			teacher = gf.findByColumn(1, className, section);
			String[] subject = gf.findByColumn(2, className, section);
			String[] subjectCredit = gf.findByColumn(subject);
			
			range = DaysList.getInstance(0).getDaysArrayList().get(0).getCardList().size();
	        column = DaysList.getInstance(0).getNumberOfCol();
	        labIndex = Allocation.getInstance().getLabLenght(subjectCredit);

			slots = new String[6][9];
			teacherTimeTable = new String[teacher.length][6][9];
			labs = new String[Allocation.getInstance().getLabLenght(subjectCredit)];
			labTimeTable = new String[labIndex][6][9];
			labIndex=0;
	        
	        System.out.println("Columns : "+column+" Row : "+range);
	        System.out.println("Subject : "+subject[0]+" Credit : "+subjectCredit[0]+" TeacherName : "+teacher[0]);
	        int subjectLength = subject.length;
	    	short interationNu = 0;
	        
	        //generate random numbers within 1 to max TimeTableCards
	    	loop:
			while(true)
			{
		        int lecture = Character.getNumericValue(subjectCredit[--subjectLength].charAt(2))+
		        Character.getNumericValue(subjectCredit[subjectLength].charAt(4));
		        int practical = Character.getNumericValue(subjectCredit[subjectLength].charAt(6))/2;
	        	isClash = gf.getFilledSlots(teacher[subjectLength]);
	        	
		        while(practical>0)
				{
					int randPeroid = (int) (Math.random() * range) + 1;
					int randDay = (int) (Math.random() * column) + 1;

					if(Allocation.getInstance().isClash(randDay, randPeroid, "P", isClash, subject[subjectLength]))
					{
						Allocation.getInstance().startAllocation("P", randDay, randPeroid, subject[subjectLength], teacher[subjectLength], className, section, subjectLength);
						practical--;
					}
		        	interationNu++;

					if(interationNu == 500)
					{
						JOptionPane.showMessageDialog(MainFrame.getInstance().frame, "Does not have enough space to allacate\nTry Again");
						break loop;
					}
				}
		        while(lecture>0)
				{
					int randPeroid = (int) (Math.random() * range) + 1;
					int randDay = (int) (Math.random() * column) + 1;
					
					if(Allocation.getInstance().isClash(randDay, randPeroid, "L", isClash, "L"))
					{
						Allocation.getInstance().startAllocation("L", randDay, randPeroid, subject[subjectLength], teacher[subjectLength], className, section, subjectLength);
						lecture--;
					}
		        	interationNu++;

					if(interationNu == 500)
					{
						JOptionPane.showMessageDialog(MainFrame.getInstance().frame, "Does not have enough space to allacate\nTry Again");
						break loop;
					}
				}
				Allocation.getInstance().dayStatus();
				if(subjectLength==0) 
				{
					System.out.println("Time Table Alloted");
					break;
				}
			}
		}
		else
		{
			new OptionPaneExample("Select Grid Size First","Alert");
		}
	}
	/*private void allocation(int lecture, int subjectIndex, String type, String className, String subject, String teacher) 
	{
		while(lecture > 0)
		{
			int randPeroid = (int) (Math.random() * range) + 1;
			int randDay = (int) (Math.random() * column) + 1;
			System.out.println("Column Status : "+DaysList.getInstance(0).getDaysArrayList().get(randDay-1).getCardList().get(randPeroid-1).getIsFull()[1]);
			if(DaysList.getInstance(0).getDaysArrayList().get(randDay-1).getDayCheck()==true)
				continue;
			System.out.println("Executed");
			if(type.equals("L"))
			{
				try{isClash[randDay-1][randPeroid-1].equals(null);continue;}catch(Exception e) {}
				System.out.println("clash Checked Executed");
				if(DaysList.getInstance(0).getDaysArrayList().get(randDay-1).getCardList().get(randPeroid-1).getIsFull()[0]==true)
					continue;
				DaysList.getInstance(0).getDaysArrayList().get(randDay-1).getCardList().get(randPeroid-1).setSubjectText((randDay-1)+","+(randPeroid-1)+" "+subject);
				DaysList.getInstance(0).getDaysArrayList().get(randDay-1).getCardList().get(randPeroid-1).setTeacherText(teacher);
				
				DaysList.getInstance(0).getDaysArrayList().get(randDay-1).setDayCheck(true);
				
				System.out.println("Day shooted true : "+randDay+" For Subject : "+subject);
				
				slots[randDay-1][randPeroid-1] = subject+"*"+teacher;
				teacherTimeTable[subjectIndex][randDay-1][randPeroid-1] = subject+"*"+className;
				
				lecture--;
			}
			else if(type.equals("P"))
			{
				if(randPeroid == 6) continue;
				try {
					try {isClash[randDay-1][randPeroid-2].equals(null); isClash[randDay-1][randPeroid-1].equals(null); continue;}catch(Exception e) {}
					if(DaysList.getInstance(0).getDaysArrayList().get(randDay-1).getCardList().get(randPeroid-2).getIsFull()[0]==true ||
						DaysList.getInstance(0).getDaysArrayList().get(randDay-1).getCardList().get(randPeroid-1).getIsFull()[0]==true)
						continue;
					DaysList.getInstance(0).getDaysArrayList().get(randDay-1).getCardList().get(randPeroid-2).setSubjectText((randDay-1)+","+(randPeroid-2)+" "+subject);
					DaysList.getInstance(0).getDaysArrayList().get(randDay-1).getCardList().get(randPeroid-2).setTeacherText(teacher);
						
					DaysList.getInstance(0).getDaysArrayList().get(randDay-1).getCardList().get(randPeroid-1).setSubjectText((randDay-1)+","+(randPeroid-1)+" "+subject);
					DaysList.getInstance(0).getDaysArrayList().get(randDay-1).getCardList().get(randPeroid-1).setTeacherText(teacher);

					DaysList.getInstance(0).getDaysArrayList().get(randDay-1).setDayCheck(true);
					
					slots[randDay-1][randPeroid-2] = subject+"*"+teacher;
					slots[randDay-1][randPeroid-1] = subject+"*"+teacher;
					teacherTimeTable[subjectIndex][randDay-1][randPeroid-2] = subject+"*"+className;
					teacherTimeTable[subjectIndex][randDay-1][randPeroid-1] = subject+"*"+className;

					lecture--;
				}catch(Exception e) {}
			}
			
		}
	}*/
}
