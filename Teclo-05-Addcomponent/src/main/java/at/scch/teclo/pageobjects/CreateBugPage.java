package at.scch.teclo.pageobjects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateBugPage extends AbstractLoggedinBugzillaPage {
	
	@FindBy(id = "expert_fields_controller")
	private WebElement linkToggleAdvancedFields;

	@FindBy(id = "rep_platform")
	private WebElement bugPlatform;

	@FindBy(id = "op_sys")
	private WebElement bugOpSys;
	
	@FindBy(name = "component")
	private WebElement componentList;

	@FindBy(id = "bug_severity")
	private WebElement bugSeverity;
	
	@FindBy(id = "priority")
	private WebElement bugPriority;
	
	@FindBy(id = "bug_status")
	private WebElement bugStatus;
	
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
	private WebElement bugUrl;

	@FindBy(id = "commit")
	private WebElement commitButton;

	
	public CreateBugPage(WebDriver driver) {
		super(driver);
	}
	
	@Override
	protected boolean isMatchingPage() {
		return getTitle().matches("Enter Bug: .*");
	}
	
	
	public void toggleAdvancedFields(){
		linkToggleAdvancedFields.click();
	}

	public void setSummary(String summary) {
		bugSummary.clear();
		bugSummary.sendKeys(summary);
	}

	public void setPlatform(String platform) {
		new Select(bugPlatform).selectByVisibleText(platform);
	}

	public void setOpSys(String opSys) {
		new Select(bugOpSys).selectByVisibleText(opSys);
	}

	public void setSeverity(String severity) {
		new Select(bugSeverity).selectByVisibleText(severity);
	}
	
	public void setPriority(String priority) {
		new Select(bugPriority).selectByVisibleText(priority);
	}
	
	public void setCc(String cc) {
		bugCC.clear();
		bugCC.sendKeys(cc);
	}

	public void setTimeEstimated(double estimatedTime) {
		timeEstimatedTime.clear();
		timeEstimatedTime.sendKeys(String.valueOf(estimatedTime));
	}

	public void setTimeDeadline(String deadline) {
		timeDeadline.clear();
		timeDeadline.sendKeys(String.valueOf(deadline));
	}
	
	public void setUrl(String url) {
		bugUrl.clear();
		bugUrl.sendKeys(url);
	}

	public void setComment(String comment) {
		bugComment.clear();
		bugComment.sendKeys(comment);
	}

	public void setBugStatus(String bugStatusName) {
		new Select(bugStatus).selectByVisibleText(bugStatusName);
	}
	
	public void selectComponent(String componentName){
		new Select(componentList).selectByVisibleText(componentName);
	}

	public BugCreatedPage commitBug() {
		commitButton.click();
		return PageFactory.initElements(driver, BugCreatedPage.class);
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

	
	public BugCreatedPage createNewBug(String summary, String description) {
		setSummary(summary);
		setComment(description);
		selectComponent("TestComponent");
		return commitBug();
	}

	
}
