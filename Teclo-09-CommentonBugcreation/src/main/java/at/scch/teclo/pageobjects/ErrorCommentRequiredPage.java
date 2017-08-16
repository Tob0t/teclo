package at.scch.teclo.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ErrorCommentRequiredPage extends AbstractBugzillaPage {
	
	@FindBy(id = "error_msg")
	private WebElement error_msg;

	public ErrorCommentRequiredPage(WebDriver driver) {
		super(driver);
	}

	@Override
	protected boolean isMatchingPage() {
		return "Comment Required".equals(driver.getTitle());
	}
	
	public String getErrorMsg() {
		return error_msg.getText();
	}

}
