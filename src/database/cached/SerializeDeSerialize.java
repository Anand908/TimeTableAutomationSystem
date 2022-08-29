package database.cached;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class SerializeDeSerialize 
{
	private static SerializeDeSerialize singleInstance;
	
	public static SerializeDeSerialize getInstance()
	{
		if(singleInstance == null)
		{
			singleInstance = new SerializeDeSerialize();
		}
		return singleInstance;
		
	}
	private SerializeDeSerialize() {}
	
	String[] fileName = new String[11];
	{
		fileName[0] = "TeacherSaveData";
		fileName[1] = "SubjectSaveData";
		fileName[2] = "ProgramSaveData";
		fileName[3] = "ClassroomSaveData";
		fileName[4] = "LabSaveData";
		fileName[5] = "AdvanceSaveData";
		fileName[6] = "TimeTableClass";
		fileName[7] = "TimeTableTeacher";
		fileName[8] = "UserDataBase";
		fileName[9] = "TAS_Data";	
		fileName[10] = "TimeTableLab";		
	}
	
	public Object deSerializeObject(int num)
	{
		Object temp=null;
		try
		{
			FileInputStream fis = new FileInputStream(fileName[num]);
			ObjectInputStream ois =  new ObjectInputStream(fis);
			
			temp = ois.readObject();
			ois.close();
			fis.close();
		}
		catch(Exception ex)
		{
			System.out.println("DeSerialization failed for "+ fileName[num] +".No File Found");
			return temp;
		}
		return temp;
	}
	
	public Object deSerializeObject(String path)
	{
		Object temp=null;
		try
		{
			FileInputStream fis = new FileInputStream(path);
			ObjectInputStream ois =  new ObjectInputStream(fis);
			
			temp = ois.readObject();
			ois.close();
			fis.close();
		}
		catch(Exception ex)
		{
			System.out.println("DeSerialization failed for "+ path +".No File Found");
			return temp;
		}
		return temp;
	}
	
	public void serializeObject(Object rowset, String path, int num)
	{
		FileOutputStream fos;
		try 
		{
			if(path.equals("")) { fos = new FileOutputStream(fileName[num]); }
			
			else { fos = new FileOutputStream(path +"\\"+ fileName[num]); }
			
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(rowset);
			
			oos.close();
			fos.close();
			
			System.out.println("Serialization Done!!");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println("Serailization Failed");
		}
	}
}
