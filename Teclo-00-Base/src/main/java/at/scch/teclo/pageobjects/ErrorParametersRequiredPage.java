package at.scch.teclo.pageobjects;

import org.openqa.selenium.WebDriver;

public class ParametersRequiredErrorPage extends AbstractErrorPage {

	public ParametersRequiredErrorPage(WebDriver driver) {
		super(driver);
	}

	@Override
	protected boolean isMatchingPage() {
		return "Parameters Required".equals(getTitle());
	}

}