package database;

import java.sql.*;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import javax.swing.JOptionPane;

import gui.OptionPaneExample;


public class DatabaseManager {
	
	
	
	//--- Instance Variable ---//
	public Connection connection;	
	private Statement st;
	public CachedRowSet dtrs;
	public CachedRowSet dsrs;
	public CachedRowSet dcrs;
	
	//Getter Setter
	public void setSt(Statement st) {		this.st = st;	}
	public Statement getSt() {		return st;	}

	//--- Constructor ---//
	public DatabaseManager(String password) 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Loaded");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/tas?useSSL=false","root",password);
			System.out.println("Connection Established");
			connection.createStatement();
			connection.setAutoCommit(false);

		}
		catch (Exception e) 
		{
			new OptionPaneExample("DATABASE Connection Failed !","Alert");
		}
	}	
	
	//--- This function will return connection object ---//
	public Connection getConnection() 
	{
		return connection;
	}
	/*private void createStatement()
	{
		try 
		{
			st = connection.createStatement();
		}
		catch(Exception e)
		{
			System.out.println("Exception while making statement!");
		}	
		
		//Test Code for Cache-------------->
//		DatabaseCached dc = new DatabaseCached();
//		dc.generateCachedObject(st, 0);
//		dtrs = dc.getTrs();
//		
//		dc.generateCachedObject(st, 1);
//		dsrs = dc.getSrs();
//		
//		dc.generateCachedObject(st, 2);
//		dcrs = dc.getCrs();
		//--------------------------------->
		
	}*/
	
	public ResultSet executeQuery(String query)
	{
		try
		{
			// ----------------------------------------------------------------
			RowSetFactory factory = RowSetProvider.newFactory();
			CachedRowSet rowset = factory.createCachedRowSet();
			// ---------------------------------------------------------------
			
			ResultSet rs = st.executeQuery(query);	
			rowset.populate(rs);
			System.out.println(rowset+" and class is "+rowset.getClass());
			
			//return rs;		
			return rowset;
			
		}
		catch(Exception e)
		{
			System.out.println("Exception caught while fetching data!");
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("finally")
	public Boolean executeUpdate(String query, String... value)
	{
		Boolean successStatus = false;
		try
		{
			PreparedStatement post;
			Integer columnNumber = 1;
			if(value.length == 0)
			{
				connection.prepareStatement(query).executeUpdate();				
			}
			else 
			{
				post = connection.prepareStatement(query);
				for (String v: value) 
				{
					post.setString(columnNumber,v);
					columnNumber++;
				}
				post.executeUpdate();				
			}
			successStatus = true;
		}
		catch(Exception e)
		{
			System.out.println("Exception caught while executing update query");
			successStatus = false;
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,e.getMessage(),"Failure",JOptionPane.ERROR_MESSAGE);
		}
		finally
		{
			System.out.println("Status: "+successStatus);
			return successStatus;
		}		
	}
	
	public int countTotalRecords(String tableName)
	{
		try
		{
			ResultSet rs = executeQuery("select count(*) from "+tableName);			
			rs.next(); return Integer.parseInt(rs.getString(1));	
		}
		catch(Exception e)
		{
			System.out.println("Exception caught while counting records");
			e.printStackTrace();			
		}
		return 0;		
	}
	
}
