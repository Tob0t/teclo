package at.scch.teclo.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewBugCreatedPage extends AbstractLoggedinBugzillaPage {

	@FindBy(id = "title")
	private WebElement bugTitle;

	public NewBugCreatedPage(WebDriver driver) {
		super(driver);
	}
	
	@Override
	protected boolean matchingPageIsDisplayed() {
		return getTitle().matches("Bug \\d+ Submitted.*");
	}
	

	public int getNewBugId() {
		return Integer.parseInt(bugTitle.getText().replaceAll("[^0-9]", ""));
	}

	public String getCreateSuccessMessage() {
		return driver.findElement(By.cssSelector("dt")).getText();
	}
}
