package at.scch.teclo.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReportsBasePage {

	private WebDriver driver;

	@FindBy(linkText = "Tabular reports")
	private WebElement TabularReportsLink;

	public ReportsBasePage(WebDriver driver) {
		this.driver = driver;
		
		// Check that we're on the right page
        if (!("Reporting and Charting Kitchen").equals(driver.getTitle())) {
        	throw new IllegalStateException("This is not the reports base page (Title: "+driver.getTitle()+")!");
        }
	}

	public TabularReportsSearchPage navigateToTabularReportsPage() {
		TabularReportsLink.click();
		return PageFactory.initElements(driver, TabularReportsSearchPage.class);
	}

}
