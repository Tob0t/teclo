package at.scch.teclo.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfigProductPage extends AbstractBugzillaPage {

	public ConfigProductPage(WebDriver driver) {
		super(driver);
	}

	@Override
	protected boolean isMatchingPage() {
		return getTitle().contains("Edit Product");
	}
	
	public void updateVotesForProduct(String numberOfVotes) {
		driver.findElement(By.name("votestoconfirm")).clear();
	    driver.findElement(By.name("votestoconfirm")).sendKeys(numberOfVotes);
	    driver.findElement(By.name("submit")).click();
	}

}
