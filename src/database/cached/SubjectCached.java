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

public class SubjectCached 
{
	private SerializeDeSerialize sd;
	
	protected CachedRowSet rowset;

	private DatabaseManager db;

	private Object[][] objs;
	
	public CachedRowSet getSrs() 	{	return rowset;	}
	
	public SubjectCached(String url,String username,String password)
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
			System.out.println("Exception catched at SubjectCached Creation");
			
			rowset = (CachedRowSet) sd.deSerializeObject(1);
			System.out.println("Desialization Done for SubjectCached");
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
	public void insertIntoCachedObj(String code,String title, String credit)
	{
		try {
			rowset.moveToInsertRow();
			/*
			 * Note that you must call updateNull(column_name) for 
			 * the primary key column of the table if that column’s 
			 * values are auto-generated.
			 */
			rowset.updateNull("SN");
			rowset.updateString("Code", code);
			rowset.updateString("Title", title);
			rowset.updateString("Credit", credit);
				 
			rowset.insertRow();
			rowset.moveToCurrentRow();
			System.out.println("Row Count after adding: "+rowset.size());
//			commitingCachedObj();
			
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
		catch(Exception e) {e.printStackTrace();}
		finally {	sd.serializeObject(rowset, "", 1); 	}
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
		    int rowCount = rowset.size();
		    
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
		catch(NullPointerException ne) { JOptionPane.showMessageDialog(null, "Subject Data Source Disconnected Plz Import Data"); }
		return objs;
	}

	public Object[] gettingSubjectName()
	{
		ArrayList<String> subject = new ArrayList<String>();
		try
		{
		    rowset.beforeFirst();
		    
		   	while(rowset.next())
		   	{
		   		subject.add(rowset.getString(2));
//		   		subject.add(rowset.getString(3));
	    	}
		}
		catch(SQLException e) {	e.printStackTrace();}
		return subject.toArray();
		
	}

	public Object[] gettingLabType()
	{
		Set<String> subject = new HashSet<String>();
		try
		{
		    rowset.beforeFirst();
		    
		   	while(rowset.next())
		   	{
		   		subject.add(rowset.getString(2).replaceAll("[0-9]", ""));
//		   		subject.add(rowset.getString(3));
	    	}
		}
		catch(SQLException e) {	e.printStackTrace();}
		return subject.toArray();
		
	}
	
	public void exportFile(String path) 
	{
		sd.serializeObject(rowset,path,1);
	}

	public void importFile(String path) 
	{
		rowset = (CachedRowSet) sd.deSerializeObject(path);
	}
	
	//Teacher CachedRowSet
	public void generateCachedObject(Statement st,int num)
	{
		String query = "Select * from TeacherDetails orderby SN";
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

//	public String[] findByColumn(String [] subject, int column) 
//	{
//		String [] rowData = new String[subject.length];
//		for(int i=0;i < subject.length; i++) 
//		{
//			try 
//			{
//				rowset.beforeFirst();
//				while(rowset.next())
//				{
//					if(subject[i].equals(rowset.getString(3))) 
//					{
//						rowData[i] = rowset.getString(column);
//					}
//				}
//			} 
//			catch (SQLException e) { }
//		}
//		
//		return rowData;
//	}
	
	public String[] findByColumn(Object [] subject, int column) 
	{
		String [] rowData = new String[subject.length];
		for(int i=0;i < subject.length; i++) 
		{
			try 
			{
				rowset.beforeFirst();
				while(rowset.next())
				{
					if(subject[i].equals(rowset.getString(2))) 
					{
						rowData[i] = rowset.getString(column);
					}
				}
			} 
			catch (SQLException e) { }
		}
		
		return rowData;
	}
	public String findByColumn(String code,int i) 
	{
		try
		{
		    rowset.beforeFirst();
		   	while(rowset.next())
		   	{
		   		if(code.equals(rowset.getString(2)))
		   			return rowset.getString(i);
	    	}
		}
		catch(SQLException e) {	e.printStackTrace();}
		
		return "";
	}
}
