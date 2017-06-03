package at.scch.teclo.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import at.scch.teclo.BugzillaSetup;
import at.scch.teclo.Helper;

public class EditBugPage {

	private final WebDriver driver;

	@FindBy(id = "short_desc")
	private WebElement bugSummary;
	
	@FindBy(id = "rep_platform")
	private WebElement bugPlatform;
	
	@FindBy(id = "op_sys")
	private WebElement bugOpSys;
	
	
	@FindBy(id = "bug_status")
	private WebElement bugState;

	@FindBy(id = "priority")
	private WebElement bugPriority;

	@FindBy(id = "commit")
	private WebElement commitButton;

	public EditBugPage(WebDriver driver) {
		this.driver = driver;
		
		// Check that we're on the right page.
        if (!("Bug "+BugzillaSetup.getExampleBugID()+" – "+BugzillaSetup.getExampleBugName()).equals(driver.getTitle())) {
        	throw new IllegalStateException("This is not the edit bug page!");
        }
	}

	// TODO: Split every edit in a single method inclusive commit!
	public ResultsPage editBug(String summary, String platform, String opSys, String priority,
			String severity) {
		driver.findElement(By.id("editme_action")).click();
		driver.findElement(By.id("short_desc")).clear();
		driver.findElement(By.id("short_desc")).sendKeys(summary);
		new Select(driver.findElement(By.id("rep_platform"))).selectByVisibleText(platform);
		new Select(driver.findElement(By.id("op_sys"))).selectByVisibleText(opSys);
		new Select(driver.findElement(By.id("priority"))).selectByVisibleText(priority);
		new Select(driver.findElement(By.id("bug_severity"))).selectByVisibleText(severity);
		driver.findElement(By.id("commit")).click();
		driver.findElement(By.linkText("My Bugs")).click();

		return PageFactory.initElements(driver, ResultsPage.class);
	}
	
	public void editSummary(String summary){
		bugSummary.clear();
		bugSummary.sendKeys(summary);
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

	public String getCurrentBugState() {
		return Helper.getSelectedOptionValue(bugState);
	}

	public void changeBugState(String bugStateString) {
		new Select(bugState).selectByVisibleText(bugStateString);
	}

	public void changePriority(String priorityString) {
		new Select(bugPriority).selectByVisibleText(priorityString);
	}

	public BugCommitedPage commitBug() {
		commitButton.click();
		return PageFactory.initElements(driver, BugCommitedPage.class);
	}


	public LoggedInBasePage navigateToMyHomePage() {
		driver.findElement(By.linkText("Home")).click();
		return PageFactory.initElements(driver, LoggedInBasePage.class);
	}

	public ResultsPage navigateToMyBugsPage() {
		driver.findElement(By.linkText("My Bugs")).click();

		return PageFactory.initElements(driver, ResultsPage.class);
	}

}
