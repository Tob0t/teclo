package at.scch.teclo.pageobjects;

import org.openqa.selenium.WebDriver;

public class ErrorParametersRequiredPage extends AbstractErrorPage {

	public ErrorParametersRequiredPage(WebDriver driver) {
		super(driver);
	}

	@Override
	protected boolean isMatchingPage() {
		return "Parameters Required".equals(getTitle());
	}

}