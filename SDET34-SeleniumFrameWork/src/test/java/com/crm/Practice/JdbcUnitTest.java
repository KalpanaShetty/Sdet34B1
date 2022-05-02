package com.crm.Practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class JdbcUnitTest {
	
	Connection conn=null;
	public static void main(String[] args) throws Throwable {
		String expectValue="ram";
		
			Driver driverRef = new Driver();//driver is coming from particular vender side in our case mysql
	          //register DB
			DriverManager.registerDriver(driverRef);
			//Connection to DB
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students", "root", "root");
			
			//Create the query statement
			Statement stat = conn.createStatement();
			String  query="select * from student_info";
			//Execute the query Statement
			ResultSet resultset = stat.executeQuery(query);
			Boolean flag=false;
			while (resultset.next()) {
				String actualValue = resultset.getString(2);
				if(actualValue.equalsIgnoreCase(expectValue)){
					flag=true;
					System.out.println("value present");
				}
				
			}	
		// close DB
			conn.close();	
		}
		
		
		
	}


