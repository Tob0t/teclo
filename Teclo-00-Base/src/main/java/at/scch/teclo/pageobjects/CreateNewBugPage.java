package at.scch.teclo.pageobjects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateNewBugPage extends AbstractLoggedinBugzillaPage {
	
	@FindBy(id = "expert_fields_controller")
	private WebElement linkToggleAdvancedFields;

	@FindBy(id = "rep_platform")
	private WebElement bugPlatform;

	@FindBy(id = "op_sys")
	private WebElement bugOpSys;

	@FindBy(id = "bug_severity")
	private WebElement bugSeverity;
	
	@FindBy(id = "priority")
	private WebElement bugPriority;
	
	@FindBy(id = "bug_status")
	private WebElement bugState;
	
	@FindBy(name = "cc")
	private WebElement bugCC;
	
	@FindBy(name = "short_desc")
	private WebElement bugSummary;

	@FindBy(id = "comment")
	private WebElement bugComment;

	@FindBy(name = "estimated_time")
	private WebElement timeEstimatedTime;
	
	@FindBy(name = "deadline")
	private WebElement timeDeadline;
	
	@FindBy(name = "bug_file_loc")
	private WebElement bugURL;

	@FindBy(id = "commit")
	private WebElement commitButton;

	
	public CreateNewBugPage(WebDriver driver) {
		super(driver);
	}
	
	@Override
	protected boolean matchingPageIsDisplayed() {
		return getTitle().matches("Enter Bug: .*");
	}
	
	
	public void toggleAdvancedFields(){
		linkToggleAdvancedFields.click();
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
	
	public void fillPriority(String priority) {
		new Select(bugPriority).selectByVisibleText(priority);
	}
	
	public void fillCC(String cc) {
		bugCC.clear();
		bugCC.sendKeys(cc);
	}

	public void fillTimeEstimatedTime(double estimatedTime) {
		timeEstimatedTime.clear();
		timeEstimatedTime.sendKeys(String.valueOf(estimatedTime));
	}

	public void fillTimeDeadline(String deadline) {
		timeDeadline.clear();
		timeDeadline.sendKeys(String.valueOf(deadline));
	}
	
	public void fillURL(String url) {
		bugURL.clear();
		bugURL.sendKeys(url);
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

	
	public NewBugCreatedPage createNewBug(String summary, String description) {
		fillSummary(summary);
		fillComment(description);
		commitBug();

		return PageFactory.initElements(driver, NewBugCreatedPage.class);
	}

	
}
