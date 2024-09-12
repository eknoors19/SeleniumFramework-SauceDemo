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

public class RemoveFromCartTest extends BaseClass{

public WebDriver driver;
	
	public RemoveFromCartTest() {
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
	public void removeFromCart(String user, String pwd) throws Exception
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
		
		WebElement userNamefield1 = driver.findElement(By.xpath("//select[@class='product_sort_container']//option[@value='lohi']"));
		userNamefield1.click();
		
		WebElement addToCartButton = driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-onesie']"));	
		addToCartButton.click();
		System.out.println("Added to Cart");
		
		WebElement navigateToCartButton = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));	
		navigateToCartButton.click();
		System.out.println("Navigate to Cart");
		
		WebElement removeFromCartButton = driver.findElement(By.xpath("(//button[normalize-space()='Remove'])[1]"));	
		removeFromCartButton.click();
		System.out.println("Removed from Cart");
		
		WebElement verifyRemoveCart= driver.findElement(By.xpath("//div[@class='cart_desc_label']"));
		Assert.assertTrue(verifyRemoveCart.isDisplayed(),"Description");
		
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
