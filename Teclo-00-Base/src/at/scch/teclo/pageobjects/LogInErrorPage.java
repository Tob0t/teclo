package at.scch.teclo.pageobjects;

import static org.junit.Assert.assertEquals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LogInErrorPage {

	private final WebDriver driver;

	public LogInErrorPage(WebDriver driver) {
		this.driver = driver;
	}

	public String getDriverTitle() {
		return driver.getTitle();
	}

}
