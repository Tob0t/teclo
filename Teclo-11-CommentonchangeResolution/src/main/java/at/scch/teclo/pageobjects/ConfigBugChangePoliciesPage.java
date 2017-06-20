package at.scch.teclo.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfigBugChangePoliciesPage extends AbstractBugzillaPage {
	
	@FindBy(id = "commentonchange_resolution-on")
	private WebElement commentOnChangeResolutionOnRadiobutton;

	@FindBy(id = "commentonchange_resolution-off")
	private WebElement commentOnChangeResolutionOffRadiobutton;
	
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
	
	public ConfigBugChangePoliciesPage setCommentOnChangeResoultion(boolean commentOnChangeResolution){
		if (commentOnChangeResolution) {
			commentOnChangeResolutionOnRadiobutton.click();
		} else {
			commentOnChangeResolutionOffRadiobutton.click();
		}
		saveChangesButton.click();		
		return PageFactory.initElements(driver, ConfigBugChangePoliciesPage.class);
	}

}
