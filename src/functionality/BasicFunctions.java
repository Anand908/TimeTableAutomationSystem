package functionality;

import javax.swing.JOptionPane;

import database.DatabaseManager;
import database.cached.DatabaseCached;
import gui.panel.advance.AdvancePanel;
import gui.panel.classroom.ClassroomPanel;
import gui.panel.grid.GridPanel;
import gui.panel.lab.LabPanel;
import gui.panel.subject.SubjectPanel;
import gui.panel.teacher.TeacherPanel;

public abstract class BasicFunctions 
{
	DatabaseManager db;
	DatabaseCached dc;
	SubjectPanel sp;
	TeacherPanel tp;
	ClassroomPanel cp;
	LabPanel lp;
	AdvancePanel ap;
	GridPanel gp;
	public void setDatabaseReference(DatabaseManager db,DatabaseCached dc)
	{
		this.db = db;
		this.dc = dc;
	}
	
	abstract void addNewRecord();	
	
	
	void editRecord(Object local) 
	{		
		System.out.println("Edit Function called...");
		String objectType = local.getClass().getSimpleName();
		
		if(objectType.equals("SubjectPanel"))
		{
			sp = (SubjectPanel)local;
			dc.getSubjectCachedObject().updatingIntoCachedObj(sp.selectedRowNumber, sp.selectedColumnName, 
					JOptionPane.showInputDialog(null,"Enter new " +sp.selectedColumnName));
		}
		
		if(objectType.equals("TeacherPanel"))
		{
			tp = (TeacherPanel)local;
			dc.getTeacherCachedObject().updatingIntoCachedObj(tp.selectedRowNumber, tp.selectedColumnName, 
					JOptionPane.showInputDialog(null,"Enter new " +tp.selectedColumnName));
		}
		if(objectType.equals("ClassroomPanel"))
		{
			cp = (ClassroomPanel)local;
			dc.getClassCachedObject().updatingIntoCachedObj(cp.selectedRowNumber, cp.selectedColumnName, 
					JOptionPane.showInputDialog(null,"Enter new " +cp.selectedColumnName));
		}
		if(objectType.equals("LabPanel"))
		{
			lp = (LabPanel)local;
			dc.getLabCachedObject().updatingIntoCachedObj(lp.selectedRowNumber, lp.selectedColumnName, 
					JOptionPane.showInputDialog(null,"Enter new " +lp.selectedColumnName));
		}
		
	}
	
	void removeRecord(Object local) 
	{		
		System.out.println("Remove Function called...");
		String objectType = local.getClass().getSimpleName();
		
		if(objectType.equals("SubjectPanel"))
		{
			sp = (SubjectPanel)local;
			dc.getSubjectCachedObject().deletingInCacheObj(sp.selectedRowNumber);
		}
		
		if(objectType.equals("TeacherPanel"))
		{
			tp = (TeacherPanel)local;
			dc.getTeacherCachedObject().deletingInCacheObj(tp.selectedRowNumber);
		}
		if(objectType.equals("ClassroomPanel"))
		{
			cp = (ClassroomPanel)local;
			dc.getClassCachedObject().deletingInCacheObj(cp.selectedRowNumber);
		}
		if(objectType.equals("LabPanel"))
		{
			lp = (LabPanel)local;
			dc.getLabCachedObject().deletingInCacheObj(lp.selectedRowNumber);
		}	
		if(objectType.equals("AdvancePanel"))
		{
			ap = (AdvancePanel)local;
			System.out.println("This is Row Number : "+ap.selectedRowNumber);
			dc.getAdvanceCachedObject().deletingInCacheObj(ap.selectedRowNumber);
		}	
	}
	
}
