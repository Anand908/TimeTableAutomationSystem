package functionality;
import javax.swing.DefaultComboBoxModel;
//------------------------------------//
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import gui.panel.lab.LabPanel;
import utility.TableInfo;
import utility.Validation;

public class LabFunction extends BasicFunctions
{
	// --- Instance Variable ---//
	private LabPanel lp = LabPanel.getInstance();		
	//----------------------------//
	//--- Constructor ---//

	
	public void notify(String keyword)
	{
//		if(keyword.equalsIgnoreCase("refresh")) { refreshTable(); }
		if(keyword.equalsIgnoreCase("add"))     { addNewRecord(); }
		if(keyword.equalsIgnoreCase("edit"))    { editRecord(lp); }
		if(keyword.equalsIgnoreCase("remove"))  { removeRecord(lp); }
		if(keyword.equalsIgnoreCase("commit"))  { commitRecord(); }
		refreshTable();
	}
	
	private void refreshFields() 
	{
		lp.label_LabName.setText("");
		lp.label_LabNumber.setText("");
		lp.labName.setText("");
		lp.labNumber.setText("");
		try {
			lp.labType.setSelectedIndex(0);
		} catch (Exception e) {	}
		lp.label_LabType.setText("");
	}

	public void refreshTable() 
	{
		System.out.println("Where's my leg?");
		LabPanel.getInstance().model.setDataVector(super.dc.gettingTableCachedObj(4),TableInfo.getInstance().getLabTableColumn());
		lp.activateTableActionListener();
		dc.getLabCachedObject().gettingMetaData();
		
		lp.labType.setModel(new DefaultComboBoxModel(super.dc.getSubjectCachedObject().gettingLabType()));
		refreshFields();
	}
	
	

	@Override
	void addNewRecord() 
	{
		lp.label_LabName.setText(Validation.getInstance().nameCheck(lp.labName.getText()));
		lp.label_LabNumber.setText(Validation.getInstance().idCheck(lp.labNumber.getText()));
		try {
			lp.label_LabType.setText(Validation.getInstance().idCheck(lp.labType.getSelectedItem().toString()));
		} 
		catch (Exception e) {
			lp.label_LabType.setText("Add Subject First for lab Type Selection."+Validation.getInstance().idCheck("").replaceAll("[a-zA-Z]", ""));
		}
		 
		if(Validation.getInstance().isReady())
		{
			dc.getLabCachedObject().insertIntoCachedObj(lp.labName.getText(), lp.labNumber.getText(), lp.labType.getSelectedItem().toString());
			notify("refresh");
			refreshFields();
		}
		Validation.getInstance().setIsReady();
	}
	private void commitRecord() 
	{
		dc.getLabCachedObject().commitingCachedObj();
	}

	public void filterAscending(int i, JTable table, DefaultTableModel model) 
	{
		TableInfo.getInstance().filterAscending(i, table, model);
	}
}