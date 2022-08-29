package database.cached;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.sql.RowSetMetaData;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import javax.swing.JOptionPane;

import database.DatabaseManager;

public class TimeTableClassCached 
{
	protected SerializeDeSerialize sd;
	
	protected CachedRowSet rowset;
	private Integer rowNumber;
	private DatabaseManager db;
//	public CachedRowSet getRowset() 	{	return rowset;	}
	
	public TimeTableClassCached(String url,String username,String password)
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
			System.out.println("Exception catched at TimeTableClass Creation");
			
			rowset = (CachedRowSet) sd.deSerializeObject(6);
			System.out.println("Desialization Done for TimeTableClass");
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
	public void insertIntoCachedObj(String className, String [][] slot)
	{
		try {
			rowset.setTableName("timeTableClass");
			rowset.moveToInsertRow();
			/*
			 * Note that you must call updateNull(column_name) for 
			 * the primary key column of the table if that column’s 
			 * values are auto-generated.
			 */
			rowset.updateNull("SN");
			rowset.updateString("Class", className);
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
	public void updatingIntoCachedObj(Integer rowNumber,String columnName,String newValue)
	{
		try 
		{			
			if(rowset.absolute(rowNumber))
			{
				rowset.updateString(columnName, newValue);				 
				rowset.updateRow();
				System.out.println("Updation Done");				
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
		finally {	sd.serializeObject(rowset, "", 6); 	}
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
	public Object[][] gettingTimeTable(String className)
	{
		Object[][] obj = null;
		try
		{
		    int rowCount = rowset.size();
		    
//		    rowset.setCommand("Select * from teacherdetails order by FName");
//		    rowset.execute();
		    
		    obj = new Object[6][9];
		
		    rowset.beforeFirst();
		    
		    for(int row=0;row<rowCount;row++)
			{
		    	rowset.next();
		    	if(!className.equals(rowset.getString("Class"))) continue;
		    	rowNumber = row;
		    	for(int i=0;i<6;i++)
		    	{
		    		for(int j=0;j<9;j++)
		    			obj[i][j] = rowset.getString("s"+i+j);
		    	}
			}
		}
		catch(SQLException e) {	e.printStackTrace(); }
		catch(NullPointerException ne) { JOptionPane.showMessageDialog(null, "Teacher Data Source Disconnected Plz Import Data"); }
		return obj;
	}

	/*public Object[] gettingClassName()
	{
		Object [] teacher=null;
		try
		{
		    int rowCount = rowset.size();
		    
		    teacher = new Object[rowCount];
		
		    rowset.beforeFirst();
		    
		   	for(int i=0;i<rowCount;i++)
		   	{
			    rowset.next();
		   		teacher[i]=rowset.getString(2)+" "+rowset.getString(3);
	    	}
		}
		catch(SQLException e) {	e.printStackTrace();}
		return teacher;
		
	}*/
	
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

	public Object[] getClassList() 
	{
		Set <String> classList = new HashSet<String>();
		try {
			rowset.beforeFirst();
			while(rowset.next())
			{
				classList.add(rowset.getString(2).split("[*]")[0]);
			}
		} 
		catch (SQLException e) {	}
		
		return classList.toArray();
	}
	public Object[] getSectionList(String classId) 
	{
		Set<String> sectionList = new HashSet<String>();
		try {
			rowset.beforeFirst();
			while(rowset.next())
			{
				if(rowset.getString(2).split("[*]")[0].equals(classId))
					sectionList.add(rowset.getString(2).split("[*]")[1]);
			}
		} 
		catch (SQLException e) {	}
		
		return sectionList.toArray();
	}

	public boolean isClassExits(String classId) 
	{
		try {
			rowset.beforeFirst();
			while(rowset.next())
			{
				if(rowset.getString(2).equals(classId))
					return true;
			}
		} 
		catch (SQLException e) {	}
		return false;
	}
	public void setDatabaseReference(DatabaseManager db) 
	{
		this.db = db;
	}
}
