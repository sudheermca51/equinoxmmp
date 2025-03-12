package org.iitwf.mmp.pages.patientmodule;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class JavaUtility {

	public static String generateFutureDate(int n,String format)
	{
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, n);
		System.out.println(cal.getTime());
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		
		String formattedDate = sdf.format(cal.getTime());
		System.out.println("Formatted Date:::" + formattedDate);
		return formattedDate;
 
	}
	public static String generateRandomString(int noOfChars){

		Random rand = new Random();
		String s = "";
		int bound = noOfChars;
		//char[] charArray = new char[bound];
		for(int i=0; i<bound; i++){
			char c = (char) ('a'+rand.nextInt(26));
			s = s+c;
		}
		System.out.println("getRandomString Method returning "+s);
		return s;
	}
	public static int getRandomNoOfDigits(int noOfDigits){

		Random rand = new Random();
		int addend1=0, addend2=0; String zero ="";String bound = "";int result=0;
		for(int i=1;i<=(noOfDigits-1); i++){
			zero = zero+0;
		}
		zero = 1+zero;
		addend1 = Integer.parseInt(zero);
		for (int j=1; j<=(noOfDigits-1); j++){
			bound = bound+9;
		}
		bound = 8+bound;
		addend2 = Integer.parseInt(bound);
		result = addend1+ rand.nextInt(addend2);
		System.out.println("Random "+noOfDigits+ " digit number is : "+result);
		return result;

	}

	public static int generateRandomDigits(int lBound,int uBound)
	{
		Random rand = new Random();
		int digits = lBound+rand.nextInt((uBound-lBound+1));
		return digits;
	}
	public static String generateRandom(int n,int range)
	{
		Random rand = new Random();
		String str = "";
		for(int j=0; j<n; j++)
		{
				str = str+ 9;	 
		}
		System.out.println(str);
			
		return str;   
	}
	public static String generateRandomString(String str)
	{
		Random rand = new Random();
		int digit1 =65+ rand.nextInt((90-65+1));
		char upperCaseCh = (char) digit1;
		System.out.println("Upper Case Char::: " + upperCaseCh);
		
		
		int digit2 = 97+rand.nextInt((122-97+1));
		System.out.println(digit2);
		char lowerCaseCh = (char) digit2;
		
		System.out.println("Lower Case Char::: " + lowerCaseCh);
		
		String randomString = str+upperCaseCh+lowerCaseCh;
		System.out.println("Random String :" + randomString);
		
		return randomString;
		
	}
	
	public static String generateRandomEmailID(String str)
	{
		Random rand = new Random();
		int digit1 =65+ rand.nextInt((90-65+1));
		char upperCaseCh = (char) digit1;
		
		
		int digit2 = 97+rand.nextInt((122-97+1));
		System.out.println(digit2);
		char lowerCaseCh = (char) digit2;
		
		System.out.println("Lower Case Char::: " + lowerCaseCh);
		
		String randomString = str+upperCaseCh+lowerCaseCh+"@gmail.com";
		
		System.out.println("Random Email ID:::::" + randomString);
		return randomString;
		
	}
	/**
	 * Description : Method used to connect to MYSQL DB and fetch the values and return an array
	 * 
	 * @param uname
	 * @param pword
	 * @param dbname
	 * @param tableName
	 * @param hostip
	 * @return String[][]
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String[][] getDBValues(String uname,String pword,String dbname,String tableName,String hostip) throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
	//	Driver driver = new Driver();
		/*
		 * url a database url of the form jdbc:subprotocol:subnameuser 
		 * the database user on whose behalf the connection is being madepassword 
		 * the user's password
		 */
		String url="jdbc:mysql://"+hostip+":3306/"+dbname;
		String username=uname;
		String password=pword;
		
		Connection con = DriverManager.getConnection(url, username, password);
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
											 ResultSet.CONCUR_READ_ONLY);
		
//	
//		int  value = stmt.executeUpdate("INSERT INTO `mmp`.`patient_data` VALUES (30,'James','22/11/2021');");
//		System.out.println("The rows are updated "+ value);
//		
		ResultSet rs =  stmt.executeQuery("Select * from "+dbname+"."+tableName);
		rs.last();
		int rows = rs.getRow();
		System.out.println("Number of rows " + rows);
		ResultSetMetaData rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();
		System.out.println("Number of cols: "+ cols);
		
		String data[][]= new String[rows][cols];
		int i=0;
		rs.first();
		while(rs.next())
		{
			for(int j=0;j<cols;j++)
			{
				data[i][j]=rs.getString(j+1);
				System.out.println(data[i][j]);
				System.out.println("i :::"  + i +"@@@@"+"j:::::" + j);
			}
			i++;
		}	
		return data;
	}
}
