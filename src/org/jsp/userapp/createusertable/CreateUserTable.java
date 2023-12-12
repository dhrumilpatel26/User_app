package org.jsp.userapp.createusertable;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class CreateUserTable {
	public static void main(String[] args) 
	{
		Connection con=null;
		Statement st=null;
		String qry="Create table User"
				+ "(id int not null,"
				+ "name varchar(45),"
				+ "phone bigint(20) unique not null,"
				+ "email varchar(45) unique not null,"
				+ "password varchar(45) not null,"
				+ "Primary key(id))";
		Properties p=new Properties();
		FileInputStream fin =null;
		try 
		{
			fin=new FileInputStream("D:\\Java jdbc_program\\user_app\\jdbc.properties");
			p.load(fin);
			Class.forName(p.getProperty("driverClass"));
			con=DriverManager.getConnection(p.getProperty("url"),p);
			st=con.createStatement();
			st.execute(qry);
			System.out.println("User Table Created");
		}
		catch(IOException|SQLException|ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(con !=null)
			{
				try
				{
					con.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
			if(st !=null)
			{
				try
				{
					st.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}
