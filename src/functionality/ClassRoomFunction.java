package functionality;
import javax.swing.JTable;
import gui.panel.classroom.ClassroomPanel;
import utility.TableInfo;

public class ClassRoomFunction extends BasicFunctions
{
	// --- Instance Variable ---//
	private ClassroomPanel cp = ClassroomPanel.getInstance();	
	//----------------------------//
	//--- Constructor ---//

	
	
	public void notify(String keyword)
	{
		if(keyword.equalsIgnoreCase("refresh")) { refreshTable(); }
		if(keyword.equalsIgnoreCase("add"))     { addNewRecord(); }
		if(keyword.equalsIgnoreCase("edit"))    { editRecord(cp); }
		if(keyword.equalsIgnoreCase("remove"))  { removeRecord(cp); }
		if(keyword.equalsIgnoreCase("commit"))  { commitRecord(); }
		refreshTable();
	}

	public void refreshTable() 
	{
//		dc.getClassCachedObject().gettingMetaData();
		System.out.println("Where's my leg?");
		ClassroomPanel.getInstance().model.setDataVector(super.dc.gettingTableCachedObj(3),TableInfo.getInstance().getClassTableColumn());
		cp.activateTableActionListener();
	}
	
	@Override
	void addNewRecord() 
	{
		dc.getClassCachedObject().insertIntoCachedObj(cp.classId, cp.section);
		refreshTable();
	}
	
	private void commitRecord() 
	{
		dc.getClassCachedObject().commitingCachedObj();
	}
	public void showSection(String classId) 
	{
		dc.getClassCachedObject().gettingMetaData();
		System.out.println("Show The Table");
		ClassroomPanel.getInstance().table2 = 
				new JTable(super.dc.gettingTableCachedObj(classId),TableInfo.getInstance().getClassSectionTableColumn());				
		ClassroomPanel.getInstance().scrollPane2.setViewportView(ClassroomPanel.getInstance().table2);	
//		cp.activateTableActionListener();
	}
}
	
		