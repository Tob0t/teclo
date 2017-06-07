package at.scch.teclo.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SummaryNeededErrorPage {
	@SuppressWarnings("unused")
	private WebDriver driver;

	@FindBy(id = "error_msg")
	private WebElement errorMsg;

	public SummaryNeededErrorPage(WebDriver driver) {
		this.driver = driver;

		// Check that we're on the right page
		if (!("Summary Needed").equals(driver.getTitle())) {
			throw new IllegalStateException(
					"This is not the summary needed error page (Title: " + driver.getTitle() + ")!");
		}
	}

	public String getErrorMsg() {
		return errorMsg.getText();
	}

}