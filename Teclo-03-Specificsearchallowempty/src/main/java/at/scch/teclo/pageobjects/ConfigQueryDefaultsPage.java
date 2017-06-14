package at.scch.teclo.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfigQueryDefaultsPage extends AbstractBugzillaPage {
	
	@FindBy(id = "specific_search_allow_empty_words-on")
	private WebElement specificsearchallowemptywordsOnRadiobutton;

	@FindBy(id = "specific_search_allow_empty_words-off")
	private WebElement specificsearchallowemptywordsOffRadiobutton;
	
	@FindBy(xpath = "//input[@name='action' and @type='submit']")
	private WebElement saveChangesButton;

	public ConfigQueryDefaultsPage(WebDriver driver) {
		super(driver);
	}

	@Override
	protected boolean isMatchingPage() {
		return ("Configuration: Query Defaults".equals(getTitle()) 
				|| "Parameters Updated".equals(getTitle())); 
	}
	
	public ConfigQueryDefaultsPage setSpecificSearchAllowEmptyWords(boolean specificsearchallowemptywords){
		if (specificsearchallowemptywords) {
			specificsearchallowemptywordsOnRadiobutton.click();
		} else {
			specificsearchallowemptywordsOffRadiobutton.click();
		}
		saveChangesButton.click();		
		return PageFactory.initElements(driver, ConfigQueryDefaultsPage.class);
	}

}
