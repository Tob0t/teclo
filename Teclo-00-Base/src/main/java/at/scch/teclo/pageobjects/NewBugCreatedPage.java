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
	private WebElement bugURL;
	
	@FindBy(id = "dependson")
	private WebElement bugDependsOn;
	
	@FindBy(id = "blocked")
	private WebElement bugBlocked;

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
	
	@FindBy(id = "comment_text_0")
	private WebElement bugFirstComment;

	public NewBugCreatedPage(WebDriver driver) {
		this.driver = driver;

		// Check that we're on the right page.
		if (!(driver.getTitle().matches("Bug \\d+ Submitted.*"))) {
			throw new IllegalStateException("This is not the new bug created page (Title: " + driver.getTitle() + ")!");
		}
	}

	public int getBugID() {
		return Integer.parseInt(bugTitle.getText().replaceAll("[^0-9]", ""));
	}

	public String getBugWasAddedText() {
		return driver.findElement(By.cssSelector("dt")).getText();
	}

	public String getSummary() {
		return bugSummary.getText();
	}
	
	public String getCurrentProduct() {
		return Helper.getSelectedOptionValue(bugProduct);
	}
	
	public String getCurrentComponent() {
		return bugComponent.getText();
	}
	
	public String getCurrentVersion() {
		return bugVersion.getText();
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

	public String getCurrentBugState() {
		return Helper.getSelectedOptionValue(bugState);
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
	
	public String getCurrentComment(){
		return bugComment.getText();
	}
	
	// Workaround: replacing \n with " " since there is a bug when reading an existing comment
	public String getCommentOfFirstBug(){
		return bugFirstComment.getText().replace("\n", " ");
	}
}
