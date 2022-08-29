package database.cached;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.RowSetMetaData;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import javax.swing.JOptionPane;

import database.DatabaseManager;

public class TimeTableTeacherCached 
{
	protected SerializeDeSerialize sd;
	
	protected CachedRowSet rowset;
	private String teacherCursor [];
	private Integer rowNumber;
	private DatabaseManager db;
//	public CachedRowSet getRowset() 	{	return rowset;	}
	
	public TimeTableTeacherCached(String url,String username,String password)
	{
		creatingCachedObj();
		settingCachedObj(url,username,password);
		serializationObj();
	}
	
	//Creating a CachedRowSet Object
	public void creatingCachedObj()
	{
		try 
		{
			RowSetFactory factory = RowSetProvider.newFactory();
			rowset = factory.createCachedRowSet();
		} 
		catch(SQLException e)	{e.printStackTrace();}
	}

	//Setting essentials to a CachedRowSet object
	public void settingCachedObj(String url,String username,String password)	
	{
		try
		{
			rowset.setUrl(url);
			rowset.setUsername(username);
			rowset.setPassword(password);
		}
		catch(SQLException e)	{e.printStackTrace();}	
	}
	
	//Populating data to a CachedRowSet object
	public void populatingCachedObj(String sql)
	{
		try
		{
			rowset.setCommand(sql);
			rowset.execute();
		}
		catch(SQLException e)	
		{
			System.out.println("Exception catched at TimeTableTeacher Creation");
			
			rowset = (CachedRowSet) sd.deSerializeObject(7);
			System.out.println("Desialization Done for TimeTableTeacher");
//			e.printStackTrace();
		}
	}
	
	private void serializationObj() 
	{
		sd = SerializeDeSerialize.getInstance();
	}
	
	//Printing a CachedRowSet object
	/*public void printingCachedObj() throws SQLException
	{
		rowset.beforeFirst();
		while (rowset.next()) 
		{
		    String id = rowset.getString(1);
		    String fName = rowset.getString(2);
		    String lName = rowset.getString(3);
		    String empID = rowset.getString(4);
		    String email = rowset.getString(5);
		 
		    System.out.printf("%s - %s - %s - %s - %s\n", id,fName,lName,empID,email);
		}
	}*/
	
	//Inserting new row to a CachedRowSet Object
	private void insertIntoCachedObj(String teacher, String [][] slot)
	{
		System.out.println("Teacher Time Table Data Insertion.");
		try {
			rowset.setTableName("timetableteacher");
			rowset.moveToInsertRow();
			/*
			 * Note that you must call updateNull(column_name) for 
			 * the primary key column of the table if that column’s 
			 * values are auto-generated.
			 */
			rowset.updateNull("SN");
			rowset.updateString("teacher", teacher);
			for(int i=0;6>i;i++)
			{
				for(int j=0;9>j;j++)
				{
					try {
						rowset.updateString("s"+i+j, slot[i][j]);
					}catch(Exception e) {
						rowset.updateNull("s"+i+j);
					}
				}
			}
					 
			rowset.insertRow();
			rowset.moveToCurrentRow();
			
		} catch (SQLException e) {e.printStackTrace();}
	}
	
	//Modifying Data in a CachedRowSet object
	public void updatingIntoCachedObj(String teacher, String [][] slot)
	{
		try 
		{			
			if(rowset.absolute(getCursor(teacher)))
			{
				for(int i=0;6>i;i++)
				{
					for(int j=0;9>j;j++)
					{
						try {
							rowset.getString("s"+i+j).isBlank();
						}catch(Exception e) 
						{
							rowset.updateString("s"+i+j, slot[i][j]);
						}
					}
				}
				rowset.updateRow();				
			}			
		} 
		catch(SQLException e) {e.printStackTrace();}
	}
	
	//Deleting Row in a CachedRowSet Object
	public void deletingInCacheObj()
	{
		try 
		{
			rowset.absolute(rowNumber);
			rowset.deleteRow();
		} 
		catch (SQLException e) {e.printStackTrace();	}
	}
	
