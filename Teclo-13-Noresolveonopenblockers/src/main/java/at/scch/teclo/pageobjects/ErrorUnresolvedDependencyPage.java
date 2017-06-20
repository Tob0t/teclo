package at.scch.teclo.pageobjects;

import org.openqa.selenium.WebDriver;

public class ErrorUnresolvedDependencyPage extends AbstractErrorPage {

	public ErrorUnresolvedDependencyPage(WebDriver driver) {
		super(driver);
	}

	@Override
	protected boolean isMatchingPage() {
		return "Error".equals(getTitle());
	}

}