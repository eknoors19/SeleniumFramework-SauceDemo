package com.nagp.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.nagp.base.BaseClass;
import com.nagp.pageobjects.LandingPage;
import com.nagp.utility.Utilities;

public class LoginTest extends BaseClass{

public WebDriver driver;
	
	public LoginTest() {
		super();
	}
	
	@BeforeMethod
	public void setup()
	{
		driver= initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		LandingPage homePage = new LandingPage(driver);	
	}
	 
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	
	@Test(priority=1,dataProvider = "LoginData")
	public void verifyLoginWithValidCredentials(String user, String pwd) throws Exception
	{		
		WebElement userNamefield = driver.findElement(By.xpath("//input[@id='user-name']"));
		userNamefield.sendKeys(user);	
		
		WebElement passwordfield = driver.findElement(By.xpath("//input[@id='password']"));
		passwordfield.sendKeys(pwd);
		System.out.println("Password entered ");
		
		WebElement loginButton = driver.findElement(By.xpath("//input[@id='login-button']"));	
		loginButton.click();
	
		WebElement validLoginText= driver.findElement(By.xpath("//span[@class='title']"));
		Assert.assertTrue(validLoginText.isDisplayed(),"Products");
		
		System.out.println("Login with valid cred");
		
		WebElement menuBtn = driver.findElement(By.xpath("//button[@id='react-burger-menu-btn']"));	
		menuBtn.click();
		System.out.println("Menu Clicked");
		
		WebElement logoutBtn = driver.findElement(By.xpath("//a[@id='logout_sidebar_link']"));	
		logoutBtn.click();
		System.out.println("Logout");

		WebElement verifyLogout= driver.findElement(By.xpath("//input[@id='login-button']"));
		Assert.assertTrue(verifyLogout.isDisplayed(),"Login");
	}
	
	@DataProvider
	public Object[][] LoginData()
	{
		Object[][] data= Utilities.getTestDatafromExcel("LoginTestData");
		return data;	
	}
}
