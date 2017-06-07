package at.scch.teclo.pageobjects;

import org.openqa.selenium.WebDriver;

public class LogInErrorPage {

	private final WebDriver driver;

	public LogInErrorPage(WebDriver driver) {
		this.driver = driver;

		// Check that we're on the right page
		if (!("Invalid Username Or Password").equals(driver.getTitle())) {
			throw new IllegalStateException("This is not the log in error page (Title: " + driver.getTitle() + ")!");
		}
	}

	public String getDriverTitle() {
		return driver.getTitle();
	}

}
