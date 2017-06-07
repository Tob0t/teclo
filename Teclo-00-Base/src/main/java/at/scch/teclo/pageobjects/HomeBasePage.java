package at.scch.teclo.pageobjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomeBasePage {

	private final WebDriver driver;
	private LoggedInBasePage loggedInBasePage;

	public HomeBasePage(WebDriver driver) {
		this.driver = driver;

		// Check that we're on the right page.
		if (!("Bugzilla Main Page").equals(driver.getTitle())) {
			throw new IllegalStateException("This is not the bugzilla main page (Title: " + driver.getTitle() + ")!");
		}
	}

	/***
	 * Method to log in (if not already logged in)
	 * 
	 * @return LoggedIn page
	 */
	public LoggedInBasePage login(String username, String password) {
		LogInBasePage logInBasePage = new LogInBasePage(driver);

		// change the waiting time 0 seconds if the element is not found
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
		try {
			// check if the log in button is existing
			if (driver.findElements(By.linkText("Log In")).isEmpty()) {
				throw new IllegalStateException("No 'Log In' button found!");
			}

			loggedInBasePage = logInBasePage.login(username, password);
		} finally {
			// change the waiting time back to 10 seconds
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}

		return PageFactory.initElements(driver, LoggedInBasePage.class);
	}

	/***
	 * Method to log out the current user (in not already logged out)
	 * 
	 * @param loggedOut
	 *            page
	 */
	public LoggedOutBasePage logout() {
		// change the waiting time 0 seconds if the element is not found
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);

		try {
			if (loggedInBasePage == null) {
				throw new IllegalStateException("Not logged in via this page object!");
			}

			// check if the log out button is existing
			if (driver.findElements(By.linkText("Log out")).isEmpty()) {
				throw new IllegalStateException("No 'Log out' button found!");
			}

			loggedInBasePage.logOut();
		} finally {
			// change the waiting time back to 10 seconds
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}

		return PageFactory.initElements(driver, LoggedOutBasePage.class);
	}

	public LogInBasePage navigateToLoginBasePage() {
		return PageFactory.initElements(driver, LogInBasePage.class);
	}

}
