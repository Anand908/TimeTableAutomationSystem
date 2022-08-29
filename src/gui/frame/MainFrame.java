package gui.frame;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.frame.userframe.UserFrame;
import gui.panel.advance.AdvancePanel;
import gui.panel.classroom.ClassroomPanel;
import gui.panel.grid.GridPanel;
import gui.panel.lab.LabPanel;
import gui.panel.subject.SubjectPanel;
import gui.panel.teacher.TeacherPanel;
import gui.panel.timetableview.TimeTableViewPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MainFrame
{
			public JFrame frame;
			JPanel panel;
			
			private static MainFrame singleInstance;
			public static MainFrame  getInstance()
			{
				if(singleInstance == null)
				{
					singleInstance = new MainFrame ();
				}
				return singleInstance;
				
			}
			
			
			
			private MainFrame()
			{			
				frame = new JFrame();	
				frame.setUndecorated(true);
				frame.setTitle("TAS");	
				frame.getContentPane();
				frame.setSize(1360, 749);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().setLayout(null);
				frame.setResizable(true);	
				
				ImageIcon icon = new ImageIcon(MainFrame.class.getResource("/img/timetable1.png"));
				frame.setIconImage(icon.getImage());
				
				panel = new JPanel();
				panel.setBounds(0,0, 1360, 749);
				frame.getContentPane().add(panel);	
				panel.setBackground(new Color(30, 30, 30));
				panel.setLayout(null);
				
				frame.setVisible(false);
			} 
			
			
			public void addRibbon()
			{
				panel.add(Ribbon.getInstance(0).getRibbon());
			}
			
			public void addFooter()
			{
				panel.add(Footer.getInstance().getFooter());
			}
			public void addTeacherPanel()
			{
				
				panel.add(TeacherPanel.getInstance().getTeacherPanel());
			}
			public void addSubjectPanel()
			{
				panel.add(SubjectPanel.getInstance().getSubjectPanel());
			}
			public void addDashBoard()
			{
				panel.add(DashBoard.getInstance().getDashBoard());
			}
			public void addBody()
			{
				panel.add(Body.getInstance().getBody());
			}
			
			public void addClassRooms()
			{
				panel.add(ClassroomPanel.getInstance().getClassRooms());
			}
			public void addLabs()
			{
				panel.add(LabPanel.getInstance().getLabs());
			}
			public void addAdvance()
			{
				panel.add(AdvancePanel.getInstance().getAdvance());
			}
			public void addBottomStrip()
			{
				panel.add(BottomStrip.getInstance().getBottomStrip());
			}
			public void addGrid()
			{
				panel.add(GridPanel.getInstance().getGrid());
			}
			public void addTimeTableView()
			{
				panel.add(TimeTableViewPanel.getInstance().getGrid());
			}
			
			
			
			
			
			
			//Activators
			public void activateTeacherPanel()
			{
				TeacherPanel.getInstance().refresh();
				TeacherPanel.getInstance().getTeacherPanel().setVisible(true);
				SubjectPanel.getInstance().getSubjectPanel().setVisible(false);
				Body.getInstance().getBody().setVisible(false);
				GridPanel.getInstance().getGrid().setVisible(false);
				ClassroomPanel.getInstance().getClassRooms().setVisible(false);
				LabPanel.getInstance().getLabs().setVisible(false);
				AdvancePanel.getInstance().getAdvance().setVisible(false);
				TimeTableViewPanel.getInstance().getGrid().setVisible(false);
			}
			public void activateSubjectPanel()
			{
				SubjectPanel.getInstance().refresh();
				SubjectPanel.getInstance().getSubjectPanel().setVisible(true);
				TeacherPanel.getInstance().getTeacherPanel().setVisible(false);
				Body.getInstance().getBody().setVisible(false);
				GridPanel.getInstance().getGrid().setVisible(false);
				ClassroomPanel.getInstance().getClassRooms().setVisible(false);
				LabPanel.getInstance().getLabs().setVisible(false);
				AdvancePanel.getInstance().getAdvance().setVisible(false);
				TimeTableViewPanel.getInstance().getGrid().setVisible(false);
			}
			public void activateBody()
			{
				Body.getInstance().getBody().setVisible(true);
				SubjectPanel.getInstance().getSubjectPanel().setVisible(false);
				TeacherPanel.getInstance().getTeacherPanel().setVisible(false);
				GridPanel.getInstance().getGrid().setVisible(false);
				ClassroomPanel.getInstance().getClassRooms().setVisible(false);
				LabPanel.getInstance().getLabs().setVisible(false);
				AdvancePanel.getInstance().getAdvance().setVisible(false);
				TimeTableViewPanel.getInstance().getGrid().setVisible(false);
				
			}
			
			public void activateClassrooms()
			{
				ClassroomPanel.getInstance().refresh();
				ClassroomPanel.getInstance().getClassRooms().setVisible(true);
				Body.getInstance().getBody().setVisible(false);
				SubjectPanel.getInstance().getSubjectPanel().setVisible(false);
				TeacherPanel.getInstance().getTeacherPanel().setVisible(false);
				GridPanel.getInstance().getGrid().setVisible(false);
				LabPanel.getInstance().getLabs().setVisible(false);
				AdvancePanel.getInstance().getAdvance().setVisible(false);
				TimeTableViewPanel.getInstance().getGrid().setVisible(false);
			}
			public void activateLabs()
			{
				LabPanel.getInstance().refresh();
				LabPanel.getInstance().getLabs().setVisible(true);
				ClassroomPanel.getInstance().getClassRooms().setVisible(false);
				Body.getInstance().getBody().setVisible(false);
				SubjectPanel.getInstance().getSubjectPanel().setVisible(false);
				TeacherPanel.getInstance().getTeacherPanel().setVisible(false);
				GridPanel.getInstance().getGrid().setVisible(false);
				AdvancePanel.getInstance().getAdvance().setVisible(false);
				TimeTableViewPanel.getInstance().getGrid().setVisible(false);
			}
			public void activateAdvance()
			{	
				///AdvancePanel.getInstance().setValuesInBox();
				
				AdvancePanel.getInstance().refresh();
				AdvancePanel.getInstance().getAdvance().setVisible(true);
				LabPanel.getInstance().getLabs().setVisible(false);
				ClassroomPanel.getInstance().getClassRooms().setVisible(false);
				Body.getInstance().getBody().setVisible(false);
				SubjectPanel.getInstance().getSubjectPanel().setVisible(false);
				TeacherPanel.getInstance().getTeacherPanel().setVisible(false);	
				GridPanel.getInstance().getGrid().setVisible(false);
				TimeTableViewPanel.getInstance().getGrid().setVisible(false);
			}
			public void activateGrid()
			{
				GridPanel.getInstance().refresh();
				GridPanel.getInstance().addSlotPanels();
				GridPanel.getInstance().getGrid().setVisible(true);
				TimeTableViewPanel.getInstance().getGrid().setVisible(false);
				AdvancePanel.getInstance().getAdvance().setVisible(false);
				LabPanel.getInstance().getLabs().setVisible(false);
				ClassroomPanel.getInstance().getClassRooms().setVisible(false);
				Body.getInstance().getBody().setVisible(false);
				SubjectPanel.getInstance().getSubjectPanel().setVisible(false);
				TeacherPanel.getInstance().getTeacherPanel().setVisible(false);
			}
			public void activateTimeTable()
			{
				TimeTableViewPanel.getInstance().refresh();
				TimeTableViewPanel.getInstance().addSlotPanels();
				TimeTableViewPanel.getInstance().getGrid().setVisible(true);
				GridPanel.getInstance().getGrid().setVisible(false);
				AdvancePanel.getInstance().getAdvance().setVisible(false);
				LabPanel.getInstance().getLabs().setVisible(false);
				ClassroomPanel.getInstance().getClassRooms().setVisible(false);
				Body.getInstance().getBody().setVisible(false);
				SubjectPanel.getInstance().getSubjectPanel().setVisible(false);
				TeacherPanel.getInstance().getTeacherPanel().setVisible(false);
			}
			
			public void setMinimize()
			{
				frame.setState(JFrame.ICONIFIED);
			}
}
