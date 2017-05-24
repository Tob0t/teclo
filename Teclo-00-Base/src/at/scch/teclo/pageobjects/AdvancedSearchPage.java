package at.scch.teclo.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AdvancedSearchPage {
	private WebDriver driver;
	
	@FindBy(id="bug_status")
	private WebElement bugState;
	
	@FindBy(id="Search")
	private WebElement searchButton;
	
	@FindBy(name="field0-0-0")
	private WebElement booleanChartField;
	
	@FindBy(name="type0-0-0")
	private WebElement booleanChartType;
	
	@FindBy(name="value0-0-0")
	private WebElement booleanChartValue;
	
	public AdvancedSearchPage(WebDriver driver){
		this.driver = driver;
	}
	
	public void deselectBugState(String bugStateString){
		new Select(bugState).deselectByVisibleText(bugStateString);
	}
	
	public void selectBugState(String bugStateString){
		new Select(bugState).selectByVisibleText(bugStateString);
	}
	
	public void fillBooleanChart(String chartField, String chartType, String chartValue){
		new Select(booleanChartField).selectByVisibleText(chartField);
		new Select(booleanChartType).selectByVisibleText(chartType);
		booleanChartValue.clear();
		booleanChartValue.sendKeys(chartValue);
	}
	
	public MyBugsPage search(){
		searchButton.click();	
		return PageFactory.initElements(driver, MyBugsPage.class);
	}

}
