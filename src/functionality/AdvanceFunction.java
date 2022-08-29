package functionality;
//------------------------------------//

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import gui.OptionPaneExample;
import gui.panel.advance.AdvancePanel;
import utility.TableInfo;

public class AdvanceFunction extends BasicFunctions 
{
	TableModel dm;
	AdvancePanel ap = AdvancePanel.getInstance();
	public void notify(String keyword)
	{
//		if(keyword.equalsIgnoreCase("refresh")) { refreshTable(); }
		if(keyword.equalsIgnoreCase("add"))     { addNewRecord(); }
//		if(keyword.equalsIgnoreCase("edit"))    { editRecord(ap); }
		if(keyword.equalsIgnoreCase("remove"))  { removeRecord(ap); }
		if(keyword.equalsIgnoreCase("commit"))  { commitRecord(); }
		refreshTable();
	}
	
	public void refreshTable() 
	{
		System.out.println("Refreshing Advance Table...");
		AdvancePanel.getInstance().table_1m.setDataVector(super.dc.gettingTableCachedObj(5),TableInfo.getInstance().getTeacherSubTableColumn());
		
		AdvancePanel.getInstance().table_2m.setDataVector(super.dc.gettingTableCachedObj(5),TableInfo.getInstance().getTeacherSubTableColumn());
		AdvancePanel.getInstance().table_2.removeColumn(AdvancePanel.getInstance().table_2.getColumnModel().getColumn(0));
//		AdvancePanel.getInstance().table_2.removeColumn(AdvancePanel.getInstance().table_2.getColumnModel().getColumn(2));
//		AdvancePanel.getInstance().table_2.moveColumn(2, 0);
		
		fillComboBox();
		AdvancePanel.getInstance().table_1.setRowSorter(null);
		AdvancePanel.getInstance().table_2.setRowSorter(null);
		ap.activateTableActionListener();		
	}
	
	
	@Override
	void addNewRecord() 
	{
		dc.getAdvanceCachedObject().insertIntoCachedObj(ap.classId.getSelectedItem().toString(),ap.section.getSelectedItem().toString(),ap.subject.getSelectedItem().toString(),ap.teacher.getSelectedItem().toString());
		refreshTable();
	}
	private void commitRecord() 
	{
		dc.getAdvanceCachedObject().committingCacheObjToDB();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void fillComboBox() 
	{
		try {
			ap.teacher.setModel(new DefaultComboBoxModel(super.dc.gettingName(1)));
			ap.subject.setModel(new DefaultComboBoxModel(super.dc.gettingName(2)));
			ap.classId.setModel(new DefaultComboBoxModel(super.dc.gettingName(3)));
			ap.section.setModel(new DefaultComboBoxModel(fillComboBox(ap.classId.getSelectedItem().toString()))); 
			ap.subjectSearch.setModel(new DefaultComboBoxModel(super.dc.gettingName(4)));
			ap.teacherSearch.setModel(new DefaultComboBoxModel(super.dc.gettingName(5)));
		}
		catch (Exception e) {
			new OptionPaneExample("All the necesary Data is not present to allocation Teacher with Class","Alert");
		}
	}
	public Object[] fillComboBox(String classId) 
	{
		
		System.out.println("fillComboBox method called");
		
		return super.dc.getClassCachedObject().gettingSection(classId);
	}

	public void dataFilter(String item, JTable table, TableRowSorter<DefaultTableModel> sorter) 
	{
		if(item.equals("All"))
			table.setRowSorter(null);
		else
		{
			table.setRowSorter(sorter);
			sorter.setRowFilter(RowFilter.regexFilter(item));
		}
	}
	
	public String getHoverData(String id,int i)
	{
		if(i==0)
		{
			return dc.getTeacherCachedObject().findName(id);
		}
		if(i==3)
		{
			return dc.getSubjectCachedObject().findByColumn(id, i);
		}
		return "";
	}
}

	