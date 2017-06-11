package at.scch.teclo.pageobjects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SpecificSearchPage extends SearchBasePage {

	@FindBy(id = "bug_status")
	private WebElement bugState;

	@FindBy(id = "content")
	private WebElement searchField;

	@FindBy(id = "search")
	private WebElement searchButton;

	
	public SpecificSearchPage(WebDriver driver) {
		super(driver);
	}

	@Override
	protected boolean isMatchingPage() {
		return "Find a Specific Bug".equals(driver.getTitle());
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

	/**
	 * Search term contains only blank chars, results in parameter required
	 * error.
	 */
	public ParametersRequiredErrorPage searchForBlanks() {
		searchField.clear();
		searchField.sendKeys(" ");
		searchButton.click();

		return PageFactory.initElements(driver, ParametersRequiredErrorPage.class);
	}

	/** Search term is empty, results in alert popup. */
	public String searchForEmpty() {
		String alertText;

		searchField.clear();
		searchButton.click();

		Alert alert = driver.switchTo().alert();
		alertText = alert.getText();
		alert.accept();
		driver.switchTo().defaultContent();

		return alertText;
	}

}
