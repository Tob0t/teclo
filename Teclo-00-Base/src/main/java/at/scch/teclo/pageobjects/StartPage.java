package at.scch.teclo.pageobjects;

import org.openqa.selenium.WebDriver;

public class StartPage extends AbstractBugzillaPage {

	public StartPage(WebDriver driver) {
		super(driver);
	}

	@Override
	protected boolean matchingPageIsDisplayed() {
		String expectedPageTitleRegex = ".*"; 
		
		return driver.getTitle().matches(expectedPageTitleRegex);
	}

}
