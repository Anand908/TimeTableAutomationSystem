package functionality;

import java.util.Date;

import javax.swing.JOptionPane;

import database.cached.SerializeDeSerialize;
import database.cached.UserDatabase;
import gui.frame.userframe.UserFrame;
import gui.panel.userpanel.TimeDataPanel;
import gui.panel.userpanel.TimeTableStylePanel;
import gui.panel.userpanel.UserDataPanel;
import utility.Validation;

public class UserFunction 
{
	private UserDataPanel udp = UserDataPanel.getInstance();
	private TimeTableStylePanel ttsp = TimeTableStylePanel.getInstance();
	private TimeDataPanel tdp = TimeDataPanel.getInstance();
	
	public boolean addUserData() 
	{
		udp.nameCheck.setText(Validation.getInstance().nameCheck(udp.nameField.getText()));
		udp.collegeIdCheck.setText(Validation.getInstance().idCheck(udp.collegeIdField.getText()));
		udp.emailCheck.setText(Validation.getInstance().emailCheck(udp.emailField.getText()));
		udp.passwordCheck.setText(Validation.getInstance().passwordCheck(udp.passwordField.getText(), udp.confirmField.getText()));
		
		if(Validation.getInstance().isReady())
		{
			UserDatabase.getInstance().setUserData(udp.nameField.getText(), udp.collegeIdField.getText(), udp.emailField.getText(), udp.passwordField.getText());
			Validation.getInstance().setIsReady();
			return true;
		}
		Validation.getInstance().setIsReady();
		return false;
	}
	public boolean addTimeTableStyleData() 
	{
		ttsp.titleCheck.setText(Validation.getInstance().paraCheck(ttsp.text.getText()));
		
		if(Validation.getInstance().isReady())
		{
			UserDatabase.getInstance().setTimeTableStyleData(ttsp.text.getText(), 
					Byte.parseByte(ttsp.comboBox.getSelectedItem().toString().split("X")[0]), 
					Byte.parseByte(ttsp.comboBox.getSelectedItem().toString().split("X")[1]));
			Validation.getInstance().setIsReady();
			return true;
		}
		Validation.getInstance().setIsReady();
		return false;
		
	}
	public void addTimeData() 
	{
		UserDatabase.getInstance().setTimeData((Date)tdp.classStart.getModel().getValue(),
				(Date)tdp.classDuration.getModel().getValue(),
				(Date)tdp.lunchDuration.getModel().getValue(), 
				Byte.parseByte(tdp.lunchPeriod.getModel().getValue().toString()), 
				tdp.checkbox.isSelected());
		
	}
	public void serializeData() 
	{
		SerializeDeSerialize sd = SerializeDeSerialize.getInstance();
		sd.serializeObject(UserDatabase.getInstance(), "", 8);
		
//		JOptionPane.showMessageDialog(UserFrame.getInstance().frame, "Data Submitted Successfully");
		System.out.println("Serialization JOptionPane");
	}
}
