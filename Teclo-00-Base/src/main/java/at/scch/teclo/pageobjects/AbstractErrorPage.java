package at.scch.teclo.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class AbstractErrorPage extends AbstractLoggedinBugzillaPage {
	
	@FindBy(id = "error_msg")
	private WebElement errorMsg;

	
	public AbstractErrorPage(WebDriver driver) {
		super(driver);
	}
	
	public String getErrorMsg() {
		return errorMsg.getText();
	}
}
