package com.nagp.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilities {

	public static final int IMPLICIT_WAIT_TIME=10;
	public static final int PAGE_LOAD_TIME=10;
	
	public static Object[][] getTestDatafromExcel(String sheetName)
	{
		File excelFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\nagp\\testdata\\TestData.xlsx");  //csv file
		XSSFWorkbook workbook = null;
		
		try {
			FileInputStream fisExcel= new FileInputStream(excelFile);
			workbook = new XSSFWorkbook(fisExcel);
		}catch(Throwable e)
		{
			e.printStackTrace();
		}
		
		XSSFSheet sheet= workbook.getSheet(sheetName);
		int rows= sheet.getLastRowNum();
		int cols= sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[rows][cols];
		
		for(int i=0; i< rows; i ++)
		{
			XSSFRow row= sheet.getRow(i+1);
			for (int j=0; j< cols; j++)
			{
				XSSFCell cell= row.getCell(j);
				CellType cellType= cell.getCellType();
				 
				switch(cellType) {
				
				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j] = Integer.toString((int)cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;	
		
				}	
			}
		}
		return data;
	}
	
	public static void navigateToLogin(WebDriver driver) throws Exception
	{
		String redBusWindow = driver.getWindowHandle();
		WebElement accountDropMenu = driver.findElement(By.xpath("//span[text()='Account']"));
		accountDropMenu.click();
		WebElement loginSignUpOption = driver.findElement(By.xpath("//li[@id='user_sign_in_sign_up']/span"));
		loginSignUpOption.click();
		WebElement iframeP= driver.findElement(By.xpath("//iframe[@class='modalIframe']"));
		driver.switchTo().frame(iframeP);
		WebElement iframeS= driver.findElement(By.xpath("//iframe[@title='Sign in with Google Button']"));
		driver.switchTo().frame(iframeS);
		Thread.sleep(3000);
		WebElement signInWithGoogle = driver.findElement(By.xpath("//div[@id='container']//span[text()= 'Sign in with Google']"));
		signInWithGoogle.click();
		Set<String> windowIds = driver.getWindowHandles();
		
		for(String windowId: windowIds) {
			driver.switchTo().window(windowId);
			if(driver.getCurrentUrl().equals("https://www.redbus.in/")) {}
			else{
				break;
			}
		}	
		
	}
	
	public static String captureScreenshot(WebDriver driver, String testName)
	{
		File srcScreenshot= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshotPath= System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png";
		File destinationScreenshot = new File (destinationScreenshotPath);
		try {
			FileHandler.copy(srcScreenshot, destinationScreenshot);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destinationScreenshotPath;
	}
	
	public static boolean isElementPresent(WebElement locatorKey) throws NoSuchElementException {
	    try {
	    	locatorKey.isDisplayed();
	        return true;
	    } catch (org.openqa.selenium.NoSuchElementException e) {
	        return false;
	    }
	}
	
	public static boolean isElementDisplayed(WebElement elementIdentifier) 
	  { try
	  { if (elementIdentifier.isDisplayed()) 
	  	{ return true; 
	  	} 
	  	else 
	  	{ return false;
	  } 
	  } catch (Exception e) 
	  { return false; 
	  } 
	  }
	public static boolean waitForElementClickable(WebDriver driver, String locatorKey) throws NoSuchElementException {
	    try {
	    	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	    	WebElement ele= wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorKey)));
	    	if(ele.isEnabled())
	    		return true;
	    	else
	    		return false;
	    } catch (org.openqa.selenium.NoSuchElementException e) {
	        return false;
	    }
	}
}
