package database.cached;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

import database.DatabaseManager;

public class DatabaseCached 
{
	TeacherCached teacherCachedObject;
	SubjectCached subjectCachedObject;
	ClassroomCached classCachedObject;
	LabCached labCachedObject;
	AdvanceCached advanceCachedObject;
	TimeTableClassCached timeTableClassCachedObject;
	TimeTableTeacherCached timeTableTeacherCachedObject;
	TimeTableLabCached timeTableLabCachedObject;
	
	
	
	public TeacherCached getTeacherCachedObject() 
	{
		return teacherCachedObject;
	}
	public SubjectCached getSubjectCachedObject() 
	{
		return subjectCachedObject;
	}	
	public ClassroomCached getClassCachedObject() 
	{
		return classCachedObject;
	}
	public LabCached getLabCachedObject() 
	{
		return labCachedObject;
	}
	public AdvanceCached getAdvanceCachedObject() 
	{
		return advanceCachedObject;
	}
	public TimeTableClassCached getTimeTableClassObject() 
	{
		return timeTableClassCachedObject;
	}
	public TimeTableTeacherCached getTimeTableTeacherObject() 
	{
		return timeTableTeacherCachedObject;
	}
	public TimeTableLabCached getTimeTableLabObject() 
	{
		return timeTableLabCachedObject;
	}
	
	private String url = "jdbc:mysql://localhost:3306/tas?useSSL=false";
	private String username = "root";
	private String password = "Shivay123";
	
	public DatabaseCached()
	{
		generateCachedObjects2();
	}
	
	public void generateCachedObjects2()
	{
		teacherCachedObject = new TeacherCached(url,username,password);
		teacherCachedObject.populatingCachedObj("select * from teacherdetails");
		
		subjectCachedObject = new SubjectCached(url,username,password);
		subjectCachedObject.populatingCachedObj("SELECT * FROM SubjectDetails");
		
		classCachedObject = new ClassroomCached(url,username,password);
		classCachedObject.populatingCachedObj("SELECT * FROM ClassroomDetails");
		
		labCachedObject = new LabCached(url,username,password);
		labCachedObject.populatingCachedObj("SELECT * FROM LabDetails");

		advanceCachedObject = new AdvanceCached(url,username,password);
		advanceCachedObject.populatingCachedObj("SELECT * FROM AdvanceTable");

		timeTableClassCachedObject = new TimeTableClassCached(url,username,password);
		timeTableClassCachedObject.populatingCachedObj("SELECT * FROM timeTableClass");
		
		timeTableTeacherCachedObject = new TimeTableTeacherCached(url,username,password);
		timeTableTeacherCachedObject.populatingCachedObj("SELECT * FROM timetableteacher");
		
		timeTableLabCachedObject = new TimeTableLabCached(url,username,password);
		timeTableLabCachedObject.populatingCachedObj("SELECT * FROM timetablelab");
	}
	private String[] tableName = new String[10];
	{
		tableName[0] = "TeacherDetails";
		tableName[1] = "SubjectDetails";
		tableName[2] = "ClassroomDetails";
		tableName[3] = "LabDetails";
		tableName[4] = "AdvanceTable";
		tableName[5] = "TimeTableClass";
		tableName[6] = "TimeTableTeacher";
		tableName[7] = "TimeTableLab";
		tableName[8] = "";
		tableName[9] = "";		
	}
	private CachedRowSet trs;
	private CachedRowSet srs;
	private CachedRowSet crs;
	private CachedRowSet lrs;
	private CachedRowSet ars;
	private CachedRowSet ttcrs;
	private CachedRowSet tttrs;
	private CachedRowSet ttlrs;
	

	//Getter Functions
	public CachedRowSet getTrs() 	{	return trs;	}
	public CachedRowSet getSrs()	{	return srs;	}
	public CachedRowSet getCrs()	{	return crs;	}
	public CachedRowSet getLrs()	{	return lrs;	}
	public CachedRowSet getArs()	{	return ars;	}
	public CachedRowSet getTtcrs()	{	return ttcrs;	}
	public CachedRowSet getTttrs()	{	return tttrs;	}
	public CachedRowSet getTtlrs()	{	return ttlrs;	}

