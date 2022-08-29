package start;

import database.DatabaseManager;
import database.SubjectClassFunction;
import database.cached.DatabaseCached;
import database.cached.UserDatabase;
import function.grid.automation.GridAutomationFunction;
import functionality.ClassRoomFunction;
import functionality.EditMenuFunction;
import functionality.GridFunction;
import functionality.LabFunction;
import functionality.SubjectFunction;
import functionality.TeacherFunction;
import functionality.TimeTableViewFunction;
import functionality.UserFunction;
import functionality.AdvanceFunction;
import gui.SetGUI;
import gui.frame.userframe.UserFrame;
import gui.panel.userpanel.TimeDataPanel;
import gui.panel.userpanel.TimeTableStylePanel;
import gui.panel.userpanel.UserDataPanel;

public class StartApp
{
	
	// --- Single Instance ---//
	
	private static StartApp singleInstance;
		
	public static StartApp getInstance()
	{
		if(singleInstance == null)
		{
			singleInstance = new StartApp();
		}
		return singleInstance;
	}
	
	//--- Instance Variables ---//
	private SetGUI gui;
	private DatabaseManager db;
	private DatabaseCached dc;
	private TeacherFunction tf;
	private ClassRoomFunction cf;
	private SubjectFunction sf;
	private LabFunction lf;
	private GridFunction gf;
	private SubjectClassFunction scf;
	private AdvanceFunction tsf;
	private GridAutomationFunction gaf;
	private TimeTableViewFunction tvf;
	private UserFunction uf;
	
	//-------------------------//
	
	public static void main(String[] args) 
	{
		StartApp.getInstance();	
	}

	//--- Constructor ---//
	private StartApp()
	{
		startUserFunction();
		establishDatabaseConnection();
		startDatabaseCached();
		startTeacherFunctions(); 
		startSubjectFunctions(); 
		startClassFunctions();
		startLabFunctions(); 
		startAdvanceFunctions(); 
		startGridFunctions(); 
//		startSubjectClassFunctions();
		startGridAutomationFunction();
		startTimeTableViewFunction();
		setupGUI();	 
		
	}
	
	private void startUserFunction() 
	{
		uf = new UserFunction(); 
		UserDataPanel.getInstance().setUserFunctionReference(uf);
		TimeTableStylePanel.getInstance().setUserFunctionReference(uf);
		TimeDataPanel.getInstance().setUserFunctionReference(uf);
		EditMenuFunction.getInstance().setUserFunctionReference(uf);
	}
	
	public void setupGUI()
	{
		gui = SetGUI.getInstance();
		gui.setReferences(tf,dc,cf,sf,lf,scf,tsf,gaf,gf,tvf);
	}
	
	private void establishDatabaseConnection() 
	{
		db = new DatabaseManager("Shivay123");		
		new Thread( new Runnable()
				{
					@Override
					public void run() 
					{
						try {db.getConnection().isValid(30000);}
						catch(Exception e) {e.printStackTrace();}
					}
			
				});
	}
	
	private void startLabFunctions()
	{
		lf = new LabFunction();
		lf.setDatabaseReference(db,dc);
	}	
	
	private void startGridFunctions() 
	{
		gf = new GridFunction();
		gf.setDatabaseReference(db,dc);
	}
	
	private void startClassFunctions()
	{
		cf = new ClassRoomFunction();
		cf.setDatabaseReference(db,dc);
	}	
	
	private void startSubjectFunctions()
	{
		sf = new SubjectFunction();
		sf.setDatabaseReference(db,dc);
	}
	
	private void startTeacherFunctions()
	{
		tf = new TeacherFunction();	
		tf.setDatabaseReference(db,dc);
	}
	private void startDatabaseCached()
	{
		dc = new DatabaseCached();
		dc.setDatabaseReference(db);
	}
	
	/*private void startSubjectClassFunctions()
	{
		scf = new SubjectClassFunction();
		scf.setDatabaseReference(db);
	}*/
	private void startAdvanceFunctions()
	{
		tsf = new AdvanceFunction();
		tsf.setDatabaseReference(db,dc);
	}
	private void startGridAutomationFunction()
	{
		gaf = new GridAutomationFunction();
		//tsf.setDatabaseReference(db);
	}
	private void startTimeTableViewFunction()
	{
		tvf = new TimeTableViewFunction();
		tvf.setDatabaseReference(db,dc);
	}
	//---------------------------------------------//

}
