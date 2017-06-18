package at.scch.teclo.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SearchSpecificPage extends SearchBasePage {

	@FindBy(id = "bug_status")
	private WebElement bugStatus;

	@FindBy(id = "content")
	private WebElement searchText;

	@FindBy(id = "search")
	private WebElement searchButton;

	
	public SearchSpecificPage(WebDriver driver) {
		super(driver);
	}

	@Override
	protected boolean isMatchingPage() {
		return "Find a Specific Bug".equals(driver.getTitle());
	}
	
	
	public void setBugStatus(String bugStatusString) {
		new Select(bugStatus).selectByVisibleText(bugStatusString);
	}

	public SearchResultsPage searchFor(String searchTerm) {
		searchText.clear();
		searchText.sendKeys(searchTerm);
		searchButton.click();

		return PageFactory.initElements(driver, SearchResultsPage.class);
	}

	/**
	 * Search term contains only blank chars, results in parameter required
	 * error.
	 */
	public ErrorParametersRequiredPage searchForBlanks() {
		searchText.clear();
		searchText.sendKeys(" ");
		searchButton.click();

		return PageFactory.initElements(driver, ErrorParametersRequiredPage.class);
	}

	/** Search term is empty, results in matching all. */
	public SearchResultsPage searchForEmpty() {
		searchText.clear();
		searchButton.click();

		return PageFactory.initElements(driver, SearchResultsPage.class);
	}

}