	//Committing Changes to the Database
	public void committingCacheObjToDB()
	{
		try 
		{	
			rowset.acceptChanges(db.getConnection());
		} 
		catch (Exception e) {e.printStackTrace();	}
		finally {	sd.serializeObject(rowset, "", 7); 	}
	}
	
	//Getting MetaData of CachedRowSet Object
	public Object[] gettingMetaData()
	{
		Object[] obj = new Object[4];
		try
		{
			RowSetMetaData rsmd = (RowSetMetaData)rowset.getMetaData();
			String tableName = rsmd.getTableName(1);
		    int colCount = rsmd.getColumnCount();
		    int rowCount = rowset.size();
		    String[] colNames = new String[colCount];
		    for(int col=1;col<=colCount;col++)
		    {
		    	colNames[col-1]=rsmd.getColumnName(col);
		    }
		    System.out.println("Table name is "+tableName);
		    System.out.println("Column Count is "+colCount);
		    System.out.println("Row Count is "+rowCount);
		    System.out.println("Column Names are: ");
		    for(String local:colNames)
		    {
		    	 System.out.println(local);
		    }
		    obj[0] = tableName;
		    obj[1] = colCount;
		    obj[2] = rowCount;
		    obj[3] = colNames;
		}
		catch(SQLException e) {e.printStackTrace();}
		return obj;
	}
	
	//Getting Object Data for Table Row
	public Object[][] gettingTimeTable(String teacher)
	{
		Object [][] obj = null;
		try
		{
//		    rowset.setCommand("Select * from teacherdetails order by FName");
//		    rowset.execute();
		    
		    obj = new Object[6][9];
		    teacherCursor = new String [rowset.size()];
		    
		    rowset.beforeFirst();

		    for(int k=0;k<rowset.size();k++)
			{
		    	if(!rowset.next()) break;
		    	teacherCursor[k] = rowset.getString(2);
		    	rowNumber = k;
		    	if(!(rowset.getString(2).equals(teacher))) continue;
		    	for(int i=0;i<6;i++)
		    	{
		    		for(int j=0;j<9;j++)
		    		{
		    			try {
		    				rowset.getString("s"+i+j).equals(" ");
		    				obj[i][j] = rowset.getString("s"+i+j);
		    				System.out.println("Data Fetched");
		    			}
		    			catch(NullPointerException ne)
		    			{
//		    				System.out.println("Null Pointer Exception Catched");
//		    				obj[i][j] = false;
		    			}
		    		}
		    	}
			}
		}
		catch(SQLException e) {	e.printStackTrace(); }
		return obj;
	}

	public Object[] gettingTeacherName()
	{
		ArrayList <String> teacherList = new ArrayList<String>();
		try {
			rowset.beforeFirst();
			while(rowset.next())
			{
				teacherList.add(rowset.getString(2));
			}
		} 
		catch (SQLException e) {	}
		
		return teacherList.toArray();		
	}
	
	public void exportFile(String path) 
	{
		sd.serializeObject(rowset, path, 0);
	}

	public void importFile(String path) 
	{
		rowset = (CachedRowSet) sd.deSerializeObject(path);
	}
	
	//Farji
	//Teacher CachedRowSet
	public void generateCachedObject(Statement st,int num)
	{
		String query = "Select * from TeacherDetails";
		try 
		{
			ResultSet rs = st.executeQuery(query);
			
			RowSetFactory factory = RowSetProvider.newFactory();
			if(num==0)
			{
				rowset = factory.createCachedRowSet();
				rowset.populate(rs);
			}		
		} 
		catch (SQLException e) 
		{
				e.printStackTrace();
		}
	}

	public void setDatabaseReference(DatabaseManager db) 
	{
		this.db = db;
	}
	private int getCursor(String teacher) 
	{
		for(int i=0;i<teacherCursor.length; i++)
		{
			if(teacherCursor[i].equals(teacher))
				return i+1;
		}
		return 0;
	}
	public void insertOrUpdate(String teacher [], String [][][] slot) 
	{
		for(int k=0;k<teacher.length;k++)
		{
			if(getCursor(teacher[k]) == 0)
			{
				insertIntoCachedObj(teacher[k], slot[k]);
			}
			else
			{
				updatingIntoCachedObj(teacher[k], slot[k]);
			}
		}
	}
}
