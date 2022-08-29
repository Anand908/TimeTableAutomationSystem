package database.cached;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class UserDatabase implements Serializable
{
	// --- Single Instance ---//
	
	private static UserDatabase singleInstance;
		
	public static UserDatabase getInstance()
	{
		if(singleInstance == null)
		{
			singleInstance = (UserDatabase) SerializeDeSerialize.getInstance().deSerializeObject(8);
			
			if(singleInstance == null)
				singleInstance = new UserDatabase();
		}
		return singleInstance;
	}
	
	private UserDatabase() 
	{
		super();
	}
	
	//-------- User Data ------------//	
	private String name;
	private String collegeId;
	private String email;
	private String passwrod;
	
	//----------Time TableStyle Data -----------//
	private String timeTableTitle;
	private byte column;
	private byte row;
	
	//---------- Time Data ---------------------//
	private Date startTime;
	private Date classDuration;
	private Date lunchDuration;
	private byte lunchPeriod;
	private boolean isAddMetaData;

	public String getName() 
	{
		return name;
	}

	public void setUserData(String name,String collegeId,String email,String passwrod) 
	{
		this.name = name;
		this.collegeId = collegeId;
		this.email = email;
		this.passwrod = passwrod;
	}

	public String getCollegeId() 
	{
		return collegeId;
	}

	public String getEmail() 
	{
		return email;
	}

	public String getPasswrod() 
	{
		return passwrod;
	}

	public String getTimeTableTitle() 
	{
		return timeTableTitle;
	}

	public void setTimeTableStyleData(String timeTableTitle,byte column,byte row) 
	{
		this.timeTableTitle = timeTableTitle;
		this.column = column;
		this.row = row;
	}

	public byte getColumn() 
	{
		return column;
	}

	public byte getRow() 
	{
		return row;
	}

	public Date getStartTime() 
	{
		return startTime;
	}

	public void setTimeData(Date startTime,Date classDuration,Date lunchDuration,byte lunchPeriod,boolean isAddMetaData) 
	{
		this.startTime = startTime;
		this.classDuration = classDuration;
		this.lunchDuration = lunchDuration;
		this.lunchPeriod = lunchPeriod;
		this.isAddMetaData = isAddMetaData;
	}

	public Date getClassDuration() 
	{
		return classDuration;
	}

	public Date getLunchDuration() {
		return lunchDuration;
	}

	public byte getLunchPeriod() {
		return lunchPeriod;
	}

	public boolean isAddMetaData() {
		return isAddMetaData;
	}
	
	
}
