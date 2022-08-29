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
import javax.sql.rowset.spi.SyncProviderException;
import javax.swing.JOptionPane;

import database.DatabaseManager;

public class AdvanceCached 
{
	private SerializeDeSerialize sd;
	
	protected CachedRowSet rowset;

	private DatabaseManager db;

	private Object[][] objs;
	public CachedRowSet getRowset() 	{	return rowset;	}
	
	public AdvanceCached(String url,String username,String password)
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
			System.out.println("Exception catched at AdvanceCached Creation");
			
			rowset = (CachedRowSet) sd.deSerializeObject(5);
			System.out.println("Desialization Done for AdvanceCached");
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
	}
	
	//Inserting new row to a CachedRowSet Object
	public void insertIntoCachedObj(String fName,String lName,String empID,String email)
	{
		try {
			rowset.setTableName("AdvanceTable");
			rowset.moveToInsertRow();
			/*
			 * Note that you must call updateNull(column_name) for 
			 * the primary key column of the table if that column’s 
			 * values are auto-generated.
			 */
			
			rowset.updateNull("SN");
			rowset.updateString("Class", fName);
			rowset.updateString("Section", lName);
			rowset.updateString("SubjectName", empID);
			rowset.updateString("Teacher", email);
				 
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
	public void committingCacheObjToDB()
	{
		try 
		{
			rowset.acceptChanges(db.connection);
		} 
		catch (SyncProviderException e) {e.printStackTrace();	}
		finally {	sd.serializeObject(rowset, "", 5); 	}
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
	public Object[][] gettingTableCachedObj()
	{
		objs = null;
		try
		{
			RowSetMetaData rsmd = (RowSetMetaData)rowset.getMetaData();
		    int colCount = rsmd.getColumnCount();
		    int rowCount = rowset.size();
		    
		    objs = new Object[rowCount][colCount];
		
		    rowset.beforeFirst();
		    
		    for(int row=0;row<rowCount;row++)
			{
		    	if(!rowset.next()) break;
		    	for(int i=0;i<colCount;i++)
		    	{
		    		if(i==0)
		    		{
		    			objs[row][i]=row+1;
		    		}
		    		else
		    		{
		    			objs[row][i]=rowset.getString(i+1);
		    		}		    		
		    	}
			}
		}
		catch(SQLException e) {	e.printStackTrace();}
		catch(NullPointerException ne) { JOptionPane.showMessageDialog(null, "Advance Data Source Disconnected Plz Import Data"); }
		return objs;
	}
	
	public Object[] findByColumn(int indicator) 
	{
		Set <String> data = new HashSet<String>();
		data.add("All");
		try {
			rowset.beforeFirst();
			while(rowset.next())
			{
				data.add(rowset.getString(indicator));
			}
		} 
		catch (SQLException e) {	}
		return data.toArray();
	}
	public Object[] findByColumn(int column, String data) 
	{
		Set <String> columndata = new HashSet<String>();
		try
		{
		    rowset.beforeFirst();
		    
		   	while(rowset.next())
		   	{
		   		if(rowset.getString(2).equals(data))
		   		{
		   			columndata.add(rowset.getString(column));
		   		}
	    	}
		}
		catch(SQLException e) {	e.printStackTrace();}
		
		return columndata.toArray();
	}
	
	public Object[] findByColumn(int column, String data, String section) 
	{
		ArrayList <String> columndata = new ArrayList<String>();
		try
		{
		    rowset.beforeFirst();
		    
		   	while(rowset.next())
		   	{
		   		if(rowset.getString(2).equals(data) && rowset.getString(3).equals(section))
		   		{
		   			columndata.add(rowset.getString(column));
		   		}
	    	}
		}
		catch(SQLException e) {	e.printStackTrace();}
		
		return columndata.toArray();
	}

	public void exportFile(String path) 
	{
		sd.serializeObject(rowset, path, 5);
	}

	public void importFile(String path) 
	{
		rowset = (CachedRowSet) sd.deSerializeObject(path);
	}
	
	//Farji
	//Teacher CachedRowSet
	public void generateCachedObject(Statement st,int num)
	{
		String query = "Select * from AdvanceTable";
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
