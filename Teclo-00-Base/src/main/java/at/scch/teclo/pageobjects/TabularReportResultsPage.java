package at.scch.teclo.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TabularReportResultsPage extends AbstractLoggedinBugzillaPage {
	
	@FindBy(xpath = "//div[@id='bugzilla-body']/div/table/tbody/tr/td[2]/strong")
	private WebElement horizontalAxisLabel;

	@FindBy(xpath = "//div[@id='bugzilla-body']/div/table/tbody/tr[2]/td/strong")
	private WebElement verticalAxisLabel;

	
	public TabularReportResultsPage(WebDriver driver) {
		super(driver);
	}
	
	@Override
	protected boolean isMatchingPage() {
		return getTitle().matches("Report: .+");
	}
	

	public String getHorizontalAxisLabel() {
		return horizontalAxisLabel.getText();
	}

	public String getVerticalAxisLabel() {
		return verticalAxisLabel.getText();
	}

}
