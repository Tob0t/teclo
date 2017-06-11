package at.scch.teclo.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BugChangedPage extends AbstractLoggedinBugzillaPage {

	@FindBy(id = "title")
	private WebElement bugTitle;	
	
	public BugChangedPage(WebDriver driver) {
		super(driver);
	}
	
	@Override
	protected boolean isMatchingPage() {
		return getTitle().matches("Bug \\d+ processed");
	}
	

	public int getChangedBugId() {
		return Integer.parseInt(bugTitle.getText().replaceAll("[^0-9]", ""));
	}

	public EditBugPage gotoChangedBug() {
		driver.findElement(By.linkText("bug " + getChangedBugId())).click();
		return PageFactory.initElements(driver, EditBugPage.class);
	}

	public String getSuccessMsg() {
		return driver.findElement(By.cssSelector("dt")).getText();
	}
}
