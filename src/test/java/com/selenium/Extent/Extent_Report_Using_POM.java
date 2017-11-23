package com.selenium.Extent;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Extent_Report_Using_POM {

	WebDriver driver;
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest test;
	
	By Locator_UserName=By.id("txtUsername");
	By Locator_Password = By.id("txtPassword");
	By Locator_LoginButton = By.id("btnLogin");
	
	private String Dashboard_URL="http://opensource.demo.orangehrmlive.com/index.php/dashboard";

	@BeforeTest
	public void setup()
	{
		htmlReporter = new ExtentHtmlReporter("HRM.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		test = extent.createTest("MyFirstTest for Orange_HRM");
		
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--start-maximized");
		
		driver= new ChromeDriver(opt);
		driver.get("http://opensource.demo.orangehrmlive.com/");
		driver.manage().timeouts().implicitlyWait(05, TimeUnit.SECONDS);
	}
	@Test
	public void log()
	{
		try {
			WebElement UserName = driver.findElement(Locator_UserName);
			UserName.sendKeys("admin");
			test.log(Status.PASS, "Enter UserName Successfully");
		} catch (Exception e) {
			test.log(Status.FAIL, " Something going wrong in Enter UserName ");
		}
		
		try {
			WebElement Password = driver.findElement(Locator_Password);
			Password.sendKeys("admin");
			test.log(Status.PASS, "Enter Password Successfully");
		} catch (Exception e) {
			test.log(Status.FAIL, " Something went wrong in Enter Password ");
		}
		try {
			WebElement LoginButton = driver.findElement(Locator_LoginButton);
			LoginButton.click();
			test.log(Status.PASS, "Enter Login button");
		} catch (Exception e) {
			test.log(Status.FAIL, " Something went wrong Login ");
		}
		
		if(driver.getCurrentUrl().equals(Dashboard_URL))
		{
			test.log(Status.PASS, "Login Successfully");
		}
		else
		{
			test.log(Status.FAIL, "Something went wrong in login");
		}

	}
	@AfterTest
	public void close()
	{
		extent.flush();
		driver.quit();
	}

}
