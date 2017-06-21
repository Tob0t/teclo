package at.scch.teclo.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeleteVersionPage extends AbstractBugzillaPage {
	
	@FindBy(xpath = "//table/tbody/tr[2]/td[2]")
	WebElement versionName;
	
	@FindBy(id = "delete")
	WebElement deleteVersionButton;

	public DeleteVersionPage(WebDriver driver) {
		super(driver);
	}

	@Override
	protected boolean isMatchingPage() {
		return getTitle().contains("DeleteVersion");
	}
	
	public String getVersionName(){
		return versionName.getText();
	}
	
	public void deleteVersion(){
		deleteVersionButton.click();
	}

}
