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
	@FindBy(id = "c_5_1")
	private WebElement unconfirmed_resolved;
	@FindBy(id = "c_6_1")
	private WebElement unconfirmed_verified;
	@FindBy(id = "c_7_1")
	private WebElement unconfirmed_closed;

	// column 2
	@FindBy(id = "c_0_2")
	private WebElement new_start;
	@FindBy(id = "c_1_2")
	private WebElement new_unconfirmed;
	@FindBy(id = "c_3_2")
	private WebElement new_assigned;
	@FindBy(id = "c_4_2")
	private WebElement new_reopened;

	// column 3
	@FindBy(id = "c_0_3")
	private WebElement assigned_start;
	@FindBy(id = "c_1_3")
	private WebElement assigned_unconfirmed;
	@FindBy(id = "c_2_3")
	private WebElement assigned_new;
	@FindBy(id = "c_4_3")
	private WebElement assigned_reopened;

	// column 4
	@FindBy(id = "c_5_4")
	private WebElement reopened_resolved;
	@FindBy(id = "c_6_4")
	private WebElement reopened_verified;
	@FindBy(id = "c_7_4")
	private WebElement reopened_closed;

	// column 5
	@FindBy(id = "c_1_5")
	private WebElement resolved_unconfirmed;
	@FindBy(id = "c_2_5")
	private WebElement resolved_new;
	@FindBy(id = "c_3_5")
	private WebElement resolved_assigned;
	@FindBy(id = "c_4_5")
	private WebElement resolved_reopened;
	@FindBy(id = "c_6_5")
	private WebElement resolved_verified;
	@FindBy(id = "c_7_5")
	private WebElement resolved_closed;

	// column 6
	@FindBy(id = "c_5_6")
	private WebElement verified_resolved;

	// column 7
	@FindBy(id = "c_5_7")
	private WebElement closed_resolved;
	@FindBy(id = "c_6_7")
	private WebElement closed_verified;

	public ConfigRequiredCommentsPage(WebDriver driver) {
		super(driver);
	}

	@Override
	protected boolean isMatchingPage() {
		return "Comments Required on Status Transitions".equals(driver.getTitle());
	}

	public ConfigRequiredCommentsPage updateCommentsRequiredOnStatusTransitions() {
		// column 1
		
		unconfirmed_start.click();
		unconfirmed_resolved.click();
		unconfirmed_verified.click();
		unconfirmed_closed.click();

		new_start.click();
		new_unconfirmed.click();
		new_assigned.click();
		new_reopened.click();

		assigned_start.click();
		assigned_unconfirmed.click();
		assigned_new.click();
		assigned_reopened.click();

		reopened_resolved.click();
		reopened_verified.click();
		reopened_closed.click();
 
		resolved_unconfirmed.click();
		resolved_new.click();
		resolved_assigned.click();
		resolved_reopened.click();
		resolved_verified.click();
		resolved_closed.click();

		verified_resolved.click();

		closed_resolved.click();
		closed_verified.click();
		
		
		driver.findElement(By.xpath("//input[@value='Commit Changes']")).click();
		return PageFactory.initElements(driver, ConfigRequiredCommentsPage.class);
	}

}
