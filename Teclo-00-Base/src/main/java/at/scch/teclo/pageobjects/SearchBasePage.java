package at.scch.teclo.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchBasePage extends AbstractLoggedinBugzillaPage {

	@FindBy(css = "td.selected")
	private WebElement currentActiveTab;

	@FindBy(css = "td.clickable_area")
	private WebElement clickableTab;

	
	public SearchBasePage(WebDriver driver) {
		super(driver);
	}
	
	@Override
	protected boolean isMatchingPage() {
		return getTitle().matches("Find a Specific Bug|Search for bugs");
	}
	

	public SearchSpecificPage gotoSpecificSearchPage() {
		// change tab if the wrong search is pre-selected
		if (currentActiveTab.getText().equals("Advanced Search")) {
			clickableTab.click();
		}
		return PageFactory.initElements(driver, SearchSpecificPage.class);
	}

	public SearchAdvancedPage gotoAdvancedSearchPage() {
		// change tab if the wrong search is pre-selected
		if (currentActiveTab.getText().equals("Find a Specific Bug")) {
			clickableTab.click();
		}
		return PageFactory.initElements(driver, SearchAdvancedPage.class);
	}
}
