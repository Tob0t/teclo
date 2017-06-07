package at.scch.teclo.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoggedInBasePage {

	private final WebDriver driver;

	@FindBy(id = "quicksearch_top")
	private WebElement quickSearch;

	@FindBy(id = "find_top")
	private WebElement quickFindButton;

	@FindBy(linkText = "Log out")
	private WebElement LogoutLink;

	@FindBy(linkText = "New")
	private WebElement NewLink;

	@FindBy(linkText = "My Bugs")
	private WebElement MyBugsLink;

	@FindBy(linkText = "Search")
	private WebElement SearchLink;

	@FindBy(linkText = "Reports")
	private WebElement ReportsLink;

	@FindBy(xpath = "//div[@id='header']/ul/li[9]")
	private WebElement loginUserName;

	public LoggedInBasePage(WebDriver driver) {
		this.driver = driver;

		// Check that we're on the right page.
		if (!("Bugzilla Main Page").equals(driver.getTitle())) {
			throw new IllegalStateException("This is not the logged in page (Title: " + driver.getTitle() + ")!");
		}
	}

	public LoggedOutBasePage logOut() {
		LogoutLink.click();
		return PageFactory.initElements(driver, LoggedOutBasePage.class);
	}

	public CreateNewBugPage navigateToCreateNewBugPage() {
		NewLink.click();
		return PageFactory.initElements(driver, CreateNewBugPage.class);
	}

	public BugResultsPage navigateToBugResultsPage() {
		MyBugsLink.click();
		return PageFactory.initElements(driver, BugResultsPage.class);
	}

	public SearchBasePage navigateToSearchBasePage() {
		SearchLink.click();
		return PageFactory.initElements(driver, SearchBasePage.class);
	}

	public ReportsBasePage navigateToReportsBasePage() {
		ReportsLink.click();
		return PageFactory.initElements(driver, ReportsBasePage.class);
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

	public BugResultsPage getSavedSearch(String savedSearchName) {
		driver.findElement(By.linkText(savedSearchName)).click();
		return PageFactory.initElements(driver, BugResultsPage.class);
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean isLogoutLinkPresent() {
		return isElementPresent(By.linkText("Log out"));
	}

	public String getLogoutTextPlusUserName() {
		return loginUserName.getText();
	}
}
