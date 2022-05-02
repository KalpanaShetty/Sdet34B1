package com.crm.comcast.organizationTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class CreateOrganizationTest {

	public static void main(String[] args) throws Throwable {
		FileInputStream fis= new FileInputStream("./PropertyFile.properties");
         
		Properties pobj= new Properties();
		pobj.load(fis);
		String URL = pobj.getProperty("url");
		String BROWSER = pobj.getProperty("browser");
		String USERNAME = pobj.getProperty("username");
		String PASSWORD = pobj.getProperty("password");
		System.out.println();
		
		
		
	}

}