	//Teacher CachedRowSet
	public void generateCachedObject(Statement st,int num)
	{
		String query = "Select * from "+tableName[num];
		try 
		{
			ResultSet rs = st.executeQuery(query);
			
			RowSetFactory factory = RowSetProvider.newFactory();
			if(num==0)
			{
				trs = factory.createCachedRowSet();
				trs.populate(rs);
			}
			if(num==1)
			{
				srs = factory.createCachedRowSet();
				srs.populate(rs);
			}
			if(num==2)
			{
				crs = factory.createCachedRowSet();
				crs.populate(rs);
			}
			if(num==3)
			{
				lrs = factory.createCachedRowSet();
				lrs.populate(rs);
			}
			if(num==4)
			{
				ars = factory.createCachedRowSet();
				ars.populate(rs);
			}
			if(num==5)
			{
				ttcrs = factory.createCachedRowSet();
				ttcrs.populate(rs);
			}
			if(num==6)
			{
				tttrs = factory.createCachedRowSet();
				tttrs.populate(rs);
			}
			if(num==7)
			{
				ttlrs = factory.createCachedRowSet();
				ttlrs.populate(rs);
			}
			
		} 
		catch (SQLException e) 
		{			
			e.printStackTrace();
		}
	}
	/*public static void main(String... args) throws SQLException
	{
		DatabaseCached dc = new DatabaseCached();
		//dc.generateCachedObjects2();
		dc.teacherCachedObject.populatingCachedObj("SELECT * FROM TeacherDetails");
		dc.teacherCachedObject.gettingMetaData();
		//dc.tc.printingCachedObj();
		dc.teacherCachedObject.gettingTableCachedObj();
	}*/
	public Object[][] gettingTableCachedObj(int i) 
	{
		if(i==1)
			return teacherCachedObject.gettingTableCachedObj();
		if(i==2)
			return subjectCachedObject.gettingTableCachedObj();
		if(i==3)
			return classCachedObject.gettingTableCachedObj();
		if(i==4)
			return labCachedObject.gettingTableCachedObj();
		if(i==5)
			return advanceCachedObject.gettingTableCachedObj();
		return null;
	}
	public Object[][] gettingTableCachedObj(String classId) 
	{		
		return classCachedObject.gettingSectionTableCachedObj(classId);
	}
	public Object[] gettingName(int i) 
	{		
		if(i==1)
			return teacherCachedObject.findById();
		if(i==2)
			return subjectCachedObject.gettingSubjectName();
		if(i==3)
			return classCachedObject.gettingClassName();
		if(i==4)
			return advanceCachedObject.findByColumn(4);
		if(i==5)
			return advanceCachedObject.findByColumn(5);
		if(i==6)
			return advanceCachedObject.findByColumn(2);
		
		return null;
	}
	public Object[] gettingColumnNames(int i)
	{
		if(i==1)
		{
			Object[] obj = teacherCachedObject.gettingMetaData();
			return (Object[]) obj[3];
		}
			 
		//if(i==2)
			//return sc.gettingMetaData();
		//if(i==3)
			//return cc.gettingMetaData();		
		return null;
	}
	public String [] objectToString(Object [] obj) 
	{
		String [] data = new String[obj.length];
		for(int i=0;i<obj.length;i++)
		{
			data [i] = obj [i].toString();
		}
		return data;
	}
	public void exportFile(int i, String path) 
	{
		if(i == 0) { exportAllFile(path); } 
		if(i == 1) { teacherCachedObject.exportFile(path); } 
		if(i == 2) { subjectCachedObject.exportFile(path); } 
		if(i == 3) { classCachedObject.exportFile(path); } 
		if(i == 4) { labCachedObject.exportFile(path); } 
		if(i == 5) { advanceCachedObject.exportFile(path); } 
	}
	private void exportAllFile(String path) 
	{
		Object [] allFile = new Object[5];
		
		allFile [0] = teacherCachedObject.rowset;
		allFile [1] = subjectCachedObject.rowset;
		allFile [2] = classCachedObject.rowset;
		allFile [3] = labCachedObject.rowset;
		allFile [4] = advanceCachedObject.rowset;
		
		teacherCachedObject.sd.serializeObject(allFile, path, 9);
	}
	public void importFile(String path) 
	{
		String tableName = null;
		boolean flag = false;
		try {
			CachedRowSet rowset = (CachedRowSet) SerializeDeSerialize.getInstance().deSerializeObject(path);
			tableName = rowset.getTableName();
			System.out.println("Table Name : "+tableName);
		} 
		catch (SQLException e) { e.printStackTrace(); }
		
		catch(ClassCastException ce) 
		{ 
			importAllFile(path);
			flag = true;
//			ce.printStackTrace();
		}
		if(flag == false) 
		{
			if(tableName.equalsIgnoreCase("TeacherDetails")) { teacherCachedObject.importFile(path); }
			if(tableName.equalsIgnoreCase("SubjectDetails")) { subjectCachedObject.importFile(path); }
			if(tableName.equalsIgnoreCase("ClassroomDetails")) { classCachedObject.importFile(path); }
			if(tableName.equalsIgnoreCase("LabDetails")) { labCachedObject.importFile(path); }
			if(tableName.equalsIgnoreCase("AdvanceTable")) { advanceCachedObject.importFile(path); }
		}
		
	}
	private void importAllFile(String path) 
	{
		Object [] allFile;
		System.out.println("importAll Called");
		allFile = (Object[]) teacherCachedObject.sd.deSerializeObject(path);
		
		System.out.println("Deserialization Done");

		teacherCachedObject.rowset = (CachedRowSet) allFile[0];
		subjectCachedObject.rowset = (CachedRowSet) allFile[1];
		classCachedObject.rowset = (CachedRowSet) allFile[2];
		labCachedObject.rowset = (CachedRowSet) allFile[3];
		advanceCachedObject.rowset = (CachedRowSet) allFile[4];
		
		System.out.println("CachedRowSet Value assigned");
	}
	public void setDatabaseReference(DatabaseManager db) 
	{
		teacherCachedObject.setDatabaseReference(db);
		subjectCachedObject.setDatabaseReference(db);
		classCachedObject.setDatabaseReference(db);
		labCachedObject.setDatabaseReference(db);
		advanceCachedObject.setDatabaseReference(db);
		getTimeTableClassObject().setDatabaseReference(db);
		getTimeTableTeacherObject().setDatabaseReference(db);
		getTimeTableLabObject().setDatabaseReference(db);
	}
}