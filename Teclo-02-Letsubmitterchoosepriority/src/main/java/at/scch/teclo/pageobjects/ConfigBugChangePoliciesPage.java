package at.scch.teclo.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfigBugChangePoliciesPage extends AbstractBugzillaPage {
	
	@FindBy(id = "letsubmitterchoosepriority-on")
	private WebElement letsubmitterchoosepriorityOnRadiobutton;

	@FindBy(id = "letsubmitterchoosepriority-off")
	private WebElement letsubmitterchoosepriorityOffRadiobutton;
	
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
	
	public ConfigBugChangePoliciesPage setLetSubmitterChoosePriority(boolean letsubmitterchoosepriority){
		if (letsubmitterchoosepriority) {
			letsubmitterchoosepriorityOnRadiobutton.click();
		} else {
			letsubmitterchoosepriorityOffRadiobutton.click();
		}
		saveChangesButton.click();		
		return PageFactory.initElements(driver, ConfigBugChangePoliciesPage.class);
	}

}
