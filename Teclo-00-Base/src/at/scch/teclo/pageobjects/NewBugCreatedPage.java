package at.scch.teclo.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewBugCreatedPage {
	
	private final WebDriver driver;

	@FindBy(id = "title")
	private WebElement bugTitle;
	
	public NewBugCreatedPage(WebDriver driver) {
		this.driver = driver;
		
		// TODO: check if the title of the page is correct
	}

	public ResultsPage navigateToMyBugsPage() {
		driver.findElement(By.linkText("My Bugs")).click();

		return PageFactory.initElements(driver, ResultsPage.class);
	}

	public int getBugID() {
		return Integer.parseInt(bugTitle.getText().replaceAll("[^0-9]", ""));
	}

	public String getBugWasAddedText() {
		return driver.findElement(By.cssSelector("dt")).getText();
	}
}
