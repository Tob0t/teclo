package at.scch.teclo.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CreateNewBugPage {

	private final WebDriver driver;

	public CreateNewBugPage(WebDriver driver) {
		this.driver = driver;
	}

	public NewBugCreatedPage createNewBug(String summary, String description) {

		driver.findElement(By.name("short_desc")).clear();
		driver.findElement(By.name("short_desc")).sendKeys(summary);
		driver.findElement(By.id("comment")).clear();
		driver.findElement(By.id("comment")).sendKeys(description);
		driver.findElement(By.id("commit")).click();

		return PageFactory.initElements(driver, NewBugCreatedPage.class);
	}
}
