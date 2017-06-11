package at.scch.teclo.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SearchAdvancedPage extends SearchBasePage {

	@FindBy(id = "bug_status")
	private WebElement bugStatus;

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

	
	public SearchAdvancedPage(WebDriver driver) {
		super(driver);
	}
	
	@Override
	protected boolean isMatchingPage() {
		return "Search for bugs".equals(driver.getTitle());
	}
	

	public SearchResultsPage searchFor(String summaryString, String commentString) {
		summaryTextBox.clear();
		summaryTextBox.sendKeys(summaryString);
		commentTextBox.clear();
		commentTextBox.sendKeys(commentString);
		return submit();
	}

	public void fillSummary(String summaryString) {
		summaryTextBox.clear();
		summaryTextBox.sendKeys(summaryString);
	}

	public void fillComment(String commentString) {
		commentTextBox.clear();
		commentTextBox.sendKeys(commentString);
	}

	public void setSummarySearchType(String searchType) {
		new Select(summarySearchType).selectByVisibleText(searchType);
	}

	public void deselectBugStatus(String bugStatusString) {
		new Select(bugStatus).deselectByVisibleText(bugStatusString);
	}

	public void selectBugStatus(String bugStatusString) {
		new Select(bugStatus).selectByVisibleText(bugStatusString);
	}

	public void fillBooleanChart(String chartField, String chartType, String chartValue) {
		new Select(booleanChartField).selectByVisibleText(chartField);
		new Select(booleanChartType).selectByVisibleText(chartType);
		booleanChartValue.clear();
		booleanChartValue.sendKeys(chartValue);
	}

	public SearchResultsPage submit() {
		searchButton.click();
		return PageFactory.initElements(driver, SearchResultsPage.class);
	}

}
