package utility;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import gui.panel.teacher.TeacherPanel;

public class TableInfo 
{
	//--- Singleton Pattern ---//
	private static TableInfo singleInstance;	
	public static TableInfo getInstance()
	{
		if(singleInstance == null)
		{
			singleInstance = new TableInfo();
		}
		return singleInstance;		
	}
	//---------------------------------------//
	
	//--- Instance Variables ---//
	
	//Table Columns Names--->
	private String[] teacherTableColumns = {"Serial No", "FName", "LName", "College Id", "Email"};
	private String[] subjectTableColumns = {"Serial No", "Code","Title", "Credit"};
	private String[] classTableColumns = {"Serial No", "ClassID"};
	private String[] classSectionTableColumns = {"Section"};
	private String[] labTableColumns = {"Serial No", "Lab Incharge Name","Lab ID", "Lab Type"};
	private String[] subclassTableColumns = {"Serial No", "Subject Code","ClassID/ClassNo"};
	private String[] teachersubTableColumns = {"Serial No", "Class", "Section","Subject","Teacher"};
	private String[] teachingLoadTableColumns = {"Teacher","Class","Section"};

	//Fixed Table Row Data properties--->
	Object[][] teacherDefaultRow = {{"Loading...","Loading...","Loading...","Loading...","Loading..."}};
	Object[][] subjectDefaultRow = {{"Loading...","Loading...","Loading..."}};
	Object[][] classDefaultRow = {{"Loading...","Loading...","Loading..."}};
	Object[][] labDefaultRow = {{"Loading...","Loading...","Loading..."}};
	Object[][] subclassDefaultRow = {{"Loading...","Loading...","Loading..."}};
	Object[][] teachingLoadDefaultRow = {{"Loading...","Loading...","Loading..."}};
	Object[][] teachersubDefaultRow = 
		{	
			{"1","Class 10","Section A","Chemistry","Dileep Kumar"},
			{"2","Class 10","Section B","Economics","Mohit Paul"},	
			{"3","Class 10","Section A","Economics","Abdul Rub"},
			{"4","Class 10","Section c","Computer Science","Vineesh Cutting"},	
		};
	
	//Table Columns widths--->
	private int[]teaccherTableColumnWidth;
	
	//-------------------------------------------------------------------------------------------//
	//Table Get functions--->
	public String[] getTeacherTableColumn()		{		return teacherTableColumns;	}
	public Object[][] getTeacherDefaultRow()	{		return teacherDefaultRow;	}	
	public int[] getTeacherTableColumnWidth()	{		return teaccherTableColumnWidth; }
	
	public String[] getSubjectTableColumn()		{		return subjectTableColumns;	}
	public Object[][] getSubjectDefaultRow()	{		return subjectDefaultRow;	}
	
	public String[] getClassTableColumn()		{		return classTableColumns;	}
	public Object[][] getClassDefaultRow()		{		return classDefaultRow;	}
	
	public String[] getClassSectionTableColumn()		{		return classSectionTableColumns;	}
	
	public String[] getLabTableColumn()			{		return labTableColumns;	}
	public Object[][] getLabDefaultRow()		{		return labDefaultRow;	}
	
	public String[] getSubClassTableColumn()	{		return subclassTableColumns;	}
	public Object[][] getSubClassDefaultRow()	{		return subclassDefaultRow;	}
	
	public String[] getTeacherSubTableColumn()	{		return teachersubTableColumns;	}
	public Object[][] getTeacherSubDefaultRow()	{		return teachersubDefaultRow;	}
	
	public String[] getTeachingLoadTableColumn(){		return teachingLoadTableColumns;	}
	public Object[][] getteachingLoadDefaultRow()	{		return teachingLoadDefaultRow;	}

	public void filterAscending(int i, JTable table, DefaultTableModel model) 
	{
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
		table.setRowSorter(sorter);
		ArrayList<RowSorter.SortKey> sortkeys = new ArrayList<RowSorter.SortKey>();
		sortkeys.add(new RowSorter.SortKey(i, SortOrder.ASCENDING));
		sorter.setSortKeys(sortkeys);
		sorter.sort();
	}
}
