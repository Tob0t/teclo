package at.scch.teclo.pageobjects;

import org.openqa.selenium.WebDriver;

public class LoginErrorPage extends AbstractErrorPage {

	public LoginErrorPage(WebDriver driver) {
		super(driver);
	}

	@Override
	protected boolean matchingPageIsDisplayed() {
		return "Invalid Username Or Password".equals(getTitle());
	}

}
