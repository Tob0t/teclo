package at.scch.teclo.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfigWorkflowPage extends AbstractLoggedinBugzillaPage {
	
	// column 1
	@FindBy(id = "w_0_1")
	private WebElement start_unconfirmed;
	@FindBy(id = "w_5_1")
	private WebElement resolved_unconfirmed;
	@FindBy(id = "w_6_1")
	private WebElement verified_unconfirmed;
	@FindBy(id = "w_7_1")
	private WebElement closed_unconfirmed;
	
	//column 2
	@FindBy(id = "w_1_2")
	private WebElement new_unconfirmed;
	@FindBy(id = "w_3_2")
	private WebElement new_assigned;
	@FindBy(id = "w_4_2")
	private WebElement new_reopened;
	@FindBy(id = "w_5_2")
	private WebElement new_resolved;
	@FindBy(id = "w_7_2")
	private WebElement new_closed;
	
	//column 3
	@FindBy(id = "w_0_3")
	private WebElement assigned_start;
	@FindBy(id = "w_1_3")
	private WebElement assigned_unconfirmed;
	@FindBy(id = "w_2_3")
	private WebElement assigned_new;
	@FindBy(id = "w_4_3")
	private WebElement assigned_reopened;
	
	//column 4
	@FindBy(id = "w_5_4")
	private WebElement reopened_resolved;
	@FindBy(id = "w_6_4")
	private WebElement reopened_verified;
	@FindBy(id = "w_7_4")
	private WebElement reopened_closed;
	
	//column 6
	@FindBy(id = "w_5_6")
	private WebElement verified_resolved;
	
	//column 7
	@FindBy(id = "w_6_7")
	private WebElement closed_verified;
	
	public ConfigWorkflowPage(WebDriver driver) {
		super(driver);
	}

	@Override
	protected boolean isMatchingPage() {
		return "Edit Workflow".equals(getTitle());
	}

	
	public ConfigWorkflowPage setupSimpleBugWorkflow() {
		start_unconfirmed.click();
		resolved_unconfirmed.click();
		verified_unconfirmed.click();
		closed_unconfirmed.click();
		
		new_unconfirmed.click();
		new_assigned.click();
		new_reopened.click();
		new_resolved.click();
		new_closed.click();
		
		assigned_start.click();
		assigned_unconfirmed.click();
		assigned_new.click();
		assigned_reopened.click();
		
		reopened_resolved.click();
		reopened_verified.click();
		reopened_closed.click();
		
		verified_resolved.click();
		
		closed_verified.click();
		
	    driver.findElement(By.xpath("//input[@value='Commit Changes']")).click();
		
		return PageFactory.initElements(driver, ConfigWorkflowPage.class);
	}
}
