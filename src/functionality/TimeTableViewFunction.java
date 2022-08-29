package functionality;
//------------------------------------//

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import database.cached.UserDatabase;
import function.grid.random.Randomizer;
import gui.OptionPaneExample;
import gui.frame.MainFrame;
import gui.grid.days.DaysList;
import gui.panel.timetableview.TimeTableViewPanel;
import share.JavaMailUtil;
import share.TimeTableTemplate;
import utility.Validation;

public class TimeTableViewFunction extends BasicFunctions 
{
	TimeTableViewPanel tvp = TimeTableViewPanel.getInstance();
	private Object[][] timetableData;
	private Object[][] timetableDatam;
	private String timetableName;
	private String timeTableId;
	private byte viewedTimeTable;
	private boolean tableUser;
	public void notify(String keyword)
	{
//		if(keyword.equalsIgnoreCase("refresh")) { refreshTable(); }
//		if(keyword.equalsIgnoreCase("add"))     { addNewRecord(); }
//		if(keyword.equalsIgnoreCase("edit"))    { editRecord(ap); }
		if(keyword.equalsIgnoreCase("remove"))  { removeRecord(); }
//		if(keyword.equalsIgnoreCase("commit"))  { commitRecord(); }
		fillComboBox();
	}
		
	private void removeRecord() 
	{
		if(!DaysList.getInstance(1).getDaysArrayList().get(0).getCardList().isEmpty())
		{
			try {
				if(viewedTimeTable == 0)
				{
					dc.getTimeTableTeacherObject().deletingInCacheObj();
				}
				if(viewedTimeTable == 1)
				{
					dc.getTimeTableLabObject().deletingInCacheObj();
				}
				if(viewedTimeTable == 2)
				{
					dc.getTimeTableClassObject().deletingInCacheObj();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(MainFrame.getInstance().frame, "No Time Table Exits");
			}
		}
		else
		{
			JOptionPane.showMessageDialog(MainFrame.getInstance().frame, "Select Grid Size First");
		}
	}

	@Override
	void addNewRecord() 
	{
		dc.getTimeTableClassObject().insertIntoCachedObj(tvp.classComboBox.getSelectedItem().toString(), Randomizer.getInstance().slots);
		dc.getTimeTableTeacherObject().insertOrUpdate(Randomizer.getInstance().teacher, Randomizer.getInstance().teacherTimeTable);
		
	}
	/*private void commitRecord() 
	{
		dc.getTimeTableClassObject().committingCacheObjToDB();
		dc.getTimeTableTeacherObject().committingCacheObjToDB();
	}*/
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void fillComboBox() 
	{
		tvp.teacherComboBox.setModel(new DefaultComboBoxModel(super.dc.getTimeTableTeacherObject().gettingTeacherName()));
		tvp.labComboBox.setModel(new DefaultComboBoxModel(super.dc.getTimeTableLabObject().gettingLabName()));
//		tvp.subjectComboBox.setModel(new DefaultComboBoxModel(super.dc.gettingName(4)));
//		tvp.subjectComboBox.removeItemAt(0);
		tvp.classComboBox.setModel(new DefaultComboBoxModel(super.dc.getTimeTableClassObject().getClassList()));
		try {
			tvp.sectionComboBox.setModel(new DefaultComboBoxModel(fillComboBox(tvp.classComboBox.getSelectedItem().toString())));
		} catch (Exception e) {
			new OptionPaneExample("No Time Table Exits","Alert");
		}
	}
	public Object[] fillComboBox(String classId) 
	{
		System.out.println("Section fillComboBox method called");
		return super.dc.getTimeTableClassObject().getSectionList(classId);
	}
	public void getTimeTable() 
	{
		if(!DaysList.getInstance(1).getDaysArrayList().get(0).getCardList().isEmpty())
		{
			try {
				if(tvp.teacherCheckButton.isSelected())
				{
					viewedTimeTable = 0;
					this.timetableName = dc.getTeacherCachedObject().findName(tvp.teacherComboBox.getSelectedItem().toString());
					this.timeTableId = tvp.teacherComboBox.getSelectedItem().toString();
					setData(dc.getTimeTableTeacherObject().gettingTimeTable(tvp.teacherComboBox.getSelectedItem().toString()),0);
				}if(tvp.labCheckButton.isSelected())
				{
					viewedTimeTable = 1;
					this.timetableName = this.timeTableId = tvp.labComboBox.getSelectedItem().toString();
					setData(dc.getTimeTableLabObject().gettingTimeTable(tvp.labComboBox.getSelectedItem().toString()),2);
				}
				if(tvp.classCheckButton.isSelected())
				{
					viewedTimeTable = 2;
					this.timetableName = this.timeTableId = "Class "+tvp.classComboBox.getSelectedItem().toString()+tvp.sectionComboBox.getSelectedItem().toString(); 
					setData(dc.getTimeTableClassObject().gettingTimeTable(tvp.classComboBox.getSelectedItem().toString()+"*"+tvp.sectionComboBox.getSelectedItem().toString()),1);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(MainFrame.getInstance().frame, "No Time Table Exits");
			}
		}
		else
		{
			JOptionPane.showMessageDialog(MainFrame.getInstance().frame, "Select Grid Size First");
		}
	}
	private void setData(Object [][] obj, int a) 
	{
		this.timetableData = new Object[obj.length][obj[0].length];
		this.timetableDatam = obj;
		for(int i=0;i<6;i++)
		{
			for(int j=0;j<9;j++)
			{
				try {
					String [] slotData = obj[i][j].toString().split("[*]");
					
					DaysList.getInstance(1).getDaysArrayList().get(i).getCardList().get(j).setSubjectText(slotData[0]);
					if(a==0)
					{
						DaysList.getInstance(1).getDaysArrayList().get(i).getCardList().get(j).setTeacherText(slotData[1]);
						DaysList.getInstance(1).getDaysArrayList().get(i).getCardList().get(j).setVenueText(slotData[2]);
						this.timetableData[i][j] = slotData[0]+"\n"+slotData[1]+"\n"+slotData[2];
						tableUser = true;
					}
					if(a==1)
					{ 
						String teacherName = dc.getTeacherCachedObject().getShortName(slotData[1]);
						DaysList.getInstance(1).getDaysArrayList().get(i).getCardList().get(j).setTeacherText(teacherName);
						DaysList.getInstance(1).getDaysArrayList().get(i).getCardList().get(j).setVenueText(slotData[2]);
						this.timetableData[i][j] = slotData[0]+"\n"+teacherName+"\n"+slotData[2];
						tableUser = false;
					}
					if(a==2)
					{
						DaysList.getInstance(1).getDaysArrayList().get(i).getCardList().get(j).setTeacherText(slotData[1]);
						DaysList.getInstance(1).getDaysArrayList().get(i).getCardList().get(j).setTeacherText(slotData[2]);
						this.timetableData[i][j] = slotData[0]+"\n"+slotData[1]+"\n"+slotData[2];
						tableUser = false;
					}
				}
				catch(Exception e) 
				{
//					DaysList.getInstance(1).getDaysArrayList().get(i).getCardList().get(j).setSubjectText("");
//					DaysList.getInstance(1).getDaysArrayList().get(i).getCardList().get(j).setTeacherText("");
				}
			}
		}
		tvp.title.setText("Time Table : "+timetableName);
		
//		this.timetableData = obj;
	}
	
	public void generateTimeTable() 
	{
		if(timetableData!=null)
		{
			JFileChooser j = new JFileChooser();
			j.setFileSelectionMode(JFileChooser.FILES_ONLY);
			j.setAcceptAllFileFilterUsed(false);
			j.addChoosableFileFilter(new FileNameExtensionFilter("PDF Document", "pdf"));
			int x=j.showSaveDialog(j);
			
			if(x==JFileChooser.APPROVE_OPTION)
			{
				TimeTableTemplate template = new TimeTableTemplate(j.getSelectedFile().getPath()+".pdf",UserDatabase.getInstance().getColumn(),tableUser);
				
				template.addTitleInfo(timetableName);
				template.setSlotData(timetableData);
				template.setMetaData(metaData());
				template.combineAll();
				
				JOptionPane.showMessageDialog(MainFrame.getInstance().frame, "Time Table Generated Successfully.\n\nAt Location : "+j.getSelectedFile().getPath()+".pdf");
			}	
		}
		else
		{
			JOptionPane.showMessageDialog(MainFrame.getInstance().frame, "Open Time table first");
		}
	}
	
	public ArrayList<String> metaData()
	{
		Set <String> teacherOrclass = new HashSet<String>();
		for(byte i=0;i<timetableDatam.length;i++)
		{
			for(byte j=0;j<timetableDatam[i].length;j++)
			{
				try {
					teacherOrclass.add(timetableDatam[i][j].toString().split("[*]")[0]+"*"+timetableDatam[i][j].toString().split("[*]")[1]);
					System.out.println("SLot Text : "+timetableDatam[i][j].toString().split("[*]")[2].replaceAll("[a-zA-Z0-9 ]", ""));
//					System.out.println("Class : "+timetableDatam[i][j].toString().split("[*]")[1]);
				} 
				catch (Exception e) {
					
				}
			}
		}
		ArrayList<String> metaData = new ArrayList<String>();
				
		for(byte i=0;i<teacherOrclass.size();i++)
		{
			metaData.add(teacherOrclass.toArray()[i].toString().split("[*]")[0]);
			metaData.add(dc.getSubjectCachedObject().findByColumn(teacherOrclass.toArray()[i].toString().split("[*]")[0], 3));
			metaData.add(dc.getSubjectCachedObject().findByColumn(teacherOrclass.toArray()[i].toString().split("[*]")[0], 4));
			
			if(tableUser)
				metaData.add("Class : "+teacherOrclass.toArray()[i].toString().split("[*]")[1]);
			else
				metaData.add(dc.getTeacherCachedObject().findName(teacherOrclass.toArray()[i].toString().split("[*]")[1]));
		}
		return metaData;
	}

	public void printTimeTable() 
	{
		if(timetableData!=null)
		{
			TimeTableTemplate template = new TimeTableTemplate("TimeTable.pdf",UserDatabase.getInstance().getColumn(),tableUser);
			
			template.addTitleInfo(timetableName);
			template.setSlotData(timetableData);
			template.setMetaData(metaData());
			template.combineAll();
			
			File file = new File("TimeTable.pdf");
			try {
				Desktop.getDesktop().print(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			JOptionPane.showMessageDialog(MainFrame.getInstance().frame, "Open Time table first");
		}
	}
	public void emailTimeTable() 
	{
		if(timetableData!=null)
		{
			TimeTableTemplate template = new TimeTableTemplate("TimeTable.pdf",UserDatabase.getInstance().getColumn(), tableUser);
			
			template.addTitleInfo(timetableName);
			template.setSlotData(timetableData);
			template.setMetaData(metaData());
			template.combineAll();
			
			String recepient = JOptionPane.showInputDialog(MainFrame.getInstance().frame,"Mail Id where Time Table to be Send : ", dc.getTeacherCachedObject().findById(timeTableId,5));
			if(Validation.getInstance().emailCheck(recepient).equals(""))
				JavaMailUtil.getInstance().sendMail(recepient);
			else if(!Validation.getInstance().emailCheck(recepient).equals("cancel"))
				JOptionPane.showMessageDialog(MainFrame.getInstance().frame, "Invalid Email!");
					
		}
		else
		{
			JOptionPane.showMessageDialog(MainFrame.getInstance().frame, "Open Time table first");
		}
	}
	
	public String getHoverData(String id)
	{
		return dc.getTeacherCachedObject().findName(id);
	}
}

	