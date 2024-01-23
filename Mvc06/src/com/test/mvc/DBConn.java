/*==================
 *   DBConn.java
 ==================*/

// 예외 처리 - throws 구성

package com.test.mvc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn
{
	public static Connection DBConn;
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException
	{
		if(DBConn == null)
		{
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "scott";
			String pwd = "tiger";
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			DBConn = DriverManager.getConnection(url, user, pwd);
		}
		
		return DBConn;
	}
	
	public static Connection getConnection(String url, String user, String pwd) throws ClassNotFoundException, SQLException
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		DBConn = DriverManager.getConnection(url, user, pwd);
		
		return DBConn;
	}
	
	public static void close() throws SQLException
	{
		if(DBConn != null)
		{
			if(!DBConn.isClosed())
				DBConn.close();
		}
		
		DBConn = null;
	}
	

}
