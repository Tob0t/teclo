package at.scch.teclo.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import at.scch.teclo.Helper;

public class NewBugCreatedPage {
	
	private final WebDriver driver;

	@FindBy(id = "title")
	private WebElement bugTitle;
	
	@FindBy(id = "short_desc_nonedit_display")
	private WebElement bugSummary;
	
	@FindBy(id = "rep_platform")
	private WebElement bugPlatform;
	
	@FindBy(id = "op_sys")
	private WebElement bugOpSys;
	
	@FindBy(id = "priority")
	private WebElement bugPriority;

	@FindBy(id = "bug_severity")
	private WebElement bugSeverity;
	
	@FindBy(id = "comment")
	private WebElement bugComment;
	
	@FindBy(id = "estimated_time")
	private WebElement timeEstimatedTime;
	
	@FindBy(id = "work_time")
	private WebElement timeWorkTime;
	
	@FindBy(xpath = "//div[@id='bugzilla-body']/form/table[2]/tbody/tr[2]/td[3]")
	private WebElement timeWorkTimeReadOnly;
	
	@FindBy(id = "remaining_time")
	private WebElement timeRemainingTime;
	
	@FindBy(id = "deadline")
	private WebElement timeDeadline;
	
	@FindBy(id = "bug_status")
	private WebElement bugState;
	
	public NewBugCreatedPage(WebDriver driver) {
		this.driver = driver;
		
		// Check that we're on the right page.
        if (!(driver.getTitle().matches("Bug \\d+ Submitted.*"))) {
        	throw new IllegalStateException("This is not the new bug created page (Title: "+driver.getTitle()+")!");
        }
	}

	public int getBugID() {
		return Integer.parseInt(bugTitle.getText().replaceAll("[^0-9]", ""));
	}

	public String getBugWasAddedText() {
		return driver.findElement(By.cssSelector("dt")).getText();
	}
	
	public String getSummary(){
		return bugSummary.getText();
	}
	
	public String getCurrentPlatform() {
		return Helper.getSelectedOptionValue(bugPlatform);
	}
	
	public String getCurrentOpSys() {
		return Helper.getSelectedOptionValue(bugOpSys);
	}
	
	public String getCurrentPriority() {
		return Helper.getSelectedOptionValue(bugPriority);
	}
	
	public String getCurrentSeverity() {
		return Helper.getSelectedOptionValue(bugSeverity);
	}

	public String getCurrentBugState() {
		return Helper.getSelectedOptionValue(bugState);
	}
	
	public String getTimeEstimatedTime(){
		return timeEstimatedTime.getAttribute("value");
	}
	
	public String getTimeWorkTime(){
		return timeWorkTimeReadOnly.getText();
	}
	
	public String getTimeRemainingTime(){
		return timeRemainingTime.getAttribute("value");
	}
	
	public String getTimeDeadline(){
		return timeDeadline.getAttribute("value");
	}
}
