package at.scch.teclo.pageobjects;

import org.openqa.selenium.By;
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
	
	@FindBy(id = "bug_file_loc")
	private WebElement bugURL;
	
	@FindBy(id = "bz_url_edit_action")
	private WebElement bugEditURLLink;
	
	@FindBy(id = "dependson")
	private WebElement bugDependsOn;
	
	@FindBy(id = "blocked_edit_action")
	private WebElement bugEditBlocksLink;
	
	@FindBy(id = "blocked")
	private WebElement bugBlocked;

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

	@FindBy(xpath = "//div[@id='bugzilla-body']/form/table[2]/tbody/tr[2]/td[2]")
	private WebElement timeCurrentEstimation;

	@FindBy(xpath = "//div[@id='bugzilla-body']/form/table[2]/tbody/tr[2]/td[3]")
	private WebElement timeWorkTimeReadOnly;
	
	@FindBy(id = "work_time")
	private WebElement timeWorkTime;

	@FindBy(id = "remaining_time")
	private WebElement timeRemainingTime;
	
	@FindBy(xpath = "//div[@id='bugzilla-body']/form/table[2]/tbody/tr[2]/td[5]")
	private WebElement timeCompletedInPercent;
	
	@FindBy(xpath = "//div[@id='bugzilla-body']/form/table[2]/tbody/tr[2]/td[6]")
	private WebElement timeGain;

	@FindBy(id = "deadline")
	private WebElement timeDeadline;

	@FindBy(id = "bug_status")
	private WebElement bugState;
	
	@FindBy(id = "resolution")
	private WebElement bugResolution;
	
	@FindBy(id = "dup_id_discoverable_action")
	private WebElement markAsDuplicateLink;
	
	@FindBy(id = "dup_id")
	private WebElement bugDuplicateID;

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
			throw new IllegalStateException("This is not the edit bug page (Title: " + driver.getTitle() + ")!");
		}
	}

	public void editSummary(String summary) {
		editBugSummaryLink.click();
		bugSummaryEdit.clear();
		bugSummaryEdit.sendKeys(summary);
	}

	public void editPlatform(String platform) {
		new Select(bugPlatform).selectByVisibleText(platform);
	}

	public void editOpSys(String opSys) {
		new Select(bugOpSys).selectByVisibleText(opSys);
	}

	public void editPriority(String priority) {
		new Select(bugPriority).selectByVisibleText(priority);
	}

	public void editSeverity(String severity) {
		new Select(bugSeverity).selectByVisibleText(severity);
	}
	
	public void editURL(String url) {
		bugURL.clear();
		bugURL.sendKeys(url);
	}
	
	public WebElement verifyURL(String url){
		return driver.findElement(By.linkText(url));
	}
	
	public void clearURL() {
		bugEditURLLink.click();
		bugURL.clear();
	}
	
	public void editDependsOn(int bugID) {
		bugDependsOn.click();
		bugDependsOn.sendKeys(String.valueOf(bugID));
	}
	
	public WebElement verifyDependsOn(int bugID){
		return driver.findElement(By.linkText(String.valueOf(bugID)));
	}
	
	public EditBugPage clickDependsOn(int bugID) {
		driver.findElement(By.linkText(String.valueOf(bugID))).click();
		return PageFactory.initElements(driver, EditBugPage.class);
	}
	
	public void clearBlocksOn() {
		bugEditBlocksLink.click();
		bugBlocked.clear();
	}
	
	public WebElement verifyBlocksOn(int bugID){
		return driver.findElement(By.linkText(String.valueOf(bugID)));
	}
	
	public void editTimeEstimatedTime(double estimatedTime) {
		timeEstimatedTime.clear();
		timeEstimatedTime.sendKeys(String.valueOf(estimatedTime));
	}

	public void editTimeWorkTime(double workTime) {
		timeWorkTime.clear();
		timeWorkTime.sendKeys(String.valueOf(workTime));
	}

	public void editTimeRemainigTime(double remainingTime) {
		timeRemainingTime.clear();
		timeRemainingTime.sendKeys(String.valueOf(remainingTime));
	}

	public void editTimeDeadline(String deadline) {
		timeDeadline.clear();
		timeDeadline.sendKeys(String.valueOf(deadline));
	}

	public void editComment(String comment) {
		bugComment.clear();
		bugComment.sendKeys(comment);
	}

	public void changeBugState(String bugStateString) {
		new Select(bugState).selectByVisibleText(bugStateString);
	}

	public String getSummary() {
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
	
	public String getCurrentURL() {
		return bugURL.getAttribute("value");
	}
	
	public String getCurrentDependsOn() {
		return bugDependsOn.getText();
	}
	
	public String getCurrentBlocks() {
		return bugBlocked.getText();
	}

	public String getTimeEstimatedTime() {
		return timeEstimatedTime.getAttribute("value");
	}
	
	public String getTimeCurrentEstimation() {
		return timeCurrentEstimation.getText();
	}

	public String getTimeWorkTime() {
		return timeWorkTimeReadOnly.getText();
	}

	public String getTimeRemainingTime() {
		return timeRemainingTime.getAttribute("value");
	}
	
	public String getTimeCompletedInPercent() {
		return timeCompletedInPercent.getText();
	}
	
	public String getTimeGain() {
		return timeGain.getText();
	}

	public String getTimeDeadline() {
		return timeDeadline.getAttribute("value");
	}
	
	public String getCurrentBugState() {
		return Helper.getSelectedOptionValue(bugState);
	}
	
	public String getCurrentBugResolution() {
		return Helper.getSelectedOptionValue(bugResolution);
	}
	
	public String getCurrentComment(){
		return bugComment.getText();
	}
	
	public void clickMarkAsDuplicate(){
		markAsDuplicateLink.click();
	}
	
	public void editBugDuplicateOf(int bugID){
		bugDuplicateID.clear();
		bugDuplicateID.sendKeys(String.valueOf(bugID));
	}

	public BugCommittedPage commitBug() {
		commitButton.click();
		return PageFactory.initElements(driver, BugCommittedPage.class);
	}

	public SummaryNeededErrorPage commitBugWithEmptySummary() {
		commitButton.click();
		return PageFactory.initElements(driver, SummaryNeededErrorPage.class);
	}
	
	/**
	 * Get the amount of comments
	 * @return number of comments
	 */
	public int getAmountOfComments(){
		// this returns one element too less since the very first comment is containing two classes ('bz_comment' and 'bz_first_comment')
		// return driver.findElements(By.xpath("//div[@class='bz_comment']")).size();
		
		// Get the amount of div-elements with the class containing 'bz_comment' 
		return driver.findElements(By.xpath("//div[contains(concat(' ', @class, ' '), ' bz_comment ')]")).size();	
	}
	
	/**
	 * Returns the content of the last comment
	 * @return content of the last comment
	 */
	public String getLastCommentContent(){
		int lastCommentID = driver.findElements(By.xpath("//div[contains(concat(' ', @class, ' '), ' bz_comment ')]")).size()-1;
		return driver.findElement(By.id("comment_text_"+lastCommentID)).getText();
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
