package utility;

public class DebugerMode 
{
	private static Boolean debugerActive = false;
	
	static public Boolean debugerActive()
	{
		return debugerActive;
	}	
	static public void setDebugerActive()
	{
		debugerActive = true;
	}

}
