package at.scch.teclo.pageobjects;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class MyBugsPage {

	private StringBuffer verificationErrors = new StringBuffer();
	private final WebDriver driver;
	
	public MyBugsPage(WebDriver driver){
		this.driver = driver;
	}
	
	public EditBugPage goToEditBug(){

		driver.findElement(By.linkText("1")).click();
	    
	    return PageFactory.initElements(driver, EditBugPage.class);
	}
	
	public MyBugsPage checkEditedBugChanges() {
		
	    try {
	        assertEquals("cri", driver.findElement(By.cssSelector("span[title=\"critical\"]")).getText());
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	      }
	      try {
	        assertEquals("P1", driver.findElement(By.cssSelector("span[title=\"P1\"]")).getText());
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	      }
	      try {
	        assertEquals("Linu", driver.findElement(By.cssSelector("span[title=\"Linux\"]")).getText());
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	      }
	      try {
	        assertEquals("EditedBug", driver.findElement(By.xpath("//div[@id='bugzilla-body']/table/tbody/tr[2]/td[8]")).getText());
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	      }
		
		return PageFactory.initElements(driver, MyBugsPage.class);
	}
	
}
