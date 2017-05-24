package at.scch.teclo.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SearchPage {
	private WebDriver driver;
	
	private String specificURL = "?format=specific";
	private String advancedURL = "?format=advanced";
	
	@FindBy(id="bug_status")
	private WebElement bugState;
	
	@FindBy(id="content")
	private WebElement searchField;
	
	@FindBy(id="search")
	private WebElement searchButton;
	
	public SearchPage(WebDriver driver){
		this.driver = driver;
	}
	
	public SearchPage navigateToSpecificSearchPage() {
		// Type in exact URL to avoid state conflicts
		driver.get(driver.getCurrentUrl() + specificURL);
		
		return PageFactory.initElements(driver, SearchPage.class);
	}
	
	public SearchPage navigateToAdvancedSearchPage() {
		// Type in exact URL to avoid state conflicts
		driver.get(driver.getCurrentUrl() + advancedURL);
		
		return PageFactory.initElements(driver, SearchPage.class);
	}
	
	public void selectBugState(String bugStateString){
		new Select(bugState).selectByVisibleText(bugStateString);
	}
	
	public MyBugsPage searchFor(String searchTerm){
		searchField.clear();
		searchField.sendKeys(searchTerm);
		searchButton.click();
		
		return PageFactory.initElements(driver, MyBugsPage.class);
	}

}
