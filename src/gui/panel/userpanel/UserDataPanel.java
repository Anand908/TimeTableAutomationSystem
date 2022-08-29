package gui.panel.userpanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;

import database.cached.UserDatabase;
import functionality.UserFunction;
import gui.frame.userframe.UserFrame;

public class UserDataPanel 
{
	
	// --- Single Instance ---//
	
	private static UserDataPanel singleInstance;
		
	public static UserDataPanel getInstance()
	{
		if(singleInstance == null)
		{
			singleInstance = new UserDataPanel();
		}
		return singleInstance;
	}

	private JPanel mainPanel;
	public JTextField nameField;
	public JTextField collegeIdField;
	public JTextField emailField;
	public JPasswordField passwordField;
	
	public JLabel nameCheck;
	public JLabel collegeIdCheck;
	public JLabel emailCheck;
	public JLabel passwordCheck;
	public JPasswordField confirmField;
	public JButton nextButton;
	public JButton backButton;
	private UserFunction uf;

	public JPanel getPanel() 
	{
		return mainPanel;
	}

	private UserDataPanel() 
	{
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBounds(300,50,1060, 677);
		mainPanel.setBorder(new MatteBorder(0,2,2,2,new Color(60, 60, 60)));
		mainPanel.setLayout(null);
		
		JLabel profileImg = new JLabel();
		profileImg.setIcon(new ImageIcon(UserDataPanel.class.getResource("/img/User.png")));
		profileImg.setBounds(455, 21, 150, 150);
		mainPanel.add(profileImg);
		
		JLabel nameLable = new JLabel("Name               :");
		nameLable.setHorizontalAlignment(SwingConstants.LEFT);
		nameLable.setFont(new Font("Calibri", Font.BOLD, 18));
		nameLable.setBounds(286, 203, 144, 39);
		mainPanel.add(nameLable);
		
		nameCheck = new JLabel();
		nameCheck.setForeground(Color.RED);
		nameCheck.setFont(new Font("Tahoma", Font.PLAIN, 12));
		nameCheck.setBounds(435, 246, 356, 20);
		mainPanel.add(nameCheck);
		
		nameField = new JTextField();
		nameField.setText(UserDatabase.getInstance().getName());
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameField.setColumns(10);
		nameField.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		nameField.setBackground(Color.WHITE);
		nameField.setBounds(435, 203, 356, 40);
		mainPanel.add(nameField);
		
		JLabel collegeIdLabel = new JLabel("College Id        :");
		collegeIdLabel.setHorizontalAlignment(SwingConstants.LEFT);
		collegeIdLabel.setFont(new Font("Calibri", Font.BOLD, 18));
		collegeIdLabel.setBounds(286, 280, 144, 39);
		mainPanel.add(collegeIdLabel);
		
		collegeIdCheck = new JLabel();
		collegeIdCheck.setForeground(Color.RED);
		collegeIdCheck.setFont(new Font("Tahoma", Font.PLAIN, 12));
		collegeIdCheck.setBounds(435, 323, 356, 20);
		mainPanel.add(collegeIdCheck);
		
		collegeIdField = new JTextField();
		collegeIdField.setText(UserDatabase.getInstance().getCollegeId());
		collegeIdField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		collegeIdField.setColumns(10);
		collegeIdField.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		collegeIdField.setBackground(Color.WHITE);
		collegeIdField.setBounds(435, 280, 356, 40);
		mainPanel.add(collegeIdField);
		
		JTextArea hover = new JTextArea();
		hover.setFont(new Font("Century", Font.PLAIN, 14));
		hover.setEditable(false);
		hover.setLineWrap(true);
		hover.setWrapStyleWord(true);
		hover.setForeground(Color.GREEN);
		hover.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.DARK_GRAY, Color.GRAY, Color.DARK_GRAY, Color.GRAY));
		hover.setVisible(false);
		mainPanel.add(hover);
		
		/*JLabel link = new JLabel();
		link.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) 
			{
				link.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.RED));
				link.setForeground(Color.RED);
			}
			@Override
			public void mouseReleased(MouseEvent e) 
			{
				link.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.BLUE));
				link.setForeground(Color.BLUE);
			}
		});
		link.setVerticalAlignment(SwingConstants.TOP);
		link.setFont(new Font("Calibri", Font.ROMAN_BASELINE, 14));
		link.setForeground(Color.BLUE);
		link.setBounds(120, 40, 57, 12);
		link.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.BLUE));
		hover.add(link);*/
		
		JLabel emailLabel = new JLabel("Email                :");
		emailLabel.setHorizontalAlignment(SwingConstants.LEFT);
		emailLabel.setFont(new Font("Calibri", Font.BOLD, 18));
		emailLabel.setBounds(286, 357, 144, 39);
		mainPanel.add(emailLabel);
		
		emailCheck = new JLabel();
		emailCheck.setForeground(Color.RED);
		emailCheck.setFont(new Font("Tahoma", Font.PLAIN, 12));
		emailCheck.setBounds(435, 400, 356, 20);
		mainPanel.add(emailCheck);
		
		emailField = new JTextField();
		emailField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				hover.setText("Enable less Security for this Email id");
