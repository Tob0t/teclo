package at.scch.teclo.pageobjects;

import org.openqa.selenium.WebDriver;

public class SummaryNeededErrorPage extends AbstractErrorPage {

	public SummaryNeededErrorPage(WebDriver driver) {
		super(driver);
	}

	@Override
	protected boolean matchingPageIsDisplayed() {
		return "Summary Needed".equals(getTitle());
	}

}