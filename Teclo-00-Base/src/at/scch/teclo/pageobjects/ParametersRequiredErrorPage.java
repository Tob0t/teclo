package at.scch.teclo.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ParametersRequiredErrorPage {
	@FindBy(id = "error_msg")
	private WebElement errorMsg;

	public ParametersRequiredErrorPage(WebDriver driver) {
		// Check that we're on the right page
		if (!("Parameters Required").equals(driver.getTitle())) {
			throw new IllegalStateException(
					"This is not the parameters required error page (Title: " + driver.getTitle() + ")!");
		}
	}

	public String getErrorMsg() {
		return errorMsg.getText();
	}

}