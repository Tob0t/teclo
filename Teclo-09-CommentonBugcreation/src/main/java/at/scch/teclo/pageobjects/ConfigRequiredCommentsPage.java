package at.scch.teclo.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfigRequiredCommentsPage extends AbstractBugzillaPage {

	// column 1
	@FindBy(id = "c_0_1")
	private WebElement unconfirmed_start;

	// column 2
	@FindBy(id = "c_0_2")
	private WebElement new_start;

	// column 3
	@FindBy(id = "c_0_3")
	private WebElement assigned_start;

	public ConfigRequiredCommentsPage(WebDriver driver) {
		super(driver);
	}

	@Override
	protected boolean isMatchingPage() {
		return "Comments Required on Status Transitions".equals(driver.getTitle());
	}

	public ConfigRequiredCommentsPage updateCommentsRequiredOnStatusTransitions() {
		unconfirmed_start.click();
		new_start.click();
		assigned_start.click();
		driver.findElement(By.xpath("//input[@value='Commit Changes']")).click();
		return PageFactory.initElements(driver, ConfigRequiredCommentsPage.class);
	}

}
