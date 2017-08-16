package at.scch.teclo.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SearchAdvancedPage extends SearchBasePage {

	@FindBy(id = "bug_status")
	private WebElement bugStatus;
	
	@FindBy(name = "version")
	private WebElement bugVersion;

	@FindBy(name = "short_desc_type")
	private WebElement summarySearchType;

	@FindBy(id = "short_desc")
	private WebElement summaryText;

	@FindBy(id = "longdesc")
	private WebElement commentText;

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
		summaryText.clear();
		summaryText.sendKeys(summaryString);
		commentText.clear();
		commentText.sendKeys(commentString);
		return submitSearch();
	}

	public void setSummary(String summaryString) {
		summaryText.clear();
		summaryText.sendKeys(summaryString);
	}

	public void setComment(String commentString) {
		commentText.clear();
		commentText.sendKeys(commentString);
	}

	public void setSummarySearchType(String searchType) {
		new Select(summarySearchType).selectByVisibleText(searchType);
	}

	public void unsetBugStatus(String bugStatusString) {
		new Select(bugStatus).deselectByVisibleText(bugStatusString);
	}

	public void setBugStatus(String bugStatusString) {
		new Select(bugStatus).selectByVisibleText(bugStatusString);
	}
	
	public void setBugVersion(String versionName){
		new Select(bugVersion).selectByVisibleText(versionName);
	}

	public void setBooleanChart(String chartField, String chartType, String chartValue) {
		new Select(booleanChartField).selectByVisibleText(chartField);
		new Select(booleanChartType).selectByVisibleText(chartType);
		booleanChartValue.clear();
		booleanChartValue.sendKeys(chartValue);
	}

	public SearchResultsPage submitSearch() {
		searchButton.click();
		return PageFactory.initElements(driver, SearchResultsPage.class);
	}

}
