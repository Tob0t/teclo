package at.scch.teclo.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.scch.teclo.Helper;

public abstract class AbstractBugzillaPage {
	private static final Logger Logger = LoggerFactory.getLogger(AbstractBugzillaPage.class);

	protected final WebDriver driver;

	@FindBy(linkText = "Home")
	private WebElement homeLink;

	@FindBy(linkText = "New")
	private WebElement newLink;

	@FindBy(linkText = "My Bugs")
	private WebElement myBugsLink;

	@FindBy(linkText = "Search")
	private WebElement searchLink;

	@FindBy(linkText = "Reports")
	private WebElement reportsLink;

	@FindBy(id = "quicksearch_top")
	private WebElement quickFindText;

	@FindBy(id = "find_top")
	private WebElement quickFindButton;
	
	@FindBy(linkText = "Administration")
	private WebElement administrationLink;
	
	@FindBy(linkText = "Bug Status Workflow")
	private WebElement bugStatusWorkflowLink;
	
	
	public AbstractBugzillaPage(WebDriver driver) {
		this.driver = driver;
		
		if (!isMatchingPage()) {
			String errorMsg = "Page object " + this.getClass().getName()
					+ " does not match the displayed page (title: " + driver.getTitle() + ")!";
			Logger.error(errorMsg);
			throw new IllegalStateException(errorMsg);
		}
	}

	/**
	 * Check that we're on the right page. The page object has to match the
	 * displayed page, otherwise a navigation error has occurred. The default
	 * implementation is checking the title of the page matching a given regular
	 * expression.
	 * 
	 * @return true if the displayed page matches this page object.
	 */
	abstract protected boolean isMatchingPage();
	// String expectedPageTitleRegex = ".*";
	// return driver.getTitle().matches(expectedPageTitleRegex);
	// }

	
	public String getTitle() {
		return driver.getTitle();
	}

	public boolean isLoggedin() {
		return Helper.isElementPresent(driver, By.linkText("Log out"));
	}

	public String getLoggedinUser() {
		// find the li that contains the link "Log out"
		WebElement liLogoutUser = driver.findElement(By.xpath("//li[a[text()[contains(.,'Log')]]]"));
		String liText = liLogoutUser.getText();
		return liText.substring(liText.lastIndexOf(" ") + 1);
	}

	public boolean login(String username, String password) {
		driver.findElement(By.id("login_link_top")).click();
		driver.findElement(By.id("Bugzilla_login_top")).click();

		// Note: the default text in the login_top field is "login"
		// for some reason if you call clear() only once the text is not deleted
		// and the username (e.g. "admin) is appended to "login"
		// then the drive tries to login with the username "loginadmin" which
		// obviously fails
		// this can be resolved by calling clear() multiple times
		// Always remember: if it looks stupid, but it works, it ain't stupid!
		driver.findElement(By.id("Bugzilla_login_top")).clear();
		driver.findElement(By.id("Bugzilla_login_top")).clear();
		driver.findElement(By.id("Bugzilla_login_top")).clear();
		driver.findElement(By.id("Bugzilla_login_top")).sendKeys(username);

		// sending Keys.TAB somehow triggers an change event for the
		// Bugzilla_password_top element and makes it visible
		driver.findElement(By.id("Bugzilla_login_top")).sendKeys(Keys.TAB);
		// driver.findElement(By.id("Bugzilla_password_top")).clear();

		driver.findElement(By.id("Bugzilla_password_top")).sendKeys(password);

		driver.findElement(By.id("log_in_top")).click();

		return isLoggedin();
	}

	public StartPage logout() {
		if (isLoggedin()) {
			WebElement logoutLink = driver.findElement(By.linkText("Log out"));
			logoutLink.click();
		}
		return PageFactory.initElements(driver, StartPage.class);
	}

	public StartPage gotoStartPage() {
		homeLink.click();
		return PageFactory.initElements(driver, StartPage.class);
	}

	public CreateBugPage gotoCreateBugPage() {
		newLink.click();
		return PageFactory.initElements(driver, CreateBugPage.class);
	}

	public SearchBasePage gotoSearchBasePage() {
		searchLink.click();
		return PageFactory.initElements(driver, SearchBasePage.class);
	}

	public ReportsBasePage gotoReportsBasePage() {
		reportsLink.click();
		return PageFactory.initElements(driver, ReportsBasePage.class);
	}

	/** Goto login page if logged out. */
	public LoginPage gotoLoginPage() {
		newLink.click();
		return PageFactory.initElements(driver, LoginPage.class);
	}

	public EditBugPage findBug(int bugId) {
		quickFindText.clear();
		quickFindText.sendKeys(String.valueOf(bugId));
		quickFindButton.click();

		return PageFactory.initElements(driver, EditBugPage.class);
	}
	
	public SearchResultsPage performSavedSearch(String savedSearchName) {
		WebElement linkToSavedSearch = driver
				.findElement(By.xpath("//li[@id='links-saved']/ul/li/a[text()='" + savedSearchName + "']"));
		linkToSavedSearch.click();
		return PageFactory.initElements(driver, SearchResultsPage.class);
	}
	
	public ConfigWorkflowPage gotoConfigWorkflowPage() {
		administrationLink.click();
		bugStatusWorkflowLink.click();
		return PageFactory.initElements(driver, ConfigWorkflowPage.class);
	}

}
