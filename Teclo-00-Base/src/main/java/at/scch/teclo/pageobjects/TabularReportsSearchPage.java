package at.scch.teclo.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class TabularReportsSearchPage extends AbstractLoggedinBugzillaPage {

	@FindBy(name = "x_axis_field")
	private WebElement horizontalAxesField;

	@FindBy(name = "y_axis_field")
	private WebElement verticalAxesField;

	@FindBy(id = "Generate_Report_top")
	private WebElement generateReportButton;

	
	public TabularReportsSearchPage(WebDriver driver) {
		super(driver);
	}

	@Override
	protected boolean matchingPageIsDisplayed() {
		return "Generate Tabular Report".equals(getTitle());
	}
	
	
	public void selectHorizontalAxes(String horizontalValue) {
		new Select(horizontalAxesField).selectByVisibleText(horizontalValue);
	}

	public void selectVeritcalAxes(String verticalValue) {
		new Select(verticalAxesField).selectByVisibleText(verticalValue);
	}

	public TabularReportsResultsPage generateReport() {
		generateReportButton.click();
		return PageFactory.initElements(driver, TabularReportsResultsPage.class);
	}

}
