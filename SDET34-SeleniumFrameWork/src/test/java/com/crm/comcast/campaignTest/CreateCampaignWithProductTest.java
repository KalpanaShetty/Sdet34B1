package com.crm.comcast.campaignTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateCampaignWithProductTest {

	public static void main(String[] args) throws Throwable {
		//propertFile
FileInputStream fis=new FileInputStream("./PropertyFile.properties");
Properties p=new Properties();
p.load(fis);
String url = p.getProperty("url");
String username = p.getProperty("username");
String password = p.getProperty("password");
String browser = p.getProperty("browser");

 Random random=new Random();
 int rannum=random.nextInt(1000);
 
 FileInputStream fis1=new FileInputStream("./campaign.xlsx"); 
Workbook wb = WorkbookFactory.create(fis1);
Sheet sh=wb.getSheet("Sheet1");
Row row=sh.getRow(1);
String campname  = row.getCell(0).getStringCellValue()+ rannum;
Row row1=sh.getRow(1);
String productname = row1.getCell(1).getStringCellValue()+ rannum;
System.out.println(productname);
wb.close();

WebDriver driver=null;
if(browser.equals("chrome")) {
 
driver=new ChromeDriver();
}
else {
 driver=new FirefoxDriver();
}
driver.manage().window().maximize();
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
driver.get(url);
WebElement un = driver.findElement(By.xpath("//input[@type='text']"));
un.sendKeys(username);
WebElement pw = driver.findElement(By.xpath("//input[@type='password']"));
pw.sendKeys(password);
driver.findElement(By.xpath("//input[@type='submit']")).click();
driver.findElement(By.xpath("//a[.='Products']")).click();
driver.findElement(By.xpath("//img[@title='Create Product...']")).click();
driver.findElement(By.xpath("//input[@name='productname']")).sendKeys(productname);
driver.findElement(By.xpath("(//input[@name='button'])[1]")).click();
WebElement more = driver.findElement(By.xpath("(//td[@class='tabUnSelected'])[11]"));

Actions a=new Actions(driver);
a.moveToElement(more).perform();
driver.findElement(By.xpath("//a[.='Campaigns']")).click();
driver.findElement(By.xpath("//img[@title='Create Campaign...']")).click();
driver.findElement(By.xpath("//input[@name='campaignname']")).sendKeys(campname);
driver.findElement(By.xpath("//img[@title='Select']")).click();
Set<String> set1 = driver.getWindowHandles();
Iterator<String> it1=set1.iterator();

while(it1.hasNext()) {
String cid = it1.next();
driver.switchTo().window(cid);
String title = driver.getTitle();
if(title.contains("products&action")) {
	break;
}
}
driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(productname);
driver.findElement(By.xpath("//input[@type='button']")).click();
driver.findElement(By.xpath("//a[.='"+productname+"']")).click();
Set<String> set2 = driver.getWindowHandles();
Iterator<String> it2=set2.iterator();

while(it2.hasNext()) {
String cid = it2.next();
driver.switchTo().window(cid);
String title1 = driver.getTitle();
if(title1.contains("campaign&action")) {
	break;
}
}


driver.findElement(By.xpath("(//input[@name='button'])[1]")).click();
String actval = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();


if(actval.contains(campname)) {
System.out.println("campaign is added and pass");
}
else {
System.out.println("campaign is not added and fail");

}
Thread.sleep(3000);
WebElement admin = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));

Actions a1=new Actions(driver);
a.moveToElement(admin).perform();
Thread.sleep(3000);
driver.findElement(By.xpath("//a[.='Sign Out']")).click();
driver.close();
       
	
	}

	

}
