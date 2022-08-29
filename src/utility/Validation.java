package utility;

import java.util.regex.Pattern;

import gui.frame.MainFrame;

public class Validation 
{
	//-----------------Regex String Used for Validation-------------------------//
	
	final private String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
			"[a-zA-Z0-9_+&*-]+)*@"+
			"(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	final private String nameRegex = "[a-zA-Z .]+";
	final private String idRegex = "[a-zA-Z0-9 .-]+";
	final private String para = "Enter The University/College/School Name OR Title";
	
	private boolean isReady = true;
	
	//--------------------------------------------------------------------------//

	private static Validation singleInstance;
	public static Validation  getInstance()
	{
		if(singleInstance == null)
		{
			singleInstance = new Validation();
		}
		return singleInstance;
		
	}
	
	private Validation() 
	{
		super();
	}

	public String emailCheck(String email) 
	{
		try {
			if(email.isBlank())
				{isReady = false; return "Required Field!";}
		} 
		catch (Exception e) {
			return "cancel";
		}
		if(!Pattern.compile(emailRegex).matcher(email).matches())
			{isReady = false; return "Invalid Email!";}
		
		return "";
	}

	public String nameCheck(String name) 
	{
		if(name.isBlank())
			{isReady = false; return "Required Field!";}
		if(!Pattern.compile(nameRegex).matcher(name).matches())
			{isReady = false; return "Invalid Name!";}

		return "";
	}

	public String idCheck(String id) 
	{
		if(id.isBlank())
			{isReady = false; return "Required Field!";}
		if(!Pattern.compile(idRegex).matcher(id).matches())
			{isReady = false; return "Invalid Data!";}

		return "";
	}
	
	public String creditCheck(byte credit) 
	{
		if(0<credit)
			return "";
		
		isReady = false;
		return "Credit can't be 0";
	}

	public String passwordCheck(String password1, String password2) 
	{
		if(password1.isBlank() || password2.isBlank())
			{isReady = false; return "Both Fields are Required Field!";}
		if(!password1.equals(password2))
			{isReady = false; return "Both the Fields should be Match";}

		return "";
	}
	
	public String paraCheck(String para)
	{
		if(this.para.equals(para))
			{isReady = false; return "Required Field!";}
		
		return "";		
	}
	
	public boolean isReady() 
	{
		return isReady;
	}
	
	public void setIsReady() 
	{
		isReady = true;
	}
}