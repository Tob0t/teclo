package at.scch.teclo.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import at.scch.teclo.BugzillaSetup;

public class BugCommitedPage {
	
	private final WebDriver driver;
	
	public BugCommitedPage(WebDriver driver) {
		this.driver = driver;
		
		// Check that we're on the right page.
        if (!("Bug "+BugzillaSetup.getExampleBugID()+" processed").equals(driver.getTitle())) {
        	throw new IllegalStateException("This is not the edit bug page!");
        }
	}
	
	public EditBugPage selectCommitedBug(int bugID) {
		driver.findElement(By.linkText("bug " + String.valueOf(bugID))).click();
		return PageFactory.initElements(driver, EditBugPage.class);
	}

}
