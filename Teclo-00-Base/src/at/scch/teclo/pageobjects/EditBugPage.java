package at.scch.teclo.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import at.scch.teclo.Helper;

public class EditBugPage {

	private final WebDriver driver;

	@FindBy(id = "short_desc")
	private WebElement bugSummaryEdit;
	
	@FindBy(id = "editme_action")
	private WebElement editBugSummaryLink;
	
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

	@FindBy(id = "commit")
	private WebElement commitButton;
	
	@FindBy(linkText = "Home")
	private WebElement homeLink;
	
	@FindBy(linkText = "My Bugs")
	private WebElement myBugsLink;

	public EditBugPage(WebDriver driver) {
		this.driver = driver;
		
		// Check that we're on the right page.
        if (!(driver.getTitle().matches("Bug \\d+ .*"))) {
        	throw new IllegalStateException("This is not the edit bug page (Title: "+driver.getTitle()+")!");
        }
	}

	public void editSummary(String summary){
		editBugSummaryLink.click();
		bugSummaryEdit.clear();
		bugSummaryEdit.sendKeys(summary);
	}
	
	public void editPlatform(String platform){
		new Select(bugPlatform).selectByVisibleText(platform);
	}
	
	public void editOpSys(String opSys){
		new Select(bugOpSys).selectByVisibleText(opSys);
	}
	
	public void editPriority(String priority){
		new Select(bugPriority).selectByVisibleText(priority);
	}
	
	public void editSeverity(String severity){
		new Select(bugSeverity).selectByVisibleText(severity);
	}
	
	public void editTimeEstimatedTime(double estimatedTime){
		timeEstimatedTime.clear();
		timeEstimatedTime.sendKeys(String.valueOf(estimatedTime));
	}
	
	public void editTimeWorkTime(double workTime){
		timeWorkTime.clear();
		timeWorkTime.sendKeys(String.valueOf(workTime));
	}
	
	public void editTimeRemainigTime(double remainingTime){
		timeRemainingTime.clear();
		timeRemainingTime.sendKeys(String.valueOf(remainingTime));
	}
	
	public void editTimeDeadline(String deadline){
		timeDeadline.clear();
		timeDeadline.sendKeys(String.valueOf(deadline));
	}
	
	public void editComment(String comment){
		bugComment.clear();
		bugComment.sendKeys(comment);
	}
	
	public void changeBugState(String bugStateString) {
		new Select(bugState).selectByVisibleText(bugStateString);
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

	public BugCommitedPage commitBug() {
		commitButton.click();
		return PageFactory.initElements(driver, BugCommitedPage.class);
	}
	
	public SummaryNeededErrorPage commitBugWithEmptySummary() {
		commitButton.click();
		return PageFactory.initElements(driver, SummaryNeededErrorPage.class);
	}

	public LoggedInBasePage navigateToMyHomePage() {
		homeLink.click();
		return PageFactory.initElements(driver, LoggedInBasePage.class);
	}

	public BugResultsPage navigateToBugResultsPage() {
		myBugsLink.click();
		return PageFactory.initElements(driver, BugResultsPage.class);
	}

}
