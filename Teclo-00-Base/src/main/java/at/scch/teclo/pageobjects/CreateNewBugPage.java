package at.scch.teclo.pageobjects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateNewBugPage {

	private final WebDriver driver;

	@FindBy(name = "short_desc")
	private WebElement bugSummary;

	@FindBy(id = "rep_platform")
	private WebElement bugPlatform;

	@FindBy(id = "op_sys")
	private WebElement bugOpSys;

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

	public CreateNewBugPage(WebDriver driver) {
		this.driver = driver;

		// Check that we're on the right page.
		if (!(driver.getTitle().matches("Enter Bug: .*"))) {
			throw new IllegalStateException("This is not the create new bug page (Title: " + driver.getTitle() + ")!");
		}
	}

	public void fillSummary(String summary) {
		bugSummary.clear();
		bugSummary.sendKeys(summary);
	}

	public void fillPlatform(String platform) {
		new Select(bugPlatform).selectByVisibleText(platform);
	}

	public void fillOpSys(String opSys) {
		new Select(bugOpSys).selectByVisibleText(opSys);
	}

	public void fillSeverity(String severity) {
		new Select(bugSeverity).selectByVisibleText(severity);
	}

	public void fillTimeEstimatedTime(double estimatedTime) {
		timeEstimatedTime.clear();
		timeEstimatedTime.sendKeys(String.valueOf(estimatedTime));
	}

	public void fillTimeWorkTime(double workTime) {
		timeWorkTime.clear();
		timeWorkTime.sendKeys(String.valueOf(workTime));
	}

	public void fillTimeRemainigTime(double remainingTime) {
		timeRemainingTime.clear();
		timeRemainingTime.sendKeys(String.valueOf(remainingTime));
	}

	public void fillTimeDeadline(String deadline) {
		timeDeadline.clear();
		timeDeadline.sendKeys(String.valueOf(deadline));
	}

	public void fillComment(String comment) {
		bugComment.clear();
		bugComment.sendKeys(comment);
	}

	public void changeBugState(String bugStateString) {
		new Select(bugState).selectByVisibleText(bugStateString);
	}

	public NewBugCreatedPage commitBug() {
		commitButton.click();
		return PageFactory.initElements(driver, NewBugCreatedPage.class);
	}
	
	/** Summary is empty, commit results in alert popup. */
	public String commitBugWithEmptySummary() {
		String alertText;
		
		bugSummary.clear();
		commitButton.click();
		
		Alert alert = driver.switchTo().alert();
		alertText = alert.getText();
		alert.accept();
		driver.switchTo().defaultContent();

		return alertText;
	}

	public NewBugCreatedPage createNewBugSimple(String name) {
		bugSummary.clear();
		bugSummary.sendKeys(name);
		commitButton.click();

		return PageFactory.initElements(driver, NewBugCreatedPage.class);
	}
}
