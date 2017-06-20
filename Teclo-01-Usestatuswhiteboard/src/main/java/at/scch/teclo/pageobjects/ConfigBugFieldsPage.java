package at.scch.teclo.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfigBugFieldsPage extends AbstractLoggedinBugzillaPage {

	@FindBy(id = "usestatuswhiteboard-on")
	private WebElement usestatuswhiteboardOnRadiobutton;

	@FindBy(id = "usestatuswhiteboard-off")
	private WebElement usestatuswhiteboardOffRadiobutton;
	
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
			usestatuswhiteboardOnRadiobutton.click();
		} else {
			usestatuswhiteboardOffRadiobutton.click();
		}
		saveChangesButton.click();		
		return PageFactory.initElements(driver, ConfigBugFieldsPage.class);
	}
}
