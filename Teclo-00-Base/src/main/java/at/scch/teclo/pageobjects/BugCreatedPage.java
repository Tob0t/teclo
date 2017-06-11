package at.scch.teclo.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BugCreatedPage extends AbstractLoggedinBugzillaPage {

	@FindBy(id = "title")
	private WebElement bugTitle;

	public BugCreatedPage(WebDriver driver) {
		super(driver);
	}
	
	@Override
	protected boolean isMatchingPage() {
		return getTitle().matches("Bug \\d+ Submitted.*");
	}
	

	public int getCreatedBugId() {
		return Integer.parseInt(bugTitle.getText().replaceAll("[^0-9]", ""));
	}

	public EditBugPage gotoCreatedBugPage() {
		driver.findElement(By.linkText("Bug " + getCreatedBugId())).click();
		return PageFactory.initElements(driver, EditBugPage.class);
	}
	
	public String getSuccessMsg() {
		return driver.findElement(By.cssSelector("dt")).getText();
	}
}
