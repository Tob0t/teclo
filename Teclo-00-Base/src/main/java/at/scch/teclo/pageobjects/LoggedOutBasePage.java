package at.scch.teclo.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoggedOutBasePage {

	private final WebDriver driver;

	@FindBy(id = "login_link_top")
	private WebElement loginLink;

	@FindBy(id = "Bugzilla_login_top")
	private WebElement loginFieldUsername;

	@FindBy(id = "Bugzilla_password_top")
	private WebElement loginFieldPassword;

	@FindBy(id = "log_in_top")
	private WebElement loginButton;

	public LoggedOutBasePage(WebDriver driver) {
		this.driver = driver;

		// Check that we're on the right page, the title jumps between "Logged
		// Out" and "Bugzilla Main Page" depending on the previous state
		if (!(("Logged Out").equals(driver.getTitle()) || ("Bugzilla Main Page").equals(driver.getTitle()))) {
			throw new IllegalStateException("This is not the logged out page (Title: " + driver.getTitle() + ")!");
		}
	}

	public LogInErrorPage logInWithWrongUsernameAndWrongPassword(String wrongUsername, String wrongPassword) {
		loginLink.click();
		loginFieldUsername.clear();
		loginFieldUsername.sendKeys(wrongUsername);

		// sending Keys.TAB somehow triggers an change event for the
		// Bugzilla_password_top element and makes it visible
		loginFieldUsername.sendKeys(Keys.TAB);
		// driver.findElement(By.id("Bugzilla_password_top")).clear();

		loginFieldPassword.sendKeys(wrongPassword);
		loginButton.click();

		return PageFactory.initElements(driver, LogInErrorPage.class);
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean isLoginLinkTopPresent() {
		return isElementPresent(By.id("login_link_top"));
	}

	public String getDriverTitle() {
		return driver.getTitle();
	}
}
