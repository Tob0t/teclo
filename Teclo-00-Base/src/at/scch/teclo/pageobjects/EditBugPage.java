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

	@FindBy(id = "bug_status")
	private WebElement bugState;

	@FindBy(id = "priority")
	private WebElement priority;

	@FindBy(id = "commit")
	private WebElement commitButton;

	public EditBugPage(WebDriver driver) {
		this.driver = driver;
	}

	/*
	 * public MyBugsPage editBug(){
	 * 
	 * driver.findElement(By.id("editme_action")).click();
	 * driver.findElement(By.id("short_desc")).clear();
	 * driver.findElement(By.id("short_desc")).sendKeys("EditedBug"); new
	 * Select(driver.findElement(By.id("rep_platform"))).selectByVisibleText(
	 * "Other"); new
	 * Select(driver.findElement(By.id("op_sys"))).selectByVisibleText("Linux");
	 * new
	 * Select(driver.findElement(By.id("priority"))).selectByVisibleText("P1");
	 * new
	 * Select(driver.findElement(By.id("bug_severity"))).selectByVisibleText(
	 * "critical"); driver.findElement(By.id("commit")).click();
	 * driver.findElement(By.linkText("My Bugs")).click();
	 * 
	 * return PageFactory.initElements(driver, MyBugsPage.class); }
	 */

	public MyBugsPage editBug(String summary, String platform, String opSys, String priority,
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

		return PageFactory.initElements(driver, MyBugsPage.class);
	}

	public String getCurrentBugState() {
		return Helper.getSelectedOptionValue(bugState);
	}

	public void changeBugState(String bugStateString) {
		new Select(bugState).selectByVisibleText(bugStateString);
	}

	public void changePriority(String priorityString) {
		new Select(priority).selectByVisibleText(priorityString);
	}

	public EditBugPage commitBug() {
		commitButton.click();
		return PageFactory.initElements(driver, EditBugPage.class);
	}

	public EditBugPage selectCommitedBug(int bugID) {
		driver.findElement(By.linkText("bug " + String.valueOf(bugID))).click();
		return PageFactory.initElements(driver, EditBugPage.class);
	}

	public LoggedInBasePage navigateToMyHomePage() {
		driver.findElement(By.linkText("Home")).click();
		return PageFactory.initElements(driver, LoggedInBasePage.class);
	}

	public MyBugsPage navigateToMyBugsPage() {
		driver.findElement(By.linkText("My Bugs")).click();

		return PageFactory.initElements(driver, MyBugsPage.class);
	}

}
