package functionality;
//------------------------------------//

import java.util.Arrays;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;

import function.grid.random.Randomizer;
import gui.OptionPaneExample;
import gui.panel.advance.AdvancePanel;
import gui.panel.grid.GridPanel;
import utility.TableInfo;

public class GridFunction extends BasicFunctions 
{
	GridPanel gp = GridPanel.getInstance();
	public void notify(String keyword)
	{
//		if(keyword.equalsIgnoreCase("refresh")) { refreshTable(); }
		if(keyword.equalsIgnoreCase("add"))     { addNewRecord(); }
//		if(keyword.equalsIgnoreCase("edit"))    { editRecord(ap); }
//		if(keyword.equalsIgnoreCase("remove"))  { removeRecord(ap); }
		if(keyword.equalsIgnoreCase("commit"))  { commitRecord(); }
		fillComboBox();
	}
	
	@Override
	void addNewRecord() 
	{
		dc.getTimeTableClassObject().insertIntoCachedObj(gp.classComboBox.getSelectedItem().toString()+"*"+gp.sectionComboBox.getSelectedItem().toString(), Randomizer.getInstance().slots);
		dc.getTimeTableTeacherObject().insertOrUpdate(Randomizer.getInstance().teacher, Randomizer.getInstance().teacherTimeTable);
		dc.getTimeTableLabObject().insertOrUpdate(Randomizer.getInstance().labs, Randomizer.getInstance().labTimeTable);
		/*
		 * for(String a : Randomizer.getInstance().labs) {
		 * System.out.println("Lab Name :"+a); }
		 */
		
	}
	private void commitRecord() 
	{
		dc.getTimeTableClassObject().committingCacheObjToDB();
		dc.getTimeTableTeacherObject().committingCacheObjToDB();
		dc.getTimeTableLabObject().committingCacheObjToDB();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void fillComboBox() 
	{
//		gp.teacherComboBox.setModel(new DefaultComboBoxModel(super.dc.gettingName(5)));
//		gp.teacherComboBox.removeItemAt(0);
//		gp.subjectComboBox.setModel(new DefaultComboBoxModel(super.dc.gettingName(4)));
//		gp.subjectComboBox.removeItemAt(0);
		gp.classComboBox.setModel(new DefaultComboBoxModel(super.dc.gettingName(6)));
		gp.classComboBox.removeItemAt(0);
		try {
			gp.sectionComboBox.setModel(new DefaultComboBoxModel(fillComboBox(gp.classComboBox.getSelectedItem().toString())));
		} 
		catch (Exception e) {	
			new OptionPaneExample("No Teacher & Subject Assigned to Class","Alert");
		}
	}
	public Object[] fillComboBox(String classId) 
	{
		System.out.println("Section fillComboBox method called");
		return super.dc.getAdvanceCachedObject().findByColumn(3, classId);
	}
	
	public String[] findByColumn(int column, String data, String section ) 
	{
		if(column == 1) { return dc.objectToString(dc.getAdvanceCachedObject().findByColumn(5, data, section)); }
		if(column == 2) { return dc.objectToString(dc.getAdvanceCachedObject().findByColumn(4, data, section)); }
		return null;
	}
	public String [] findByColumn(String [] subject) 
	{
		return dc.getSubjectCachedObject().findByColumn(subject, 4);
	}
	public Object[][] getFilledSlots(String teacher) 
	{
		return dc.getTimeTableTeacherObject().gettingTimeTable(teacher);
	}
	public boolean isClassExits(String classId) 
	{
		return dc.getTimeTableClassObject().isClassExits(classId);
	}
	public String getShortName(String name) 
	{
		return dc.getTeacherCachedObject().getShortName(name);
	}
	public Object[] getLabType(String labType) 
	{
		return dc.getLabCachedObject().gettingLabType(labType);
	}
	public Object[][] getFilledlabs(String labid) 
	{
		return dc.getTimeTableLabObject().gettingTimeTable(labid);
	}
}

	