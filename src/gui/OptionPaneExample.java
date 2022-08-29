package gui;

import javax.swing.*;  
public class OptionPaneExample 
{  
	JFrame f;  
	public OptionPaneExample(String msg,String head)
	{  
	    f=new JFrame();  
	    JOptionPane.showMessageDialog(f,msg,head,JOptionPane.WARNING_MESSAGE);     
	}   
}  