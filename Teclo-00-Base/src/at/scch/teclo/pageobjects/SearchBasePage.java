package at.scch.teclo.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchBasePage {
	private WebDriver driver;

	@FindBy(css = "td.selected")
	private WebElement currentActiveTab;
	
	@FindBy(css = "td.clickable_area")
	private WebElement clickableTab;

	public SearchBasePage(WebDriver driver) {
		this.driver = driver;
		
		// Check that we're on the right page
        if (!(driver.getTitle().matches("Find a Specific Bug|Search for bugs"))) {
        	throw new IllegalStateException("This is not the search base page (Title: "+driver.getTitle()+")!");
        }
	}

	public SpecificSearchPage navigateToSpecificSearchPage() {
		// change tab if the wrong search is pre-selected
		if(currentActiveTab.getText().equals("Advanced Search")){
			clickableTab.click();
		}
		return PageFactory.initElements(driver, SpecificSearchPage.class);
	}

	public AdvancedSearchPage navigateToAdvancedSearchPage() {
		// change tab if the wrong search is pre-selected
		if(currentActiveTab.getText().equals("Find a Specific Bug")){
			clickableTab.click();
		}
		
		return PageFactory.initElements(driver, AdvancedSearchPage.class);
	}
}
