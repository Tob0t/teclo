package at.scch.teclo.pageobjects;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class NewBugCreatedPage {
	
	private StringBuffer verificationErrors = new StringBuffer();
	private final WebDriver driver;
	
	public NewBugCreatedPage(WebDriver driver){
		this.driver = driver;
	}

	public NewBugCreatedPage checkCreatedBug(){
		try {
		      assertEquals("Bug 1 has been added to the database", driver.findElement(By.cssSelector("dt")).getText());
		    } catch (Error e) {
		      verificationErrors.append(e.toString());
		    }		    
	    
	    return PageFactory.initElements(driver, NewBugCreatedPage.class);
	    
	}
	
	public MyBugsPage navigateToMyBugsPage() {
		driver.findElement(By.linkText("My Bugs")).click();
		
		return PageFactory.initElements(driver, MyBugsPage.class);
	}

}
