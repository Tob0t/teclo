package at.scch.teclo.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SearchBasePage {
	private WebDriver driver;
	
	private String specificURL = "?format=specific";
	private String advancedURL = "?format=advanced";
	
	public SearchBasePage(WebDriver driver){
		this.driver = driver;
	}
	
	public SpecificSearchPage navigateToSpecificSearchPage() {
		// Type in exact URL to avoid state conflicts
		driver.get(driver.getCurrentUrl() + specificURL);
		
		return PageFactory.initElements(driver, SpecificSearchPage.class);
	}
	
	public AdvancedSearchPage navigateToAdvancedSearchPage() {
		// Type in exact URL to avoid state conflicts
		driver.get(driver.getCurrentUrl() + advancedURL);
		
		return PageFactory.initElements(driver, AdvancedSearchPage.class);
	}
}
