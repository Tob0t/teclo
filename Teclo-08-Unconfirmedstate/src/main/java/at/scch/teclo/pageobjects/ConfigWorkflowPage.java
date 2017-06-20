package at.scch.teclo.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfigWorkflowPage extends AbstractLoggedinBugzillaPage {
	

	//column 2
	@FindBy(id = "w_0_2")
	private WebElement new_start;
	
	//column 3
	@FindBy(id = "w_0_3")
	private WebElement assigned_start;

	public ConfigWorkflowPage(WebDriver driver) {
		super(driver);
	}

	@Override
	protected boolean isMatchingPage() {
		return "Edit Workflow".equals(getTitle());
	}

	
	public ConfigWorkflowPage setupSimpleBugWorkflow() {

		new_start.click();

		assigned_start.click();

	    driver.findElement(By.xpath("//input[@value='Commit Changes']")).click();
		
		return PageFactory.initElements(driver, ConfigWorkflowPage.class);
	}
}
