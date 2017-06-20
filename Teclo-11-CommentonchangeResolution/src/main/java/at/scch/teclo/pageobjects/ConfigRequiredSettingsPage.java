package at.scch.teclo.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfigRequiredSettingsPage extends AbstractLoggedinBugzillaPage {

	@FindBy(id = "announcehtml")
	private WebElement announcehtmlField;
	
	@FindBy(xpath = "//input[@name='action' and @type='submit']")
	private WebElement saveChangesButton;
	
	public ConfigRequiredSettingsPage(WebDriver driver){
		super(driver);
	}

	@Override
	protected boolean isMatchingPage() {
		return ("Configuration: Required Settings".equals(getTitle()) 
				|| "Parameters Updated".equals(getTitle())); 
	}	
	
	
	public ConfigRequiredSettingsPage setAnnounceHtml(String announcement){
		announcehtmlField.clear();
		announcehtmlField.sendKeys(announcement);
		saveChangesButton.click();
		return PageFactory.initElements(driver, ConfigRequiredSettingsPage.class);
	}
}
