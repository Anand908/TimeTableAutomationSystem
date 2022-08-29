package functionality;
import java.util.ArrayList;

//-------------------------------------//
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import gui.panel.teacher.TeacherPanel;
import utility.TableInfo;
import utility.Validation;

public class TeacherFunction extends BasicFunctions
{
	TeacherPanel tp = TeacherPanel.getInstance();
	public void notify(String keyword)
	{
		if(keyword.equalsIgnoreCase("refresh")) { refreshFields(); }
		if(keyword.equalsIgnoreCase("add"))     { addNewRecord(); }
		if(keyword.equalsIgnoreCase("edit"))    { editRecord(tp); }
		if(keyword.equalsIgnoreCase("remove"))  { removeRecord(tp); }
		if(keyword.equalsIgnoreCase("commit"))  { commitRecord(); }
		refreshTable();
	}
	
	private void refreshFields() 
	{
		tp.fName_label.setText("");
		tp.lName_label.setText("");
		tp.collegeId_label.setText("");
		tp.email_label.setText("");
		tp.fName.setText("");
		tp.lName.setText("");
		tp.collegeID.setText("");
		tp.email.setText("");
	}

	public void refreshTable() 
	{
		System.out.println("Refreshing Teachers Table...");
		TeacherPanel.getInstance().model.setDataVector(super.dc.gettingTableCachedObj(1),TableInfo.getInstance().getTeacherTableColumn());
		tp.activateTableActionListener();		
	}
	
	
	@Override
	void addNewRecord() 
	{
		tp.fName_label.setText(Validation.getInstance().nameCheck(tp.fName.getText()));
		tp.lName_label.setText(Validation.getInstance().nameCheck(tp.lName.getText()));
		tp.collegeId_label.setText(Validation.getInstance().idCheck(tp.collegeID.getText()));
		tp.email_label.setText(Validation.getInstance().emailCheck(tp.email.getText()));
		
		if(Validation.getInstance().isReady())
		{
			dc.getTeacherCachedObject().insertIntoCachedObj(tp.namePrefix.getSelectedItem()+tp.fName.getText(),tp.lName.getText(),tp.collegeID.getText(),tp.email.getText());
			notify("refresh");
		}
		Validation.getInstance().setIsReady();
	}
	private void commitRecord() 
	{
		dc.getTeacherCachedObject().committingCacheObjToDB();
	}
	
	public void filterAscending(int i, JTable table, DefaultTableModel model) 
	{
		TableInfo.getInstance().filterAscending(i, table, model);
	}
}
	
	