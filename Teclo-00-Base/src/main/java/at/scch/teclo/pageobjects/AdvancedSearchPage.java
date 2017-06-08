package at.scch.teclo.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AdvancedSearchPage {
	private WebDriver driver;

	@FindBy(id = "bug_status")
	private WebElement bugState;

	@FindBy(name = "short_desc_type")
	private WebElement summarySearchType;
	
	@FindBy(id = "short_desc")
	private WebElement summaryTextBox;

	@FindBy(id = "longdesc")
	private WebElement commentTextBox;

	@FindBy(id = "Search")
	private WebElement searchButton;

	@FindBy(name = "field0-0-0")
	private WebElement booleanChartField;

	@FindBy(name = "type0-0-0")
	private WebElement booleanChartType;

	@FindBy(name = "value0-0-0")
	private WebElement booleanChartValue;

	public AdvancedSearchPage(WebDriver driver) {
		this.driver = driver;

		// Check that we're on the right page.
		if (!("Search for bugs").equals(driver.getTitle())) {
			throw new IllegalStateException("This is not the advanced search page (Title: " + driver.getTitle() + ")!");
		}
	}

	public BugResultsPage searchFor(String summaryString, String commentString) {
		summaryTextBox.clear();
		summaryTextBox.sendKeys(summaryString);
		commentTextBox.clear();
		commentTextBox.sendKeys(commentString);
		return submitSearch();
	}

	public void fillSummary(String summaryString) {
		summaryTextBox.clear();
		summaryTextBox.sendKeys(summaryString);
	}

	public void fillComment(String commentString) {
		commentTextBox.clear();
		commentTextBox.sendKeys(commentString);
	}
	
	public void setSummarySearchType(String searchType)	{
		new Select(summarySearchType).selectByVisibleText(searchType);
	}

	public void deselectBugState(String bugStateString) {
		new Select(bugState).deselectByVisibleText(bugStateString);
	}

	public void selectBugState(String bugStateString) {
		new Select(bugState).selectByVisibleText(bugStateString);
	}

	public void fillBooleanChart(String chartField, String chartType, String chartValue) {
		new Select(booleanChartField).selectByVisibleText(chartField);
		new Select(booleanChartType).selectByVisibleText(chartType);
		booleanChartValue.clear();
		booleanChartValue.sendKeys(chartValue);
	}

	public BugResultsPage submitSearch() {
		searchButton.click();
		return PageFactory.initElements(driver, BugResultsPage.class);
	}

}
