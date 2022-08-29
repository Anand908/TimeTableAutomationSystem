package gui.frame;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;

public class DashBoard
{
	private static DashBoard singleInstance;
	
	public static DashBoard getInstance()
	{
		if(singleInstance == null)
		{
			singleInstance = new DashBoard();
		}
		return singleInstance;
		
	}
	
	JPanel mainPanel;
	private JLabel teacher;
	private JLabel sub;
	private JLabel prog;
	private JLabel classroom;
	private JLabel lab;
	private JLabel ad;
	private JLabel view;
	
	public JPanel getDashBoard()
	{
		return mainPanel;		
	}
	
	private DashBoard()
	{
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(51, 51, 51));
		mainPanel.setBounds(0, 50, 80, 630);
		mainPanel.setLayout(null);
		
		teacher = new JLabel("");
		teacher.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				switchTab(1);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				Footer.getInstance().helper.setText("Teacher's Section");
				teacher.setOpaque(true);
				teacher.setBackground(new Color(0, 122, 204));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Footer.getInstance().helper.setText(null);
				teacher.setOpaque(false);
				teacher.setBackground(Color.gray);
			}
		});
		teacher.setIcon(new ImageIcon(DashBoard.class.getResource("/img/teacherIcon.png")));
		
		teacher.setForeground(Color.WHITE);
		teacher.setHorizontalAlignment(SwingConstants.CENTER);
		teacher.setFont(new Font("Segoe Script", Font.BOLD | Font.ITALIC, 19));
		teacher.setBounds(0, 22, 80, 62);
		mainPanel.add(teacher);
		
		sub = new JLabel("");
		sub.setIcon(new ImageIcon(DashBoard.class.getResource("/img/subIcon.png")));
		sub.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				switchTab(2);
			}
			@Override
			public void mouseEntered(MouseEvent e) 
			{
				Footer.getInstance().helper.setText("Subject Section");
				sub.setOpaque(true);
				sub.setBackground(new Color(0, 122, 204));
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				Footer.getInstance().helper.setText(null);
				sub.setOpaque(false);
				sub.setBackground(Color.gray);
			}
		});
		sub.setForeground(Color.WHITE);
		sub.setHorizontalAlignment(SwingConstants.CENTER);
		sub.setFont(new Font("Segoe Script", Font.BOLD | Font.ITALIC, 19));
		sub.setBounds(0, 102, 80, 62);
		mainPanel.add(sub);
		
		prog = new JLabel("");
		prog.setIcon(new ImageIcon(DashBoard.class.getResource("/img/ProgramIcon.png")));
		prog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				switchTab(3);
			}
			@Override
			public void mouseEntered(MouseEvent e) 
			{
				Footer.getInstance().helper.setText("Grid Section");
				prog.setOpaque(true);
				prog.setBackground(new Color(0, 122, 204));
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				Footer.getInstance().helper.setText(null);
				prog.setOpaque(false);
				prog.setBackground(Color.gray);
			}
		});
		prog.setForeground(Color.WHITE);
		prog.setHorizontalAlignment(SwingConstants.CENTER);
		prog.setFont(new Font("Segoe Script", Font.BOLD | Font.ITALIC, 19));
		prog.setBounds(0, 390, 80, 62);
		mainPanel.add(prog);
		
		classroom = new JLabel("");
		classroom.setIcon(new ImageIcon(DashBoard.class.getResource("/img/classroom.png")));
		classroom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				switchTab(4);
			}
			@Override
			public void mouseEntered(MouseEvent e) 
			{
				Footer.getInstance().helper.setText("Class Room Section");
				classroom.setOpaque(true);
				classroom.setBackground(new Color(0, 122, 204));
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				Footer.getInstance().helper.setText(null);
				classroom.setOpaque(false);
				classroom.setBackground(Color.gray);
			}
		});
		classroom.setForeground(Color.WHITE);
		classroom.setHorizontalAlignment(SwingConstants.CENTER);
		classroom.setFont(new Font("Segoe Script", Font.BOLD | Font.ITALIC, 19));
		classroom.setBounds(0, 174, 80, 62);
		mainPanel.add(classroom);
		
		lab = new JLabel("");
		lab.setIcon(new ImageIcon(DashBoard.class.getResource("/img/labIcon.png")));
		lab.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				switchTab(5);
			}
			@Override
			public void mouseEntered(MouseEvent e)
			{
				Footer.getInstance().helper.setText("Lab Section");
				lab.setOpaque(true);
				lab.setBackground(new Color(0, 122, 204));
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				Footer.getInstance().helper.setText(null);
				lab.setOpaque(false);
				lab.setBackground(Color.gray);
			}
		});
		lab.setForeground(Color.WHITE);
		lab.setHorizontalAlignment(SwingConstants.CENTER);
		lab.setFont(new Font("Segoe Script", Font.BOLD | Font.ITALIC, 19));
		lab.setBounds(0, 246, 80, 62);
		mainPanel.add(lab);
		
		ad = new JLabel("");
		ad.setIcon(new ImageIcon(DashBoard.class.getResource("/img/advanceIcon.png")));
		ad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				switchTab(6);
			}
			@Override
			public void mouseEntered(MouseEvent e) 
			{
				Footer.getInstance().helper.setText("Advance Section");
				ad.setOpaque(true);
				ad.setBackground(new Color(0, 122, 204));
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				Footer.getInstance().helper.setText(null);
				ad.setOpaque(false);
				ad.setBackground(Color.gray);
			}
		});
		ad.setForeground(Color.WHITE);
		ad.setHorizontalAlignment(SwingConstants.CENTER);
		ad.setFont(new Font("Segoe Script", Font.BOLD | Font.ITALIC, 19));
		ad.setBounds(0, 318, 80, 62);
		mainPanel.add(ad);
		
		view = new JLabel("");
		view.setIcon(new ImageIcon(DashBoard.class.getResource("/img/timetable1.png")));
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				switchTab(7);
			}
			@Override
			public void mouseEntered(MouseEvent e) 
			{
				Footer.getInstance().helper.setText("Time Table Section");
				view.setIcon(new ImageIcon(DashBoard.class.getResource("/img/timetable2.png")));
				view.setOpaque(true);
				view.setBackground(new Color(0, 122, 204));
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				Footer.getInstance().helper.setText(null);
				view.setIcon(new ImageIcon(DashBoard.class.getResource("/img/timetable1.png")));
				view.setOpaque(false);
				view.setBackground(Color.gray);
			}
		});
		view.setForeground(Color.WHITE);
		view.setHorizontalAlignment(SwingConstants.CENTER);
		view.setFont(new Font("Segoe Script", Font.BOLD | Font.ITALIC, 19));
		view.setBounds(0, 558, 80, 62);
		mainPanel.add(view);
		
			
	}
	
	public void switchTab(int num)
	{
		if(num ==1)
		{
			teacher.setBorder(new MatteBorder(0,6,0,0,(Color) new Color(250,250,250)));
			sub.setBorder(null);
			prog.setBorder(null);
			classroom.setBorder(null);
			lab.setBorder(null);
			ad.setBorder(null);
			view.setBorder(null);
			MainFrame.getInstance().activateTeacherPanel();
		}
		
		if(num==2)
		{
			teacher.setBorder(null);
			sub.setBorder(new MatteBorder(0,6,0,0,(Color) new Color(250,250,250)));
			prog.setBorder(null);
			classroom.setBorder(null);
			lab.setBorder(null);
			ad.setBorder(null);
			view.setBorder(null);
			MainFrame.getInstance().activateSubjectPanel();
		}
		
		if(num==3) 
		{
			teacher.setBorder(null);
			sub.setBorder(null);
			prog.setBorder(new MatteBorder(0,6,0,0,(Color) new Color(250,250,250)));
			classroom.setBorder(null);
			lab.setBorder(null);
			ad.setBorder(null);
			view.setBorder(null);
			MainFrame.getInstance().activateGrid();
			
		}
		
		if(num==4)
		{
			teacher.setBorder(null);
			sub.setBorder(null);
			prog.setBorder(null);
			classroom.setBorder(new MatteBorder(0,6,0,0,(Color) new Color(250,250,250)));
			lab.setBorder(null);
			ad.setBorder(null);
			view.setBorder(null);
			MainFrame.getInstance().activateClassrooms();

		}
		if(num==5)
		{
			teacher.setBorder(null);
			sub.setBorder(null);
			prog.setBorder(null);
			classroom.setBorder(null);
			lab.setBorder(new MatteBorder(0,6,0,0,(Color) new Color(250,250,250)));
			ad.setBorder(null);
			view.setBorder(null);
			MainFrame.getInstance().activateLabs();
			

		}
		if(num==6)
		{
			teacher.setBorder(null);
			sub.setBorder(null);
			prog.setBorder(null);
			classroom.setBorder(null);
			lab.setBorder(null);
			ad.setBorder(new MatteBorder(0,6,0,0,(Color) new Color(250,250,250)));
			view.setBorder(null);
			MainFrame.getInstance().activateAdvance();

		}
		if(num==7)
		{
			teacher.setBorder(null);
			sub.setBorder(null);
			prog.setBorder(null);
			classroom.setBorder(null);
			lab.setBorder(null);
			ad.setBorder(null);
			view.setBorder(new MatteBorder(0,6,0,0,(Color) new Color(250,250,250)));
			MainFrame.getInstance().activateTimeTable();

		}
	}
}
