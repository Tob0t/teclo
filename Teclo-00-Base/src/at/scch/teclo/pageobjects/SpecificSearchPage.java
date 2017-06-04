package at.scch.teclo.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SpecificSearchPage {
	private WebDriver driver;

	@FindBy(id = "bug_status")
	private WebElement bugState;

	@FindBy(id = "content")
	private WebElement searchField;

	@FindBy(id = "search")
	private WebElement searchButton;

	public SpecificSearchPage(WebDriver driver) {
		this.driver = driver;
		
		// Check that we're on the right page
        if (!("Find a Specific Bug").equals(driver.getTitle())) {
        	throw new IllegalStateException("This is not the specific search page (Title: "+driver.getTitle()+")!");
        }
	}

	public void selectBugState(String bugStateString) {
		new Select(bugState).selectByVisibleText(bugStateString);
	}

	public BugResultsPage searchFor(String searchTerm) {
		searchField.clear();
		searchField.sendKeys(searchTerm);
		searchButton.click();

		return PageFactory.initElements(driver, BugResultsPage.class);
	}

}
