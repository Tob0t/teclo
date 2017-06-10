package at.scch.teclo.pageobjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import at.scch.teclo.BugzillaSetup;

public abstract class AbstractBugzillaPage {

	protected final WebDriver driver;

	public AbstractBugzillaPage(WebDriver driver) {
		this.driver = driver;

		if (!matchingPageIsDisplayed()) {
			throw new IllegalStateException("Page object " + this.getClass().getName()
					+ " does not match the displayed page (title: " + driver.getTitle() + ")!");
		}
	}
	
	/** Check that we're on the right page.
	 *  The page object has to match the displayed page, otherwise a navigation error has occurred.
	 *  The default implementation is checking the title of the page matching a given regular expression.
	 *  
	 * @return true if the displayed page fits to this page object.
	 */
	abstract protected boolean matchingPageIsDisplayed();
//		String expectedPageTitleRegex = ".*"; 
//		return driver.getTitle().matches(expectedPageTitleRegex);
//	}
	
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	public StartPage gotoStartPage() {
		return BugzillaSetup.gotoStartPage();
	}
	
	public boolean isLoggedin() {
		return isElementPresent(By.linkText("Log out"));
	}
	
	public String getLoggedinUser() {
		// find the li that contains the link "Log out"
		WebElement liLogoutUser = driver.findElement(By.xpath("//li[a[text()[contains(.,'Log')]]]"));
		String liText = liLogoutUser.getText();
		return liText.substring(liText.lastIndexOf(" ")+1);
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
	
	
	protected boolean isElementPresent(By by) {
		boolean elementFound = false;
		// change the waiting time 0 seconds if the element is not found
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
		try {
			driver.findElement(by);
			elementFound = true;
		} catch (NoSuchElementException e) {
			elementFound = false;
		} finally {
			// change the waiting time back to 10 seconds
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		return elementFound;
	}
}
