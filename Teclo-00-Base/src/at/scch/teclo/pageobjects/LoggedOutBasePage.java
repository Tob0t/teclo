package at.scch.teclo.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LoggedOutBasePage {

	
	private final WebDriver driver;

	public LoggedOutBasePage(WebDriver driver) {
		this.driver = driver;
	}


	public LogInErrorPage logInWithWrongUsernameAndWrongPassword(String wrongUsername,
			String wrongPassword) {
		driver.findElement(By.id("login_link_top")).click();
		driver.findElement(By.id("Bugzilla_login_top")).clear();
		driver.findElement(By.id("Bugzilla_login_top")).sendKeys(wrongUsername);

		// sending Keys.TAB somehow triggers an change event for the
		// Bugzilla_password_top element and makes it visible
		driver.findElement(By.id("Bugzilla_login_top")).sendKeys(Keys.TAB);
		// driver.findElement(By.id("Bugzilla_password_top")).clear();

		driver.findElement(By.id("Bugzilla_password_top")).sendKeys(wrongPassword);
		driver.findElement(By.id("log_in_top")).click();

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
