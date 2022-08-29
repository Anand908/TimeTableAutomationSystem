package gui.frame;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import database.cached.DatabaseCached;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JCheckBox;

public class ExportDialogBox 
{
	private static ExportDialogBox singleInstance;
	
	public static ExportDialogBox getInstance()
	{
		if(singleInstance == null)
		{
			singleInstance = new ExportDialogBox();
		}
		return singleInstance;
		
	}
	 
	private ExportDialogBox() {}
	
	DatabaseCached dc;
	public void setDataCchedReference(DatabaseCached dc)
	{
		this.dc = dc;
	}
	
	private JTextField filePathText;
	private JCheckBox teacherCheck;
	private JCheckBox subjectCheck;
	private JCheckBox allCheck;
	private JCheckBox classCheck;
	private JCheckBox labCheck;
	private JCheckBox advanceCheck;
	private String path;
	private JDialog dialog;
	
	public void exportGui() 
	{		
		JOptionPane jop = new JOptionPane();
		jop.setVisible(false);
		dialog = jop.createDialog(MainFrame.getInstance().frame,"Export");
		dialog.setIconImage(Toolkit.getDefaultToolkit().getImage(ExportDialogBox.class.getResource("/img/export.png")));
		dialog.setSize(500, 500);
		dialog.setLocationRelativeTo(null);
		dialog.getContentPane().setBackground(Color.WHITE);
		dialog.getContentPane().setLayout(null);
		
		JLabel title = new JLabel("Export File");
		title.setFont(new Font("Tahoma", Font.BOLD, 26));
		title.setBounds(30, 11, 162, 43);
		dialog.getContentPane().add(title);
		
		JLabel subtitle = new JLabel("Export Resources to an file on the local file system.");
		subtitle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		subtitle.setBounds(30, 52, 383, 24);
		dialog.getContentPane().add(subtitle);
		
		JLabel exportIcon = new JLabel();
		exportIcon.setIcon(new ImageIcon(ExportDialogBox.class.getResource("/img/export.png")));
		exportIcon.setBounds(370, 11, 80, 80);
		dialog.getContentPane().add(exportIcon);
		
		JPanel panel = new JPanel();
		panel.setBounds(30,115,420,203);
		panel.setBackground(Color.GRAY);
		panel.setBorder(new MatteBorder(6,6,6,6,(Color) Color.LIGHT_GRAY));
		panel.setLayout(null);
		dialog.getContentPane().add(panel);
		
		allCheck = new JCheckBox("All");
		allCheck.setBounds(43, 36, 120, 23);
		panel.add(allCheck);
		
		teacherCheck = new JCheckBox("Teacher's Data");
		teacherCheck.setBounds(43, 87, 120, 23);
		panel.add(teacherCheck);
		
		subjectCheck = new JCheckBox("Subject's Data");
		subjectCheck.setBounds(43, 139, 120, 23);
		panel.add(subjectCheck);
		
		classCheck = new JCheckBox("Classes Data");
		classCheck.setBounds(240, 36, 120, 23);
		panel.add(classCheck);
		
		labCheck = new JCheckBox("Lab's Data");
		labCheck.setBounds(240, 87, 120, 23);
		panel.add(labCheck);
		
		advanceCheck = new JCheckBox("Advance Data");
		advanceCheck.setBounds(240, 139, 120, 23);
		panel.add(advanceCheck);
		
		JLabel filaPath = new JLabel("File Path : ");
		filaPath.setFont(new Font("Tahoma", Font.PLAIN, 13));
		filaPath.setBounds(30, 356, 62, 14);
		dialog.getContentPane().add(filaPath);
		
		filePathText = new JTextField();
		filePathText.setBounds(102, 353, 249, 20);
		dialog.getContentPane().add(filePathText);
		filePathText.setColumns(10);
		
		JButton choose = new JButton("Choose");
		choose.setBounds(361, 353, 89, 23);
		choose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				chooseDir(1);
			}
		});
		dialog.getContentPane().add(choose);
		
		JButton exportButton = new JButton("Export");
		exportButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae) {
				exportFiles();
			};
		});
		exportButton.setBounds(250, 427, 89, 23);
		dialog.getContentPane().add(exportButton);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(30, 387, 420, 14);
//		dialog.getContentPane().add(progressBar);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				dialog.dispose();
			}
		});
		cancelButton.setBounds(361, 427, 89, 23);
		dialog.getContentPane().add(cancelButton);
		
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}

	public void chooseDir(int i) 
	{
		path = "";
		
		JFileChooser j = new JFileChooser();
		
		if(i == 0) 
		{
			j.setDialogTitle("Import");
			j.setFileSelectionMode(JFileChooser.FILES_ONLY);
		}
		if(i == 1) 
		{
			j.setDialogTitle("Export");
			j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		}
		
		int x=j.showSaveDialog(j);
		
		if(x==JFileChooser.APPROVE_OPTION)
		{
			path = j.getSelectedFile().getPath();
			if(i == 0)
				importFile();
			if(i == 1)
				filePathText.setText(path);
		}
	}

	private void importFile() 
	{
		dc.importFile(path); 
	}

	private void exportFiles() 
	{
		boolean flag = false;
		
		if(allCheck.isSelected() == true) { flag = true; dc.exportFile(0, path); }
		if(teacherCheck.isSelected() == true) { flag = true;  dc.exportFile(1, path); }
		if(subjectCheck.isSelected() == true) { flag = true;  dc.exportFile(2, path); }
		if(classCheck.isSelected() == true) { flag = true;  dc.exportFile(3, path); }
		if(labCheck.isSelected() == true) { flag = true;  dc.exportFile(4, path); }
		if(advanceCheck.isSelected() == true) { flag = true;  dc.exportFile(5, path); }
		
		if(flag == true)
			dialog.dispose();
	}
}
