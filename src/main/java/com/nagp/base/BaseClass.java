package com.nagp.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import com.nagp.utility.Utilities;

public class BaseClass {

	WebDriver driver;
	public Properties prop;
	public Properties dataProp;
	
	public BaseClass()
	{
		prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")+ "\\src\\main\\java\\com\\nagp\\config\\config.properties");
		
		dataProp = new Properties();
		File dataPropFile = new File(System.getProperty("user.dir")+ "\\src\\main\\java\\com\\nagp\\testdata\\testdata.properties");
		
		try {
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		try {
			FileInputStream dataFis = new FileInputStream(dataPropFile);
			dataProp.load(dataFis);
		}catch(Throwable e)
		{
			e.printStackTrace();
		}
		
	}
	
	public WebDriver initializeBrowserAndOpenApplicationURL(String browserName)
	{
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			ChromeOptions chromeoptions= new ChromeOptions();
			chromeoptions.addArguments("--remote-allow-origins=*");
			chromeoptions.addArguments("--disable-notifications");
			chromeoptions.addArguments("--disable-web-security");
			chromeoptions.addArguments("--allow-running-insecure-content");			
			driver= new ChromeDriver(chromeoptions);
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			FirefoxOptions fopt= new FirefoxOptions();
			fopt.setProfile(new FirefoxProfile());
			fopt.addPreference("dom.webnotifications.enabled", false);
			driver= new FirefoxDriver(fopt);
		}
		else if (browserName.equalsIgnoreCase("edge"))
		{
			EdgeOptions eoptions = new EdgeOptions();
	        eoptions.addArguments("--disable-notifications");
			driver= new EdgeDriver(eoptions);
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		driver.get(prop.getProperty("url"));
		
		return driver;
		
	}
}
