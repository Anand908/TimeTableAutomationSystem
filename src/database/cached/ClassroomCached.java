package database.cached;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.RowSetMetaData;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.spi.SyncProviderException;
import javax.swing.JOptionPane;

import database.DatabaseManager;

public class ClassroomCached 
{
	private SerializeDeSerialize sd;
	
	protected CachedRowSet rowset;
	private Object[][] objs;
	private int rowCount;

	private DatabaseManager db;
	public CachedRowSet getTrs() 	{	return rowset;	}
	
	public ClassroomCached(String url,String username,String password)
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
			System.out.println("Exception catched at ClassroomCached Creation");
			
			rowset = (CachedRowSet) sd.deSerializeObject(3);
			System.out.println("Desialization Done for ClassroomCached");
//			e.printStackTrace();
		}
	}
	
	private void serializationObj() 
	{
		sd = SerializeDeSerialize.getInstance();
	}
	
	//Printing a CachedRowSet object
	public void printingCachedObj() throws SQLException
	{
		CachedRowSet local = rowset.createCopy();
		while (local.next()) 
		{
		    String id = local.getString(1);
		    String fName = local.getString(2);
		    String lName = local.getString(3);
		    String empID = local.getString(4);
		    String email = local.getString(5);
		 
		    System.out.printf("%s - %s - %s - %s - %s\n", id,fName,lName,empID,email);
		}
	}
	
	//Inserting new row to a CachedRowSet Object
	public void insertIntoCachedObj(String classId,String section)
	{
		try {
			rowset.moveToInsertRow();
			/*
			 * Note that you must call updateNull(column_name) for 
			 * the primary key column of the table if that column’s 
			 * values are auto-generated.
			 */
			rowset.updateNull("SN");
			rowset.updateString("ClassId", classId);
			rowset.updateString("Section", section);		
				 
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
	public void deletingInCacheObj(Integer rowNumber)
	{
		try 
		{
			rowset.absolute(rowNumber);
			rowset.deleteRow();
		} 
		catch (SQLException e) {e.printStackTrace();	}
	}
	
	//Committing Changes to the Database
	public void commitingCachedObj()
	{
		try 
		{
			rowset.acceptChanges(db.connection);
		} 
		catch(SyncProviderException e) {e.printStackTrace();}
		finally {	sd.serializeObject(rowset, "", 3); 	}
	}
	
	//Getting MetaData of CachedRowSet Object
	public void gettingMetaData()
	{
		try
		{
			RowSetMetaData rsmd = (RowSetMetaData)rowset.getMetaData();
		    int colCount = rsmd.getColumnCount();
		    int type = rsmd.getColumnType(2);
		    int rowCount = rowset.size();
		    
		    System.out.println("Column Count is "+colCount);
		    System.out.println("Column Type is "+type);
		    System.out.println("Row Count is "+rowCount);
		    System.out.println(rsmd.getColumnName(1));
		    System.out.println(rsmd.getColumnName(2));
		    System.out.println(rsmd.getColumnName(3));
		}
		catch(SQLException e) {e.printStackTrace();}   
	}
	
	//Getting Object Data for Table Row
	public Object[][] gettingTableCachedObj()
	{
		objs = null;
		try
		{
			RowSetMetaData rsmd = (RowSetMetaData)rowset.getMetaData();
		    int colCount = rsmd.getColumnCount();
		    rowCount = rowset.size();
		    
		    /*System.out.println("col Count "+colCount);
		    System.out.println("row Count "+rowCount);*/
		    
		    objs = new Object[rowCount][colCount];
		
		    CachedRowSet local = rowset.createCopy();
		    local.beforeFirst();
		    
		    for(int row=0;row<rowCount;row++)
			{
		    	local.next();
		    	for(int i=0;i<colCount;i++)
		    	{
		    		if(i==0)
		    		{
		    			objs[row][i]=row+1;
		    		}
		    		else
		    		{
		    			objs[row][i]=local.getString(i+1);
		    		}		
		    	}
			}
		}
		catch(SQLException e) {	e.printStackTrace();}
		catch(NullPointerException ne) { ne.printStackTrace(); JOptionPane.showMessageDialog(null, "Classroom Data Source Disconnected Plz Import Data"); }
		return objs;
	}
	public Object[][] gettingSectionTableCachedObj(String classId)
	{
		Object [][] section = null;
		try
		{
		    rowset.beforeFirst();
		    
		   	while(rowset.next())
		   	{
		   		if(rowset.getString(2).equals(classId))
		   		{
		   			section = new Object[rowset.getString(3).split(",").length][1];
		   			for(int i=0;i < section.length;i++)
		   			{
		   				section[i][0] = rowset.getString(3).split(",")[i];
		   			}
		   		}
	    	}
		}
		catch(SQLException e) {	e.printStackTrace();}
		
		return section;
	}
	

	public Object[] gettingSection(String classId)
	{
		try
		{
		    rowset.beforeFirst();
		    
		   	while(rowset.next())
		   	{
		   		if(rowset.getString(2).equals(classId))
		   		{
		   			return rowset.getString(3).split(",");		   			
		   		}
	    	}
		}
		catch(SQLException e) {	e.printStackTrace();}
		
		return null;
	}
	
	public Object[] gettingClassName()
	{
		ArrayList<String> className = new ArrayList<String>();
		try
		{
		    rowset.beforeFirst();
		    
		   	while(rowset.next())
		   	{
			    className.add(rowset.getString(2));
	    	}
		}
		catch(SQLException e) {	e.printStackTrace();}
		
		return className.toArray();
		
	}
	
	public void exportFile(String path) 
	{
		sd.serializeObject(rowset,path, 3);
	}

	public void importFile(String path) 
	{
		rowset = (CachedRowSet) sd.deSerializeObject(path);
	}
	
	//Teacher CachedRowSet
	public void generateCachedObject(Statement st,int num)
	{
		String query = "Select * from ClassroomDetails";
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
}
