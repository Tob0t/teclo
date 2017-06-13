package at.scch.teclo.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfigBugFieldsPage extends AbstractLoggedinBugzillaPage {

	@FindBy(id = "usetargetmilestone-on")
	private WebElement usetargetmilestoneOnRadiobutton;

	@FindBy(id = "usetargetmilestone-off")
	private WebElement usetargetmilestoneOffRadiobutton;
	
	@FindBy(xpath = "//input[@name='action' and @type='submit']")
	private WebElement saveChangesButton;
	
	public ConfigBugFieldsPage(WebDriver driver){
		super(driver);
	}

	@Override
	protected boolean isMatchingPage() {
		return ("Configuration: Bug Fields".equals(getTitle()) 
				|| "Parameters Updated".equals(getTitle())); 
	}	
	
	
	public ConfigBugFieldsPage setUseStatusWhiteboard(boolean usestatuswhiteboard){
		if (usestatuswhiteboard) {
			usetargetmilestoneOnRadiobutton.click();
		} else {
			usetargetmilestoneOffRadiobutton.click();
		}
		saveChangesButton.click();		
		return PageFactory.initElements(driver, ConfigBugFieldsPage.class);
	}
}
