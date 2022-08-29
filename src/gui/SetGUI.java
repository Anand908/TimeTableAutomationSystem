package gui;

import database.SubjectClassFunction;
import database.cached.DatabaseCached;
import database.cached.UserDatabase;
import function.grid.automation.GridAutomationFunction;
import function.grid.random.Allocation;
import function.grid.random.LabAllocation;
import function.grid.random.Randomizer;
import functionality.AdvanceFunction;
import functionality.ClassRoomFunction;
import functionality.GridFunction;
import functionality.LabFunction;
import functionality.SubjectFunction;
import functionality.TeacherFunction;
import functionality.TimeTableViewFunction;
import gui.frame.ExportDialogBox;
import gui.frame.MainFrame;
import gui.frame.userframe.UserFrame;
import gui.panel.advance.AdvancePanel;
import gui.panel.classroom.ClassroomPanel;
import gui.panel.grid.GridPanel;
import gui.panel.lab.LabPanel;
import gui.panel.subject.SubjectPanel;
import gui.panel.teacher.TeacherPanel;
import gui.panel.timetableview.TimeTableViewPanel;

public class SetGUI 
{
	// --- Single Instance ---//
	
	private static SetGUI singleInstance;
	private MainFrame mf;
	private UserFrame uf;
	
	public static SetGUI getInstance()
	{
		if(singleInstance == null)
		{
			singleInstance = new SetGUI();
		}
		return singleInstance;
	}
	
	private SetGUI()
	{	
		setGUIUserFrame();
		setGUIMainFrame();
		
		try {
			UserDatabase.getInstance().getCollegeId().isBlank();
			activateMainFrame();
		} catch (Exception e) 
		{
			activateUserFrame();
		}
	}

	private void setGUIUserFrame() 
	{
		uf = UserFrame.getInstance();
		uf.addRibbon();
		
		uf.addUserDataPanel();
		uf.addTimeTableStylePanel();
		uf.addTimeDataPanel();
		uf.activateUserDataPanel();
		uf.frame.revalidate();
		uf.frame.repaint();
	}

	private void setGUIMainFrame() 
	{
		mf = MainFrame.getInstance();
		mf.addRibbon();
		mf.addDashBoard();
		mf.addSubjectPanel();
		mf.addTeacherPanel();
		mf.addFooter();
		mf.addBody();
		mf.activateBody();
		
		mf.addClassRooms();
		mf.addLabs();
		mf.addAdvance();
		mf.addBottomStrip();
		mf.addGrid();
		mf.addTimeTableView();
		mf.frame.revalidate();
		mf.frame.repaint();
	}
	
	public void activateMainFrame() 
	{
		mf.frame.setVisible(true);
		uf.frame.setVisible(false);
		uf.frame.dispose();
	}

	public void activateUserFrame() 
	{
		uf.frame.setVisible(true);
		mf.frame.setVisible(false);
		mf.frame.dispose();
	}
	
	public void setReferences(TeacherFunction tf,DatabaseCached dc,ClassRoomFunction cf, SubjectFunction sf, LabFunction lf, SubjectClassFunction scf, AdvanceFunction tsf, GridAutomationFunction gaf, GridFunction gf, TimeTableViewFunction tvf)
	{		
		TeacherPanel.getInstance().setTeacherReference(tf,dc);
		ClassroomPanel.getInstance().setClassReference(cf);
		SubjectPanel.getInstance().setSubjectReference(sf);
		LabPanel.getInstance().setLabReference(lf);
		AdvancePanel.getInstance().setClassReference(cf);
		AdvancePanel.getInstance().setLabReference(lf);
		AdvancePanel.getInstance().setSubjectReference(sf);
		AdvancePanel.getInstance().setTeacherReference(tf);
		AdvancePanel.getInstance().setSubjectClassReference(scf);
		AdvancePanel.getInstance().setTeacherSubReference(tsf);
		GridPanel.getInstance().setGridAutomationFunction(gaf);
		TimeTableViewPanel.getInstance().setGridAutomationFunction(gaf);
		ExportDialogBox.getInstance().setDataCchedReference(dc);
		GridPanel.getInstance().setGridReference(gf);
		TimeTableViewPanel.getInstance().setGridReference(tvf);
		Randomizer.getInstance().setGridReference(gf);
		Allocation.getInstance().setGridReference(gf);
		LabAllocation.getInstance().setGridReference(gf);
	}

}
