package at.scch.teclo.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StartPage extends AbstractBugzillaPage {
	
	@FindBy(id = "quicksearch_top")
	private WebElement quickSearch;

	@FindBy(id = "find_top")
	private WebElement quickFindButton;
	

	public StartPage(WebDriver driver) {
		super(driver);
	}

	@Override
	protected boolean matchingPageIsDisplayed() {
		String expectedPageTitleRegex = ".*"; 
		
		return driver.getTitle().matches(expectedPageTitleRegex);
	}

	
	public BugResultsPage searchFor(String searchTerm) {
		quickSearch.clear();
		quickSearch.sendKeys(searchTerm);
		quickFindButton.click();

		return PageFactory.initElements(driver, BugResultsPage.class);
	}

	public EditBugPage searchFor(int bugId) {
		quickSearch.clear();
		quickSearch.sendKeys(String.valueOf(bugId));
		quickFindButton.click();

		return PageFactory.initElements(driver, EditBugPage.class);
	}
}
