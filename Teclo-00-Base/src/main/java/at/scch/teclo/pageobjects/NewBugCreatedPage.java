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

	public NewBugCreatedPage(WebDriver driver) {
		this.driver = driver;

		// Check that we're on the right page.
		if (!(driver.getTitle().matches("Bug \\d+ Submitted.*"))) {
			throw new IllegalStateException("This is not the new bug created page (Title: " + driver.getTitle() + ")!");
		}
	}

	public int getNewBugId() {
		return Integer.parseInt(bugTitle.getText().replaceAll("[^0-9]", ""));
	}

	public String getCreateSuccessMessage() {
		return driver.findElement(By.cssSelector("dt")).getText();
	}
}
