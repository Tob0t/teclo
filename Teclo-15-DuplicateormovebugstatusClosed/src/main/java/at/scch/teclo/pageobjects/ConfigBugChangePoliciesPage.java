package at.scch.teclo.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ConfigBugChangePoliciesPage extends AbstractBugzillaPage {
	
	@FindBy(id = "duplicate_or_move_bug_status")
	private WebElement duplicateOrMoveBugStatus;
	
	@FindBy(xpath = "//input[@name='action' and @type='submit']")
	private WebElement saveChangesButton;
	
	public ConfigBugChangePoliciesPage(WebDriver driver){
		super(driver);
	}

	@Override
	protected boolean isMatchingPage() {
		return ("Configuration: Bug Change Policies".equals(getTitle()) 
				|| "Parameters Updated".equals(getTitle())); 
	}
	
	public ConfigBugChangePoliciesPage setDuplicateOrMoveBugStatus(String bugStatusName) {
		new Select(duplicateOrMoveBugStatus).selectByVisibleText(bugStatusName);
		
		saveChangesButton.click();		
		return PageFactory.initElements(driver, ConfigBugChangePoliciesPage.class);
	}

}
