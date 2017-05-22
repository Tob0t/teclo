package at.scch.teclo.src.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class EditBugPage {

private final WebDriver driver;
	
	public EditBugPage(WebDriver driver){
		this.driver = driver;
	}
	
	public MyBugsPage editBug(){

		driver.findElement(By.id("editme_action")).click();
	    driver.findElement(By.id("short_desc")).clear();
	    driver.findElement(By.id("short_desc")).sendKeys("EditedBug");
	    new Select(driver.findElement(By.id("rep_platform"))).selectByVisibleText("Other");
	    new Select(driver.findElement(By.id("op_sys"))).selectByVisibleText("Linux");
	    new Select(driver.findElement(By.id("priority"))).selectByVisibleText("P1");
	    new Select(driver.findElement(By.id("bug_severity"))).selectByVisibleText("critical");
	    driver.findElement(By.id("commit")).click();
	    driver.findElement(By.linkText("My Bugs")).click();
	    
	    return PageFactory.initElements(driver, MyBugsPage.class);
	}
	
}
