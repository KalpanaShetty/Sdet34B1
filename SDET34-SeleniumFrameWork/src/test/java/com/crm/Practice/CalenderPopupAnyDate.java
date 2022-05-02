package com.crm.Practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CalenderPopupAnyDate {

	public static void main(String[] args) throws Throwable {
		
        //Set the webdrivermanager to chrome
		WebDriverManager.chromedriver().setup();
		//Launch the Browser
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://www.makemytrip.com/");
		Actions act= new Actions(driver);
		act.moveByOffset(10, 10).click();
		//Navigate to from and to  elements
		WebElement src = driver.findElement(By.xpath("//input[@id='fromCity']"));
		WebElement dst = driver.findElement(By.xpath("//input[@id='toCity']"));
		
		src.sendKeys("mumbai");
		driver.findElement(By.xpath("//p[.='Mumbai, India']")).click();
		Thread.sleep(2000);
		src.sendKeys("chennai");
		driver.findElement(By.xpath("//p[.='Chennai, India']")).click();
		//navigate to departure
		driver.findElement(By.xpath("//label[@for='departure']")).click();
		//navigate to desired date in the calender
		driver.findElement(By.xpath("//div[@aria-label='Sat Apr 23 2022']")).click();
	//close driver
		driver.quit();
	
	}

}
