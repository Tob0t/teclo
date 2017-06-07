package at.scch.teclo.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BugCommittedPage {

	private final WebDriver driver;

	public BugCommittedPage(WebDriver driver) {
		this.driver = driver;

		// Check that we're on the right page.
		if (!(driver.getTitle().matches("Bug \\d+ processed"))) {
			throw new IllegalStateException("This is not the committed bug page (Title: " + driver.getTitle() + ")!");
		}
	}

	public EditBugPage selectCommittedBug(int bugID) {
		driver.findElement(By.linkText("bug " + bugID)).click();
		return PageFactory.initElements(driver, EditBugPage.class);
	}

}
