package com.nagp.pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

	WebDriver driver;
	
	//select[@class='product_sort_container']//option[@value='lohi']
	@FindBy(how = How.XPATH, using="//select[@class='product_sort_container']//option[@value='lohi']")
	WebElement Lnk_SortProduct;
	
	@FindBy(how = How.XPATH, using="//button[@id='a-autoid-2-announce']")
	WebElement Btn_AddToCart;
	
	@FindBy(how = How.XPATH, using="//div[@id='nav-cart-count-container']")
	WebElement Btn_NavigateToCart;
	
	public void SearchResultPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getProductDesc()
	{
		String text = Lnk_SortProduct.getText();
		return text;
	}
	
	public void clickAddToCartBtn() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Btn_AddToCart);
		Thread.sleep(3000);
		Btn_AddToCart.click();
    	
    }
	
	
}
