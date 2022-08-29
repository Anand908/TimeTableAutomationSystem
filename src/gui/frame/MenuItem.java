package gui.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import functionality.EditMenuFunction;

import database.cached.DatabaseCached;

public class MenuItem 
{
		private static MenuItem singleInstance;
			
			public static MenuItem getInstance()
			{
				if(singleInstance == null)
				{
					singleInstance = new MenuItem();
				}
				return singleInstance;
				
			}
			
			DatabaseCached dc;
			public void setMenuReference(DatabaseCached dc)
			{
				this.dc = dc;
			}
			
			JPanel mainPanel;
			
			public JPanel getMenuItem()
			{
				return mainPanel;		
			}
			
			private MenuItem()
			{
				mainPanel = new JPanel();
				mainPanel.setBackground(new Color(60,60, 60));
				mainPanel.setBounds(80,0,200,50);
				mainPanel.setLayout(null);
				
				JMenuBar menuBar = new JMenuBar();
				menuBar.setBorderPainted(false);
				menuBar.setBounds(0, 0, 300, 50);
				menuBar.setBackground(new Color(60, 60, 60));
				mainPanel.add(menuBar);
				
				JMenu menuBtn1_File = new JMenu("File");
				menuBtn1_File.setForeground(Color.WHITE);
				menuBtn1_File.setMnemonic(KeyEvent.VK_F);
				menuBtn1_File.setFont(new Font("Calibri", Font.PLAIN, 18));
				menuBar.add(menuBtn1_File);
				
				JMenuItem menuBtn1_File_New = new JMenuItem("New");
				menuBtn1_File_New.setFont(new Font("Calibri", Font.PLAIN, 14));
				menuBtn1_File.add(menuBtn1_File_New);
				
				JMenuItem menuBtn1_File_Open = new JMenuItem("Open");
				menuBtn1_File.add(menuBtn1_File_Open);
				menuBtn1_File_Open.setFont(new Font("Calibri", Font.PLAIN, 14));
				
				JMenuItem menuBtn1_File_Import = new JMenuItem("Import");
				menuBtn1_File_Import.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent ae) {
						importExportFiles("import");
					}
				});
				menuBtn1_File.add(menuBtn1_File_Import);
				menuBtn1_File_Open.setFont(new Font("Calibri", Font.PLAIN, 14));
				
				JMenuItem menuBtn1_File_Export = new JMenuItem("Export");
				menuBtn1_File_Export.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent ae) {
						importExportFiles("export");
					}
				});
				menuBtn1_File.add(menuBtn1_File_Export);
				menuBtn1_File_Open.setFont(new Font("Calibri", Font.PLAIN, 14));
				
				JMenuItem menuBtn1_File_Exit = new JMenuItem("Exit");
				menuBtn1_File_Exit.setMnemonic(KeyEvent.VK_X);
				menuBtn1_File_Exit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
				menuBtn1_File_Exit.setFont(new Font("Calibri", Font.PLAIN, 14));
				menuBtn1_File.add(menuBtn1_File_Exit);
				
				JMenu menuBtn1_Edit = new JMenu("Edit");
				menuBtn1_Edit.setForeground(Color.WHITE);
				menuBtn1_Edit.setFont(new Font("Calibri", Font.PLAIN, 18));
				menuBar.add(menuBtn1_Edit);
				
				JMenuItem menuBtn1_Edit_UserData = new JMenuItem("Edit User Info");
				menuBtn1_Edit_UserData.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						EditMenuFunction.getInstance().editUserData();
					}
					
				});
				menuBtn1_Edit_UserData.setFont(new Font("Calibri", Font.PLAIN, 14));
				menuBtn1_Edit.add(menuBtn1_Edit_UserData);
				
				JMenuItem menuBtn1_Edit_tableStyle = new JMenuItem("Edit Table Style");
				menuBtn1_Edit_tableStyle.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						EditMenuFunction.getInstance().editTableStyle();
					}
					
				});
				menuBtn1_Edit_tableStyle.setFont(new Font("Calibri", Font.PLAIN, 14));
				menuBtn1_Edit.add(menuBtn1_Edit_tableStyle);
				
				JMenuItem menuBtn1_Edit_TimeData = new JMenuItem("Edit Time Data");
				menuBtn1_Edit_TimeData.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						EditMenuFunction.getInstance().editTimeData();
					}
					
				});
				menuBtn1_Edit_TimeData.setFont(new Font("Calibri", Font.PLAIN, 14));
				menuBtn1_Edit.add(menuBtn1_Edit_TimeData);
				
				JMenu menuBtn1_Help = new JMenu("Help");
				menuBtn1_Help.setForeground(Color.WHITE);
				menuBtn1_Help.setFont(new Font("Calibri", Font.PLAIN, 18));
				menuBar.add(menuBtn1_Help);
				
				JMenuItem menuBtn3_Help_Welcome = new JMenuItem("Welcome");
				menuBtn1_Help.add(menuBtn3_Help_Welcome);
				menuBtn3_Help_Welcome.setFont(new Font("Calibri", Font.PLAIN, 14));
				
				JMenuItem menuBtn3_Help_About = new JMenuItem("About");
				menuBtn3_Help_About.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				menuBtn3_Help_About.setFont(new Font("Calibri", Font.PLAIN, 14));
				menuBtn1_Help.add(menuBtn3_Help_About);
			}

			private void importExportFiles(String keyword) 
			{
				if(keyword.equalsIgnoreCase("export")) { ExportDialogBox.getInstance().exportGui(); }
				if(keyword.equalsIgnoreCase("import")) { ExportDialogBox.getInstance().chooseDir(0); }
			}
}
