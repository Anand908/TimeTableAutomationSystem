package database;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SubjectClassFunction 
{
	// --- Instance Variable ---//
	
	//----------------------------//
	//--- Constructor ---//
	public SubjectClassFunction() 
	{
		
	}
	
	private DatabaseManager db;
	public void setDatabaseReference(DatabaseManager db)
	{
		this.db = db;
	}
	
	//--- Adding subject to database ---//
	public void addSubjectClass(ArrayList<String> temp)
	{
		String query = "Insert into SubjectClassDetails (Code, ClassID) values(?,?)";
		db.executeUpdate(query,temp.get(0),temp.get(1));
		System.out.println("Class Added Successfully\n");		
	}	
	
	//--- Function to remove subject from database ---//
	public Boolean removeSubjectClass(String pk)
	{
		String query= "Delete from SubjectClassDetails where Code='"+pk+"'";
		return db.executeUpdate(query);	
	}
	public Object[][] getRow(String... sortBy)
	{		
		
		// --- Local Variables ---//
		Object[][] obj = null;
		ResultSet rs = null;		
		// -----------------------//
		
		try
		{
			if(sortBy.length == 1)
			{		
				System.out.println("Sorting: "+sortBy[0]);
				rs = db.executeQuery("SELECT * FROM SubjectClassDetails WHERE ClassID =" +"'"+sortBy[0]+"'");	
				obj = new Object[classwiseCount(sortBy[0])][3];
				int row = 0;
				while(rs.next())			
				{				
					obj[row][0] = row+1;
					obj[row][1] = rs.getString(2);
					obj[row][2] = rs.getString(3);
					row++;
				}			
			}			
			else
			{
				rs = db.executeQuery("Select * from SubjectClassDetails");
				obj = new Object[db.countTotalRecords("SubjectClassDetails")][3];
				int row = 0;
				while(rs.next())			
				{				
					obj[row][0] = row+1;
					obj[row][1] = rs.getString(2);
					obj[row][2] = rs.getString(3);
					row++;
				}			
			}
			}
			
	
			
		catch(Exception e) 
		{
			e.printStackTrace();
			return new Object[][] {{"","",""}};
		}
		
		return obj;
	}
	
	public int classwiseCount(String classID)
	{
		ResultSet rs = db.executeQuery("select count(*) from SubjectClassDetails WHERE ClassID =" +"'"+classID+"'");
		try { rs.next(); return rs.getInt(1); }
		catch(Exception e) {return 0;}
	}
	
	public String[] getAllPK()
	{
		String[] classID = new String[db.countTotalRecords("SubjectClassDetails")];
		int index =0 ;
		for(Object[] obj : getRow())
		{
			classID[index] = (String) obj[2];
			index++;
		}
		return classID;
	}	
}

	
	