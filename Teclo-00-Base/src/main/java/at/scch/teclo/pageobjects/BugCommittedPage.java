package at.scch.teclo.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BugCommittedPage extends AbstractLoggedinBugzillaPage {

	public BugCommittedPage(WebDriver driver) {
		super(driver);
	}
	
	@Override
	protected boolean isMatchingPage() {
		return getTitle().matches("Bug \\d+ processed");
	}
	

	public EditBugPage selectCommittedBug(int bugID) {
		driver.findElement(By.linkText("bug " + bugID)).click();
		return PageFactory.initElements(driver, EditBugPage.class);
	}

}
