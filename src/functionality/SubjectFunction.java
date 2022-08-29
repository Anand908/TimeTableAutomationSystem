package functionality;
//------------------------------------//
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import gui.panel.subject.SubjectPanel;
import gui.panel.teacher.TeacherPanel;
import utility.TableInfo;
import utility.Validation;

public class SubjectFunction extends BasicFunctions
{
//	private ArrayList<String> tempTeacher;

	// --- Instance Variable ---//
	SubjectPanel sp = SubjectPanel.getInstance();
	//----------------------------//
	//--- Constructor ---//
	
	public void notify(String keyword)
	{
		if(keyword.equalsIgnoreCase("refresh")) { refreshFields(); }
		if(keyword.equalsIgnoreCase("add"))     { addNewRecord(); }
		if(keyword.equalsIgnoreCase("edit"))    { editRecord(sp); }	
		if(keyword.equalsIgnoreCase("remove"))  { removeRecord(sp); }	
		if(keyword.equalsIgnoreCase("commit"))  { commitRecord(); }
		refreshTable();
	}
	
	private void refreshFields() 
	{
		sp.label_Code.setText("");
		sp.label_Title.setText("");
		sp.label_Credit.setText("");
		sp.subjectCodetxt.setText("");
		sp.subTitle.setText("");
		sp.numberLecture.setSelectedIndex(0);
		sp.numberTutorial.setSelectedIndex(0);
		sp.numberLabs.setSelectedIndex(0);
	}

	public void refreshTable() 
	{
		System.out.println("Refreshing table...");
		SubjectPanel.getInstance().model.setDataVector(super.dc.gettingTableCachedObj(2),TableInfo.getInstance().getSubjectTableColumn());
		sp.activateTableActionListener();
		System.out.println(sp.getClass());
	}
	
	
	@Override
	public void addNewRecord() 
	{	
		byte tCredit = (byte) (Byte.parseByte(sp.numberLecture.getSelectedItem().toString())+
				Byte.parseByte(sp.numberTutorial.getSelectedItem().toString())+
				Byte.parseByte(sp.numberLabs.getSelectedItem().toString())/2);
		
		sp.label_Code.setText(Validation.getInstance().idCheck(sp.subjectCodetxt.getText()));
		sp.label_Title.setText(Validation.getInstance().nameCheck(sp.subTitle.getText()));
		sp.label_Credit.setText(Validation.getInstance().creditCheck(tCredit));
		
		String credit = tCredit+"("+sp.numberLecture.getSelectedItem().toString()+"+"+
						sp.numberTutorial.getSelectedItem().toString()+"+"+
						sp.numberLabs.getSelectedItem().toString()+")";
		
		if(Validation.getInstance().isReady())
		{
			dc.getSubjectCachedObject().insertIntoCachedObj(sp.subjectCodetxt.getText(), sp.subTitle.getText(), credit);
			notify("refresh");
		}
		Validation.getInstance().setIsReady();
	}	
	private void commitRecord() 
	{
		dc.getSubjectCachedObject().commitingCachedObj();
	}

	public void filterAscending(int i, JTable table, DefaultTableModel model) 
	{
		TableInfo.getInstance().filterAscending(i, table, model);
	}
}
	
	