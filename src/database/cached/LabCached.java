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

public class LabCached 
{
	private SerializeDeSerialize sd;
	
	protected CachedRowSet rowset;

	private DatabaseManager db;
	public CachedRowSet getTrs() 	{	return rowset;	}
	
	public LabCached(String url,String username,String password)
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
			System.out.println("Exception catched at LabCached Creation");
			
			rowset = (CachedRowSet) sd.deSerializeObject(4);
			System.out.println("Desialization Done for LabCached");
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
	public void insertIntoCachedObj(String name,String labID,String labType)
	{
		try {
			rowset.moveToInsertRow();
			/*
			 * Note that you must call updateNull(column_name) for 
			 * the primary key column of the table if that column’s 
			 * values are auto-generated.
			 */
			rowset.updateNull("SR");
			rowset.updateString("LabName", name);
			rowset.updateString("LabID", labID);
			rowset.updateString("LabType", labType);
			
				 
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
		finally {	sd.serializeObject(rowset, "", 4); 	}
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
		Object[][] obj = null;
		try
		{
			RowSetMetaData rsmd = (RowSetMetaData)rowset.getMetaData();
		    int colCount = rsmd.getColumnCount();
		    int rowCount = rowset.size();
		    
		    obj = new Object[rowCount][colCount];
		
		    CachedRowSet local = rowset.createCopy();
		    local.beforeFirst();
		    for(int row=0;local.next();row++)
			{
//		    	local.next();
		    	for(int i=0;i<colCount;i++)
		    	{
		    		if(i==0)
		    		{
		    			obj[row][i]=row+1;
		    		}
		    		else
		    		{
		    			obj[row][i]=local.getString(i+1);
		    		}		
		    	}
			}
		}
		catch(SQLException e) {	e.printStackTrace();}
		catch(NullPointerException ne) { JOptionPane.showMessageDialog(null, "Lab Data Source Disconnected Plz Import Data"); }
		return obj;
	}
	
	public void exportFile(String path) 
	{
		sd.serializeObject(rowset, path, 4);
	}

	public void importFile(String path) 
	{
		rowset = (CachedRowSet) sd.deSerializeObject(path);
	}
	
	
	//Teacher CachedRowSet
	public void generateCachedObject(Statement st,int num)
	{
		String query = "Select * from LabDetails";
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

	public Object[] gettingLabType(String labType)
	{
		ArrayList<String> subject = new ArrayList<String>();
		try
		{
		    rowset.beforeFirst();
		    
		   	while(rowset.next())
		   	{
		   		if(rowset.getString(4).equals(labType))
		   			subject.add(rowset.getString(3));
	    	}
		}
		catch(SQLException e) {	e.printStackTrace();}
		return subject.toArray();
		
	}

	public void setDatabaseReference(DatabaseManager db) 
	{
		this.db = db;
	}
}
