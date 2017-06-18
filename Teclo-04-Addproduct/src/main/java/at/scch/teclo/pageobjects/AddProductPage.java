package at.scch.teclo.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddProductPage extends AbstractBugzillaPage {
	
	@FindBy(name = "product")
	private WebElement productNameInputField;

	@FindBy(name = "description")
	private WebElement productDescriptionTextArea;
	
	@FindBy(xpath = "//input[@value='Add' and @type='submit']")
	private WebElement addProductButton;

	public AddProductPage(WebDriver driver) {
		super(driver);
	}

	@Override
	protected boolean isMatchingPage() {
		return "Add Product".equals(getTitle()); 
	}
	
	public void addProduct(String name, String description){
		productNameInputField.click();
		productNameInputField.clear();
		productNameInputField.sendKeys(name);
		
		productDescriptionTextArea.click();
		productDescriptionTextArea.clear();
		productDescriptionTextArea.sendKeys(description);
		
		addProductButton.click();
	}

}
