package at.scch.teclo.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StartPage extends AbstractBugzillaPage {
	
	public StartPage(WebDriver driver) {
		super(driver);
	}

	@Override
	protected boolean isMatchingPage() {
		String expectedPageTitleRegex = ".*"; 
		
		return driver.getTitle().matches(expectedPageTitleRegex);
	}

}
