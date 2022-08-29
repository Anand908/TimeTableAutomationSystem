package test.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.RowSetMetaData;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.spi.SyncProviderException;

import database.DatabaseManager;

public class Test 
{
	private Connection connection;	
	private Statement st;
	private CachedRowSet rowset;
	
	public Test() 
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("JDBC Driver Loaded");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useSSL=false","root","Shivay123");
			System.out.println("Connection Established");	
			connection.setAutoCommit(false);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	private void createStatement()
	{
		try 
		{
			st = connection.createStatement();
		}
		catch(Exception e)
		{
			System.out.println("Exception while making statement!");
		}	
	}
	
	public void executeQuery()
	{
		try
		{
			// ----------------------------------------------------------------
			RowSetFactory factory = RowSetProvider.newFactory();
			rowset = factory.createCachedRowSet();
			// ---------------------------------------------------------------
			
			ResultSet rs = st.executeQuery("SELECT * FROM login");	
			rowset.populate(rs);
			System.out.println(rowset+" and class is "+rowset.getClass());
			
		}
		catch(Exception e)
		{
			System.out.println("Exception caught while fetching data!");
			e.printStackTrace();
		}
	}
	
	public void printingCachedObj() throws SQLException
	{
		rowset.beforeFirst();
		while (rowset.next()) 
		{
//		    String id = rowset.getString("id");
		    String name = rowset.getString("name");
		    String email = rowset.getString("password");
		 
		    System.out.printf("%s - %s\n",name,email);
		}
	}
	
	//Inserting new row to a CachedRowSet Object
	public void insertIntoCachedObj(String name,String email)
	{
		try {
			rowset.setTableName("login");
			rowset.moveToInsertRow();
			/*
			 * Note that you must call updateNull(column_name) for 
			 * the primary key column of the table if that column’s 
			 * values are auto-generated.
			 */
//			rowset.updateInt("id",id);
			rowset.updateString("name", name);
			rowset.updateString("password", email);
				
			//confirm insert by insertRow()
			rowset.insertRow();
			rowset.moveToCurrentRow();
		} catch (SQLException e) {e.printStackTrace();}
	}
	
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
		    
		    System.out.println("Column Names:");
		    for(int col=1;col<=colCount;col++)
		    {
		    	System.out.println(rsmd.getColumnName(col));
		    }
		}
		catch(SQLException e) {e.printStackTrace();}   
	}
	
	public void committingToDB() throws SyncProviderException
	{
		rowset.acceptChanges(connection);
	}
	public static void main(String[] args) throws SQLException
	{
		
		DatabaseManager dm = new DatabaseManager("Shivay123");
		dm.executeUpdate("insert into AdvanceTable(Class, Section, Subject, Teacher)values(\"Class 10\", \"B\", \"Math\", \"Prateek Singh\");");
		
		Test t = new Test();
		t.createStatement();
		t.executeQuery();
		t.printingCachedObj();
		t.insertIntoCachedObj("Aeliya", "ana@");
		t.printingCachedObj();
		t.gettingMetaData();
		t.committingToDB();
	}
}
