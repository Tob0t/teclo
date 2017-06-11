package at.scch.teclo.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import at.scch.teclo.Helper;

public class EditBugPage extends AbstractLoggedinBugzillaPage {
	
	@FindBy(id = "short_desc_nonedit_display")
	private WebElement bugSummary;
	
	@FindBy(id = "editme_action")
	private WebElement editBugSummaryLink;

	@FindBy(id = "short_desc")
	private WebElement bugSummaryEdit;

	
	@FindBy(id = "product")
	private WebElement bugProduct;
	
	@FindBy(xpath = "//td[@id='bz_show_bug_column_1']/table/tbody/tr[4]/td[2]")
	private WebElement bugComponent;
	
	@FindBy(xpath = "//td[@id='bz_show_bug_column_1']/table/tbody/tr[5]/td[2]")
	private WebElement bugVersion;

	
	@FindBy(id = "rep_platform")
	private WebElement bugPlatform;
	
	@FindBy(id = "op_sys")
	private WebElement bugOpSys;
	
	@FindBy(id = "priority")
	private WebElement bugPriority;
	
	@FindBy(id = "bug_severity")
	private WebElement bugSeverity;
	
	@FindBy(id = "bug_file_loc")
	private WebElement bugUrl;
	
	@FindBy(id = "bz_url_edit_action")
	private WebElement bugEditUrlLink;

	
	@FindBy(id = "dependson")
	private WebElement bugDependsOn;
	
	@FindBy(id = "dependson_edit_action")
	private WebElement bugEditDependsOnLink;	
	
	@FindBy(id = "blocked")
	private WebElement bugBlocked;
	
	@FindBy(id = "blocked_edit_action")
	private WebElement bugEditBlockedLink;


	@FindBy(id = "estimated_time")
	private WebElement timeEstimated;

	@FindBy(xpath = "//div[@id='bugzilla-body']/form/table[2]/tbody/tr[2]/td[2]")
	private WebElement timeCurrentEstimation;

	@FindBy(xpath = "//div[@id='bugzilla-body']/form/table[2]/tbody/tr[2]/td[3]")
	private WebElement timeWorkTimeCompleted;
	
	@FindBy(id = "work_time")
	private WebElement timeWork;

	@FindBy(id = "remaining_time")
	private WebElement timeRemaining;
	
	@FindBy(xpath = "//div[@id='bugzilla-body']/form/table[2]/tbody/tr[2]/td[5]")
	private WebElement timeCompletedInPercent;
	
	@FindBy(xpath = "//div[@id='bugzilla-body']/form/table[2]/tbody/tr[2]/td[6]")
	private WebElement timeGain;

	@FindBy(id = "deadline")
	private WebElement timeDeadline;

	
	@FindBy(id = "comment")
	private WebElement bugComment;
	
	@FindBy(id = "bug_status")
	private WebElement bugStatus;
	
	@FindBy(id = "resolution")
	private WebElement bugResolution;
	
	@FindBy(id = "dup_id_discoverable_action")
	private WebElement markAsDuplicateLink;
	
	@FindBy(id = "dup_id")
	private WebElement bugDuplicateId;

	
	@FindBy(id = "commit")
	private WebElement commitButton;

	
	@FindBy(id = "comment_text_0")
	private WebElement bugFirstComment;
	
	
	@FindBy(linkText = "Home")
	private WebElement homeLink;

	@FindBy(linkText = "My Bugs")
	private WebElement myBugsLink;

	
	public EditBugPage(WebDriver driver) {
		super(driver);
	}

	@Override
	protected boolean isMatchingPage() {
		return getTitle().matches("Bug \\d+ .*");
	}
	
	
	public String getSummary() {
		return bugSummary.getText();
	}
	
	public void setSummary(String summary) {
		editBugSummaryLink.click();
		bugSummaryEdit.clear();
		bugSummaryEdit.sendKeys(summary);
	}

	
	public String getProduct() {
		return Helper.getSelectedOptionValue(bugProduct);
	}
	
	public void setProduct(String product) {
		new Select(bugProduct).selectByVisibleText(product);
	}
	
	public String getComponent() {
		return bugComponent.getText();
	}
	
	public String getVersion() {
		return bugVersion.getText();
	}
	
	
	public String getPlatform() {
		return Helper.getSelectedOptionValue(bugPlatform);
	}
	
	public void setPlatform(String platform) {
		new Select(bugPlatform).selectByVisibleText(platform);
	}

	public String getOpSys() {
		return Helper.getSelectedOptionValue(bugOpSys);
	}

	public void setOpSys(String opSys) {
		new Select(bugOpSys).selectByVisibleText(opSys);
	}

	public String getPriority() {
		return Helper.getSelectedOptionValue(bugPriority);
	}

