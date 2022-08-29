package functionality;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import database.cached.UserDatabase;
import gui.SetGUI;
import gui.frame.MainFrame;
import gui.frame.userframe.UserFrame;
import gui.panel.userpanel.TimeDataPanel;
import gui.panel.userpanel.TimeTableStylePanel;
import gui.panel.userpanel.UserDataPanel;

public class EditMenuFunction 
{

	private static EditMenuFunction singleInstance;
		
	public static EditMenuFunction getInstance()
	{
		if(singleInstance == null)
		{
			singleInstance = new EditMenuFunction();
		}
		return singleInstance;
			
	}
	private UserFunction uf;
	private EditMenuFunction() 
	{
		super();
	}
	public void editUserData() 
	{
		UserDataPanel.getInstance().nextButton.setText("SUBMIT");
		UserDataPanel.getInstance().nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(uf.addUserData())
				{
					uf.serializeData();
					SetGUI.getInstance().activateMainFrame();
				}
			}
		});
		UserDataPanel.getInstance().backButton.setVisible(true);
		UserDataPanel.getInstance().backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SetGUI.getInstance().activateMainFrame();
			}
		});
		UserFrame.getInstance().activateUserDataPanel();
		SetGUI.getInstance().activateUserFrame();
	}
	public void editTableStyle() 
	{
//		TimeTableStylePanel.getInstance().text.setText(UserDatabase.getInstance().getTimeTableTitle());
		TimeTableStylePanel.getInstance().nextButton.setText("SUBMIT");
		TimeTableStylePanel.getInstance().nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(uf.addTimeTableStyleData())
				{
					uf.serializeData();
					SetGUI.getInstance().activateMainFrame();
				}
			}
		});
		TimeTableStylePanel.getInstance().backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SetGUI.getInstance().activateMainFrame();
			}
		});
		UserFrame.getInstance().activateTimeTableStylePanel();
		SetGUI.getInstance().activateUserFrame();
	}
	public void editTimeData() 
	{
		TimeDataPanel.getInstance().backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SetGUI.getInstance().activateMainFrame();
			}
		});
		TimeDataPanel.getInstance().submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				uf.addTimeData();
				uf.serializeData();
				SetGUI.getInstance().activateMainFrame();
			}
		});
		UserFrame.getInstance().activateTimeDataPanel();
		SetGUI.getInstance().activateUserFrame();
	}
	public void setUserFunctionReference(UserFunction uf) 
	{
		this.uf = uf;
	}
}