//				link.setText("Click Here");
				hover.setBounds(801, 358, 197, 60);
				hover.setVisible(true);
			}

			@Override
			public void focusLost(FocusEvent e) {
				hover.setText("");
//				link.setText("");
				hover.setVisible(false);
			}
		});
		emailField.setText(UserDatabase.getInstance().getEmail());
		emailField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		emailField.setColumns(10);
		emailField.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		emailField.setBackground(Color.WHITE);
		emailField.setBounds(435, 357, 356, 40);
		mainPanel.add(emailField);
		
		JLabel passwordLabel = new JLabel("Password        :");
		passwordLabel.setHorizontalAlignment(SwingConstants.LEFT);
		passwordLabel.setFont(new Font("Calibri", Font.BOLD, 18));
		passwordLabel.setBounds(286, 434, 144, 39);
		mainPanel.add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				hover.setText("Real Password Should be entered.\nSo that Software can access email to send Time Table on Mail.");
				hover.setBounds(801, 420, 197, 100);
				hover.setVisible(true);
			}

			@Override
			public void focusLost(FocusEvent e) {
				hover.setText("");
				hover.setVisible(false);
			}
		});
		passwordField.setToolTipText("");
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		passwordField.setColumns(10);
		passwordField.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		passwordField.setBackground(Color.WHITE);
		passwordField.setBounds(435, 434, 356, 40);
		mainPanel.add(passwordField);
		
		JLabel confirmLabel = new JLabel("Confirm          :");
		confirmLabel.setHorizontalAlignment(SwingConstants.LEFT);
		confirmLabel.setFont(new Font("Calibri", Font.BOLD, 18));
		confirmLabel.setBounds(286, 502, 144, 39);
		mainPanel.add(confirmLabel);
		
		passwordCheck = new JLabel();
		passwordCheck.setForeground(Color.RED);
		passwordCheck.setFont(new Font("Tahoma", Font.PLAIN, 12));
		passwordCheck.setBounds(435, 545, 356, 20);
		mainPanel.add(passwordCheck);
		
		confirmField = new JPasswordField();
		confirmField.setToolTipText("");
		confirmField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		confirmField.setColumns(10);
		confirmField.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		confirmField.setBackground(Color.WHITE);
		confirmField.setBounds(435, 502, 356, 40);
		mainPanel.add(confirmField);
		
		nextButton = new JButton("NEXT");
		nextButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				if(uf.addUserData())
				{
					UserFrame.getInstance().activateTimeTableStylePanel();
				}
			}
		});
		nextButton.setForeground(Color.WHITE);
		nextButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		nextButton.setBackground(new Color(34, 139, 34));
		nextButton.setBounds(830, 610, 150, 40);
		mainPanel.add(nextButton);
		
		backButton = new JButton("BACK");
		backButton.setVisible(false);
		backButton.setForeground(Color.WHITE);
		backButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		backButton.setBackground(new Color(34, 139, 34));
		backButton.setBounds(80, 610, 150, 40);
		mainPanel.add(backButton);
				
	}

	public void setUserFunctionReference(UserFunction uf) 
	{
		this.uf = uf;
	}
}
