package at.scch.teclo.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddComponentPage extends AbstractBugzillaPage {
	
	@FindBy(name = "component")
	private WebElement componentInputField;

	@FindBy(name = "description")
	private WebElement componentDescriptionTextArea;
	
	@FindBy(id = "initialowner")
	private WebElement componentDefaultAssigneeInputField;
	
	@FindBy(id = "create")
	private WebElement addComponentButton;

	public AddComponentPage(WebDriver driver) {
		super(driver);
	}

	@Override
	protected boolean isMatchingPage() {
		return ("Add component to the Foo product".equals(getTitle()) 
				|| "Component Created".equals(getTitle())); 
	}
	
	public AddComponentPage addComponent(String name, String description, String defaultAssignee){
		componentInputField.click();
		componentInputField.clear();
		componentInputField.sendKeys(name);
		
		componentDescriptionTextArea.click();
		componentDescriptionTextArea.clear();
		componentDescriptionTextArea.sendKeys(description);
		
		componentDefaultAssigneeInputField.click();
		componentDefaultAssigneeInputField.clear();
		componentDefaultAssigneeInputField.sendKeys(defaultAssignee);
		
		addComponentButton.click();
		
		return PageFactory.initElements(driver, AddComponentPage.class);
	}

}
