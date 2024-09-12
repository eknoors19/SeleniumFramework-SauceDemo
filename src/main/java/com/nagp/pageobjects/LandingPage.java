package com.nagp.pageobjects;

import java.time.Duration;
import java.time.Month;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nagp.pageobjects.LandingPage;

public class LandingPage {

	WebDriver driver;

	//input[@id='user-name']
	@FindBy(how = How.ID, using = "user-name")
	WebElement EDT_Username;
	
	//input[@id='password']
	@FindBy(how = How.ID, using = "password")
	WebElement EDT_Password;
	
	@FindBy(how = How.ID, using = "twotabsearchtextbox")
	WebElement TXT_SearchText;
	
	//input[@id='login-button']
	@FindBy(how = How.ID, using = "login-button")
	WebElement BTN_LoginBtn;
	
	@FindBy(how = How.XPATH, using = "//div[@class='s-suggestion-container']/div[1]")
	WebElement AutoSuggest_Source;
	
		
	public LandingPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public String getHomePageTitle()
	{
		
		String title= driver.getTitle();
		return title;
	}
	
	public LandingPage clickLoginButton() {
		BTN_LoginBtn.click();
    	return new LandingPage(driver);
    	
    }
	
    
    public void enterSearchItem(String source) {
        TXT_SearchText.sendKeys(source);
        //AutoSuggest_Source.click();
    }
    
   
}