	public void setPriority(String priority) {
		new Select(bugPriority).selectByVisibleText(priority);
	}

	public String getSeverity() {
		return Helper.getSelectedOptionValue(bugSeverity);
	}
	
	public void setSeverity(String severity) {
		new Select(bugSeverity).selectByVisibleText(severity);
	}

	
	public String getUrl() {
		return bugUrl.getAttribute("value");
	}
	
	public void setUrl(String url) {
		if (!bugUrl.isDisplayed()) {
			bugEditUrlLink.click();
		}
		bugUrl.clear();
		bugUrl.sendKeys(url);
	}
	
	
	public String getDependsOn() {
		return bugDependsOn.getAttribute("value");		
	}
	
	public void setDependsOn(int bugId) {
		if (!bugDependsOn.isDisplayed()) {
			bugEditDependsOnLink.click();
		}		
		bugDependsOn.clear();
		bugDependsOn.sendKeys(String.valueOf(bugId));
	}
	
	public String getBlocks() {
		return bugBlocked.getAttribute("value");
	}
	
	public void setBlocks(String bugId) {
		if (!bugBlocked.isDisplayed()) {
			bugEditBlockedLink.click();
		}		
		bugBlocked.clear();
		bugBlocked.sendKeys(String.valueOf(bugId));		
	}

	
	public String getTimeEstimated() {
		return timeEstimated.getAttribute("value");
	}
	
	public void setTimeEstimated(double estimatedTime) {
		timeEstimated.clear();
		timeEstimated.sendKeys(String.valueOf(estimatedTime));
	}
	
	public String getTimeCurrentEstimation() {
		return timeCurrentEstimation.getText();
	}

	public String getTimeWorkCompleted() {
		return timeWorkTimeCompleted.getText();
	}
	
	public String getTimeWorked() {
		return timeWork.getAttribute("value");
	}
	
	public void setTimeWorked(double workTime) {
		timeWork.clear();
		timeWork.sendKeys(String.valueOf(workTime));
	}

	public String getTimeHoursLeft() {
		return timeRemaining.getAttribute("value");
	}
	
	public void setTimeHoursLeft(double remainingTime) {
		timeRemaining.clear();
		timeRemaining.sendKeys(String.valueOf(remainingTime));
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

	public void setTimeDeadline(String deadline) {
		timeDeadline.clear();
		timeDeadline.sendKeys(String.valueOf(deadline));
	}


	public String getComment() {
		return bugComment.getText();
	}
	
	public void setComment(String comment) {
		bugComment.clear();
		bugComment.sendKeys(comment);
	}

	
	public String getBugStatus() {
		return Helper.getSelectedOptionValue(bugStatus);
	}
	
	public void setBugStatus(String bugStatusName) {
		new Select(bugStatus).selectByVisibleText(bugStatusName);
	}
	
	public String getBugResolution() {
		return Helper.getSelectedOptionValue(bugResolution);
	}
	
	public void setBugResolution(String bugResolutionName) {
		new Select(bugResolution).selectByVisibleText(bugResolutionName);
	}

	public String getBugDuplicateOf(){
		return bugDuplicateId.getAttribute("value");
	}
	
	public void setBugDuplicateOf(int bugId){
		bugDuplicateId.clear();
		bugDuplicateId.sendKeys(String.valueOf(bugId));
	}

	public void clickMarkAsDuplicate(){
			markAsDuplicateLink.click();
	}

	
	public BugChangedPage commitBug() {
		commitButton.click();
		return PageFactory.initElements(driver, BugChangedPage.class);
	}

	public ErrorSummaryNeededPage commitBugWithEmptySummary() {
		commitButton.click();
		return PageFactory.initElements(driver, ErrorSummaryNeededPage.class);
	}
	
	
	public String getFirstComment(){
		return bugFirstComment.getText().replace("\n", " ");
	}
	
	/**
	 * Get the amount of comments.
	 * @return number of comments
	 */
	public int getNumberOfComments(){
		// this returns one element too less since the very first comment is containing two classes ('bz_comment' and 'bz_first_comment')
		// return driver.findElements(By.xpath("//div[@class='bz_comment']")).size();
		
		// Get the amount of div-elements with the class containing 'bz_comment' 
		return driver.findElements(By.xpath("//div[contains(concat(' ', @class, ' '), ' bz_comment ')]")).size();	
	}
	
	/**
	 * Returns the content of the last comment.
	 * @return content of the last comment
	 */
	public String getLastComment(){
		int lastCommentId = driver.findElements(By.xpath("//div[contains(concat(' ', @class, ' '), ' bz_comment ')]")).size()-1;
		return driver.findElement(By.id("comment_text_"+lastCommentId)).getText().replace("\n", " ");
	}
	
}
