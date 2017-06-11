package at.scch.teclo.pageobjects;

import org.openqa.selenium.WebDriver;

public class ErrorLoginPage extends AbstractErrorPage {

	public ErrorLoginPage(WebDriver driver) {
		super(driver);
	}

	@Override
	protected boolean isMatchingPage() {
		return "Invalid Username Or Password".equals(getTitle());
	}

}
